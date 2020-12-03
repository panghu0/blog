package cn.panghu.blog.base.pojo;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(value = "User", description = "用户信息表")
public class User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    @ApiModelProperty(value="用户ID")
    private Integer id;
    @ApiModelProperty(value="用户名")
    private String username;
    @ApiModelProperty(value="密码")
    private String password;
    @ApiModelProperty(value="真实名称")
    private String realname;
    @ApiModelProperty(value="1:管理员 2:普通用户")
    private Integer roleId;
    @ApiModelProperty(value="用户头像")
    private String avatar;
    @ApiModelProperty(value="用户介绍")
    private String description;
    @ApiModelProperty(value="用户邮箱")
    private String email;
    @ApiModelProperty(value="最后登录时间")
    private Date lastLoginTime;
    @ApiModelProperty(value="修改日期")
    private Date updateTime;
    @ApiModelProperty(value="创建日期")
    private Date createTime;
    @ApiModelProperty(value="状态")
    private Integer status;
   	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
   	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
   	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
   	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}
	
   	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	
   	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
   	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
   	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
   	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	
   	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
   	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
   	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	

	@Override
	public String toString() {
		return String.format("User [, id=%s, username=%s, password=%s, realname=%s, roleId=%s, avatar=%s, description=%s, email=%s, lastLoginTime=%s, updateTime=%s, createTime=%s, status=%s]"
					  , id, username, password, realname, roleId, avatar, description, email, lastLoginTime, updateTime, createTime, status);
	}

}
