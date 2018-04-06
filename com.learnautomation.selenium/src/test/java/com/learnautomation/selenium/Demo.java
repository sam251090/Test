package com.learnautomation.selenium;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



	public class Demo {
		
	 public static String driverPath = "C:/new/";
	 public static WebDriver driver;
	 
	 
	 @Test(dataProvider="loginData")
	 public void login(String id, String pass)throws InterruptedException 
	 {

		  System.out.println("launching chrome browser");
			System.setProperty("webdriver.chrome.driver", driverPath+"chromedriver.exe");
			driver = new ChromeDriver();
			driver.get("http://app.int.k2k.devthing.link/");
			driver.manage().window().maximize(); 
			Thread.sleep(4000);
			
	
		 WebElement username=driver.findElement(By.xpath("//input[@id='inputEmail']"));
			username.clear();
			username.sendKeys(id);
			
			WebElement password=driver.findElement(By.id("inputPassword"));
			password.clear();
			password.sendKeys(pass);
			
			WebElement signIn=driver.findElement(By.id("signIn"));
			signIn.click();
	 }
	 @AfterMethod
	 public void tearDown() throws InterruptedException
	 {
		 driver.quit();
		 Thread.sleep(2000);
	 }
	 @DataProvider(name="loginData")
	 public Object[][] passData()
	 {
		 ExcelDataConfig excel=new ExcelDataConfig("C:\\Excel Data\\TestData.xlsx");
		 int rows=excel.getRowCount(0);
		 Object[][] data=new Object[rows][2];
		 for(int i=0; i<rows; i++ )
		 {
		 data[i][0]=excel.getData(0, i, 0);
		 data[i][1]=excel.getData(0, i, 1);
		 }
			return data;
	 }
		
	}


