package com.learnautomation.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;

public class Driver {

	public static String driverPath = "C:/new/";
	 public WebDriver driver;
	 
	 public Driver() throws InterruptedException 
	 {
		 System.out.println("launching chrome browser");
			System.setProperty("webdriver.chrome.driver", driverPath+"chromedriver.exe");
			driver = new ChromeDriver();
			driver.get("http://app.int.k2k.devthing.link/");
			driver.manage().window().maximize(); 
			Thread.sleep(10000);
	 }
	
}
