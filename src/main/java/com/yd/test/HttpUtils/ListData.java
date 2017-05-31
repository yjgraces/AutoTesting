package com.yd.test.HttpUtils;

import java.util.Map;

public class ListData {
	private String id;
    private String testName;
    private String uri;
    private String methodType;
    private Map<String, String> headers;
    private Map<String, String> params;
    private Map<String, String> filePath;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getMethodType() {
		return methodType;
	}
	public void setMethodType(String methodType) {
		this.methodType = methodType;
	}
	public Map<String, String> getHeaders() {
		return headers;
	}
	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}
	public Map<String, String> getParams() {
		return params;
	}
	public void setParams(Map<String, String> params) {
		this.params = params;
	}
	public Map<String, String> getFilePath() {
		return filePath;
	}
	public void setFilePath(Map<String, String> filePath) {
		this.filePath = filePath;
	}
}
