package cn.panghu.blog.global;

import cn.panghu.blog.common.enums.GlobalResultEnum;
import cn.panghu.blog.common.exception.BusinessException;
import cn.panghu.blog.common.exception.LoginException;
import cn.panghu.blog.common.exception.ParamsException;
import cn.panghu.blog.common.result.ResultBody;
import cn.panghu.blog.common.utils.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.ResourceAccessException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author pang hu
 * @date 2020/5/16 12:38
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    protected static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = LoginException.class)
    public ResultBody<String> handlerLoginException(HttpServletRequest request,
                                                    BusinessException exception) {
        logger.error(ExceptionUtils.getErrorStack(exception));
        return new ResultBody<String>(GlobalResultEnum.UNAUTHORIZED);
    }

    @ExceptionHandler(value = ParamsException.class)
    public ResultBody<String> handlerParamsException(HttpServletRequest request,
                                                     ParamsException exception) {
        logger.error(ExceptionUtils.getErrorStack(exception));
        return new ResultBody<String>(exception.getExceptionCode(), exception.getMessage());
    }

    @ExceptionHandler(value = ResourceAccessException.class)
    public ResultBody<String> handlerResourceAccessException(HttpServletRequest request,
                                                             ResourceAccessException exception) {
        logger.error(ExceptionUtils.getErrorStack(exception));
        return new ResultBody<String>(GlobalResultEnum.CONNECT_ERROR);
    }

    @ExceptionHandler(value = BusinessException.class)
    public ResultBody<String> handlerBusinessException(HttpServletRequest request,
                                                       BusinessException exception) {
        logger.error(exception.getMessage());
        return new ResultBody<String>(exception.getExceptionCode(), exception.getMessage());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResultBody<String> handlerMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        BindingResult result = exception.getBindingResult();
        if (result.hasErrors()) {
            logger.error(ExceptionUtils.getErrorStack(exception));
            return new ResultBody<>(GlobalResultEnum.INVALID_PARAM.getCode(),
                    result.getAllErrors().get(0).getDefaultMessage());
        }
        return new ResultBody<>(GlobalResultEnum.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = Exception.class)
    public ResultBody<String> handlerException(HttpServletRequest request,
                                               Exception exception) {
        logger.error(ExceptionUtils.getErrorStack(exception));
        return new ResultBody<String>(GlobalResultEnum.INTERNAL_SERVER_ERROR);
    }

}
