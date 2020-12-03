package cn.panghu.blog.base.pojo;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(value = "Article", description = "文章")
public class Article implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    @ApiModelProperty(value="文章ID")
    private Integer id;
    @ApiModelProperty(value="文章标题")
    private String title;
    @ApiModelProperty(value="文章分类")
    private Integer categoryId;
    @ApiModelProperty(value="是否禁止评论，默认允许")
    private Integer disallowComment;
    @ApiModelProperty(value="原文章，无格式")
    private String originalContent;
    @ApiModelProperty(value="格式化的文章")
    private String formatContent;
    @ApiModelProperty(value="seo关键词")
    private String metaKeywords;
    @ApiModelProperty(value="文章访问密码")
    private String password;
    @ApiModelProperty(value="文章摘要，不写自动截取")
    private String summary;
    @ApiModelProperty(value="是否置顶")
    private Integer topPriority;
    @ApiModelProperty(value="文章封面图id")
    private Integer photoId;
    @ApiModelProperty(value="喜欢")
    private Long likes;
    @ApiModelProperty(value="阅读")
    private Long visits;
    @ApiModelProperty(value="创建日期")
    private Date createTime;
    @ApiModelProperty(value="修改日期")
    private Date updateTime;
    @ApiModelProperty(value="文章状态")
    private Integer status;
   	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
   	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
   	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	
   	public Integer getDisallowComment() {
		return disallowComment;
	}

	public void setDisallowComment(Integer disallowComment) {
		this.disallowComment = disallowComment;
	}
	
   	public String getOriginalContent() {
		return originalContent;
	}

	public void setOriginalContent(String originalContent) {
		this.originalContent = originalContent;
	}
	
   	public String getFormatContent() {
		return formatContent;
	}

	public void setFormatContent(String formatContent) {
		this.formatContent = formatContent;
	}
	
   	public String getMetaKeywords() {
		return metaKeywords;
	}

	public void setMetaKeywords(String metaKeywords) {
		this.metaKeywords = metaKeywords;
	}
	
   	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
   	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}
	
   	public Integer getTopPriority() {
		return topPriority;
	}

	public void setTopPriority(Integer topPriority) {
		this.topPriority = topPriority;
	}
	
   	public Integer getPhotoId() {
		return photoId;
	}

	public void setPhotoId(Integer photoId) {
		this.photoId = photoId;
	}
	
   	public Long getLikes() {
		return likes;
	}

	public void setLikes(Long likes) {
		this.likes = likes;
	}
	
   	public Long getVisits() {
		return visits;
	}

	public void setVisits(Long visits) {
		this.visits = visits;
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
		return String.format("Article [, id=%s, title=%s, categoryId=%s, disallowComment=%s, originalContent=%s, formatContent=%s, metaKeywords=%s, password=%s, summary=%s, topPriority=%s, photoId=%s, likes=%s, visits=%s, createTime=%s, updateTime=%s, status=%s]"
					  , id, title, categoryId, disallowComment, originalContent, formatContent, metaKeywords, password, summary, topPriority, photoId, likes, visits, createTime, updateTime, status);
	}

}
