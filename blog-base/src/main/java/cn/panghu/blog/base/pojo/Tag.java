package cn.panghu.blog.base.pojo;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(value = "Tag", description = "标签表")
public class Tag implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    @ApiModelProperty(value="标签ID")
    private Integer id;
    @ApiModelProperty(value="标签名称")
    private String name;
    @ApiModelProperty(value="标签创建日期")
    private Date createTime;
    @ApiModelProperty(value="标签修改日期")
    private Date updateTime;
    @ApiModelProperty(value="标签状态")
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
		return String.format("Tag [, id=%s, name=%s, createTime=%s, updateTime=%s, status=%s]"
					  , id, name, createTime, updateTime, status);
	}

}
