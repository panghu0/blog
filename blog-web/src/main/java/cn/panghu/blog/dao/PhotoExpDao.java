package cn.panghu.blog.dao;

import cn.panghu.blog.base.pojo.Photo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author pang hu
 * @date 2020/10/28 20:56
 */
@Repository
public interface PhotoExpDao {
    /***
     * @Description 根据文章id和类型查找关联图片Id
     * @Param [id]
     * @return java.util.List<java.lang.Integer>
     */

    List<Photo> findByArticleAndType(@Param("articleId")Integer id, @Param("type") Integer type);

    /***
     * @Description 根据文章id删除图片文章关联
     * @Param [id]
     * @return void
     */
    void deleteRelByArticle(@Param("articleId")Integer id);

    /***
     * @Description 根据图片id批量删除图片文章关联
     * @Param [idList]
     * @return void
     */
    void batchDelRel(List<Integer> idList);

}
