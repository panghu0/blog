package cn.panghu.blog.common.exception;


import cn.panghu.blog.common.enums.EnumInterface;

public class LoginException extends IllegalArgumentException{

	private static final long serialVersionUID = -591186551288279221L;

	protected EnumInterface errorCode;

    public LoginException(EnumInterface errorCode) {
        super(formatMsg(errorCode));
        this.errorCode = errorCode;
    }

    public EnumInterface getErrorCode() {
        return this.errorCode;
    }

    public final static String formatMsg(EnumInterface errorCode) {
        return String.format("%s:%s", errorCode.getCode(), errorCode.getMessage());
    }
}
