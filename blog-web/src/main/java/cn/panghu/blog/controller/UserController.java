package cn.panghu.blog.controller;

import cn.panghu.blog.common.constant.GlobalConstant;
import cn.panghu.blog.common.enums.GlobalResultEnum;
import cn.panghu.blog.common.exception.BusinessException;
import cn.panghu.blog.common.pojo.TokenVO;
import cn.panghu.blog.common.pojo.UserToken;
import cn.panghu.blog.common.properties.CommonProperties;
import cn.panghu.blog.common.redis.RedisUtil;
import cn.panghu.blog.common.spring.SpringContextHolder;
import cn.panghu.blog.common.utils.UUIDUtils;
import cn.panghu.blog.base.pojo.User;
import cn.panghu.blog.service.UserService;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author pang hu
 * @date 2020/6/20 15:17
 */
@Api(value = "用户管理", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    private CommonProperties commonProperties;
    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @RequestMapping(value = "/register",method= RequestMethod.POST)
    @ApiOperation(notes="注册",value="注册",httpMethod="POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", value = "用户信息", required = true, paramType = "body", dataType = "User")
    })
    public void register(@RequestBody User user){
        userService.addUser(user);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation(value="登录接口",notes="用户登录接口",httpMethod="POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userInfo",value = "用户数据对象",dataType = "User",required = true,paramType = "body")
    })
    public TokenVO login(HttpServletRequest request, @RequestBody  User userInfo) {

        UserToken uToken = userService.login(userInfo);
        logger.debug("用户token信息==>" + JSONObject.toJSONString(uToken));
        TokenVO token = new TokenVO();
        token.setType(uToken.getUserInfo().getRoleId());
        String loginType = this.getCommonProperties().getType();
        if("token".equalsIgnoreCase(loginType)){
            RedisUtil redisUtil = SpringContextHolder.getBean(RedisUtil.class);
            if(redisUtil == null){
                throw new BusinessException(GlobalResultEnum.UUMS_CACHE_NOT_FOUND);
            }
            //根据UUID生成uuid，作为token主体信息
            String uuid = UUIDUtils.getUUID();
            String key = uuid;
            Integer time = GlobalConstant.EXPIRE_TIME;
            if(!redisUtil.set(key, JSONObject.toJSONString(uToken))){
                throw new BusinessException(GlobalResultEnum.UUMS_CACHE_ERROR);
            }
            redisUtil.expire(key, time);
            token.setToken(key);
        }else{
            //放入session
            logger.debug("获取session....");
            HttpSession session = request.getSession();
            logger.debug("放入用户信息....");
            session.setAttribute(GlobalConstant.SESSION_USER_KEY, uToken);
            logger.debug("放入用户信息....end");
        }
        return token;
    }

    private CommonProperties getCommonProperties(){
        if(commonProperties == null){
            commonProperties = SpringContextHolder.getBean("commonProperties");
        }
        return commonProperties;
    }
}
