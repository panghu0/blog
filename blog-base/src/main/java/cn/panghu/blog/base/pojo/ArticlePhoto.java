package cn.panghu.blog.base.pojo;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel(value = "ArticlePhoto", description = "")
public class ArticlePhoto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    @ApiModelProperty(value="id")
    private Integer id;
    @ApiModelProperty(value="文章主键")
    private Integer articleId;
    @ApiModelProperty(value="图片主键")
    private Integer photoId;
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
	
   	public Integer getPhotoId() {
		return photoId;
	}

	public void setPhotoId(Integer photoId) {
		this.photoId = photoId;
	}
	

	@Override
	public String toString() {
		return String.format("ArticlePhoto [, id=%s, articleId=%s, photoId=%s]"
					  , id, articleId, photoId);
	}

}
