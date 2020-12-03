package cn.panghu.blog.base.dao;
import java.util.List;

import cn.panghu.blog.base.pojo.CategoryTagRel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * CategoryTagRelDao接口
 */
@Repository
public interface CategoryTagRelDao {

	/**
	 * insert
	 */
	public void save(CategoryTagRel categoryTagRel);
	
	/**
	 * insert include id
	 */
	public void saveAll(CategoryTagRel categoryTagRel);
	
	/**
	 * 根据ID删除CategoryTagRel
	 */
	public void deleteById(@Param("id") Integer id);
	
	/**
	 * 更新
	 */
	public void update(CategoryTagRel categoryTagRel);
	
	/**
	 * 根据ID查询CategoryTagRel
	 */
	public CategoryTagRel findById(@Param("id") Integer id);
	
	/**
	 * 批量添加
	 */
	public void batchSave(@Param("list") List<CategoryTagRel> list);
	
	/**
	 * 批量添加(包含主键)
	 */
	public void batchSaveAll(@Param("list") List<CategoryTagRel> list);
	
	/**
	 * 根据CategoryTagRel某个属性来查询
	 */
	public List<CategoryTagRel> findByPage(CategoryTagRel categoryTagRel);
	
	/**
	 * 批量删除
	 */
	public void batchDelete(@Param("ids") List<Integer> ids);
	
}
