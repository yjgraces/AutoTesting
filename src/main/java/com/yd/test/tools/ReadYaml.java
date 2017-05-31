package com.yd.test.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.yaml.snakeyaml.Yaml;

public class ReadYaml {
	public static Logger log = Logger.getLogger(ReadYaml.class);

	public void readYaml() {
		Yaml yaml = new Yaml();
		String filepath = System.getProperty("user.dir") + "\\resources\\testdata\\pages\\login.yaml";
		System.out.println(System.getProperty("user.dir"));
		File f = new File(filepath);
		try {
			Map<?, ?> allParamsMap = new HashMap<>();
			Map result = (Map) yaml.load(new FileInputStream(f));
			System.out.println(result);
			System.out.println(result.get("packageName"));
			System.out.println("taskList:" + result.get("taskList").getClass().toString());

			ArrayList<?> alist = new ArrayList<Object>();
			alist = (ArrayList<?>) result.get("taskList");
			System.out.println("alistsize:" + alist.size());
			System.out.println(alist.get(0).getClass().toString());
			allParamsMap = (Map<?,? >) alist.get(0);
			System.out.println("paramsMap:" + allParamsMap.toString());
			System.out.println(allParamsMap.get("headers").getClass());

			Map<?, ?> headerParamsMap = new HashMap<>();
			Map<?, ?> paramsMap = new HashMap<>();

			headerParamsMap = (Map<?, ?>) allParamsMap.get("headers");
			System.out.println(headerParamsMap.get("cookie"));
			System.out.println(headerParamsMap.get("User-Agent"));
			ArrayList<?> paramsList = new ArrayList<Object>();
			paramsList = (ArrayList<?>) allParamsMap.get("paramsList");
			// paramsMap=(Map<?, ?>) allParamsMap.get("paramsList");

			// System.out.println(paramsMap.toString());
			System.out.println(paramsList);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Map<?, ?> readDeviceConfig(String testDeviceName) {
		Map<?, ?> DeviceParamsMap = new HashMap<>();
		testDeviceName = testDeviceName.toLowerCase();
		try {
			Yaml yaml = new Yaml();
			String filepath = System.getProperty("user.dir") + "\\resources\\config\\DeviceConfig.yaml";
			File f = new File(filepath);
			Map<?, ?> result = (Map<?, ?>) yaml.load(new FileInputStream(f));
			ArrayList<?> alist = new ArrayList<Object>();
			alist = (ArrayList<?>) result.get("DeviceList");
			for (int i = 0; i < alist.size(); i++) {
				String s = ((Map<?, ?>) alist.get(i)).get("testDevice").toString().toLowerCase();
				if (s.equalsIgnoreCase(testDeviceName)) {
					DeviceParamsMap = (Map<?, ?>) alist.get(i);
					break;
				}
			}

			if (DeviceParamsMap.isEmpty()) {
				log.info("读取配置文件错误，找不到device配置信息，请检查设备名称是否正确");
			} else {
				log.info("读取配置文件" + testDeviceName + "成功");
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			log.info("读取deviceConfig文件测出错，请检查配置文件路径、以及文件是否存在--\\resources\\config\\DeviceConfig.yaml");
			e.printStackTrace();
		}

		return DeviceParamsMap;
	}

	public static ArrayList<TestData> readParamsYaml(String filePath) {
		Yaml yaml = new Yaml(); 
		TestData testData;
		ArrayList<TestData> testDataList = new ArrayList<TestData>();
		try {
			Object rs = yaml.load(new FileInputStream(new File(filePath)));

			if (!(rs.equals(null))) {
				System.out.println("Start init Yaml File");
				log.info("Start init Yaml File");
				Map<?, ?> result = (Map<?, ?>) rs;
				Map<?, ?> taskMap = new HashMap<>();
				ArrayList<?> taskList = new ArrayList();
				Map<String, String> headerParamsMap = new HashMap<>();
				Map<String, String> paramsMap = new HashMap();
				ArrayList<?> paramsList = new ArrayList();
				taskList = (ArrayList<?>) result.get("taskList");
				for (int i = 0; i < taskList.size(); i++) {
					taskMap = (Map<?, ?>) taskList.get(i);
					headerParamsMap = (Map<String, String>) taskMap.get("headers");
					paramsList = (ArrayList<?>) taskMap.get("paramsList");
					testData = new TestData();
					testData.setPackageName(result.get("packageName").toString().trim());
					testData.setHost(result.get("host").toString().trim());
					testData.setPort(result.get("port").toString().trim());
					testData.setProtocol(result.get("protocol").toString().trim());
					testData.setId(taskMap.get("id").toString().trim());
					testData.setTestName(taskMap.get("testName").toString());
					testData.setUri(taskMap.get("uri").toString().trim());
					testData.setMethodType(taskMap.get("methodType").toString().trim());
					testData.setHeaderParamsMap(headerParamsMap);
					testData.setParamsList(paramsList);

					testDataList.add(testData);
				}

			} else {
				log.info("Test Yaml config File is null,please check!");

			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return testDataList;
	}

	public static void main(String[] args) {
		ReadYaml r = new ReadYaml();
		Map<?, ?> m = new HashMap<>();

		// m=r.readDeviceConfig("chrome");
		// System.out.println(m);
		 r.readYaml();
//		String filePath = System.getProperty("user.dir") + "\\resources\\testdata\\pages\\login.yaml";
//		ArrayList<TestData> testdata = ReadYaml.readParamsYaml(filePath);
//		System.out.println(testdata.size());
	}
}
