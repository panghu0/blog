package cn.panghu.blog.base.dao;

import java.util.List;

import cn.panghu.blog.base.pojo.Article;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * ArticleDao接口
 */
@Repository
public interface ArticleDao {

	/**
	 * insert
	 * @param article article
	 * @return
	 */
	public void save(Article article);
	
	/**
	 * insert include id
	 * @param article article
	 * @return
	 */
	public void saveAll(Article article);
	
	/**
	 * 根据ID删除Article
	 * @param id
	 * @return 
	 */
	public void deleteById(@Param("id") Integer id);
	
	/**
	 * 更新
	 * @param article
	 * @return
	 */
	public void update(Article article);
	
	/**
	 * 根据ID查询Article
	 * @param id
	 * @return
	 */
	public Article findById(@Param("id") Integer id);
	
	/**
	 * 批量添加
	 * @param list
	 * @return 
	 */
	public void batchSave(@Param("list") List<Article> list);
	
	/**
	 * 批量添加(包含主键)
	 * @param list<Article> articles
	 * @return 
	 */
	public void batchSaveAll(@Param("list") List<Article> list);
	
	/**
	 * 根据Article某个属性来查询
	 * @param article<Article> articles
	 * return List<Article>
	 */
	public List<Article> findByPage(Article article);
	
	/**
	 * 批量删除
	 * @param ids<Integer> ids
	 * return 
	 */
	public void batchDelete(@Param("ids") List<Integer> ids);
	
}
