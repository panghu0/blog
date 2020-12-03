package cn.panghu.blog.common.exception;


import cn.panghu.blog.common.enums.EnumInterface;

/**
 * 
 * @author Administrator
 *
 */
public class ParamsException extends RuntimeException {

	private static final long serialVersionUID = -6546670537878536741L;
	
	private String exceptionCode;
	
	public ParamsException(EnumInterface errorCodeEnum){
		super(errorCodeEnum.getMessage());
		this.exceptionCode = errorCodeEnum.getCode();
	}
	
	public ParamsException(EnumInterface errorCodeEnum, String msg){
		super(msg);
		this.exceptionCode = errorCodeEnum.getCode();
	}

	public ParamsException(String exceptionCode) {
		this.exceptionCode = exceptionCode;
	}

	public ParamsException(String exceptionCode, String msg) {
		super(msg);
		this.exceptionCode = exceptionCode;
	}

	public String getExceptionCode() {
		return exceptionCode;
	}

	/**
	 * Gets the msg. 如果没有自定义msg，则返回从validatemessages.xml中获取到的msg
	 * 
	 * @return the msg
	 */
	@Override
	public String getMessage() {
		return super.getMessage();
	}

}
