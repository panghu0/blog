package cn.panghu.blog.dao;

import cn.panghu.blog.base.pojo.Tag;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author pang hu
 * @date 2020/10/29 20:26
 */
@Repository
public interface TagExpDao {

    public List<Tag> findTagsByArticle(@Param("articleId") Integer articleId);


    /***
     * @Description 根据文章id删除关联标签
     * @Param [id]
     * @return void
     */
    void deleteRelByArticle(@Param("articleId") Integer articleId);
}
