package cn.panghu.blog.service;

import cn.panghu.blog.common.enums.GlobalResultEnum;
import cn.panghu.blog.common.exception.BusinessException;
import cn.panghu.blog.common.pojo.UserToken;
import cn.panghu.blog.base.dao.UserDao;
import cn.panghu.blog.base.dao.UserRoleDao;
import cn.panghu.blog.base.pojo.Role;
import cn.panghu.blog.base.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

/**
 * @author pang hu
 * @date 2020/6/20 16:10
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;
    @Autowired
    UserRoleDao userRoleDao;

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    @Transactional
    public void addUser(User userInfo) {

        User userQry = new User();
        userQry.setUsername(userInfo.getUsername());
        List<User> users = userDao.findByPage(userQry);
        if(!CollectionUtils.isEmpty(users)) {
            throw new BusinessException("该用户名已存在");
        }

        userInfo.setCreateTime(new Date());
        userDao.save(userInfo);
    }

    @Override
    public UserToken login(User userInfo) {

        logger.debug("user login...");

        User userQry = new User();
        userQry.setUsername(userInfo.getUsername());
        userQry.setPassword(userInfo.getPassword());
        List<User> userList = userDao.findByPage(userQry);
        if(userList == null || userList.size() == 0){
            throw new BusinessException(GlobalResultEnum.UUMS_ACCOUNT_PWD_ERROR);
        }

        if(userList.size() > 1){
            throw new BusinessException(GlobalResultEnum.DATA_ERROR);
        }

        User user = userList.get(0);

        //封装用户token信息
        UserToken uToken = new UserToken();
        uToken.setUserInfo(user);

        Role role = userRoleDao.getRoleByUserId(user.getId());
        if(role != null){
            uToken.setRole(role);
        }

        //更新最后一次登录时间
        User userUpd = new User();
        userUpd.setId(user.getId());
        userUpd.setLastLoginTime(new Date());
        userDao.update(userUpd);

        return uToken;
    }
}
