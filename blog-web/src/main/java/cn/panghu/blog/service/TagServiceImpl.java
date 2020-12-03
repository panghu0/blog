package cn.panghu.blog.service;

import cn.panghu.blog.base.dao.CategoryTagRelDao;
import cn.panghu.blog.base.dao.TagDao;
import cn.panghu.blog.base.pojo.CategoryTagRel;
import cn.panghu.blog.base.pojo.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author pang hu
 * @date 2020/10/24 16:31
 */
@Service
public class TagServiceImpl implements TagService{

    @Autowired
    TagDao tagDao;
    @Autowired
    CategoryTagRelDao categoryTagRelDao;

    @Override
    public List<Tag> getTagList(Integer categoryId) {
        return tagDao.findTagsByCategory(categoryId);
    }

    @Override
    @Transactional
    public Tag save(Tag tag, Integer categoryId) {

        CategoryTagRel categoryTagRel = new CategoryTagRel();

        tagDao.save(tag);
        categoryTagRel.setCategoryId(categoryId);
        categoryTagRel.setTagId(tag.getId());
        categoryTagRelDao.save(categoryTagRel);

        return tag;
    }
}
