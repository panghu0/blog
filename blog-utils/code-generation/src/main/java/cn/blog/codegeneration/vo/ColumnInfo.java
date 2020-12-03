package com.zhongrui.codegeneration.vo;

import java.io.Serializable;

public class ColumnInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5909689806580544270L;

	private String attnum;
	private String columnName;
	private String columnType;
	private String columnLength;
	private String columnVarLength;
	private Boolean isNotNull;
	private String columnComment;

	private String columnCharacterMaximumLength;

	private String modelName;
	private String modelType;
	private Boolean isNumber = false;
	private String modelComment;
	private String modelCharacterMaximumLength;

	private String modelNameFirstUpper;
	
	private Boolean hidden = false;

	public String getAttnum() {
		return attnum;
	}

	public void setAttnum(String attnum) {
		this.attnum = attnum;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getColumnType() {
		return columnType;
	}

	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}

	public String getColumnLength() {
		return columnLength;
	}

	public void setColumnLength(String columnLength) {
		this.columnLength = columnLength;
	}

	public String getColumnVarLength() {
		return columnVarLength;
	}

	public void setColumnVarLength(String columnVarLength) {
		this.columnVarLength = columnVarLength;
	}

	public Boolean getIsNotNull() {
		return isNotNull;
	}

	public void setIsNotNull(Boolean isNotNull) {
		this.isNotNull = isNotNull;
	}

	public String getColumnComment() {
		return columnComment;
	}

	public void setColumnComment(String columnComment) {
		this.columnComment = columnComment.replaceAll("\r\n", "");
	}

	public String getColumnCharacterMaximumLength() {
		return columnCharacterMaximumLength;
	}

	public void setColumnCharacterMaximumLength(String columnCharacterMaximumLength) {
		this.columnCharacterMaximumLength = columnCharacterMaximumLength;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getModelType() {
		return modelType;
	}

	public void setModelType(String modelType) {
		this.modelType = modelType;
	}

	public String getModelComment() {
		return modelComment;
	}

	public void setModelComment(String modelComment) {
		this.modelComment = modelComment;
	}

	public String getModelCharacterMaximumLength() {
		return modelCharacterMaximumLength;
	}

	public void setModelCharacterMaximumLength(String modelCharacterMaximumLength) {
		this.modelCharacterMaximumLength = modelCharacterMaximumLength;
	}

	public String getModelNameFirstUpper() {
		return modelNameFirstUpper;
	}

	public void setModelNameFirstUpper(String modelNameFirstUpper) {
		this.modelNameFirstUpper = modelNameFirstUpper;
	}

	public Boolean getHidden() {
		return hidden;
	}

	public void setHidden(Boolean hidden) {
		this.hidden = hidden;
	}

	public Boolean getIsNumber() {
		return isNumber;
	}

	public void setIsNumber(Boolean isNumber) {
		this.isNumber = isNumber;
	}

}
