package cn.panghu.blog.base.pojo;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(value = "Photo", description = "图片信息表")
public class Photo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    @ApiModelProperty(value="id")
    private Integer id;
    @ApiModelProperty(value="图片名称")
    private String fileName;
    @ApiModelProperty(value="图片保存地址")
    private String url;
    @ApiModelProperty(value="图片介绍")
    private String description;
    @ApiModelProperty(value="是否使用过 0未使用，1已使用")
    private Integer used;
    @ApiModelProperty(value="0:头像 1:封面图 2:文章图 3:光影")
    private Integer type;
    @ApiModelProperty(value="创建时间")
    private Date createTime;
    @ApiModelProperty(value="更新时间")
    private Date updateTime;
    @ApiModelProperty(value="状态")
    private Integer status;
   	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
   	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
   	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
   	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
   	public Integer getUsed() {
		return used;
	}

	public void setUsed(Integer used) {
		this.used = used;
	}
	
   	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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
		return String.format("Photo [, id=%s, fileName=%s, url=%s, description=%s, used=%s, type=%s, createTime=%s, updateTime=%s, status=%s]"
					  , id, fileName, url, description, used, type, createTime, updateTime, status);
	}

}
