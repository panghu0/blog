package cn.panghu.blog.base.dao;

import cn.panghu.blog.base.pojo.Journal;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * JournalDao接口
 */
@Repository
public interface JournalDao {

	/**
	 * insert
	 */
	public void save(Journal journal);
	
	/**
	 * insert include id
	 */
	public void saveAll(Journal journal);
	
	/**
	 * 根据ID删除Journal
	 * @param Integer id
	 * @return 
	 */
	public void deleteById(@Param("id") Integer id);
	
	/**
	 * 更新
	 * @param Journal journal
	 * @return
	 */
	public void update(Journal journal);
	
	/**
	 * 根据ID查询Journal
	 * @param Integer id
	 * @return
	 */
	public Journal findById(@Param("id") Integer id);
	
	/**
	 * 批量添加
	 * @param List<Journal> journals
	 * @return 
	 */
	public void batchSave(@Param("list") List<Journal> list);
	
	/**
	 * 批量添加(包含主键)
	 * @param List<Journal> journals
	 * @return 
	 */
	public void batchSaveAll(@Param("list") List<Journal> list);
	
	/**
	 * 根据Journal某个属性来查询
	 * @param List<Journal> journals
	 * return List<Journal>
	 */
	public List<Journal> findByPage(Journal journal);
	
	/**
	 * 批量删除
	 * @param List<Integer> ids
	 * return 
	 */
	public void batchDelete(@Param("ids") List<Integer> ids);
	
}
