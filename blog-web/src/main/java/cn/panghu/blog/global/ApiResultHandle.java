package cn.panghu.blog.global;

import cn.panghu.blog.common.enums.GlobalResultEnum;
import cn.panghu.blog.common.result.ResultBody;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.AnnotatedElement;
import java.util.Arrays;

@SuppressWarnings("rawtypes")
@ControllerAdvice(annotations = RestController.class)
public class ApiResultHandle implements ResponseBodyAdvice{

	//private ThreadLocal<ObjectMapper>  mapperThreadLocal = ThreadLocal.withInitial(ObjectMapper::new);
	protected final Logger logger = LoggerFactory.getLogger(ApiResultHandle.class);

    private static final Class[] annos = {
            RequestMapping.class,
            GetMapping.class,
            PostMapping.class,
            DeleteMapping.class,
            PutMapping.class
    };

    /**
     * 对所有RestController的接口方法进行拦截
     */
    @SuppressWarnings("unchecked")
	@Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        AnnotatedElement element = returnType.getAnnotatedElement();
        if(returnType.getMethod().getReturnType().equals(ResponseEntity.class)){
        	return Boolean.FALSE;
        }
        return Arrays.stream(annos).anyMatch(anno -> anno.isAnnotation() && element.isAnnotationPresent(anno));
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, 
    		Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
    	Object out = null;
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        
        //去除onlyoffice的返回结构转换
        String uri = request.getURI().getPath();
        if(StringUtils.isNotBlank(uri) && uri.contains("/onlyoffice/callback")){
        	return body;
        }
        if(body == null){
        	out = new ResultBody(GlobalResultEnum.SUCCESS);
        	
        }else if(body instanceof ResultBody){
            out = body;
            
        }else if (body instanceof GlobalResultEnum){
        	GlobalResultEnum resultCode = (GlobalResultEnum) body;
            out = new ResultBody(resultCode);
            
        }else{
            out = new ResultBody<Object>(body);
        }
        return out;
    }


}
