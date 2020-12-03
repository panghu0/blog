package cn.panghu.blog.base.pojo;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(value = "Menu", description = "导航菜单表")
public class Menu implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    @ApiModelProperty(value="导航菜单ID")
    private Integer id;
    @ApiModelProperty(value="名称")
    private String name;
    @ApiModelProperty(value="图标")
    private String icon;
    @ApiModelProperty(value="排序")
    private Integer priority;
    @ApiModelProperty(value="导航菜单创建时间")
    private Date createTime;
    @ApiModelProperty(value="导航菜单修改时间")
    private Date updateTime;
    @ApiModelProperty(value="打开方式,默认当前窗口")
    private String target;
    @ApiModelProperty(value="导航菜单链接地址")
    private String url;
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
	
   	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
   	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
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
	
   	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}
	
   	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	

	@Override
	public String toString() {
		return String.format("Menu [, id=%s, name=%s, icon=%s, priority=%s, createTime=%s, updateTime=%s, target=%s, url=%s]"
					  , id, name, icon, priority, createTime, updateTime, target, url);
	}

}
