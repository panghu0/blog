package cn.panghu.blog.base.pojo;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "CategoryTagRel", description = "分类标签关联表")
public class CategoryTagRel implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    @ApiModelProperty(value="id")
    private Integer id;
    @ApiModelProperty(value="分类id")
    private Integer categoryId;
    @ApiModelProperty(value="标签id")
    private Integer tagId;
   	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
   	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	
   	public Integer getTagId() {
		return tagId;
	}

	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}
	

	@Override
	public String toString() {
		return String.format("CategoryTagRel [, id=%s, categoryId=%s, tagId=%s]"
					  , id, categoryId, tagId);
	}

}
