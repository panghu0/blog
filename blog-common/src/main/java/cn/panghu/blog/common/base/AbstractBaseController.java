package cn.panghu.blog.common.base;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.text.SimpleDateFormat;

/**
 * @author pang hu
 * @date 2020/5/16 12:24
 */
public class AbstractBaseController {
    @InitBinder
    public void initBinder(WebDataBinder binder) {	// 在本程序里面需要针对于日期格式进行处理
        // 首先建立一个可以将字符串转换为日期的工具程序类
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
        // 明确的描述此时需要注册一个日期格式的转化处理程序类
        binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(sdf, true));
    }
}
