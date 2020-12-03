package cn.panghu.blog.base.pojo;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(value = "Comment", description = "评论")
public class Comment implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    @ApiModelProperty(value="id")
    private Long id;
    @ApiModelProperty(value="允许通知")
    private Integer allowNotification;
    @ApiModelProperty(value="评论人")
    private String author;
    @ApiModelProperty(value="评论人个人网址")
    private String authorUrl;
    @ApiModelProperty(value="评论内容")
    private String content;
    @ApiModelProperty(value="评论人邮箱")
    private String email;
    @ApiModelProperty(value="头像md5")
    private String gravatarMd5;
    @ApiModelProperty(value="ip地址")
    private String ipAddress;
    @ApiModelProperty(value="是否是管理员")
    private Integer isAdmin;
    @ApiModelProperty(value="回复的父节点")
    private Long parentId;
    @ApiModelProperty(value="文章ID")
    private Integer articleId;
    @ApiModelProperty(value="用户浏览器信息")
    private String userAgent;
    @ApiModelProperty(value="创建时间")
    private Date createTime;
    @ApiModelProperty(value="修改时间")
    private Date updateTime;
    @ApiModelProperty(value="状态 0:删除，1:待审核，2:通过")
    private Integer status;
   	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
   	public Integer getAllowNotification() {
		return allowNotification;
	}

	public void setAllowNotification(Integer allowNotification) {
		this.allowNotification = allowNotification;
	}
	
   	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
   	public String getAuthorUrl() {
		return authorUrl;
	}

	public void setAuthorUrl(String authorUrl) {
		this.authorUrl = authorUrl;
	}
	
   	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
   	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
   	public String getGravatarMd5() {
		return gravatarMd5;
	}

	public void setGravatarMd5(String gravatarMd5) {
		this.gravatarMd5 = gravatarMd5;
	}
	
   	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	
   	public Integer getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Integer isAdmin) {
		this.isAdmin = isAdmin;
	}
	
   	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
   	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}
	
   	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
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
		return String.format("Comment [, id=%s, allowNotification=%s, author=%s, authorUrl=%s, content=%s, email=%s, gravatarMd5=%s, ipAddress=%s, isAdmin=%s, parentId=%s, articleId=%s, userAgent=%s, createTime=%s, updateTime=%s, status=%s]"
					  , id, allowNotification, author, authorUrl, content, email, gravatarMd5, ipAddress, isAdmin, parentId, articleId, userAgent, createTime, updateTime, status);
	}

}
