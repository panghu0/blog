package com.zhongrui.codegeneration.service;

public interface GeneratorHelperService {
	
//	boolean setProperty(Map<String, String> map);

	boolean allTables(String dbname);

	String oneTable(String tableName, String schemaName);

}
