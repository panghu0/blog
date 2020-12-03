package cn.panghu.blog.common.enums;

/***
 * @Description 全局接口异常枚举定义
 * @author pang hu
 * @date 2020/10/17 23:41
 */
public enum GlobalResultEnum implements EnumInterface {

    SUCCESS("0000", "Success"),

    FAILURE("9999", "Failure"),

    /**
     * 三位数的编码为异常整合httpcode编码
     */
    BAD_REQUEST("400", "请求异常,请联系管理员."),
    UNAUTHORIZED("401", "未授权,请联系管理员."),
    FORBIDDEN("403", "禁止访问,请联系管理员."),
    NOT_FOUND("404", "资源没找到,请联系管理员."),
    METHOD_NOT_ALLOWED("405", "方法不允许,请联系管理员."),
    INTERNAL_SERVER_ERROR("500", "未知错误,请联系管理员."),
    CONNECT_ERROR("600", "请检查网络是否正常,请联系管理员."),
    FILE_TYPE_ERROR("700", "上传的格式不正确"),
    FILE_LOAD_ERROR("701", "文件下载失败"),
    FILE_UN_COMPRESS_ERROR("702", "文件解压失败"),
    SUPDOG_AUTH_ERROR("800", "请联系管理员,检查系统授权状况"),
    PREVIEW_NOT_FOUND_ERROR("900", "未找到实验文档,请联系管理员"),


    /**
     * 用户模块编码(6xxx)
     */
    UUMS_ACCOUNT_PWD_ERROR("6000", "用户名密码错误"),
    UUMS_CACHE_NOT_FOUND("6100", "缓存模块未启用"),
    UUMS_CACHE_ERROR("6101", "缓存模块配置出错"),



    /**
     * 自定义异常...暂不增加,后续整合
     */
    DATA_NOT_FOUND("2404", "数据不存在"),
    DATA_ERROR("2400", "数据出错"),
    INVALID_PARAM("1000", "参数错误"),
    UNSUPPORT_IMAGE_TYPE("1100", "不支持的图片格式"),
    UNSUPPORT_STORE_PATH("1110", "找不到文件"),
    DUPLICATE_NAME("1111", "名称已存在");


    GlobalResultEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    private final String code;

    private final String message;

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
