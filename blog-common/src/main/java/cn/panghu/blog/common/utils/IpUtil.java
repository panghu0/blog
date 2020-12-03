package cn.panghu.blog.common.utils;

import cn.panghu.blog.common.constant.GlobalConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author pang hu
 * @date 2020/5/16 12:48
 */
public class IpUtil {

    private static Logger logger = LoggerFactory.getLogger(IpUtil.class);

    public static HttpServletRequest getHttpServletRequest(){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return requestAttributes.getRequest();
    }

    public static String getIpAddr(){
        return getIpAddr(getHttpServletRequest());
    }

    public static String getSessionId(){
        HttpSession session = getHttpServletRequest().getSession(false);
        return session == null?null:session.getId();
    }

    public static String getIpAddr(HttpServletRequest request) {
        String ipAddress = null;
        try {
            //nginx配置以设置
            ipAddress = request.getHeader("X-Real-IP");
            if (ipAddress == null || ipAddress.length() == 0 || GlobalConstant.Symbol.UNKNOW.equalsIgnoreCase(ipAddress)) {
                //取值XFF
                ipAddress = request.getHeader("X-Forwarded-For");
                if (ipAddress == null || ipAddress.length() == 0 || GlobalConstant.Symbol.UNKNOW.equalsIgnoreCase(ipAddress)) {
                    ipAddress = request.getRemoteAddr();
                    if (ipAddress.equals(GlobalConstant.LOCALHOST_IP)) {
                        // 根据网卡取本机配置的IP
                        InetAddress inet = null;
                        try {
                            inet = InetAddress.getLocalHost();
                        } catch (UnknownHostException e) {
                            logger.error(ExceptionUtils.getErrorStack(e));
                        }
                        ipAddress = inet.getHostAddress();
                    }
                }
            }
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
                // = 15
                if (ipAddress.indexOf(GlobalConstant.Symbol.COMMA) > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(GlobalConstant.Symbol.COMMA));
                }
            }
        } catch (Exception e) {
            logger.error(ExceptionUtils.getErrorStack(e));
            ipAddress = GlobalConstant.Symbol.EMPTY;
        }
        return ipAddress;
    }

}
