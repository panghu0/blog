package cn.panghu.blog.common.result;

import cn.panghu.blog.common.enums.EnumInterface;
import cn.panghu.blog.common.enums.GlobalResultEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "接口返回结构", description = "api 接口返回体")
public class ResultBody<T> implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 4685910902965274218L;

	@ApiModelProperty(name = "code",value = "响应代码",dataType = "String",required = true,position=0,notes="0000-success 9999-fail") 
    private String code;

	@ApiModelProperty(name = "msg",value = "响应消息",dataType = "String",position=1,notes="以编码做表示") 
    private String msg;

	@ApiModelProperty(name = "data",value = "响应数据",dataType = "T",position=2,notes="以每个接口定义的数据做返回，只在code为0000下才有效") 
    private T data;
    
    public ResultBody(){}

    public ResultBody(EnumInterface resultInfo) {
        this.code = resultInfo.getCode();
        this.msg = resultInfo.getMessage();
    }
    
    public ResultBody(EnumInterface resultInfo, String msg) {
        this.code = resultInfo.getCode();
        this.msg = msg;
    }

    public ResultBody(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    
    public ResultBody(T data) {
    	this.code = GlobalResultEnum.SUCCESS.getCode();
        this.msg = GlobalResultEnum.SUCCESS.getMessage();
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}