package cn.panghu.blog.base.dao;

import cn.panghu.blog.base.pojo.Comment;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * CommentDao接口
 */
@Repository
public interface CommentDao {

	/**
	 * insert
	 */
	public void save(Comment comment);
	
	/**
	 * insert include id
	 */
	public void saveAll(Comment comment);
	
	/**
	 * 根据ID删除Comment
	 */
	public void deleteById(@Param("id") Integer id);
	
	/**
	 * 更新
	 */
	public void update(Comment comment);
	
	/**
	 * 根据ID查询Comment
	 */
	public Comment findById(@Param("id") Integer id);
	
	/**
	 * 批量添加
	 */
	public void batchSave(@Param("list") List<Comment> list);
	
	/**
	 * 批量添加(包含主键)
	 */
	public void batchSaveAll(@Param("list") List<Comment> list);
	
	/**
	 * 根据Comment某个属性来查询
	 */
	public List<Comment> findByPage(Comment comment);
	
	/**
	 * 批量删除
	 */
	public void batchDelete(@Param("ids") List<Integer> ids);
	
}
