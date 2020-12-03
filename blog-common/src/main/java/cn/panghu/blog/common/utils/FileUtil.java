package cn.panghu.blog.common.utils;

import cn.panghu.blog.common.constant.GlobalConstant;
import cn.panghu.blog.common.enums.EnumInterface;
import cn.panghu.blog.common.enums.GlobalResultEnum;
import cn.panghu.blog.common.exception.BusinessException;
import cn.panghu.blog.common.pojo.FileData;
import cn.panghu.blog.common.properties.CommonProperties;
import cn.panghu.blog.common.spring.SpringContextHolder;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Base64Utils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigInteger;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Date;

public class FileUtil {

	private static Logger logger = LoggerFactory.getLogger(FileUtil.class);
	private static CommonProperties commonProperties;


	/**
	 * 根据文件名称,获取真实的文件名称(不包含后缀)
	 * @param fileName 文件名称
	 * @return
	 */
	public static String getRealFileName(String fileName){
		if(StringUtils.isNotBlank(fileName)){
    		if(fileName.contains(GlobalConstant.Symbol.SPOT)){
    			return fileName.substring(0, fileName.lastIndexOf(GlobalConstant.Symbol.SPOT));
    		}else{
    			return fileName;
    		}
		}
		return null;
	}

	/**
	 * 根据文件后缀  包含.
	 * @param fileInfo 文件名称或者路径
	 * @return
	 */
	public static String getFilePrefix(String fileInfo){
		if(StringUtils.isNotBlank(fileInfo)){
    		if(fileInfo.contains(GlobalConstant.Symbol.SPOT)){
    			return fileInfo.substring(fileInfo.lastIndexOf(GlobalConstant.Symbol.SPOT));
    		}
		}
		return null;
	}

	/**
	 * 根据文件后缀
	 * @param fileInfo 文件名称或者路径
	 * @return
	 */
	public static String getFilePrefixSimple(String fileInfo){
		if(StringUtils.isNotBlank(fileInfo)){
    		if(fileInfo.contains(GlobalConstant.Symbol.SPOT)){
    			return fileInfo.substring(fileInfo.lastIndexOf(GlobalConstant.Symbol.SPOT) + 1);
    		}
		}
		return null;
	}

	/**
	 * 根据文件路径,获取文件名称
	 * @param
	 * @return
	 */
	public static String getFileInfo(String fileUrl){
		if(StringUtils.isNotBlank(fileUrl)){
			File tempFile = new File(fileUrl.trim());
			if(tempFile != null){
				return tempFile.getName();
			}
		}
		return null;
	}

	/**
	 * 根据文件路径计算MD5sum
	 * @param path 文件完整路径路径
	 * @return
	 */
	public static String getMd5(String path){
		try (InputStream in = new FileInputStream(path)){
			return DigestUtils.md5Hex(in);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getErrorStack(e));
		}
		return null;
	}

	/**
	 * 检查文件是否存在  抛出异常
	 * @param path
	 */
	public static void checkIsExisted(String path){
		if(StringUtils.isBlank(path)){
			throw new BusinessException(GlobalResultEnum.INVALID_PARAM);
		}
	}

	/**
	 * 检查文件是否存在
	 * @param path  完整路径
	 */
	public static Boolean checkExisted(String path){
		if(StringUtils.isBlank(path)){
			return Boolean.FALSE;
		}
		File file = new File(path);
		if(file == null || !file.exists()){
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}

	public static Integer getFiles(String path){
		File file = new File(path);
		return file.list().length;
	}

	/**
	 * 根据上传文件数据计算MD5sum
	 * @param file
	 * @return
	 */
	public static String getMd5(MultipartFile file) {
        try {
            byte[] uploadBytes = file.getBytes();
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] digest = md5.digest(uploadBytes);
            String hashString = new BigInteger(1, digest).toString(16);
            return hashString;
        } catch (Exception e) {
        	logger.error(ExceptionUtils.getErrorStack(e));
        }
        return null;
    }

	public static void fileWrite(String filePath,String text){
		mkdirs(filePath);
		try(Writer writer = new FileWriter(filePath,true)){
			writer.write(text);
			writer.flush();
		} catch (IOException e) {
			logger.error(ExceptionUtils.getErrorStack(e));
		}
	}

	public static String readtFile(String filePath) {
		try {
			StringBuffer sb = new StringBuffer();
			Files.lines(Paths.get(filePath)).forEach(line -> {
				sb.append(line);
			});
			return sb.toString();
		} catch (IOException e) {
			logger.error(ExceptionUtils.getErrorStack(e));
		}
		return null;
    }

	/**
	 * 使用apacher comon进行文件复制
	 * @param sourceFilePath 源文件完成路径
	 * @param copyPath 目的文件完整路径
	 * @return
	 */
	public static Boolean copyFileUsingApacheCommonsIO(String sourceFilePath, String copyPath){
		mkdirs(copyPath);

		File source = new File(sourceFilePath);
		File dest = new File(copyPath);

	    try {
			FileUtils.copyFile(source, dest);
			return Boolean.TRUE;
		} catch (IOException e) {
			logger.error(ExceptionUtils.getErrorStack(e));
			return Boolean.FALSE;
		}
	}

	/**
	 * 使用file channel进行文件复制
	 * @param sourceFilePath 源文件完成路径
	 * @param copyPath 目的文件完整路径
	 * @return
	 */
	public static Boolean copyFileUsingFileChannels(String sourceFilePath, String copyPath) {
		mkdirs(copyPath);

		File source = new File(sourceFilePath);
		File dest = new File(copyPath);
	    try(
	    		FileChannel inputChannel = new FileInputStream(source).getChannel();
	    		FileChannel outputChannel = new FileOutputStream(dest).getChannel();){

	        outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
	        return Boolean.TRUE;

	    } catch (IOException e) {
			logger.error(ExceptionUtils.getErrorStack(e));
			return Boolean.FALSE;
		}
	}

	/**
	 * 文件复制
	 * @param sourceFilePath
	 * @param copyPath
	 * @return
	 * @throws Exception
	 */
	public static void copyFile(String sourceFilePath, String copyPath){
		String sourceFileFullPath = genFileFullPath(sourceFilePath.replace("\\", "/"));
		String copyFullPath = genFileFullPath(copyPath.replace("\\", "/"));

        File source_file = new File(sourceFileFullPath);
        File copy_file = new File(copyFullPath);

        if (!source_file.exists()) {
            throw new BusinessException("文件复制失败：源文件(" + source_file + ") 不存在");
        }
        if (copy_file.isDirectory()) {
            throw new BusinessException("文件复制失败：复制路径(" + copy_file + ") 错误");
        }

        File parent = copy_file.getParentFile();
        // 创建复制路径
        if(parent.exists()) {
        	parent.delete();
        }
        parent.mkdirs();

        FileInputStream fis = null;
        FileOutputStream fos = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try{
        	// 创建复制文件
            if (!copy_file.exists()) {
                copy_file.createNewFile();
            }
        	// BufferedStream缓冲字节流
            fis = new FileInputStream(source_file);
            fos = new FileOutputStream(copy_file);
            bis = new BufferedInputStream(fis);
            bos = new BufferedOutputStream(fos);

            byte[] KB = new byte[1024];
            int index;
            while ((index = bis.read(KB)) != -1) {
                bos.write(KB, 0, index);
            }
        }catch(Exception e){
        	logger.error(ExceptionUtils.getErrorStack(e));
        	throw new BusinessException(GlobalResultEnum.INTERNAL_SERVER_ERROR);
        }finally {
        	try{
        		if(bos != null){
            		bos.close();
            	}
        		if(bis != null){
        			bis.close();
            	}
        		if(fos != null){
        			fos.close();
            	}
        		if(fis != null){
        			fis.close();
            	}
        	}catch(Exception e){
        		logger.error(ExceptionUtils.getErrorStack(e));
        	}
		}
    }

	public static FileData getBase64String(String path){
		if(StringUtils.isBlank(path)){
			return null;
		}

		File file = new File(genFileFullPath(path));
		FileInputStream fis=null;
		try{

			fis = new FileInputStream(file);
			byte [] buffers = new byte[fis.available()];
			fis.read(buffers, 0,buffers.length);
			BufferedImage image = ImageIO.read(file);
			int width = image.getWidth();
			int height = image.getHeight();
			if(2.54*width/72>18){//当长度大于18时
				height = (int)(height*(512.0/width));
				width = 512;
			}
			return new FileData(Base64Utils.encodeToString(buffers), width, height);
		}catch(Exception e){
			logger.error(ExceptionUtils.getErrorStack(e));
			throw new BusinessException(GlobalResultEnum.NOT_FOUND);
		}finally{
			if(fis!=null){
				try {
					fis.close();
				} catch (IOException e) {
					logger.error(ExceptionUtils.getErrorStack(e));
				}
			}
		}
	}

	/**
	 * 根据文件判断文件是否存在，是则删除文件
	 * @param filePath 相对于根目录的文件路径
	 */
	public static void delFileData(String filePath){
		if(StringUtils.isNotBlank(filePath)){
			String fileFullPath = String.format("%s%s",
					getCommonProperties().getFilePathPrefix(), filePath);
			File file = new File(fileFullPath);
			if(file.exists()){
				file.delete();
			}
		}
	}

	/**
	 *
	 * @param fileFullPath 完整路径
	 */
	public static void delFileDataByFull(String fileFullPath){
		if(StringUtils.isNotBlank(fileFullPath)){
			File file = new File(fileFullPath);
			if(file.exists()){
				file.delete();
			}
		}
	}

	/**
	 * 根据文件路径生成文件完整路径
	 * @param filePath
	 * @return
	 */
	public static String genFileFullPath(String filePath){
		if(StringUtils.isBlank(filePath)){
			throw new BusinessException(GlobalResultEnum.NOT_FOUND);
		}

		String fileRoot = getCommonProperties().getFilePathPrefix();
		if(filePath.startsWith(fileRoot)){
			return filePath;
		}
		return String.format("%s%s",
				fileRoot,filePath);
	}

	/**
	 * 创建文件夹路径
	 * @param filePath
	 */
	public static void mkdirs(String filePath) {
		File file = new File(filePath);
		mkdirs(file);
	}

	/**
	 * 创建文件夹路径
	 * @param file
	 */
	public static void mkdirs(File file) {
		if (!file.exists()) {
			if (file.isDirectory()) {
				file.mkdirs();
			} else {
				file.getParentFile().mkdirs();
			}
		}
	}

	/**
	 * 展示文件下的所有信息
	 * @param path
	 */
	public static void showAllFiles(String path) {
		File file = new File(path);
		if (file.exists()) {
			if (file.isDirectory()) {
				for (File f : file.listFiles()) {
					showAllFiles(f.getAbsolutePath());
				}
			} else {
				logger.info(file.getAbsolutePath());
			}
		}
	}

	/**
	 * 校验文件是否存在
	 * @param filePath
	 */
	public static void checkFileExists(String filePath){
		if(StringUtils.isBlank(filePath)){
			throw new BusinessException(GlobalResultEnum.INVALID_PARAM);
		}

		File file = new File(genFileFullPath(filePath));
		if(file == null || !file.exists()){
			throw new BusinessException(GlobalResultEnum.NOT_FOUND,"对应文件不存在");
		}
	}

	public static ResponseEntity<byte[]> download(String fileName, String filePath) {
		checkFileExists(filePath);

		String fileFullPath = genFileFullPath(filePath);
		try {
			return download(fileName, FileUtils.readFileToByteArray(new File(fileFullPath)));
		} catch (IOException e) {
			logger.error(ExceptionUtils.getErrorStack(e));
		}
		return null;
	}

	public static ResponseEntity<byte[]> download(String fileName, File file) throws IOException {
		return download(fileName, FileUtils.readFileToByteArray(file));
	}

	public static ResponseEntity<byte[]> download(String fileName, byte[] bs) throws IOException {
		String dfileName = new String(fileName.getBytes("gb2312"), "iso8859-1");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentDispositionFormData("attachment", dfileName);
		return new ResponseEntity<byte[]>(bs, headers, HttpStatus.CREATED);
	}

	/**
	 *
	 * @param mFile
	 * @param fileEnum
	 * @return
	 */
	public static FileData uploadFile(MultipartFile mFile, EnumInterface fileEnum){
		return uploadFile(mFile, fileEnum.getCode());
	}

	/**
	 * 保存指定的内容到文件上
	 * @param content 文件内容
	 * @param fileEnum 文件存放路径前缀
	 * @param fileName 文件名称
	 * @return
	 */
	public static FileData uploadFile(String content, EnumInterface fileEnum, String fileName){
		if (StringUtils.isBlank(content) || StringUtils.isBlank(fileName)) {
			return null;
		}

		String uuid = UUIDUtils.getUUID();

		FileData fileData = null;

		String filePath = String.format("%s%s", fileEnum.getCode(),DateFormateUtils.toYMD(new Date().getTime()));
		String fileFullPath = String.format("%s%s",
				getCommonProperties().getFilePathPrefix(),filePath);
		logger.debug("file full path is :" + fileFullPath);
		//保证文件路径
		File file = new File(fileFullPath);
		if (!file.exists()){
			file.mkdirs();
		}

		File targetFile = new File(fileFullPath, fileName);
        try {
        	byte[] fileBytes = content.getBytes("UTF-8");
            FileUtils.writeByteArrayToFile(targetFile, fileBytes);

            fileData = new FileData();
			fileData.setCode(uuid);
			fileData.setName(fileName);
			fileData.setFilePath(String.format("%s/%s", filePath, fileName));
        } catch (IOException e) {
        	logger.error(ExceptionUtils.getErrorStack(e));
        }

        return fileData;
	}

	/**
	 * 把base的图片文件保存成文件
	 * @param base64Str 图片对应的base编码文本
	 * @param fileEnum 文件前缀
	 * @return
	 */
	public static FileData uploadFile(String base64Str, EnumInterface fileEnum){
		if (StringUtils.isBlank(base64Str)) {
			return null;
		}

		String uuid = UUIDUtils.getUUID();
		String fileName = uuid;
		if(base64Str.indexOf("data:image/png;") != -1) {
            base64Str = base64Str.replace("data:image/png;base64,", "");
            fileName += ".png";
        } else if (base64Str.indexOf("data:image/jpeg;") != -1) {
            base64Str = base64Str.replace("data:image/jpeg;base64,", "");
            fileName += ".jpeg";
        } else{
        	return null;
        }

		FileData fileData = null;

		String filePath = String.format("%s%s", fileEnum.getCode(),DateFormateUtils.toYMD(new Date().getTime()));
		String fileFullPath = String.format("%s%s",
				getCommonProperties().getFilePathPrefix(),filePath);
		logger.debug("file full path is :" + fileFullPath);
		//保证文件路径
		File file = new File(fileFullPath);
		if (!file.exists()){
			file.mkdirs();
		}

		File targetFile = new File(fileFullPath, fileName);
        byte[] fileBytes = Base64.getDecoder().decode(base64Str);
        try {
            FileUtils.writeByteArrayToFile(targetFile, fileBytes);

            fileData = new FileData();
			fileData.setCode(uuid);
			fileData.setName(fileName);
			fileData.setFilePath(String.format("%s/%s", filePath, fileName));
        } catch (IOException e) {
        	logger.error(ExceptionUtils.getErrorStack(e));
        }

        return fileData;
	}

	/**
	 *
	 * @param mFile 文件信息
	 * @param filePrefixPath 文件存放前缀
	 * @return
	 */
	public static FileData uploadFile(MultipartFile mFile, String filePrefixPath) {
		if (mFile == null || mFile.isEmpty()) {
            return null;
        }

        String fileName = mFile.getOriginalFilename();
        String ext= null;
        if(fileName.contains(".")){
            ext = fileName.substring(fileName.lastIndexOf("."));
        }else{
            ext = "";
        }

        String uuid = UUIDUtils.getUUID();
        String nfileName = uuid + ext;

        FileData fileData = null;

		byte[] buffer = new byte[1024];
		FileOutputStream fos = null;
		InputStream is = null;
		try {
			is = mFile.getInputStream();

			String filePath = String.format("%s%s", filePrefixPath,DateFormateUtils.toYMD(new Date().getTime()));
			String fileFullPath = genFileFullPath(filePath);
			logger.debug("file full path is :" + fileFullPath);

			File file = new File(fileFullPath);
			if (!file.exists()){
				file.mkdirs();
			}

			File targetFile = new File(fileFullPath, nfileName);
			fos = new FileOutputStream(targetFile);
			int n = -1;
			while ((n = is.read(buffer, 0, buffer.length)) != -1) {
				fos.write(buffer, 0, n);
			}

			logger.debug("upload file success...");

			fileData = new FileData();
			fileData.setCode(uuid);
			fileData.setName(fileName);
			fileData.setFilePath(String.format("%s/%s", filePath, nfileName));

		} catch (FileNotFoundException fnfe) {
			logger.error(ExceptionUtils.getErrorStack(fnfe));
		} catch (IOException ioe) {
			logger.error(ExceptionUtils.getErrorStack(ioe));
		} catch (Exception e) {
			logger.error(ExceptionUtils.getErrorStack(e));
		} finally {
			if (fos != null) {
				try {
					fos.close();
					fos = null;
				} catch (IOException e) {
					logger.error(ExceptionUtils.getErrorStack(e));
				}
			}
			if (is != null) {
				try {
					is.close();
					is = null;
				} catch (IOException e) {
					logger.error(ExceptionUtils.getErrorStack(e));
				}
			}
		}

		return fileData;
	}

	public static void pushToWeb(
			HttpServletResponse response,
			HttpServletRequest request,
			String filePath,
			String fileName,
			String contentType){
		if(StringUtils.isEmpty(contentType)) {
			response.setContentType("multipart/form-data");
		}

        response.setHeader("Content-Disposition", "attachment;fileName=" + UserAgentUtils.genName(request, fileName));

        InputStream inputStream = null;
        try {
	        inputStream = new FileInputStream(FileUtil.genFileFullPath(
	        		filePath
	        		));
	        OutputStream os = response.getOutputStream();
	        byte[] b = new byte[1024];
	        int length;
	        while ((length = inputStream.read(b)) > 0) {
	            os.write(b, 0, length);
	        }
	        inputStream.close();
	    } catch (FileNotFoundException e) {
	        logger.error("文件找不到.", e);
	    } catch (IOException e) {
	        logger.error(ExceptionUtils.getErrorStack(e));
	    } finally {
	        if (inputStream != null) {
	            try {
	                inputStream.close();
	            } catch (IOException e) {
	            	logger.error(ExceptionUtils.getErrorStack(e));
	            }
	        }
	    }
	}

	private static CommonProperties getCommonProperties(){
		if(commonProperties == null){
			commonProperties = SpringContextHolder.getBean("commonProperties");
		}
		return commonProperties;
	}

	public static FileData uploadFileAllowEmpty(MultipartFile mFile, EnumInterface fileEnum) {

		String filePrefixPath = fileEnum.getCode();

		String fileName = mFile.getOriginalFilename();
        String ext= null;
        if(fileName.contains(".")){
            ext = fileName.substring(fileName.lastIndexOf("."));
        }else{
            ext = "";
        }

        String uuid = UUIDUtils.getUUID();
        String nfileName = uuid + ext;

        FileData fileData = null;

		byte[] buffer = new byte[1024];
		FileOutputStream fos = null;
		InputStream is = null;
		try {
			is = mFile.getInputStream();

			String filePath = String.format("%s%s", filePrefixPath,DateFormateUtils.toYMD(new Date().getTime()));

			String fileFullPath = String.format("%s%s",
					getCommonProperties().getFilePathPrefix(),filePath);
			logger.debug("file full path is :" + fileFullPath);

			File file = new File(fileFullPath);
			if (!file.exists()){
				file.mkdirs();
			}

			File targetFile = new File(fileFullPath, nfileName);
			fos = new FileOutputStream(targetFile);
			int n = -1;
			while ((n = is.read(buffer, 0, buffer.length)) != -1) {
				fos.write(buffer, 0, n);
			}

			logger.debug("upload file success...");

			fileData = new FileData();
			fileData.setCode(uuid);
			fileData.setName(fileName);
			fileData.setFilePath(String.format("%s/%s", filePath, nfileName));

		} catch (FileNotFoundException fnfe) {
			logger.error(ExceptionUtils.getErrorStack(fnfe));
		} catch (IOException ioe) {
			logger.error(ExceptionUtils.getErrorStack(ioe));
		} catch (Exception e) {
			logger.error(ExceptionUtils.getErrorStack(e));
		} finally {
			if (fos != null) {
				try {
					fos.close();
					fos = null;
				} catch (IOException e) {
					logger.error(ExceptionUtils.getErrorStack(e));
				}
			}
			if (is != null) {
				try {
					is.close();
					is = null;
				} catch (IOException e) {
					logger.error(ExceptionUtils.getErrorStack(e));
				}
			}
		}

		return fileData;
	}

}
