package com.ninjatutorials.utils;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;

public class util {

	public static void main(String[] args) throws IOException {
		
		
		
		//screenshot example
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.google.com/");

		TakesScreenshot screenshot = (TakesScreenshot) driver;

		File source = screenshot.getScreenshotAs(OutputType.FILE);

		String destinationScreenshotPath = System.getProperty("user.dir") + "\\Screenshots\\testName.png";
		File deatinationPath = new File(destinationScreenshotPath);

		FileHandler.copy(source, deatinationPath);

		driver.quit();

	}

}
