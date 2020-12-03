package cn.panghu.blog.base.pojo;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(value = "Link", description = "导航表")
public class Link implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    @ApiModelProperty(value="主键ID")
    private Integer id;
    @ApiModelProperty(value="导航链接名称")
    private String name;
    @ApiModelProperty(value="父级id")
    private Integer parentId;
    @ApiModelProperty(value="0:主页导航 1:后台导航")
    private Integer type;
    @ApiModelProperty(value="url")
    private String url;
    @ApiModelProperty(value="图标")
    private String icon;
    @ApiModelProperty(value="创建时间")
    private Date createTime;
    @ApiModelProperty(value="更新时间")
    private Date updateTime;
    @ApiModelProperty(value="0:不可用 1:可用")
    private Integer status;
   	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
   	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
   	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	
   	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
   	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
   	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
   	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
   	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
   	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	

	@Override
	public String toString() {
		return String.format("Link [, id=%s, name=%s, parentId=%s, type=%s, url=%s, icon=%s, createTime=%s, updateTime=%s, status=%s]"
					  , id, name, parentId, type, url, icon, createTime, updateTime, status);
	}

}
