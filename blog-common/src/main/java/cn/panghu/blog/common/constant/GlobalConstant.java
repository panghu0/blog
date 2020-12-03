package cn.panghu.blog.common.constant;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author pang hu
 * @date 2020/5/16 12:26
 */
public class GlobalConstant {
    public static final String DEFAULT_VERSION_NAME = "1.0.0";


    public static final String SESSION_USER_KEY = "user_token";
    public static final String SESSION_ID = "session_id";

    public static final String TOKEN_USER_ID = "userId";
    public static final String TOKEN_USER_NAME = "username";
    public static final String TOKEN_USER_TYPE = "userType";

    //男
    public static final Integer SEX_MALE = 1;
    //女
    public static final Integer SEX_FEMALE = 2;

    //
    public static final Integer Y = 1;
    public static final Integer N = 0;

    /***
     * 文件上传路径
     */
    //文章图片根路径
    public static final String ARTICLE_ROOT = "picture/article/";
    //文章封面图
    public static final String ARTICLE_COVER = "picture/article/cover/";
    //文章内容图
    public static final String ARTICLE_CONTENT = "picture/article/content/";


    //缓存相关
    //常规登录。30分钟有效
    public static final Integer EXPIRE_TIME = 30;
    //记住我时，7天有效
    public static final Integer EXPIRE_TIME_REMEMBERME = 7*24*60;

    public static final String REDIS_USER_TOKEN_KEY_REMEMBERME = "rememberme:";

    /**
     * The constant FILE_MAX_SIZE.
     */
    public static final long FILE_MAX_SIZE = 5 * 1024 * 1024;
    public static final String UNKNOWN = "unknown";

    public static final String X_FORWARDED_FOR = "X-Forwarded-For";
    public static final String X_REAL_IP = "X-Real-IP";
    public static final String PROXY_CLIENT_IP = "Proxy-Client-IP";
    public static final String WL_PROXY_CLIENT_IP = "WL-Proxy-Client-IP";
    public static final String HTTP_CLIENT_IP = "HTTP_CLIENT_IP";
    public static final String HTTP_X_FORWARDED_FOR = "HTTP_X_FORWARDED_FOR";
    /**
     * 请求头TOKEN
     */
    public static final String X_AUTH_TOKEN = "Authorization";

    public static final String LOCALHOST_IP = "127.0.0.1";
    public static final String LOCALHOST_IP_16 = "0:0:0:0:0:0:0:1";
    public static final int MAX_IP_LENGTH = 15;

    public static final class Number {
        public static final Integer NUM_0 = 2 >> 0;
        public static final Integer NUM_1 = 2 >> 1;
        public static final Integer NUM_2 = 2 >> 2;
        public static final Integer NUM_3 = 2 >> 3;
        public static final Integer NUM_4 = 2 >> 4;
        public static final Integer NUM_5 = 2 >> 5;
        public static final Integer NUM_6 = 2 >> 6;
    }

    /**
     * The class Symbol.
     *
     */
    public static final class Symbol {
        private Symbol() {
        }

        public static final String EMPTY = "";
        /**
         * The constant COMMA.
         */
        public static final String COMMA = ",";
        public static final String SPOT = ".";
        /**
         * The constant UNDER_LINE.
         */
        public final static String UNDER_LINE = "_";
        /**
         * The constant PER_CENT.
         */
        public final static String PER_CENT = "%";
        /**
         * The constant AT.
         */
        public final static String AT = "@";
        /**
         * The constant PIPE.
         */
        public final static String PIPE = "||";
        public final static String SHORT_LINE = "-";
        public final static String SPACE = " ";
        public static final String SLASH = "/";
        public static final String MH = ":";

        public static final String UNKNOW = "unknown";
    }


    /**
     * 图片压缩高度和宽度
     */
    public static final int IMAGE_WIDTH = 1920;
    /**
     * The constant IMAGE_HEIGHT.
     */
    public static final int IMAGE_HEIGHT = 1280;
}
