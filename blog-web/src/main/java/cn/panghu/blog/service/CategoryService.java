package cn.panghu.blog.service;

import cn.panghu.blog.base.pojo.Category;

import java.util.List;

/**
 * @author pang hu
 * @date 2020/7/19 11:26
 */
public interface CategoryService {

    /***
     * @Description 获取分类列表
     * @Param []
     * @return List<Category>
     */
    List<Category> getCategoryList();

    /***
     * @Description 保存分类
     * @Param [category]
     * @return cn.panghu.blog.base.pojo.Category
     */
    Category save(Category category);
}
