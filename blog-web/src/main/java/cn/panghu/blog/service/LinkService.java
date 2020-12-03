package cn.panghu.blog.service;

import cn.panghu.blog.base.pojo.Link;

import java.util.List;

/**
 * @author pang hu
 * @date 2020/5/17 11:08
 */
public interface LinkService {
    /**
     * @author pang hu
     * @params
     * @descript 获取博客导航
     * @return List
     */
    List<Link> getBlogNavLinks();

    /**
     * @Description
     * @Param
     * @return List
     */
    List<Link> getAdminNavLinks();
}
