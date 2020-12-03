package com.zhongrui.codegeneration.domain;

public class GeneratorHelper {

	private Integer id;

	/** 用户账号 */
	private String version;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "GeneratorHelper [id=" + id + ", version=" + version + "]";
	}

}
