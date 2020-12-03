package cn.panghu.blog.common.interceptor;

import cn.panghu.blog.common.constant.GlobalConstant;
import cn.panghu.blog.common.data.ThreadLocalMap;
import cn.panghu.blog.common.enums.GlobalResultEnum;
import cn.panghu.blog.common.properties.CommonProperties;
import cn.panghu.blog.common.redis.RedisUtil;
import cn.panghu.blog.common.result.ResultBody;
import cn.panghu.blog.common.spring.SpringContextHolder;
import cn.panghu.blog.common.utils.IpUtil;
import cn.panghu.blog.base.pojo.User;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginContextInterceptor implements HandlerInterceptor {
	
    private final Logger logger = LoggerFactory.getLogger(LoginContextInterceptor.class);
    private static CommonProperties commonProperties;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
    	if (request.getMethod().equals(RequestMethod.OPTIONS.name())) {
        	response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }
    	
    	// 如果不是映射到方法直接通过
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        
        response.setContentType("text/html;charset=utf-8");
        
        String type = this.getCommonProperties().getType();
    	if(logger.isDebugEnabled()){
    		logger.debug("会话类型={}", type);
    		logger.debug("user client is {}", IpUtil.getIpAddr(request));
    	}
    	
    	if("token".equalsIgnoreCase(type)){
    		// 从 http 请求头中取出 token
    		String token = request.getHeader(GlobalConstant.X_AUTH_TOKEN);
    		//如果token为空，在从URL中获取token值
    		token = StringUtils.isBlank(token)?request.getParameter(GlobalConstant.X_AUTH_TOKEN):token;
    		logger.debug("login token is :" + token);
    		
    		if(StringUtils.isBlank(token)){
    			response.getWriter().write(JSONObject.toJSONString(
            			new ResultBody<String>(GlobalResultEnum.UNAUTHORIZED)));
    			logger.debug("********************************{} is not allow.", request.getServletPath());
            	return Boolean.FALSE;
    		}else{
    			//从缓存中获取到用户的信息
    			RedisUtil redisUtil = SpringContextHolder.getBean(RedisUtil.class);
    			Object obj = redisUtil.get(token);
    			if(obj != null){
    				User uToken = JSON.parseObject(obj.toString(), User.class);
    				ThreadLocalMap.put(GlobalConstant.SESSION_ID, token);
                	ThreadLocalMap.put(GlobalConstant.SESSION_USER_KEY, uToken);
                	
                	redisUtil.expire(token, 
                			token.startsWith(GlobalConstant.REDIS_USER_TOKEN_KEY_REMEMBERME)?
									GlobalConstant.EXPIRE_TIME_REMEMBERME:GlobalConstant.EXPIRE_TIME);
    			}else{
					//根据token获取不到缓存数据时，表示需要重新登录
					response.getWriter().write(JSONObject.toJSONString(
                			new ResultBody<String>(GlobalResultEnum.UNAUTHORIZED)));
					logger.debug("********************************{} is not allow.", request.getServletPath());
                	return Boolean.FALSE;
				}
    		}
    	}else{
    		//获取session
            HttpSession session = request.getSession(false);
            if(session == null){
            	logger.debug("no session!!!");
            	response.getWriter().write(JSONObject.toJSONString(
            			new ResultBody<String>(GlobalResultEnum.UNAUTHORIZED)));
            	return Boolean.FALSE;
            }else{
            	//判断用户ID是否存在，不存在就跳转到登录界面
                Object uToken = session.getAttribute(GlobalConstant.SESSION_USER_KEY);
                if (uToken == null) {
                	logger.debug("session no user!!!");
                	response.getWriter().write(JSONObject.toJSONString(
                			new ResultBody<String>(GlobalResultEnum.UNAUTHORIZED)));
                	return Boolean.FALSE;
                }else{
                	ThreadLocalMap.put(GlobalConstant.SESSION_ID, session.getId());
                	ThreadLocalMap.put(GlobalConstant.SESSION_USER_KEY, uToken);
                }
            }
    	}
        return true;
    }

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		logger.debug("login after completion...");
		ThreadLocalMap.remove();
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		logger.debug("login post handle...");
	}
	
	private CommonProperties getCommonProperties(){
		if(commonProperties == null){
			commonProperties = SpringContextHolder.getBean("commonProperties");
		}
		return commonProperties;
	}

}