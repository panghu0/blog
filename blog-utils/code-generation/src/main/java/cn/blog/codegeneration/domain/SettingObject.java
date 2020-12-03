package com.zhongrui.codegeneration.domain;

import java.io.Serializable;

public class SettingObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7829055676645801998L;

	private String packagePath;

	public String getPackagePath() {
		return packagePath;
	}

	public void setPackagePath(String packagePath) {
		this.packagePath = packagePath;
	}

	public SettingObject(String packagePath) {
		this.packagePath = packagePath;
	}

	public SettingObject() {
		
	}

}