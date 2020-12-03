package cn.panghu.blog.base.pojo;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(value = "Journal", description = "日志表")
public class Journal implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    @ApiModelProperty(value="日志Id")
    private Integer id;
    @ApiModelProperty(value="日志创建时间")
    private Date createTime;
    @ApiModelProperty(value="日志修改时间")
    private Date updateTime;
    @ApiModelProperty(value="日志内容")
    private String content;
    @ApiModelProperty(value="操作地址")
    private String ip;
    @ApiModelProperty(value="类型")
    private Integer type;
   	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
	
   	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
   	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
   	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	

	@Override
	public String toString() {
		return String.format("Journal [, id=%s, createTime=%s, updateTime=%s, content=%s, ip=%s, type=%s]"
					  , id, createTime, updateTime, content, ip, type);
	}

}
