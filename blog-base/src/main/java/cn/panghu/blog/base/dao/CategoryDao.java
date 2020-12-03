package cn.panghu.blog.base.dao;

import java.util.List;

import cn.panghu.blog.base.pojo.Category;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * CategoryDao接口
 */
@Repository
public interface CategoryDao {

	/**
	 * insert
	 * @param category
	 * @return
	 */
	public void save(Category category);

	/**
	 * insert include id
	 * @param category
	 * @return
	 */
	public void saveAll(Category category);
	
	/**
	 * 根据ID删除Category
	 * @param id
	 * @return 
	 */
	public void deleteById(@Param("id") Integer id);
	
	/**
	 * 更新
	 * @param category
	 * @return
	 */
	public void update(Category category);
	
	/**
	 * 根据ID查询Category
	 * @param id
	 * @return
	 */
	public Category findById(@Param("id") Integer id);
	
	/**
	 * 批量添加
	 * @param list<Category> list
	 * @return 
	 */
	public void batchSave(@Param("list") List<Category> list);
	
	/**
	 * 批量添加(包含主键)
	 * @param list<Category> categorys
	 * @return 
	 */
	public void batchSaveAll(@Param("list") List<Category> list);
	
	/**
	 * 根据Category某个属性来查询
	 * @param category<Category> categorys
	 * return List<Category>
	 */
	public List<Category> findByPage(Category category);
	
	/**
	 * 批量删除
	 * @param ids<Integer> ids
	 * return 
	 */
	public void batchDelete(@Param("ids") List<Integer> ids);
	
}
