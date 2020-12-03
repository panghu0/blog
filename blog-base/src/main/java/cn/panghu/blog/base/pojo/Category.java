package cn.panghu.blog.base.pojo;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(value = "Category", description = "文章分类")
public class Category implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    @ApiModelProperty(value="分类Id")
    private Integer id;
    @ApiModelProperty(value="分类名称")
    private String name;
    @ApiModelProperty(value="上级分类目录Id")
    private Integer parentId;
    @ApiModelProperty(value="分类创建时间")
    private Date createTime;
    @ApiModelProperty(value="分类修改时间")
    private Date updateTime;
    @ApiModelProperty(value="分类描述")
    private String description;
    @ApiModelProperty(value="封面图")
    private String thumbnail;
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
	
   	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
   	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	

	@Override
	public String toString() {
		return String.format("Category [, id=%s, name=%s, parentId=%s, createTime=%s, updateTime=%s, description=%s, thumbnail=%s]"
					  , id, name, parentId, createTime, updateTime, description, thumbnail);
	}

}
