package cn.panghu.blog.service;

import cn.panghu.blog.base.pojo.Photo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 *图片信息表
 */
public interface PhotoService {

	/**
	 * 新增
	 */
	Photo save(MultipartFile file, Integer type);

	
	/**
	 * 根据ID删除
	 */
	void deleteById(Integer id);

	/***
	 * 获取照片
	 */
	List<Photo> list(Integer type, Photo photo);

	/***
	 * 批量删除
	 */
    void batchDel(List<Integer> idList);
}
