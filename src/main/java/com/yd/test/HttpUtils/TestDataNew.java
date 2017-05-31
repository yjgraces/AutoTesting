package com.yd.test.HttpUtils;

import java.util.ArrayList;

public class TestDataNew {
	
	private String  packageName;
    private String  host;
    private ArrayList<TaskList> taskList;
    
    public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public ArrayList<TaskList> getTaskList() {
		return taskList;
	}
	public void setTaskList(ArrayList<TaskList> taskList) {
		this.taskList = taskList;
	}

    @Override
    public String toString() {
        return "TaskPackage{" +
                "packageName='" + packageName + '\'' +
                ", host='" + host + '\'' +
                ", taskList=" + taskList +
                '}';
    }
}
