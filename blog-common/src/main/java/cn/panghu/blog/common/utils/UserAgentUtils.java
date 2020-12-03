package cn.panghu.blog.common.utils;

import eu.bitwalker.useragentutils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * 获取浏览器信息
 * @author Administrator
 *
 */
public class UserAgentUtils {
	
	public static void main(String[] args) {
		try {
			System.out.println("=?utf-8?B?" + Base64.getEncoder().encodeToString("Hadoop2.0 集群探索".getBytes("utf-8")) + "?=");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static Logger logger = LoggerFactory.getLogger(UserAgentUtils.class);

	public static UserAgent getUserAgent(HttpServletRequest request){
		return UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
	}
	
	public static Browser getBrowser(UserAgent userAgent){
		return userAgent.getBrowser();
	}
	
	public static Version getBrowserVersion(UserAgent userAgent){
		return userAgent.getBrowserVersion();
	}
	
	public static OperatingSystem getOperatingSystem(UserAgent userAgent){
		return userAgent.getOperatingSystem();
	}
	
	public static String genName(HttpServletRequest request,String fileName){
		try {
			Browser browser = getBrowser(getUserAgent(request));
			// 针对IE或者以IE为内核的浏览器
			if(Manufacturer.MICROSOFT.compareTo(browser.getManufacturer()) == 0){
				return java.net.URLEncoder.encode(fileName, "UTF-8")
						.replace("+", " ");
			} else {  
		        // 非IE浏览器的处理：  
				return "=?utf-8?B?" + Base64.getEncoder().encodeToString(fileName.getBytes("utf-8")) + "?=";
		    }
		} catch (UnsupportedEncodingException e) {
			logger.error(ExceptionUtils.getErrorStack(e));
		}
		return fileName;
	}
}
