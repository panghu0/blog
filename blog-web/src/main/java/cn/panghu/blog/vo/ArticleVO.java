package cn.panghu.blog.vo;

import cn.panghu.blog.base.pojo.Article;
import cn.panghu.blog.base.pojo.Photo;
import cn.panghu.blog.base.pojo.Tag;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author pang hu
 * @date 2020/10/29 19:45
 */
@ApiModel(value = "ArticleVO", description = "文章综合信息")
public class ArticleVO extends Article {

    @ApiModelProperty(value="标签列表")
    private List<Tag> tagList;
    @ApiModelProperty(value="内容图片列表")
    private List<Photo> photoList;
    @ApiModelProperty(value = "封面图")
    private Photo coverPhoto;

    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }

    public List<Photo> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(List<Photo> photoList) {
        this.photoList = photoList;
    }

    public Photo getCoverPhoto() {
        return coverPhoto;
    }

    public void setCoverPhoto(Photo coverPhoto) {
        this.coverPhoto = coverPhoto;
    }
}
