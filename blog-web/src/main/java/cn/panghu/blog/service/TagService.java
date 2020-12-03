package cn.panghu.blog.service;

import cn.panghu.blog.base.pojo.Tag;

import java.util.List;

/**
 * @author pang hu
 * @date 2020/10/24 16:31
 */
public interface TagService {

    /***
     * @Description 获取所有标签
     */
    List<Tag> getTagList(Integer categoryId);

    Tag save(Tag tag, Integer categoryId);
}
