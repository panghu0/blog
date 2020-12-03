package cn.panghu.blog.base.dao;

import cn.panghu.blog.base.pojo.Role;
import cn.panghu.blog.base.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author pang hu
 * @date 2020/6/20 16:12
 */
@Repository
public interface UserDao {
        /**
         * insert
         * @param user
         * @return
         */
        public void save(User user);

        /**
         * insert include id
         * @param user
         * @return
         */
        public void saveAll(User user);

        /**
         * 根据ID删除User
         * @param id
         * @return
         */
        public void deleteById(@Param("id") Integer id);

        /**
         * 更新
         * @param user
         * @return
         */
        public void update(User user);

        /**
         * 根据ID查询User
         * @param id
         * @return
         */
        public User findById(@Param("id") Integer id);

        /**
         * 批量添加
         * @param list<User> users
         * @return
         */
        public void batchSave(@Param("list") List<User> list);

        /**
         * 批量添加(包含主键)
         * @param list<User> users
         * @return
         */
        public void batchSaveAll(@Param("list") List<User> list);

        /**
         * 根据User某个属性来查询
         * @param user<User> users
         * return List<User>
         */
        public List<User> findByPage(User user);

        /**
         * 批量删除
         * @param ids<Integer> ids
         * return
         */
        public void batchDelete(@Param("ids") List<Integer> ids);
}
