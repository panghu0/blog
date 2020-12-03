package cn.panghu.blog.base.dao;

import cn.panghu.blog.base.pojo.Link;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author pang hu
 * @date 2020/5/17 11:25
 */
@Repository
public interface LinkDao {

    List<Link> getBlogNavLinks();

    List<Link> getAdminNavLinks();
}
