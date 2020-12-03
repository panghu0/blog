package com.zhongrui.codegeneration.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.zhongrui.codegeneration.vo.ColumnInfo;
import com.zhongrui.codegeneration.vo.TableInfo;

/**
 * 用户相关接口 对应 AuthUser这个表
 *
 */

public interface GeneratorHelperDao {

	List<String> findAllDatabaseSchemas();

	List<String> findAllTablesFromSchema(@Param("schemaName") String schemaName);

	TableInfo findTableInfo(@Param("tableName") String tableName);

	List<ColumnInfo> listColumInfos(@Param("tableName") String tableName);

}
