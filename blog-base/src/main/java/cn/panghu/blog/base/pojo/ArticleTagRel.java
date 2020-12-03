package cn.panghu.blog.base.pojo;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "ArticleTagRel", description = "文章标签关联")
public class ArticleTagRel implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    @ApiModelProperty(value="主键")
    private Integer id;
    @ApiModelProperty(value="文章ID")
    private Integer articleId;
    @ApiModelProperty(value="标签ID")
    private Integer tagId;
   	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
   	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}
	
   	public Integer getTagId() {
		return tagId;
	}

	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}
	

	@Override
	public String toString() {
		return String.format("ArticleTagRel [, id=%s, articleId=%s, tagId=%s]"
					  , id, articleId, tagId);
	}

}
