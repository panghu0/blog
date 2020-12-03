package cn.panghu.blog.service;

import cn.panghu.blog.common.constant.GlobalConstant;
import cn.panghu.blog.common.enums.GlobalResultEnum;
import cn.panghu.blog.common.exception.ParamsException;
import cn.panghu.blog.common.pojo.FileData;
import cn.panghu.blog.common.utils.FileUtil;
import cn.panghu.blog.common.utils.ParameterUtils;
import cn.panghu.blog.base.dao.PhotoDao;
import cn.panghu.blog.base.pojo.Photo;
import cn.panghu.blog.dao.PhotoExpDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 *图片信息表
 */
@Service("photoService")
public class PhotoServiceImpl implements PhotoService {

	@Autowired
	private PhotoDao photoDao;
	@Autowired
	private PhotoExpDao photoExpDao;

	@Override
	public List<Photo> list(Integer type, Photo photo) {
		photo.setType(type);
		return photoDao.findByPage(photo);
	}

	@Override
	public Photo save(MultipartFile file, Integer type) {

	    Photo photo = new Photo();

        if(ParameterUtils.validateFileIsNotNull(file)){
        	FileData fileData = new FileData();
        	switch (type) {
				case 0:  //头像
					break;
				case 1:  //封面图
					fileData = FileUtil.uploadFile(file,GlobalConstant.ARTICLE_COVER);
					photo.setUsed(0);
					//break;
				case 2:  //内容图
					fileData = FileUtil.uploadFile(file,GlobalConstant.ARTICLE_CONTENT);
					photo.setUsed(1);
					break;
				case 3:  //光影
					photo.setUsed(0);
					break;
			}
			photo.setFileName(fileData.getName());
        	photo.setType(type);
        	photo.setUrl(fileData.getFilePath());
        }


		photoDao.save(photo);
		return photo;
	}

	
	@Override
	@Transactional
	public void deleteById(Integer id) {

		Photo photo = photoDao.findById(id);
		FileUtil.delFileData(photo.getUrl());
		photoDao.deleteById(id);
	}

	@Override
	@Transactional
	public void batchDel(List<Integer> idList) {

		Photo photo = new Photo();
		if (!(idList.size() > 0)) {
			throw new ParamsException(GlobalResultEnum.INVALID_PARAM);
		}

		for(Integer id : idList) {
			photo = photoDao.findById(id);
			if(photo != null) {
				FileUtil.delFileData(photo.getUrl());
			}
		}
		photoDao.batchDelete(idList);
	}
}
