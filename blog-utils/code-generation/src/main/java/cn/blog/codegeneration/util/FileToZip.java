package com.zhongrui.codegeneration.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 生成zip
 * @author pang hu
 * @date 2020/10/18
 */
public class FileToZip {
	
	private static Logger logger = LoggerFactory.getLogger(FileToZip.class);

	private FileToZip(){}
	
	/**
	 * 创建ZIP文件
	 * @param sourcePath 文件或文件夹路径
	 * @param zipPath 生成的zip文件存在路径（包括文件名）
	 */
	public static void createZip(String sourcePath, String zipPath) {
		FileOutputStream fos = null;
		ZipOutputStream zos = null;
		try {
			fos = new FileOutputStream(zipPath);
			zos = new ZipOutputStream(fos);
			writeZip(new File(sourcePath), "", zos);
		} catch (FileNotFoundException e) {
			logger.error("创建ZIP文件夹失败", e);
		} finally {
			try {
				if(zos != null) {
					zos.close();
				}
			} catch (IOException e2) {
				logger.error("创建ZIP文件夹失败", e2);
			}
		}
	}
	
	private static void writeZip(File file, String parentPath, ZipOutputStream zos) {
		if(file.exists()) {
			if(file.isDirectory()) {//处理文件夹
				parentPath += file.getName() + File.separator;
				File[] files = file.listFiles();
				if(files.length != 0) {
					for (File f : files) {
						writeZip(f, parentPath, zos);
					}
				} else {//空目录则创建当前目录
					try {
						zos.putNextEntry(new ZipEntry(parentPath));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			} else {
				FileInputStream fis = null;
				try {
					fis = new FileInputStream(file);
					ZipEntry ze = new ZipEntry(parentPath + file.getName());
					zos.putNextEntry(ze);
					byte[] content = new byte[1024];
					int len;
					while((len = fis.read(content)) != -1) {
						zos.write(content, 0, len);
						zos.flush();
					}
				} catch (FileNotFoundException e) {
					logger.error("创建ZIP文件夹失败", e);
				} catch (IOException e) {
					logger.error("创建ZIP文件夹失败", e);
				} finally {
					try {
						if(fis != null) {
							fis.close();
						}
					} catch (IOException e2) {
						logger.error("创建ZIP文件夹失败", e2);
					}
				}
			}
		}
	}
}
