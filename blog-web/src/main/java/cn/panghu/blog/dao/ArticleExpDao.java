package cn.panghu.blog.dao;

import cn.panghu.blog.base.pojo.Article;
import cn.panghu.blog.vo.ArticleFilter;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * @author pang hu
 * @date 2020/7/5 20:30
 */
@Repository
public interface ArticleExpDao {

    List<Article> getPopular();

    List<HashMap> getCountByMonth();

    List<HashMap> getCountByDay();

    List<Article> findByFilter(ArticleFilter filter);

    List<Article> findByPage(Article article);

    void updatePublishState(@Param("list") List<Integer> idList, @Param("type") Integer type);
}
