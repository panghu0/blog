package cn.panghu.blog.base.dao;

import cn.panghu.blog.base.pojo.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author pang hu
 * @date 2020/10/24 9:42
 */
@Repository
public interface UserRoleDao {

    /***
     * @Description 获取用户权限
     * @Param
     * @return
     */
    public Role getRoleByUserId(@Param("userId") Integer userId);
}
