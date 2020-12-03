package cn.panghu.blog.base.dao;

import cn.panghu.blog.base.pojo.ArticlePhoto;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * ArticlePhotoDao接口
 */
@Repository
public interface ArticlePhotoDao {

	/**
	 * insert
	 * @param ArticlePhoto articlePhoto
	 * @return
	 */
	public void save(ArticlePhoto articlePhoto);
	
	/**
	 * insert include id
	 * @param ArticlePhoto articlePhoto
	 * @return
	 */
	public void saveAll(ArticlePhoto articlePhoto);
	
	/**
	 * 根据ID删除ArticlePhoto
	 * @param Integer id
	 * @return 
	 */
	public void deleteById(@Param("id") Integer id);
	
	/**
	 * 更新
	 * @param ArticlePhoto articlePhoto
	 * @return
	 */
	public void update(ArticlePhoto articlePhoto);
	
	/**
	 * 根据ID查询ArticlePhoto
	 * @param Integer id
	 * @return
	 */
	public ArticlePhoto findById(@Param("id") Integer id);
	
	/**
	 * 批量添加
	 * @param List<ArticlePhoto> articlePhotos
	 * @return 
	 */
	public void batchSave(@Param("list") List<ArticlePhoto> list);
	
	/**
	 * 批量添加(包含主键)
	 * @param List<ArticlePhoto> articlePhotos
	 * @return 
	 */
	public void batchSaveAll(@Param("list") List<ArticlePhoto> list);
	
	/**
	 * 根据ArticlePhoto某个属性来查询
	 * @param List<ArticlePhoto> articlePhotos
	 * return List<ArticlePhoto>
	 */
	public List<ArticlePhoto> findByPage(ArticlePhoto articlePhoto);
	
	/**
	 * 批量删除
	 * @param List<Integer> ids
	 * return 
	 */
	public void batchDelete(@Param("ids") List<Integer> ids);
	
}
