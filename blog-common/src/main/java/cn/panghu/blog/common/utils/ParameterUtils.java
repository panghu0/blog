package cn.panghu.blog.common.utils;

import cn.panghu.blog.common.enums.GlobalResultEnum;
import cn.panghu.blog.common.exception.BusinessException;
import cn.panghu.blog.common.exception.ParamsException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParameterUtils {
    protected static Logger logger = LoggerFactory.getLogger(ParameterUtils.class);

    /**
     * 校验是否为空
     *
     * @param args
     */
    public static void validateParam(Object... args) {
        for (Object arg : args) {
            if (null == arg) {
                throw new ParamsException(GlobalResultEnum.INVALID_PARAM);
            }
            if (arg instanceof String) {
                if (StringUtils.isBlank(String.valueOf(arg))) {
                    throw new ParamsException(GlobalResultEnum.INVALID_PARAM);
                }
            }
            if (arg instanceof Integer) {
                try {
                    Integer.parseInt(String.valueOf(arg));
                } catch (Exception e) {
                    throw new ParamsException(GlobalResultEnum.INVALID_PARAM);
                }
            }
            if (arg instanceof MultipartFile) {
                try {
                    if (((MultipartFile) arg).isEmpty()) {
                        throw new ParamsException(GlobalResultEnum.INVALID_PARAM);
                    }
                } catch (Exception e) {
                    throw new ParamsException(GlobalResultEnum.INVALID_PARAM);
                }
            }
        }
    }

    /**
     * 检验是否为纯数字
     *
     * @param num
     * @param msg
     */
    public static void validateInt(String num, String msg) {
        try {
            logger.debug("validateInt-" + num);
            Double.parseDouble(num);
        } catch (Exception e) {
            throw new ParamsException(GlobalResultEnum.INVALID_PARAM, msg);
        }
    }

    /**
     * 校验是否为纯中文
     *
     * @param name             名称
     * @param exceptionMessage 异常信息
     * @return
     */
    public static void validateChinese(String name, String exceptionMessage) {
        if (!Pattern.compile("^[\u4e00-\u9fa5]+$").matcher(name).matches()) {
            throw new BusinessException(GlobalResultEnum.INVALID_PARAM, exceptionMessage);
        }
    }

    public static void vaildatePhone(String phone, String exceptionMessage) {
    	String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
        if(StringUtils.isBlank(phone) ||
        		phone.length() != 11 ||
        		!Pattern.compile(regex).matcher(phone).matches()){
            throw new BusinessException(GlobalResultEnum.INVALID_PARAM, exceptionMessage);
        }
    }

    /**
     * 验证是否为正确的邮箱号
     *
     * @param email
     * @return
     */
    public static void vaildateEmail(String email, String exceptionMessage) {
        // 1、\\w+表示@之前至少要输入一个匹配字母或数字或下划线 \\w 单词字符：[a-zA-Z_0-9]
        // 2、(\\w+\\.)表示域名. 如新浪邮箱域名是sina.com.cn
        // {1,3}表示可以出现一次或两次或者三次.
        if (!Pattern.compile("\\w+@(\\w+\\.){1,3}\\w+").matcher(email).matches()) {
            throw new BusinessException(GlobalResultEnum.INVALID_PARAM, exceptionMessage);
        }
    }

    /**
     * 长度校验
     *
     * @param size
     * @param exceptionMessage
     */
    public static void validateLength(String name, Integer size, String exceptionMessage) {
        if (name.length() > size) {
            throw new BusinessException(GlobalResultEnum.INVALID_PARAM, exceptionMessage);
        }
    }

    /**
     * 判断文件格式
     *
     * @param allowType 允许的格式
     * @param fileType  文件类型
     */
    public static void validateFileType(String allowType, String fileType) {
        if (!allowType.toUpperCase().contains(fileType.toUpperCase())) {
            throw new BusinessException(GlobalResultEnum.FILE_TYPE_ERROR);
        }
    }

    /**
     * 校验文件不为空文件
     *
     * @param file
     * @return 空文件返回：false
     * 有上传文件返回：true
     */
    public static boolean validateFileIsNotNull(MultipartFile file) {
    	if(file == null){
    		return false;
    	}
        if (("blob".equals(file.getOriginalFilename()) && file.getSize() == 0) || "del".equals(file.getOriginalFilename())) {
            // blob为前端传来的文件，默认认为没有传文件
            return false;
        } else if (file.getSize() > 0 && !file.isEmpty()) {
            // 有上传文件
            return true;
        } else {
            // 否则认为有传文件但是文件为空
            throw new BusinessException(GlobalResultEnum.FILE_TYPE_ERROR);
        }
    }

    /**
     * 校验文件是否删除
     *
     * @param file
     * @return
     */
    public static boolean validateFileIsDelete(MultipartFile file) {
        if (file != null && !"del".equals(file.getOriginalFilename())) {
            return false;
        }
        return true;
    }

    /**
     * 校验是否是IP地址
     *
     * @param addr
     * @return
     */
    public static boolean validateIsIP(String addr) {
        if (addr.length() < 7 || addr.length() > 15 || "".equals(addr)) {
            return false;
        }
        String rexp = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";
        Pattern pat = Pattern.compile(rexp);
        Matcher mat = pat.matcher(addr);
        boolean ipAddress = mat.find();
        return ipAddress;
    }
    
    public static boolean validate(String str, String rexp){
    	Pattern pat = Pattern.compile(rexp);
    	Matcher mat = pat.matcher(str);
    	return mat.matches();
    }

    public static void main(String[] args) {
//        List<String> list = new ArrayList<String>();
//        list.add("fsfs");
//        list.add("abc11");
//        list.add("ffdes");
//        System.out.println(list.contains("abc111"));
//        validateChinese("试试  ", "ssss");
//    	vaildatePhone("15211005266", "第" + 2 + "行,手机号码必须为11位数字手机号格式");
    	System.out.println(validate("/uums/a", "/uums/*"));
    	System.out.println("/static/a/a/a/".contains("/a"));
    }
}
