package cn.panghu.blog.base.dao;

import cn.panghu.blog.base.pojo.ArticleTagRel;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * ArticleTagRelDao接口
 */
@Repository
public interface ArticleTagRelDao {

	/**
	 * insert
	 */
	public void save(ArticleTagRel articleTagRel);
	
	/**
	 * insert include id
	 */
	public void saveAll(ArticleTagRel articleTagRel);
	
	/**
	 * 根据ID删除ArticleTagRel
	 */
	public void deleteById(@Param("id") Integer id);
	
	/**
	 * 更新
	 */
	public void update(ArticleTagRel articleTagRel);
	
	/**
	 * 根据ID查询ArticleTagRel
	 */
	public ArticleTagRel findById(@Param("id") Integer id);
	
	/**
	 * 批量添加
	 */
	public void batchSave(@Param("list") List<ArticleTagRel> list);
	
	/**
	 * 批量添加(包含主键)
	 */
	public void batchSaveAll(@Param("list") List<ArticleTagRel> list);
	
	/**
	 * 根据ArticleTagRel某个属性来查询
	 */
	public List<ArticleTagRel> findByPage(ArticleTagRel articleTagRel);
	
	/**
	 * 批量删除
	 */
	public void batchDelete(@Param("ids") List<Integer> ids);
	
}
