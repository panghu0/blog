package cn.panghu.blog.common.exception;

import cn.panghu.blog.common.enums.EnumInterface;

public class ArgumentException extends IllegalArgumentException {

	private static final long serialVersionUID = -9222068231575806834L;
	
	protected EnumInterface errorCode;

    public ArgumentException(EnumInterface errorCode) {
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
