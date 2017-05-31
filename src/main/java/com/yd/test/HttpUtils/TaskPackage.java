package com.yd.test.HttpUtils;

import java.util.List;

/**
 * Created by kuicui on 2017/5/26.
 */
public class TaskPackage {
    private String  packageName;
    private String  host;
    private List<Task>  taskList;

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

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
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
