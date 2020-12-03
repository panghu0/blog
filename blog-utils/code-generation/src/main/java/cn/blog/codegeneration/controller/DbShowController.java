package com.zhongrui.codegeneration.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zhongrui.codegeneration.constants.CommonConstant;
import com.zhongrui.codegeneration.dao.GeneratorHelperDao;
import com.zhongrui.codegeneration.vo.TableInfo;

@Controller
public class DbShowController {
	
	private static Logger logger = LoggerFactory.getLogger(DbShowController.class);

	@Autowired
	GeneratorHelperDao generatorHelperDao;
	
	@RequestMapping("/")
	public String index(HttpServletRequest request, ModelMap model) {
		List<String> list = generatorHelperDao.findAllDatabaseSchemas();
		model.put("schemaName", list);
		return "index";
	}

	/**
	 * 获取schema列表
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/index")
	public String getAllSchema(HttpServletRequest request, ModelMap model) {
		List<String> list = generatorHelperDao.findAllDatabaseSchemas();
		model.put("schemaName", list);
		return "index";
	}

	/**
	 * 获取schema下的所有的表
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/showtable")
	public ModelAndView getAllTables(HttpServletRequest request, ModelMap model) {
		List<TableInfo> tableInfos = new ArrayList<>();
		String schemaName = request.getParameter("schemaName");
		if(StringUtils.isBlank(schemaName)) {
			logger.error("schemaName为空");
		}
		CommonConstant.SCHEMA_NAME = schemaName;
		List<String> tableNameList = generatorHelperDao.findAllTablesFromSchema(schemaName);
		for (String str : tableNameList) {
			TableInfo tableInfo = new TableInfo();
			tableInfo.setTableName(str);

			tableInfos.add(tableInfo);
		}
		Collections.sort(tableInfos);
		model.put("tablelist", tableInfos);
		return new ModelAndView("showschema");
	}

}
