package cn.panghu.blog.common.pojo;

/**
 * @author pang hu
 * @date 2020/10/24 13:18
 */
public class FileData {

    private String name;
    private String code;
    private String filePath;
    private String fullFilePath;
    private Integer type;
    private String suffix;
    private String checkCode;

    private String base64String;
    private Integer width;
    private Integer height;

    public FileData(){}

    public FileData(String base64String,Integer width,Integer height){
        this.base64String=base64String;
        this.width=width;
        this.height=height;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getFilePath() {
        return filePath;
    }
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    public String getFullFilePath() {
        return fullFilePath;
    }
    public void setFullFilePath(String fullFilePath) {
        this.fullFilePath = fullFilePath;
    }
    public Integer getType() {
        return type;
    }
    public void setType(Integer type) {
        this.type = type;
    }
    public String getSuffix() {
        return suffix;
    }
    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
    public String getCheckCode() {
        return checkCode;
    }
    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }
    public String getBase64String() {
        return base64String;
    }
    public void setBase64String(String base64String) {
        this.base64String = base64String;
    }
    public Integer getWidth() {
        return width;
    }
    public void setWidth(Integer width) {
        this.width = width;
    }
    public Integer getHeight() {
        return height;
    }
    public void setHeight(Integer height) {
        this.height = height;
    }

}
