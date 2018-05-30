package csl.v4.smokeTest.Automation;


import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class Admin_Tear_Down_Activities {
	public static String driverPath = "C:/new/";
	public WebDriver driver;
	 
	ExcelDataConfig excel=new ExcelDataConfig("C:\\Excel Data\\TestData.xlsx");
	
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	Date date = new Date();

	@BeforeMethod
	 public void DriverLaunch() throws InterruptedException 
	 {
		System.out.println("launching chrome browser");
		System.setProperty("webdriver.chrome.driver", driverPath+"chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://app.int.k2k.devthing.link/");
		driver.manage().window().maximize();
			WebDriverWait wait = new WebDriverWait(driver, 50);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='inputEmail']")));
			Thread.sleep(5000);
	 }
	
	public void Group_Delete() throws InterruptedException, IOException
	{
		WebElement Setting=driver.findElement(By.xpath("//*[@class='adminMenu']/a"));
		Setting.click();
		Thread.sleep(1000);
		
		WebElement Groups_Icon=driver.findElement(By.xpath("//*[@data-orgroute='groups']"));
		Groups_Icon.click();
		
		 WebDriverWait wait = new WebDriverWait(driver, 50);
		 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@type='search']")));
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
		 Thread.sleep(2000);
		 
		String Group_Name=excel.getData(4, 1, 0);
		
		WebElement Group_Filter=driver.findElement(By.xpath("//*[@type='search']"));
		Group_Filter.sendKeys(Group_Name);
		Thread.sleep(1000);
		
		WebElement Group_Delete=driver.findElement(By.xpath("//*[@id='groupListGrid']//tr[1]//td[5]//a[1]"));
		Group_Delete.click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='confirmDelete']")));
		Thread.sleep(2000);
		
		WebElement Confirm_Delete=driver.findElement(By.xpath("//*[@id='confirmDelete']"));
		Confirm_Delete.click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@type='search']")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
		Thread.sleep(2000);
		
		WebElement Group_Filter1=driver.findElement(By.xpath("//*[@type='search']"));
		Group_Filter1.clear();
		Group_Filter1.sendKeys(Group_Name);
		Thread.sleep(2000);
		
		for(int x=0; x<10; x++)
		{
		if(driver.findElement(By.xpath("//*[@id='groupListGrid']//tr[1]//td[1]")).getText().equals(Group_Name))
		{
		driver.navigate().refresh();
		}
		else
		{
		x=10;
		}
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='groupListGrid']//tr[1]")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
		Thread.sleep(3000);
		}
		
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id='groupListGrid']//tr[1]//td[1]")).getText(), "Nothing found - sorry");
		Thread.sleep(2000);
	}
	
	public void Dealer_Delete() throws InterruptedException, IOException
	{
		WebElement Setting=driver.findElement(By.xpath("//*[@class='adminMenu']/a"));
		Setting.click();
		Thread.sleep(1000);
		
		WebElement Dealer_Icon=driver.findElement(By.xpath("//*[@data-orgroute='dealers']"));
		Dealer_Icon.click();
		
		WebDriverWait wait = new WebDriverWait(driver, 50);
		 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@type='search']")));
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
		 Thread.sleep(2000);
		 
		String Dealer_Name=excel.getData(4, 4, 0);
		String Dealer_Code=excel.getData(4, 4, 1);
		
		WebElement Dealer_Filter=driver.findElement(By.xpath("//*[@type='search']"));
		Dealer_Filter.sendKeys(Dealer_Name);
		Thread.sleep(1000);
		
		WebElement Dealer_Delete=driver.findElement(By.xpath("//*[@id='dlrListGrid']//tr[1]//td[5]//a[1]"));
		Dealer_Delete.click();
		Thread.sleep(2000);
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='pull-right btn btn-danger dlrDeleteConfirmBtn']")));
		Thread.sleep(2000);
		
		WebElement Confirm_Delete=driver.findElement(By.xpath("//*[@class='pull-right btn btn-danger dlrDeleteConfirmBtn']"));
		Confirm_Delete.click();
		Thread.sleep(2000);
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@type='search']")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
		Thread.sleep(2000);
		
		WebElement Dealer_Filter1=driver.findElement(By.xpath("//*[@type='search']"));
		Dealer_Filter1.clear();
		Dealer_Filter1.sendKeys(Dealer_Name);
		Thread.sleep(3000);
		
		for(int x=0; x<10; x++)
		{
		if(driver.findElement(By.xpath("//*[@id='dlrListGrid']//tr[1]//td[1]")).getText().equals(Dealer_Code))
		{
		driver.navigate().refresh();
		}
		else
		{
		x=10;
		}
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='dlrListGrid']//tr[1]")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
		Thread.sleep(3000);
		}
		
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id='dlrListGrid']//tr[1]//td[1]")).getText(), "Nothing found - sorry");
		Thread.sleep(2000);
	}
	
	public void User_CSV_Delete() throws InterruptedException, IOException
	{
		WebElement Setting=driver.findElement(By.xpath("//*[@class='adminMenu']/a"));
		Setting.click();
		Thread.sleep(1000);
		
		WebElement User_Icon=driver.findElement(By.xpath("//*[@data-orgroute='users']"));
		User_Icon.click();
		
		WebDriverWait wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@type='search']")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
		Thread.sleep(2000);
		
		String Username=excel.getData(4, 7, 0);
		
		WebElement User_Filter=driver.findElement(By.xpath("//*[@type='search']"));
		User_Filter.sendKeys(Username);
		Thread.sleep(1000);
		
		WebElement User_Delete=driver.findElement(By.xpath("//*[@class='btn btn-danger btn-sm userDeleteBtn ']"));
		User_Delete.click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='pull-right btn btn-danger userDeleteInterBtn']")));
		Thread.sleep(2000);
		
		WebElement Delete=driver.findElement(By.xpath("//*[@class='pull-right btn btn-danger userDeleteInterBtn']"));
		Delete.click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//*[@name='username'])[2]")));
		Thread.sleep(2000);
		String A_Username=excel.getData(0, 0, 0);
		
		WebElement Admin_Username=driver.findElement(By.xpath("(//*[@name='username'])[2]"));
		Admin_Username.sendKeys(A_Username);
		Thread.sleep(2000);
		
		WebElement Confirm_Delete=driver.findElement(By.xpath("//*[@class='pull-right btn btn-danger userDeleteConfirmBtn']"));
		Confirm_Delete.click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@type='search']")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
		Thread.sleep(2000);
		
		WebElement User_Filter1=driver.findElement(By.xpath("//*[@type='search']"));
		User_Filter1.clear();
		User_Filter1.sendKeys(Username);
		Thread.sleep(2000);
		
		for(int x=0; x<10; x++)
		{
		if(driver.findElement(By.xpath("//*[@id='orgListGrid']//tr[1]//td[1]")).getText().equals("Nothing found - sorry"))
		{
			x=10;
		}
		else
		{
			driver.navigate().refresh();
		}
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='orgListGrid']//tr[1]")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
		Thread.sleep(3000);
		}
		
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id='orgListGrid']//tr[1]//td[1]")).getText(), "Nothing found - sorry");		
		Thread.sleep(2000);
	}
	
	public void User_Delete() throws InterruptedException, IOException
	{	
		WebDriverWait wait = new WebDriverWait(driver, 50);
		
		String Username=excel.getData(4, 13, 0);
		
		WebElement User_Filter=driver.findElement(By.xpath("//*[@type='search']"));
		User_Filter.clear();
		User_Filter.sendKeys(Username);
		Thread.sleep(1000);
		
		WebElement User_Delete=driver.findElement(By.xpath("//*[@class='btn btn-danger btn-sm userDeleteBtn ']"));
		User_Delete.click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='pull-right btn btn-danger userDeleteInterBtn']")));
		Thread.sleep(2000);
		
		WebElement Delete=driver.findElement(By.xpath("//*[@class='pull-right btn btn-danger userDeleteInterBtn']"));
		Delete.click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//*[@name='username'])[2]")));
		Thread.sleep(2000);
		String A_Username=excel.getData(0, 0, 0);
		
		WebElement Admin_Username=driver.findElement(By.xpath("(//*[@name='username'])[2]"));
		Admin_Username.sendKeys(A_Username);
		Thread.sleep(2000);
		
		WebElement Confirm_Delete=driver.findElement(By.xpath("//*[@class='pull-right btn btn-danger userDeleteConfirmBtn']"));
		Confirm_Delete.click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@type='search']")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
		Thread.sleep(2000);
		
		WebElement User_Filter1=driver.findElement(By.xpath("//*[@type='search']"));
		User_Filter1.clear();
		User_Filter1.sendKeys(Username);
		Thread.sleep(2000);
		
		for(int x=0; x<10; x++)
		{
		if(driver.findElement(By.xpath("//*[@id='orgListGrid']//tr[1]//td[1]")).getText().equals("Nothing found - sorry"))
		{
			x=10;
		}
		else
		{
			driver.navigate().refresh();
		}
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='orgListGrid']//tr[1]")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
		Thread.sleep(3000);
		}
		
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id='orgListGrid']//tr[1]//td[1]")).getText(), "Nothing found - sorry");		
		Thread.sleep(2000);
	}
	
	public void Script_Delete() throws InterruptedException, IOException
	{
		WebElement Setting=driver.findElement(By.xpath("//*[@class='adminMenu']/a"));
		Setting.click();
		Thread.sleep(1000);
		
		WebElement Script_Call=driver.findElement(By.xpath("//*[@data-orgroute='callscripts']"));
		Script_Call.click();
		
		WebDriverWait wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@type='search']")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
		Thread.sleep(2000);
		
		String Script_Name=excel.getData(4, 10, 0);
		
		WebElement Script_Filter=driver.findElement(By.xpath("//*[@type='search']"));
		Script_Filter.sendKeys(Script_Name);
		Thread.sleep(1000);
		
		WebElement Script_Delete=driver.findElement(By.xpath("//*[@id='callscriptListGrid']//tr[1]//td[6]//a[1]"));
		Script_Delete.click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='pull-right btn btn-danger scriptInterBtn']")));
		Thread.sleep(2000);
		
		WebElement Delete=driver.findElement(By.xpath("//*[@class='pull-right btn btn-danger scriptInterBtn']"));
		Delete.click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@name='username']")));
		Thread.sleep(2000);
		
		String A_Username=excel.getData(0, 0, 0);
		
		WebElement Admin_Username=driver.findElement(By.xpath("//*[@name='username']"));
		Admin_Username.sendKeys(A_Username);
		Thread.sleep(2000);
		
		WebElement Confirm_Delete=driver.findElement(By.xpath("//*[@class='pull-right btn btn-danger scriptDeleteConfirmBtn']"));
		Confirm_Delete.click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@type='search']")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
		Thread.sleep(2000);
		
		WebElement Script_Filter1=driver.findElement(By.xpath("//*[@type='search']"));
		Script_Filter1.clear();
		Script_Filter1.sendKeys(Script_Name);
		Thread.sleep(2000);
		
		for(int x=0; x<10; x++)
		{
		if(driver.findElement(By.xpath("//*[@id='callscriptListGrid']//tr[1]//td[1]")).getText().equals(Script_Name))
		{
		driver.navigate().refresh();
		}
		else
		{
		x=10;
		}
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='callscriptListGrid']//tr[1]")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
		Thread.sleep(3000);
		}
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id='callscriptListGrid']//tr[1]//td[1]")).getText(), "Nothing found - sorry");
		Thread.sleep(2000);
	}
	
	public void Pricing_Delete() throws InterruptedException, IOException
	{
		WebElement Setting=driver.findElement(By.xpath("//*[@class='adminMenu']/a"));
		Setting.click();
		Thread.sleep(1000);
		
		WebElement Pricing_Call=driver.findElement(By.xpath("//*[@data-orgroute='pricing']"));
		Pricing_Call.click();
		
		WebDriverWait wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='pricingListGrid']//tr[1]")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
		Thread.sleep(2000);
		
		WebElement Pricing_Delete=driver.findElement(By.xpath("//*[@id='pricingListGrid']//tr[1]//td[11]//a[1]"));
		Pricing_Delete.click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='pull-right btn btn-danger confirmdelete']")));
		Thread.sleep(2000);
		
		WebElement Confirm_Delete=driver.findElement(By.xpath("//*[@class='pull-right btn btn-danger confirmdelete']"));
		Confirm_Delete.click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='pricingListGrid']//tr[1]")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
		Thread.sleep(2000);
		
		for(int x=0; x<10; x++)
		{
		if(driver.findElement(By.xpath("//*[@id='pricingListGrid']//tr[1]//td[1]")).getText().equals("Nothing found - sorry"))
		{
			x=10;
		}
		else
		{
			driver.navigate().refresh();
		}
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='pricingListGrid']//tr[1]")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
		Thread.sleep(3000);
		}
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id='pricingListGrid']//tr[1]//td[1]")).getText(), "Nothing found - sorry");
		Thread.sleep(2000);
	}
	
	public void Customer_File_Processing() throws InterruptedException
	{
		WebElement Setting=driver.findElement(By.xpath("//*[@class='adminMenu']/a"));
		Setting.click();
		Thread.sleep(1000);
		
		WebElement Processing=driver.findElement(By.xpath("//*[@class='list-group-item processingMenuItem']"));
		Processing.click();
		
		 WebDriverWait wait = new WebDriverWait(driver, 50);
		 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@type='search']")));
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
		 Thread.sleep(2000);
		 
		WebElement Search=driver.findElement(By.xpath("//*[@type='search']"));
		Search.sendKeys("MBAnon.csv");
		Thread.sleep(1000);
		
		WebElement Process=driver.findElement(By.xpath("//*[@id='processingListGrid']//tr[1]//td[4]//a[1]"));
		Process.click();
		
		 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='newname']")));
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
		 Thread.sleep(2000);
		 
		String File_Name="Auto_CustomerProcessing_"+date;
		WebElement Process_New_Name=driver.findElement(By.xpath("//*[@id='newname']"));
		Process_New_Name.sendKeys(File_Name);
		Thread.sleep(1000);
		
		Select Processing_Template=new Select(driver.findElement(By.xpath("//Select[@id='processingTemplateDropdown']")));
		Processing_Template.selectByValue("2e66b050-46d5-11e8-a2b5-063929a48e11");
		Thread.sleep(3000);
		
		WebElement Process_Save=driver.findElement(By.xpath("//*[@class='btn btn-primary btn-save saveProcessBtn']"));
		Process_Save.click();
		
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@role='tablist']//li[4]")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
		Thread.sleep(2000);
		
		WebElement Current_Processing_Queue=driver.findElement(By.xpath("//*[@role='tablist']//li[4]"));
		Current_Processing_Queue.click();
		Thread.sleep(5000);
		
		String Process_File_Name=driver.findElement(By.xpath("//*[@id='jobsListGrid']//tr[1]//td[1]")).getText();
		String Process_Status=driver.findElement(By.xpath("//*[@id='jobsListGrid']//tr[1]//td[4]//p")).getText();
		
		Assert.assertEquals(Process_File_Name, File_Name);
		if(Process_Status=="QUEUED")
		{
			Assert.assertEquals(Process_Status, "QUEUED");
		}
		else if(Process_Status=="PROCESSING")
		{
			Assert.assertEquals(Process_Status, "PROCESSING");
		}
		
	}
	
	@Test(dataProvider="loginData", description="Dashboard1")
	 public void login(String id, String pass) throws InterruptedException, IOException
	 {
		 
		 WebElement username=driver.findElement(By.xpath("//input[@id='inputEmail']"));
		 username.clear();
		 username.sendKeys(id);
		 Thread.sleep(1000);
			
		 WebElement password=driver.findElement(By.id("inputPassword"));
		 password.clear();
		 password.sendKeys(pass);
		 Thread.sleep(1000);
			
		 WebElement signIn=driver.findElement(By.id("signIn"));
		 signIn.click();
			
		 WebDriverWait wait = new WebDriverWait(driver, 50);
		 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h3[@id='early-good-to-go']")));
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='p1Loader']")));
		
		 Thread.sleep(5000);
			
		String Organisation_Branch=driver.findElement(By.xpath("//*[@class='breadcrumb-featured']//li[3]")).getText();
		Assert.assertEquals(Organisation_Branch, "CT_Auto_Test");
		
		Group_Delete();
		Thread.sleep(5000);	
		
		Dealer_Delete();
		Thread.sleep(5000);			
		
		User_CSV_Delete();
		Thread.sleep(3000);		
		
		User_Delete();
		Thread.sleep(5000);
		
		Script_Delete();
		Thread.sleep(5000);	
		
		Pricing_Delete();
		Thread.sleep(5000);	
		
		Customer_File_Processing();
		Thread.sleep(5000);		
		}
	 
	 @AfterMethod
	 public void logout() throws InterruptedException, NoSuchElementException
	 {
		 
		 WebElement logout=driver.findElement(By.xpath("//*[@data-status='profile']"));
		logout.click();
		Thread.sleep(5000);
		WebElement logout1=driver.findElement(By.xpath("//a[@class='user-logout']"));
		logout1.click();
		Thread.sleep(2000);
		driver.quit();			 
	 }
	
	 @AfterClass
	 public void tearDown() throws InterruptedException
	 {
			driver.quit();
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
