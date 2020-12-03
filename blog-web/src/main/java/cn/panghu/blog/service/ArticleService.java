package cn.panghu.blog.service;

import cn.panghu.blog.base.pojo.Article;
import cn.panghu.blog.vo.ArticleFilter;
import cn.panghu.blog.vo.ArticleVO;
import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.List;

/**
 * @author pang hu
 * @date 2020/7/5 20:30
 */
public interface ArticleService {

    /***
     * @Description 获取阅读最多的文章
     * @Param []
     * @return List<Article>
     */
    List<Article> getPopular();

    /***
     * @Description 获取最近6个月每月发表文章数
     * @Param []
     * @return List<HashMap>
     */
    List<HashMap> getCountByMonth();

    /***
     * @Description 获取今年每天发表文章数量
     * @Param
     * @return List<HashMap>
     */
    List<HashMap> getCountByDay();

    /***
     * @Description //根据日期获取文章
     * @Param [article]
     * @return List<Article>
     */
    List<Article> getArticleByDate(Article article);

    /***
     * @Description 获取分页文章列表
     * @Param [filter, pageNum, pageSize]
     * @return List<Article>
     */
    PageInfo<Article> getArticleList(ArticleFilter filter, Integer pageNum, Integer pageSize);

    /***
     * @Description 保存文章
     * @Param [article]
     * @return void
     */
    void save(ArticleVO articleVO);

    void update(ArticleVO articleVO);

    /***
     * @Description 修改发布状态
     * @Param [idList, type]
     * @return void
     */
    void updatePublishState(List<Integer> idList, Integer type);


    void batchDelete(List<Integer> idList);

    ArticleVO getArticleById(Integer id);
}
