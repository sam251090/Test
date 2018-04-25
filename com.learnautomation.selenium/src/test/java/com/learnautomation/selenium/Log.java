package com.learnautomation.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Log {
	public static String driverPath = "C:/new/";
	 public WebDriver driver;
	 
	 @BeforeMethod
	 public void DriverLaunch() throws InterruptedException 
	 {
		 System.out.println("launching chrome browser");
			System.setProperty("webdriver.chrome.driver", driverPath+"chromedriver.exe");
			driver = new ChromeDriver();
			driver.get("http://app.int.k2k.devthing.link/");
			driver.manage().window().maximize(); 
			Thread.sleep(10000);
	 }


@Test(dataProvider="loginData", description="Dashboard2")
public void login(String id, String pass) throws InterruptedException 
{
	 
	 WebElement username=driver.findElement(By.xpath("//input[@id='inputEmail']"));
		username.clear();
		username.sendKeys(id);
		
		WebElement password=driver.findElement(By.id("inputPassword"));
		password.clear();
		password.sendKeys(pass);
		
		WebElement signIn=driver.findElement(By.id("signIn"));
		signIn.click();
		
		Thread.sleep(10000);
		driver.quit();
}

@DataProvider(name="loginData")
public Object[][] passData()
{
	 ExcelDataConfig excel=new ExcelDataConfig("C:\\Excel Data\\TestData.xlsx");
	 int rows=excel.getRowCount(0);
	 Object[][] data=new Object[rows][2];
	 for(int i=1; i<rows; i++ )
	 {
	 data[i][0]=excel.getData(0, i, 0);
	 data[i][1]=excel.getData(0, i, 1);
	 }
		return data;
}

}
