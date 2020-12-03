package cn.panghu.blog.base.dao;

import java.util.List;

import cn.panghu.blog.base.pojo.Tag;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * TagDao接口
 */
@Repository
public interface TagDao {

	/***
	 * @Description 查询分类下的标签
	 */
	List<Tag> findTagsByCategory(@Param("categoryId") Integer categoryId);
	/**
	 * insert
	 */
	public void save(Tag tag);
	
	/**
	 * insert include id
	 */
	public void saveAll(Tag tag);
	
	/**
	 * 根据ID删除Tag
	 */
	public void deleteById(@Param("id") Integer id);
	
	/**
	 * 更新
	 */
	public void update(Tag tag);
	
	/**
	 * 根据ID查询Tag
	 */
	public Tag findById(@Param("id") Integer id);
	
	/**
	 * 批量添加
	 */
	public void batchSave(@Param("list") List<Tag> list);
	
	/**
	 * 批量添加(包含主键)
	 */
	public void batchSaveAll(@Param("list") List<Tag> list);
	
	/**
	 * 根据Tag某个属性来查询
	 */
	public List<Tag> findByPage(Tag tag);
	
	/**
	 * 批量删除
	 */
	public void batchDelete(@Param("ids") List<Integer> ids);
}
