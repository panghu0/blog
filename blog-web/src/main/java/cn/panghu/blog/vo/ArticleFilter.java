package cn.panghu.blog.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author pang hu
 * @date 2020/10/24 10:07
 */
@ApiModel(value = "ArticleFilter", description = "文章查询过滤条件")
public class ArticleFilter {

    @ApiModelProperty(value="文章标题")
    private String title;
    @ApiModelProperty(value="文章分类")
    private Integer categoryId;
    @ApiModelProperty(value="创建日期")
    private Date startDate;
    @ApiModelProperty(value="修改日期")
    private Date endDate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
