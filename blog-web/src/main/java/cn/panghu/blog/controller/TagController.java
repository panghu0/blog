package cn.panghu.blog.controller;

import cn.panghu.blog.base.pojo.Category;
import cn.panghu.blog.base.pojo.Tag;
import cn.panghu.blog.service.TagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author pang hu
 * @date 2020/10/24 16:30
 */
@Api(value = "标签接口", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RestController
@RequestMapping("/tag")
public class TagController {

    @Autowired
    TagService tagService;

    @RequestMapping(value = "/list/{categoryId}", method = RequestMethod.GET)
    @ApiOperation(notes="获取标签列表",value="获取标签列表",httpMethod="GET")
    public List<Tag> getCategoryList(@PathVariable("categoryId") Integer categoryId) {
        return tagService.getTagList(categoryId);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation(notes="添加标签",value="添加标签",httpMethod="POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tag", value = "标签对象", required = true, paramType = "body", dataType = "Tag"),
            @ApiImplicitParam(name = "categoryId", value = "分类id", required = true, paramType = "query", dataType = "Integer")
    })
    public Tag add(@RequestBody Tag tag, @RequestParam("categoryId") Integer categoryId) {
        return tagService.save(tag, categoryId);
    }
}
