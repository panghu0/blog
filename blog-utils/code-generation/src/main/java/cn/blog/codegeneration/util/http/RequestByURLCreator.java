package com.zhongrui.codegeneration.util.http;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;

public class RequestByURLCreator implements RequestCreator {

	public RequestByURLCreator(String url) {
		this.url = url;
	}

	final String url;

	public HttpRequestBase create() throws Exception {
		HttpGet get = new HttpGet(url);
		get.setConfig(CONFIG);
		return get;
	}
}
