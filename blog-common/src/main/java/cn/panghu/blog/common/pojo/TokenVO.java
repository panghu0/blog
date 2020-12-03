package cn.panghu.blog.common.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author pang hu
 * @date 2020/10/18 13:19
 */
@ApiModel(value = "TokenVO", description = "用户TOKEN信息")
public class TokenVO {

    private static final long serialVersionUID = -30297418139905434L;
    @ApiModelProperty(value="用户标示")
    private String code;
    @ApiModelProperty(value="token信息")
    private String token;
    @ApiModelProperty(value="1-管理员  2-普通用户")
    private Integer type;

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public Integer getType() {
        return type;
    }
    public void setType(Integer type) {
        this.type = type;
    }
}
