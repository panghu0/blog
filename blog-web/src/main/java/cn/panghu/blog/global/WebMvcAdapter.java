package cn.panghu.blog.global;

import cn.panghu.blog.common.interceptor.LoginContextInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class WebMvcAdapter extends WebMvcConfigurerAdapter {
	
	final static Logger logger = LoggerFactory.getLogger(WebMvcAdapter.class);
	
	@Value("${file.upload-path}")
	private String filePathPrefix;
	@Value("${login.enable:true}")
	private boolean logginEnable;
	
	@Override
    public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
	 		.allowedOrigins("*")
	         .allowedMethods("GET", "HEAD", "POST","PUT", "DELETE", "OPTIONS")
	         .allowedHeaders("*")
	         .exposedHeaders("access-control-allow-headers",
	                 "access-control-allow-methods",
	                 "access-control-allow-origin",
	                 "access-control-max-age",
	                 "X-Frame-Options",
	                 HttpHeaders.SET_COOKIE)
	         .allowCredentials(true)
	         .maxAge(3600);
		
		
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedHeaders("*")
                .allowedMethods("*")
                .exposedHeaders(HttpHeaders.SET_COOKIE)
                .allowCredentials(true)
                .maxAge(3600);
    }
	
	/**
     * 拦截器配置
     * @param registry
     */
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        //拦截规则：除了excludePathPatterns中地址，其他都拦截判断
		//用户登录拦截
		if(logginEnable){
			registry.addInterceptor(new LoginContextInterceptor())
			//添加你不需要拦截的请求路径
            .excludePathPatterns("/")
            //框架的错误返回请求
            .excludePathPatterns("/error/**")
            //添加你不需要拦截的请求路径
            .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**","/doc.html/**")
            //静态文件展示
            .excludePathPatterns("/static/**")
            //登录接口
            .excludePathPatterns("/login/**")
            //测试录接口
            .excludePathPatterns("/test/**")
            //资源更新接口
            .excludePathPatterns("/resourceFileDownload/**")
            .excludePathPatterns("/resourceDataDownload/**")
            .excludePathPatterns("/resourceDownload/**")
            //版本获取
            .excludePathPatterns("/versions/currentInfo")
            //激活授权码接口
            .excludePathPatterns("/auths/check/**")
            //私有云汇报操作信息
            .excludePathPatterns("/auths/operation/**")
            //私有云向公有云同步消息信息，先这样处理.
            .excludePathPatterns("/org/school/pub/**")
            //资源库对外提供相关接口
            .excludePathPatterns("/resource/**")
            .excludePathPatterns("/resourceDetails/**")
            //文件转换相关接口
			.excludePathPatterns("/save")
			.excludePathPatterns("/file")
			.excludePathPatterns("/preview")
			.excludePathPatterns("/safety/**")
			//文件上传下载相关接口
			.excludePathPatterns("/fileData/**")
			//元数据配置平台的基础接口
			.excludePathPatterns("/configBase/**")
			//对外登录授权验证接口
			.excludePathPatterns("/openApi/**")
			//资源下发接口
			.excludePathPatterns("/dataDownload/**")
			//资源下发接口
			.excludePathPatterns("/zroffice/**")
			//对外提供的接口
			.excludePathPatterns("/stackmgr/**")
            //添加你要拦截的请求路径，如果有多个路径，就继续addPathPatterns
            .addPathPatterns("/**");
			
			super.addInterceptors(registry);
		}
    }
	
	/**
     * 资源处理器
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	registry.addResourceHandler("/**").addResourceLocations(
                "classpath:/static/");
        registry.addResourceHandler("swagger-ui.html").addResourceLocations(
                "classpath:/META-INF/resources/");
        registry.addResourceHandler("doc.html").addResourceLocations(
        		"classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations(
                "classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("/static/**").addResourceLocations("file:"+filePathPrefix)
				.setCacheControl(CacheControl.maxAge(1, TimeUnit.HOURS).cachePublic());
        registry.addResourceHandler("/resourceFileDownload/**").addResourceLocations("file:"+filePathPrefix);
        super.addResourceHandlers(registry);
    }
	
}