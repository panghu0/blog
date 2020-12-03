package cn.panghu.blog.controller;

import cn.panghu.blog.base.pojo.Article;
import cn.panghu.blog.base.pojo.Category;
import cn.panghu.blog.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author pang hu
 * @date 2020/7/19 11:26
 */
@Api(value = "分类接口", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ApiOperation(notes="获取分类列表",value="获取分类列表",httpMethod="GET")
    public List<Category> getCategoryList() {
        return categoryService.getCategoryList();
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation(notes="添加分类",value="添加分类",httpMethod="POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "category", value = "分类对象", required = true, paramType = "body", dataType = "Category")
    })
    public Category add(@RequestBody Category category) {
        return categoryService.save(category);
    }
}
