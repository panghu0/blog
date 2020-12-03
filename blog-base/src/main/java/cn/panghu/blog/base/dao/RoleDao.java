package cn.panghu.blog.base.dao;

import cn.panghu.blog.base.pojo.Role;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * RoleDao接口
 */
@Repository
public interface RoleDao {

	/**
	 * insert
	 */
	public void save(Role role);
	
	/**
	 * insert include id
	 */
	public void saveAll(Role role);

	/**
	 * 根据ID删除Role
	 */
	public void deleteById(@Param("id") Integer id);
	
	/**
	 * 更新
	 */
	public void update(Role role);
	
	/**
	 * 根据ID查询Role
	 */
	public Role findById(@Param("id") Integer id);

	/**
	 * 批量添加
	 */
	public void batchSave(@Param("list") List<Role> list);
	
	/**
	 * 批量添加(包含主键)
	 * @param List<Role> roles
	 * @return 
	 */
	public void batchSaveAll(@Param("list") List<Role> list);
	
	/**
	 * 根据Role某个属性来查询
	 */
	public List<Role> findByPage(Role role);
	
	/**
	 * 批量删除
	 */
	public void batchDelete(@Param("ids") List<Integer> ids);
	
}
