package cn.panghu.blog.controller;

import cn.panghu.blog.base.pojo.Article;
import cn.panghu.blog.vo.ArticleFilter;
import cn.panghu.blog.vo.ArticleVO;
import cn.panghu.blog.service.ArticleService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * @author pang hu
 * @date 2020/7/5 20:28
 */
@Api(value = "文章接口", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(notes="根据ID获取文章",value="根据ID获取文章",httpMethod="GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "文章id", required = true, paramType = "path", dataType =
                    "Integer")
    })
    public ArticleVO getArticle(@PathVariable("id") Integer id) {

        return articleService.getArticleById(id);
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ApiOperation(notes="获取文章列表",value="获取文章列表",httpMethod="POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", dataType = "int", paramType = "query", example = "1", defaultValue = "1", required = false),
            @ApiImplicitParam(name = "pageSize", value = "每页数目", dataType = "int", paramType = "query", example = "10", defaultValue = "10", required = false),
            @ApiImplicitParam(name = "filter", value = "查询条件", required = true, paramType = "body", dataType = "ArticleFilter")
    })
    public PageInfo<Article> getArticleList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                            @RequestBody ArticleFilter filter) {

        return articleService.getArticleList(filter, pageNum, pageSize);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation(notes="添加文章",value="添加文章",httpMethod="POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "articleVO", value = "文章对象", required = true, paramType = "body", dataType = "ArticleVO")
    })
    public void addArticle(@RequestBody ArticleVO articleVO) {
        articleService.save(articleVO);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    @ApiOperation(notes="更新文章",value="更新文章",httpMethod="POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "文章id", required = true, paramType = "path", dataType = "Integer"),
            @ApiImplicitParam(name = "articleVO", value = "文章对象", required = true, paramType = "body", dataType = "ArticleVO")
    })
    public void updArticle(@PathVariable("id") Integer id,@RequestBody ArticleVO articleVO) {
        articleService.update(articleVO);
    }


    @RequestMapping(value = "", method = RequestMethod.DELETE)
    @ApiOperation(notes="删除文章",value="删除文章",httpMethod="DELETE")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "idList", value = "文章id列表，如[1,2,3,4,5]", required = true, paramType = "body", dataType = "List<Integer>")
    })
    public void delete(@RequestBody(required = true) List<Integer> idList) {
        articleService.batchDelete(idList);
    }


    @RequestMapping(value = "/publishStatus/{type}", method = RequestMethod.PUT)
    @ApiOperation(value = "修改上下架状态", notes = "修改上下架状态", httpMethod = "PUT")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "idList", value = "文章id列表，如[1,2,3,4,5]", required = true, paramType = "body", dataType = "List<Integer>"),
            @ApiImplicitParam(name = "type", value = "0：下架，1上架，", required = true, paramType = "path", dataType = "int")
    })
    public void updatePublishState(@RequestBody(required = true) List<Integer> idList,
                                   @PathVariable(value = "type", required = true) Integer type) {

        articleService.updatePublishState(idList, type);
    }

    @RequestMapping(value = "/getPopular", method = RequestMethod.GET)
    @ApiOperation(notes="获取最受欢迎的几篇文章",value="获取最受欢迎的几篇文章",httpMethod="GET")
    public List<Article> getBlogNavLinks() {

        return articleService.getPopular();
    }

    @RequestMapping(value = "/getCountByMonth", method = RequestMethod.GET)
    @ApiOperation(notes="获取最近6个月每月发表文章数",value="获取最近6个月每月发表文章数",httpMethod="GET")
    public List<HashMap> getCountByMonth() {

        return articleService.getCountByMonth();
    }

    @RequestMapping(value = "/getCountByDay", method = RequestMethod.GET)
    @ApiOperation(notes="获取今年每天文章数",value="获取今年每天文章数",httpMethod="GET")
    public List<HashMap> getCountByDay() {

        return articleService.getCountByDay();
    }

    @RequestMapping(value = "/getArticleByDate", method = RequestMethod.POST)
    @ApiOperation(notes="获取某天发表的文章",value="获取某天发表的文章",httpMethod="POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "article", value = "", required = true, paramType = "body", dataType = "Article")
    })
    public List<Article> getArticleByDate(@RequestBody Article article) {

        return articleService.getArticleByDate(article);
    }
}
