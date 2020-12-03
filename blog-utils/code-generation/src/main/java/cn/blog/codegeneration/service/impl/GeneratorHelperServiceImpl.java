package com.zhongrui.codegeneration.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.zhongrui.codegeneration.dao.GeneratorHelperDao;
import com.zhongrui.codegeneration.service.GeneratorHelperService;
import com.zhongrui.codegeneration.util.DateUtils;
import com.zhongrui.codegeneration.util.FileToZip;
import com.zhongrui.codegeneration.vo.ColumnInfo;
import com.zhongrui.codegeneration.vo.TableInfo;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

/**
 * @author pang hu
 * @date 2020/10/18 12:21
 */
@Service
public class GeneratorHelperServiceImpl implements GeneratorHelperService {

	private static Logger logger = LoggerFactory.getLogger(GeneratorHelperServiceImpl.class);

	@Autowired
	GeneratorHelperDao generatorHelperDao;

	@Value("${generator.project.path}")
	private String localProjectPath;

	@Value("${generator.templete.path}")
	private String templetePath;
	
	@Value("${generator.package.path}")
	private String packagePath;

//	@Override
//	public boolean setProperty(Map<String, String> map) {
//		CommonConstant.propertyMap.putAll(map);
//		return true;
//	}

	public String oneTable(String tableName, String schemaName) {
		TableInfo tableInfo = generatorHelperDao.findTableInfo(tableName);
		return generateCode(checkTabelInfo(tableInfo, schemaName));
	}

	private String generateCode(TableInfo item) {
		List<ColumnInfo> columnInfos = generatorHelperDao.listColumInfos(item.getTableName());
		String idType = "String";
		columnInfos = checkColumnInfos(columnInfos);
		if ("id".equals(columnInfos.get(0).getColumnName())) {
			if ("int".equals(columnInfos.get(0).getModelType())) {
				idType = "Integer";
			} else if ("long".equals(columnInfos.get(0).getModelType())) {
				idType = "Long";
			}
			item.setPrimaryKey("id");
		}
		item.setIdType(idType);
		
		for(ColumnInfo c : columnInfos){
			if(c.getColumnName().toLowerCase().equals("seq_num")){
				item.setIsOrderBySeqNum(Boolean.TRUE);
			}
			if(c.getColumnName().toLowerCase().equals("gmt_modified")){
				if(!item.getTableName().toLowerCase().equals("tb_professional_direction")){
					item.setIsShowGmtModified(Boolean.TRUE);
				}
			}
		}
		
		createModel(item, columnInfos);
		createOther(item, "dao", columnInfos);
		createOther(item, "service", columnInfos);
		createOther(item, "serviceImpl", columnInfos);
		createOther(item, "mapper", columnInfos);
		
		//生成zip包
		String zipFilePath = localProjectPath + item.getSchemaName() + "-" + DateUtils.getCurrentTime() + ".zip";
		FileToZip.createZip(localProjectPath +  "codetemplate/", zipFilePath);
		return zipFilePath;
	}

	private void createModel(TableInfo item, List<ColumnInfo> columnInfos) {
		String path = localProjectPath;
		String dir = String.format("%sbo", path + "codetemplate/");
		logger.info("dir-path:" + dir);
		File file = new File(dir);
		if (!file.exists()) {
			file.mkdirs();
		}
		
		for(ColumnInfo columnInfo : columnInfos){
			if(columnInfo.getColumnName().toLowerCase().contains("gmt") ||
					columnInfo.getColumnName().toLowerCase().equals("status")){
				columnInfo.setHidden(true);
				break;
			}
		}
		
		String codeTempletePath = String.format("%s%s", localProjectPath, templetePath);
		String filePath = String.format("%s/%s.java", dir, item.getModelName());
		logger.info("filePath:" + filePath);
		Map<String, Object> data = Maps.newHashMap();
		data.put("proList", columnInfos);
		data.put("modelParam", String.format("%s%s", item.getModelName().substring(0, 1).toLowerCase(),
				item.getModelName().substring(1)));
		data.put("modellower", item.getModelName().toLowerCase());
		data.put("item", item);
		data.put("packagePath", packagePath);
		data.put("importList", genImportList(columnInfos));
		createTempleteFile(filePath, codeTempletePath, "domain.flt", data);

	}
	
	private List<String> genImportList(List<ColumnInfo> columnInfos){
		List<String> importList = new ArrayList<>();
		//标记BigDecimal是否引用
		Boolean flag1 = Boolean.FALSE;
		//标记Date是否引用
		Boolean flag2 = Boolean.FALSE;
		
		for(ColumnInfo cItem : columnInfos){
			if(!flag1 && "BigDecimal".equals(cItem.getModelType())){
				importList.add("import java.math.BigDecimal;");
				flag1 = Boolean.TRUE;
				
			}else if(!flag2 && "Date".equals(cItem.getModelType())){
				importList.add("import java.util.Date;");
				flag2 = Boolean.TRUE;
			}
		}
		
		return importList;
	}

	@SuppressWarnings("deprecation")
	private void createTempleteFile(String filename, String templetePath, String templeteName,
			Map<String, Object> data) {
		try {
			Configuration cfg = new Configuration();
			cfg.setDirectoryForTemplateLoading(new File(templetePath));
			cfg.setObjectWrapper(new DefaultObjectWrapper());

			// 设置字符集
			cfg.setDefaultEncoding("UTF-8");

			// 设置尖括号语法和方括号语法,默认是自动检测语法
			// 自动 AUTO_DETECT_TAG_SYNTAX
			// 尖括号 ANGLE_BRACKET_TAG_SYNTAX
			// 方括号 SQUARE_BRACKET_TAG_SYNTAX
			cfg.setTagSyntax(Configuration.AUTO_DETECT_TAG_SYNTAX);

			Writer out = new OutputStreamWriter(new FileOutputStream(filename), "UTF-8");
			Template temp = cfg.getTemplate(templeteName);
			temp.process(data, out);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("process due to erro", e);
		}
	}

	private void createOther(TableInfo item, String type, List<ColumnInfo> columnInfos) {
		String path = localProjectPath;
		String lastDir = type;
		if ("serviceImpl".equals(type)) {
			lastDir = "service";
		} else if ("mapper".equals(type)) {
			lastDir = "mybatis";
		}

		String dir = String.format("%s/%s", path + "codetemplate/", lastDir);
		File file = new File(dir);
		if (!file.exists()) {
			file.mkdirs();
		}
		String codeTempletePath = String.format("%s%s", localProjectPath, templetePath);
		String filePath = "";
		if("mapper".equals(type)) {
			filePath = String.format("%s/%s.xml", dir,
					item.getModelName() + type.substring(0, 1).toUpperCase() + type.substring(1));
			columnInfos = removeTimeColumn(columnInfos);
		} else {
			filePath = String.format("%s/%s.java", dir,
					item.getModelName() + type.substring(0, 1).toUpperCase() + type.substring(1));
		}
		 

		Map<String, Object> data = Maps.newHashMap();
		data.put("proList", columnInfos);
		data.put("modelParam", String.format("%s%s", item.getModelName().substring(0, 1).toLowerCase(),
				item.getModelName().substring(1)));
		data.put("modellower", item.getModelName().toLowerCase());
		data.put("item", item);
		data.put("packagePath", packagePath);

		createTempleteFile(filePath, codeTempletePath, String.format("%s.flt", type), data);
	}

	public boolean allTables(String dbname) {

		return false;
	}
	
	/**
	 * 表基本信息校验
	 * @param tableInfo
	 * @return
	 */
	private TableInfo checkTabelInfo(TableInfo tableInfo, String schemaName) {
		String[] items = tableInfo.getTableName().split("_");

		String modelName = "";
		for (String item : items) {
			if("tb".equalsIgnoreCase(item)){
				continue;
			}
			modelName += item.substring(0, 1).toUpperCase() + item.substring(1);
		}
		tableInfo.setModelName(modelName);
		String tableComment = tableInfo.getTableComment() != null ? String.valueOf(tableInfo.getTableComment()) : "";
		tableInfo.setTableComment(tableComment);
		tableInfo.setSchemaName(schemaName);
		return tableInfo;
	}
	
	/**
	 * 表字段校验
	 * @param columnInfos
	 * @return
	 */
	private List<ColumnInfo> checkColumnInfos(List<ColumnInfo> columnInfos) {
		for (ColumnInfo columnInfo : columnInfos) {

			if (columnInfo.getColumnType().contains("int")) {
				columnInfo.setColumnCharacterMaximumLength(columnInfo.getColumnLength());
			} else if (columnInfo.getColumnType().contains("varchar")) {
				columnInfo.setColumnCharacterMaximumLength(columnInfo.getColumnVarLength());
			}

			columnInfo.setModelComment(columnInfo.getColumnComment());
			columnInfo.setModelCharacterMaximumLength(columnInfo.getColumnCharacterMaximumLength());

			String[] items = columnInfo.getColumnName().split("_");
			String modelName = "";
			String modelNameFirstUpper = "";
			int i = 0;
			for (String item : items) {
				if (i == 0) {
					modelName += item.substring(0, 1) + item.substring(1);
				} else {
					modelName += item.substring(0, 1).toUpperCase() + item.substring(1);
				}
				i++;
				modelNameFirstUpper += item.substring(0, 1).toUpperCase() + item.substring(1);
			}
			columnInfo.setModelName(modelName);
			columnInfo.setModelNameFirstUpper(modelNameFirstUpper);

			String typeLower = columnInfo.getColumnType().toLowerCase();
			if (typeLower.startsWith("varchar") || typeLower.startsWith("char")) {
				columnInfo.setColumnType("VARCHAR");
				columnInfo.setModelType("String");
			} else if (typeLower.startsWith("text")) {
				columnInfo.setColumnType("VARCHAR");
				columnInfo.setModelType("String");
			} else if (typeLower.startsWith("int4") || typeLower.startsWith("int2")) {
				columnInfo.setColumnType("INTEGER");
				columnInfo.setModelType("Integer");
				columnInfo.setIsNumber(true);
			} else if (typeLower.startsWith("int8")) {
				columnInfo.setColumnType("INTEGER");
				columnInfo.setModelType("Long");
				columnInfo.setIsNumber(true);
			} else if (typeLower.startsWith("timestamp")) {
				columnInfo.setColumnType("TIMESTAMP");
				columnInfo.setModelType("Date");
			} else if (typeLower.startsWith("numeric")) {
				columnInfo.setColumnType("NUMERIC");
				columnInfo.setModelType("BigDecimal");
				columnInfo.setIsNumber(true);
			} else if (typeLower.startsWith("float")) {
				columnInfo.setColumnType("FLOAT");
				columnInfo.setModelType("BigDecimal");
				columnInfo.setIsNumber(true);
			} else if (typeLower.contains("char")){
				columnInfo.setColumnType("CHAR");
				columnInfo.setModelType("String");
			}
		}
		return columnInfos;
	}
	
	private List<ColumnInfo> removeTimeColumn(List<ColumnInfo> columnInfos) {
		Iterator<ColumnInfo> iterator = columnInfos.iterator();
		while (iterator.hasNext()) {
			ColumnInfo columnInfo = iterator.next();
			if ("id".equals(columnInfo.getColumnName())
					) {
				iterator.remove();
			}
		}

		return columnInfos;
	}
	
}
