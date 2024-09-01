package com.ninjatutorials.base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.ninjatutorials.utils.Utilities;

public class Base {

	public WebDriver driver;
	public Properties prop;
	public Properties dataProp;

	public Base() {
		prop = new Properties();
		File file = new File(
				System.getProperty("user.dir") + "\\src\\main\\java\\com\\ninjatutorials\\config\\config.properties");

		dataProp = new Properties();
		File datafile = new File(System.getProperty("user.dir")
				+ "\\src\\main\\java\\com\\ninjatutorials\\testdata\\testdata.properties");

		try {
			FileInputStream fis = new FileInputStream(file);
			prop.load(fis);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		try {
			FileInputStream datafis = new FileInputStream(datafile);
			dataProp.load(datafis);
		} catch (Throwable e) {
			e.printStackTrace();
		}

	}

	public WebDriver initializBrowser(String browserName) {

		switch (browserName.toLowerCase()) {
		case "chrome":
			driver = new ChromeDriver();
			break;
		case "firefox":
			driver = new FirefoxDriver();
			break;
		case "edge":
			driver = new EdgeDriver();
			break;
		case "safari":
			driver = new SafariDriver();
			break;
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_WAIT_TIMEOUT));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIMEOUT));

		driver.get(prop.getProperty("url"));

		return driver;
	}

}
