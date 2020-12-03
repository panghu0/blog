package com.zhongrui.codegeneration.vo;

import java.io.Serializable;

/**
 * @author pang hu
 * @date 2020/10/18
 */
public class TableInfo implements Comparable<TableInfo>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 659219752798109088L;

	// TABLE_NAME,TABLE_COMMENT
	private String tableName;

	private String modelName;

	private String tableComment;

	private String idType;
	private String primaryKey;
	private String schemaName;
	
	private Boolean isShowGmtModified = Boolean.FALSE;
	private Boolean isOrderBySeqNum = Boolean.FALSE;

	public TableInfo(Object[] arr) {
		this.tableName = String.valueOf(arr[0]);
		String[] items = this.tableName.split("_");

		String modelName = "";
		for (String item : items) {
			modelName += item.substring(0, 1).toUpperCase() + item.substring(1);
		}
		this.modelName = modelName;
		this.tableComment = arr[1] != null ? String.valueOf(arr[1]).replaceAll("\r\n", "") : "";
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getTableComment() {
		return tableComment;
	}

	public void setTableComment(String tableComment) {
		this.tableComment = tableComment.replaceAll("\r\n", "");
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}

	public String getSchemaName() {
		return schemaName;
	}

	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}

	public Boolean getIsShowGmtModified() {
		return isShowGmtModified;
	}

	public void setIsShowGmtModified(Boolean isShowGmtModified) {
		this.isShowGmtModified = isShowGmtModified;
	}

	public Boolean getIsOrderBySeqNum() {
		return isOrderBySeqNum;
	}

	public void setIsOrderBySeqNum(Boolean isOrderBySeqNum) {
		this.isOrderBySeqNum = isOrderBySeqNum;
	}

	@Override
	public String toString() {
		return "TableInfo [tableName=" + tableName + ", modelName=" + modelName + ", tableComment=" + tableComment
				+ ", idType=" + idType + ", schemaName=" + schemaName + "]";
	}

	public TableInfo() {
	}

	@Override
	public int compareTo(TableInfo o) {

		return this.tableName.compareTo(o.getTableName());
	}

}
