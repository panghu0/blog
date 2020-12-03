package cn.panghu.blog.common.pojo;

import cn.panghu.blog.base.pojo.Role;
import cn.panghu.blog.base.pojo.User;

import java.io.Serializable;

/**
 * @author pang hu
 * @date 2020/10/18 13:26
 */
public class UserToken implements Serializable {

    private static final long serialVersionUID = 4513319766782963238L;

    private User userInfo;
    private Role role;

    public User getUserInfo() {
        return userInfo;
    }
    public void setUserInfo(User userInfo) {
        this.userInfo = userInfo;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}