package cn.panghu.blog.service;

import cn.panghu.blog.common.pojo.UserToken;
import cn.panghu.blog.base.pojo.User;

/**
 * @author pang hu
 * @date 2020/6/20 16:09
 */
public interface UserService {
    void addUser(User user);

    UserToken login(User userInfo);
}
