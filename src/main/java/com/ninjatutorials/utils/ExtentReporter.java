package com.ninjatutorials.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {

	public static ExtentReports  generateExtentReporter() {

		ExtentReports extentReports = new ExtentReports();

		File extentreporterFile = new File(
				System.getProperty("user.dir") + "\\test-output\\extentReports\\extentreport.html");

		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentreporterFile);

		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setDocumentTitle("TutorialsNinja Test Automation Report");
		sparkReporter.config().setReportName("TutorialsNinja Test Report");
		sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");

		extentReports.attachReporter(sparkReporter);

		Properties properties = new Properties();
		File file = new File(
				System.getProperty("user.dir") + "\\src\\main\\java\\com\\ninjatutorials\\config\\config.properties");
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			properties.load(fileInputStream);
		} catch (Throwable e) {
			e.printStackTrace();
		}

		extentReports.setSystemInfo("Application URL", properties.getProperty("url"));
		extentReports.setSystemInfo("Browser Name", properties.getProperty("browserName"));
		extentReports.setSystemInfo("Operationg System", System.getProperty("os.name"));
		extentReports.setSystemInfo("UserName", System.getProperty("user.name"));
		extentReports.setSystemInfo("UserName", System.getProperty("user.name"));
		extentReports.setSystemInfo("Java version", System.getProperty("java.version"));
		return extentReports;

	}

}
