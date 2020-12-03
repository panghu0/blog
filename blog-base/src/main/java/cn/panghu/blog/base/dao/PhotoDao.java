package cn.panghu.blog.base.dao;

import java.util.List;

import cn.panghu.blog.base.pojo.Photo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * PhotoDao接口
 */
@Repository
public interface PhotoDao {

	/**
	 * insert
	 * @param photo
	 * @return
	 */
	public void save(Photo photo);
	
	/**
	 * insert include id
	 * @param photo
	 * @return
	 */
	public void saveAll(Photo photo);
	
	/**
	 * 根据ID删除Photo
	 * @param id
	 * @return 
	 */
	public void deleteById(@Param("id") Integer id);
	
	/**
	 * 更新
	 * @param photo
	 * @return
	 */
	public void update(Photo photo);
	
	/**
	 * 根据ID查询Photo
	 * @param id
	 * @return
	 */
	public Photo findById(@Param("id") Integer id);
	
	/**
	 * 批量添加
	 * @param list
	 * @return 
	 */
	public void batchSave(@Param("list") List<Photo> list);
	
	/**
	 * 批量添加(包含主键)
	 * @param list
	 * @return 
	 */
	public void batchSaveAll(@Param("list") List<Photo> list);
	
	/**
	 * 根据Photo某个属性来查询
	 * @param photo
	 * return List<Photo>
	 */
	public List<Photo> findByPage(Photo photo);
	
	/**
	 * 批量删除
	 * @param ids
	 * return 
	 */
	public void batchDelete(@Param("ids") List<Integer> ids);
}
