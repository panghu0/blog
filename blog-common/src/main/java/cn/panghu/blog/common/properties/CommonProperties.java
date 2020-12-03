package cn.panghu.blog.common.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonProperties {

	@Value("${file.upload-path:''}")
	private String filePathPrefix;
	@Value("${login.type}")
    private String type;
	@Value("${server.session.timeout:1800}")
	private Integer sessionTimeOut;

	public String getFilePathPrefix() {
		return filePathPrefix;
	}
	public void setFilePathPrefix(String filePathPrefix) {
		this.filePathPrefix = filePathPrefix;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getSessionTimeOut() {
		return sessionTimeOut;
	}
	public void setSessionTimeOut(Integer sessionTimeOut) {
		this.sessionTimeOut = sessionTimeOut;
	}
	
}
