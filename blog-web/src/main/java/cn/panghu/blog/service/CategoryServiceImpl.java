package cn.panghu.blog.service;

import cn.panghu.blog.common.exception.BusinessException;
import cn.panghu.blog.base.dao.CategoryDao;
import cn.panghu.blog.base.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author pang hu
 * @date 2020/7/19 11:27
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public List<Category> getCategoryList() {
        return categoryDao.findByPage(null);
    }

    @Override
    @Transactional
    public Category save(Category category) {
        Category categoryQry = new Category();
        categoryQry.setName(category.getName());
        List<Category> categoryList =  categoryDao.findByPage(category);
        if(!CollectionUtils.isEmpty(categoryList)) {
            throw new BusinessException("该分类名称已存在，请重命名！");
        }

        categoryDao.save(category);
        return category;
    }
}
