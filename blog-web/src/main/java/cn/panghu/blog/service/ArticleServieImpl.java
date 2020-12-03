package cn.panghu.blog.service;

import cn.panghu.blog.base.dao.*;
import cn.panghu.blog.base.pojo.*;
import cn.panghu.blog.common.enums.GlobalResultEnum;
import cn.panghu.blog.common.exception.ParamsException;
import cn.panghu.blog.common.utils.FileUtil;
import cn.panghu.blog.dao.*;
import cn.panghu.blog.vo.ArticleFilter;
import cn.panghu.blog.vo.ArticleVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author pang hu
 * @date 2020/7/5 20:30
 */
@Service
public class ArticleServieImpl implements ArticleService {

    @Autowired
    ArticleDao articleDao;
    @Autowired
    ArticleExpDao articleExpDao;
    @Autowired
    PhotoDao photoDao;
    @Autowired
    PhotoExpDao photoExpDao;
    @Autowired
    TagExpDao tagExpDao;
    @Autowired
    ArticleTagRelDao articleTagRelDao;
    @Autowired
    ArticlePhotoDao articlePhotoDao;

    @Override
    @Transactional
    public ArticleVO getArticleById(Integer id) {
        ArticleVO articleVO = new ArticleVO();
        Article article = articleDao.findById(id);
        BeanUtils.copyProperties(article, articleVO);

        List<Tag> tags = tagExpDao.findTagsByArticle(id);
        articleVO.setTagList(tags);

        List<Photo> photos = photoExpDao.findByArticleAndType(id, 2);
        articleVO.setPhotoList(photos);
        if(article.getPhotoId() != null)
            articleVO.setCoverPhoto(photoDao.findById(article.getPhotoId()));

        return articleVO;
    }

    @Override
    public List<Article> getPopular() {
        return articleExpDao.getPopular();
    }

    @Override
    public List<HashMap> getCountByMonth() {
        return articleExpDao.getCountByMonth();
    }

    @Override
    public List<HashMap> getCountByDay() {
        return articleExpDao.getCountByDay();
    }

    @Override
    public List<Article> getArticleByDate(Article article) {
        return articleExpDao.findByPage(article);
    }

    @Override
    public PageInfo<Article> getArticleList(ArticleFilter filter, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<Article>(articleExpDao.findByFilter(filter));
    }

    @Override
    @Transactional
    public void save(ArticleVO articleVO) {

        List<Tag> tagList = articleVO.getTagList();
        List<Photo> photoList = articleVO.getPhotoList();
        Article article = new Article();
        BeanUtils.copyProperties(articleVO, article);
        articleDao.save(article);


        if(!CollectionUtils.isEmpty(tagList)) {
            ArticleTagRel articleTagRel = new ArticleTagRel();
            articleTagRel.setArticleId(article.getId());
            for(Tag tag : tagList) {
                articleTagRel.setTagId(tag.getId());
                articleTagRelDao.save(articleTagRel);
            }
        }

        if(!CollectionUtils.isEmpty(photoList)) {
            ArticlePhoto articlePhoto = new ArticlePhoto();
            articlePhoto.setArticleId(article.getId());
            for(Photo photo : photoList) {
                articlePhoto.setPhotoId(photo.getId());
                articlePhotoDao.save(articlePhoto);
            }
        }
    }

    @Override
    @Transactional
    public void update(ArticleVO articleVO) {
        List<Tag> tagList = articleVO.getTagList();
        List<Photo> photoList = articleVO.getPhotoList();
        Article article = new Article();

        //更新文章
        BeanUtils.copyProperties(articleVO, article);
        articleDao.update(article);
        //更新标签关联
        tagExpDao.deleteRelByArticle(article.getId());
        if(!CollectionUtils.isEmpty(tagList)) {
            ArticleTagRel articleTagRel = new ArticleTagRel();
            articleTagRel.setArticleId(article.getId());
            for(Tag tag : tagList) {
                articleTagRel.setTagId(tag.getId());
                articleTagRelDao.save(articleTagRel);
            }
        }

        //更新图片关联
        photoExpDao.deleteRelByArticle(article.getId());
        if(!CollectionUtils.isEmpty(photoList)) {
            ArticlePhoto articlePhoto = new ArticlePhoto();
            articlePhoto.setArticleId(article.getId());
            for(Photo photo : photoList) {
                articlePhoto.setPhotoId(photo.getId());
                articlePhotoDao.save(articlePhoto);
            }
        }
    }


    @Override
    @Transactional
    public void batchDelete(List<Integer> idList) {

        List<Photo> photoList = new ArrayList<>();
        List<ArticleTagRel> articleTagList = new ArrayList<>();
        List<Integer> otherIdList = new ArrayList<>();
        if (!(idList.size() > 0)) {
            throw new ParamsException(GlobalResultEnum.INVALID_PARAM);
        }
        articleDao.batchDelete(idList);


        for(Integer id : idList) {
            //删除文章关联图片
            photoList = photoExpDao.findByArticleAndType(id, 2);
            if(!CollectionUtils.isEmpty(photoList)) {
                otherIdList = photoList.stream().map(Photo::getId).collect(Collectors.toList());

                photoExpDao.deleteRelByArticle(id);
                photoDao.batchDelete(otherIdList);
                for(Photo photo : photoList) {
                    FileUtil.delFileData(photo.getUrl());
                }
            }

            //删除文章关联标签
            ArticleTagRel articleTagRel = new ArticleTagRel();
            articleTagRel.setArticleId(id);
            articleTagList = articleTagRelDao.findByPage(articleTagRel);
            if(!CollectionUtils.isEmpty(articleTagList)) {
                otherIdList = articleTagList.stream().map(ArticleTagRel::getId).collect(Collectors.toList());
                articleTagRelDao.batchDelete(otherIdList);
            }
        }

    }

    @Override
    public void updatePublishState(List<Integer> idList, Integer type) {
        if (!(idList.size() > 0)) {
            throw new ParamsException(GlobalResultEnum.INVALID_PARAM);
        }
        articleExpDao.updatePublishState(idList, type);
    }
}
