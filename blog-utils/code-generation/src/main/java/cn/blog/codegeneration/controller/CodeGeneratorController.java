package com.zhongrui.codegeneration.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhongrui.codegeneration.domain.SettingObject;
import com.zhongrui.codegeneration.service.GeneratorHelperService;

/**
 * 代码生成器
 * @author pang hu
 * @date 2020/10/18 12:21
 */
@Controller
@RequestMapping("/generate")
public class CodeGeneratorController {
	
	private static Logger logger = LoggerFactory.getLogger(CodeGeneratorController.class);

	@Autowired
	private GeneratorHelperService generatorHelperService;
	@Value("${generator.project.path}")
	private String localProjectPath;

	/**
	 * 设置公共参数
	 * 
	 * @param projectPath
	 * @param packagePath
	 * @param templetePath
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/setProperty")
	public void setProperty(@ModelAttribute(value = "settingObject") SettingObject settingObject, ModelMap model,
			HttpServletResponse response) throws IOException {
//		CommonConstant.propertyMap.put("packagePath", settingObject.getPackagePath());
		response.sendRedirect("/showtable");
	}

	/**
	 * 生成某个库下所有表对应的模板代码 小心使用，避免覆盖现有代码
	 * 
	 * @param dbname
	 *            数据库名称
	 * @return
	 */
	@RequestMapping("/tables")
	public String tables(String dbname) {
		boolean success = generatorHelperService.allTables(dbname);
		return success ? "success" : "fail";
	}

	/**
	 * 生成某个库下某个表对应的模板代码
	 * 
	 * @param dbname
	 *            数据库名称
	 * @param tableName
	 *            表名称
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/table", method = RequestMethod.POST)
	public void table(@RequestParam(value = "choose") List<String> tableNames,
			@RequestParam(value = "schemaName") String schemaName, HttpServletResponse response) {
		String zipFilePath = "";
		//清空模板文件夹中已生成的文件
		if(!deleteDir(localProjectPath + "codetemplate/")) {
			logger.error("清空模板文件夹中已生成的文件");
		}
		for (String tableName : tableNames) {
			zipFilePath = generatorHelperService.oneTable(tableName, schemaName);
		}
		File zipFile = new File(zipFilePath);
		downloadFile(zipFile, response, true);
	}

	private static void downloadFile(File file, HttpServletResponse response, boolean isDelete) {
		try {
			// 以流的形式下载文件。
			BufferedInputStream fis = new BufferedInputStream(new FileInputStream(file.getPath()));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			// 清空response
			response.reset();
			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition",
					"attachment;filename=" + new String(file.getName().getBytes("UTF-8"), "ISO-8859-1"));
			toClient.write(buffer);
			toClient.flush();
			toClient.close();
			if (isDelete) {
				file.delete(); // 是否将生成的服务器端文件删除
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 清空模板文件夹中已有的文件
	 * @param path
	 * @return
	 */
	private boolean deleteDir(String path) {
		File file = new File(path);
		logger.info("模板文件夹路径：" + path);
		if(!file.exists()) {
			logger.error("The dir are not exists!");
		}
		
		//取得当前目录下所有文件和文件夹
		String[] content = file.list();
		if (null!=content){//如果localProjectPath + "codetemplate/" 这个文件夹不存在，就会报空指针异常，加上判断就能自动生成路径
			for (String name : content) {
				File temp = new File(path, name);
				if(temp.isDirectory()){//判断是否是目录
					deleteDir(temp.getAbsolutePath());//递归调用删除目录中的内容
					//temp.delete();//删除空目录
				} else {
					if(!temp.delete()) {
						logger.error("Failed to delete " + name);
					}
				}
			}
		}

		
		return true;
	}
}
