package csl.v4.smokeTest.Automation;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;

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

public class Admin_Activities {
	public static String driverPath = "C:/new/";
	public WebDriver driver;
	String User_Count, User_Count_After;
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
	
	public void Group_Create() throws InterruptedException, IOException
	{
		WebElement Setting=driver.findElement(By.xpath("//*[@class='adminMenu']/a"));
		Setting.click();
		Thread.sleep(1000);
		
		WebElement Groups_Icon=driver.findElement(By.xpath("//*[@data-orgroute='groups']"));
		Groups_Icon.click();
		
		WebDriverWait wait = new WebDriverWait(driver, 50);
		 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='view-title']//*[@class='btn-group']/a")));
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
		 Thread.sleep(2000);
		 
		WebElement Create_Group=driver.findElement(By.xpath("//*[@class='view-title']//*[@class='btn-group']/a"));
		Create_Group.click();
		Thread.sleep(3000);
		
		String name="Auto_Group_"+date;
		
		WebElement Group_Name=driver.findElement(By.xpath("//*[@data-fv-field='name']"));
		Group_Name.sendKeys(name);
		Thread.sleep(1000);
		
		excel.writeStringData(4,1,0,name);	
		
		String description="Auto_Group_Description_"+date;
		
		WebElement Group_Description=driver.findElement(By.xpath("//*[@data-fv-field='description']"));
		Group_Description.sendKeys(description);
		Thread.sleep(1000);
		
		WebElement Create_Button=driver.findElement(By.xpath("//button[@id='btnSaveClose']"));
		Create_Button.click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='btn btn-primary btn-save saveScriptBtn']")));
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
		 Thread.sleep(2000);
		 
		WebElement Save_Button=driver.findElement(By.xpath("//*[@class='btn btn-primary btn-save saveScriptBtn']"));
		Save_Button.click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@type='search']")));
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
		 Thread.sleep(2000);
		 
		WebElement Search=driver.findElement(By.xpath("//*[@type='search']"));
		Search.sendKeys(name);
		Thread.sleep(1000);
		
		for(int x=0; x<10; x++)
		{
		if(driver.findElement(By.xpath("//*[@id='groupListGrid']//tr[1]//td[1]")).getText().equals("Nothing found - sorry"))
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
		
		String New_Created_Group= driver.findElement(By.xpath("//*[@id='groupListGrid']//tr[1]//td[1]")).getText();
		
		Assert.assertEquals(New_Created_Group, name);
	}
	
	public void Dealer_Create() throws InterruptedException, IOException
	{
		WebElement Setting=driver.findElement(By.xpath("//*[@class='adminMenu']/a"));
		Setting.click();
		Thread.sleep(1000);
		
		WebElement Dealer_Icon=driver.findElement(By.xpath("//*[@data-orgroute='dealers']"));
		Dealer_Icon.click();
		
		WebDriverWait wait = new WebDriverWait(driver, 50);
		 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//*[@class='view-title']//*[@class='btn-group'])[1]//a")));
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
		 Thread.sleep(2000);
		 
		WebElement Create_Dealer=driver.findElement(By.xpath("(//*[@class='view-title']//*[@class='btn-group'])[1]//a"));
		Create_Dealer.click();
		Thread.sleep(3000);
		
		String D_code="A_"+date;
		String D_name="Auto_dealer_"+date;
		
		WebElement Dealer_Code=driver.findElement(By.xpath("//*[@class='inpage-menu-buttons']//*[@name='code']"));
		Dealer_Code.sendKeys(D_code);
		Thread.sleep(1000);
		
		WebElement Dealer_Name=driver.findElement(By.xpath("//*[@class='inpage-menu-buttons']//*[@name='name']"));
		Dealer_Name.sendKeys(D_name);
		Thread.sleep(2000);
		
		excel.writeStringData(4,4,0,D_name);
		excel.writeStringData(4,4,1,D_code);
		
		WebElement Dealer_Save_Close=driver.findElement(By.xpath("//*[@class='btn btn-success dlrCreateSubmitBtn']"));
		Dealer_Save_Close.click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='btn btn-primary btn-save updateDlr']")));
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
		 Thread.sleep(2000);
		 
		WebElement Save_Button=driver.findElement(By.xpath("//*[@class='btn btn-primary btn-save updateDlr']"));
		Save_Button.click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@type='search']")));
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
		 Thread.sleep(2000);
		 
		WebElement Search=driver.findElement(By.xpath("//*[@type='search']"));
		Search.sendKeys(D_name);
		Thread.sleep(3000);
		
		for(int x=0; x<10; x++)
		{
		if(driver.findElement(By.xpath("//*[@id='dlrListGrid']//tr[1]//td[1]")).getText().equals("Nothing found - sorry"))
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
		
		String D_Code1=driver.findElement(By.xpath("//*[@id='dlrListGrid']//tr[1]//td[1]")).getText();
		String D_Name1=driver.findElement(By.xpath("//*[@id='dlrListGrid']//tr[1]//td[2]")).getText();
		
		Assert.assertEquals(D_Code1, D_code);
		Assert.assertEquals(D_Name1, D_name);
		
	}
	
	public void User_CSV_Create() throws InterruptedException, IOException
	{
		WebElement Setting=driver.findElement(By.xpath("//*[@class='adminMenu']/a"));
		Setting.click();
		Thread.sleep(1000);
		
		WebElement User_Icon=driver.findElement(By.xpath("//*[@data-orgroute='users']"));
		User_Icon.click();
		
		WebDriverWait wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='orgListGrid_info']")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
		Thread.sleep(7000);
		
		String Entries_Status=driver.findElement(By.xpath("//*[@id='orgListGrid_info']")).getText();
		
		String[] words=Entries_Status.split("\\s");
		 for(int i=0; i<words.length; i++){  
			 User_Count=words[5];
		 }
		 System.out.println(User_Count);
		
		WebElement CSV_toggle=driver.findElement(By.xpath("//*[@class='btn btn-primary dropdown-toggle']"));
		CSV_toggle.click();
		Thread.sleep(2000);
		
		WebElement CSV_import=driver.findElement(By.xpath("//*[@class='bulkImport']"));
		CSV_import.click();
		Thread.sleep(5000);
		
		WebElement CSV_Browse=driver.findElement(By.xpath("//*[@class='btn btn-success btn-file']"));
		CSV_Browse.click();
		Thread.sleep(1000);	
		
		Runtime.getRuntime().exec("C:\\Excel Data\\AutoIT\\fileUpload.exe");	//Calling AutoIT Script from Desktop
		Thread.sleep(5000);

		WebElement Processing=driver.findElement(By.xpath("//*[@class='pull-right btn btn-primary importProcessConfirm']"));
		Processing.click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//*[@class='successTransaction'])[1]")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
		Thread.sleep(3000);
		
		int Success_Count=Integer.parseInt(driver.findElement(By.xpath("(//*[@class='successTransaction'])[1]")).getText());
		
		Assert.assertEquals(1, Success_Count);
		
		WebElement CSV_Continue=driver.findElement(By.xpath("(//*[@class='btn btn-primary btn-save'])[1]"));
		CSV_Continue.click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//*[@class='successTransaction'])[2]")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
		Thread.sleep(3000);
		
		int Inserted_Count=Integer.parseInt(driver.findElement(By.xpath("(//*[@class='successTransaction'])[2]")).getText());
		
		Assert.assertEquals(1, Inserted_Count);
		
		WebElement View_User_List=driver.findElement(By.xpath("(//*[@class='btn btn-primary btn-save'])[2]"));
		View_User_List.click();
		
	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@type='search']")));
	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
	Thread.sleep(3000);
	
	String Username=excel.getData(4, 7, 0);
	
	WebElement Search=driver.findElement(By.xpath("//*[@type='search']"));
	Search.sendKeys(Username);
	Thread.sleep(2000);
	
	for(int x=0; x<10; x++)
	{
	if(driver.findElement(By.xpath("//*[@id='orgListGrid']//tr[1]//td[1]")).getText().equals("Nothing found - sorry"))
	{
	driver.navigate().refresh();
	}
	else
	{
	x=10;
	}
	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='orgListGrid']//tr[1]")));
	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
	Thread.sleep(3000);
	}
	
	String Username1=driver.findElement(By.xpath("//*[@id='orgListGrid']//tr[1]//td[4]")).getText();
	
	Assert.assertEquals(Username1, Username);
	
	String Entries_Status_After=driver.findElement(By.xpath("//*[@id='orgListGrid_info']")).getText();
	
	String[] words1=Entries_Status_After.split("\\s");
	 for(int i=0; i<words1.length; i++){  
		 User_Count_After=words1[9];
	 }
	 System.out.println(User_Count_After);
	 
	 Assert.assertEquals(Integer.parseInt(User_Count)+1, Integer.parseInt(User_Count_After)); 
	 Thread.sleep(2000);
	 
	 WebElement User_Edit=driver.findElement(By.xpath("//*[@class='btn btn-primary btn-sm ']"));
	 User_Edit.click();
	 
	 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@name='default_language']")));
	 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));	
	 Thread.sleep(2000);
	 
	 Select Default_Language=new Select(driver.findElement(By.xpath("//*[@name='default_language']")));
	 Default_Language.selectByValue("es_es");
	 Thread.sleep(5000);
	 
	 driver.findElement(By.xpath("//*[@class='btn btn-primary btn-save ']")).click();
	 
	 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='orgListGrid']//tr[1]//td[1]")));
	 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
	 Thread.sleep(3000);
	 
	 WebElement User_Edit1=driver.findElement(By.xpath("//*[@class='btn btn-primary btn-sm ']"));
	 User_Edit1.click();
	 
	 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@name='default_language']")));
	 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));	
	 Thread.sleep(2000);
	 
	 WebElement Associate_Dealer=driver.findElement(By.xpath("//*[@role='tablist']//li[2]//a"));
	 Associate_Dealer.click();
	 Thread.sleep(5000);
	 
	 WebElement Search1=driver.findElement(By.xpath("//*[@type='search']"));
	 Search1.sendKeys("3973");
	 Thread.sleep(2000);
	 
	 driver.findElement(By.xpath("//*[@class=' select-checkbox']")).click();
	 Thread.sleep(2000);
	 
	 driver.findElement(By.xpath("//*[@class='btn btn-primary btn-save ']")).click();
	 
	 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='orgListGrid']//tr[1]//td[1]")));
	 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
	 Thread.sleep(3000);
	}
	
	public void User_Create() throws InterruptedException, IOException
	{	
		WebDriverWait wait = new WebDriverWait(driver, 50);
		
		WebElement Search=driver.findElement(By.xpath("//*[@type='search']"));
		Search.clear();
		Thread.sleep(3000);
	
	String Entries_Status=driver.findElement(By.xpath("//*[@id='orgListGrid_info']")).getText();
	
	String[] words=Entries_Status.split("\\s");
	 for(int i=0; i<words.length; i++){  
		 User_Count=words[9];
	 }
	 System.out.println(User_Count);
	 
	WebElement User_Create=driver.findElement(By.xpath("//*[@class='btn btn-primary ct-context showUserCreateBtn ']"));
	User_Create.click();
	Thread.sleep(3000);
	
	String Username="Auto_user"+date.toString().replaceAll("[^a-zA-Z0-9\\\\s+]", "_")+"@k2k.net";
	String Password="Test123$";
	
	excel.writeStringData(4, 13, 0, Username);
	excel.writeStringData(4, 16, 0, Username);
	excel.writeStringData(4, 13, 1, Password);
	
	driver.findElement(By.xpath("//*[@name='first_name']")).sendKeys("Automation");
	driver.findElement(By.xpath("//*[@name='last_name']")).sendKeys("Test");
	driver.findElement(By.xpath("//*[@id='createEmail']")).sendKeys(Username);
	driver.findElement(By.xpath("//*[@name='password']")).sendKeys(Password);
	driver.findElement(By.xpath("//*[@name='confirm-password']")).sendKeys(Password);
	driver.findElement(By.xpath("(//*[@name='userAccess'])[2]")).click();
	
	driver.findElement(By.xpath("//*[@class='pull-right btn btn-success createUserBtn']")).click();
	Thread.sleep(3000);
	
	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='btn btn-primary btn-save ']")));
	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
	Thread.sleep(1000);
	
	driver.findElement(By.xpath("//*[@class='btn btn-primary btn-save ']")).click();
	
	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@type='search']")));
	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
	Thread.sleep(3000);
	
	WebElement Search1=driver.findElement(By.xpath("//*[@type='search']"));
	Search1.clear();
	Search1.sendKeys(Username);
	Thread.sleep(5000);
	
	for(int x=0; x<10; x++)
	{
	if(driver.findElement(By.xpath("//*[@id='orgListGrid']//tr[1]//td[1]")).getText().equals("Nothing found - sorry"))
	{
	driver.navigate().refresh();
	}
	else
	{
	x=10;
	}
	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='orgListGrid']//tr[1]")));
	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
	Thread.sleep(3000);
	}
	
	String Username1=driver.findElement(By.xpath("//*[@id='orgListGrid']//tr[1]//td[4]")).getText();
	
	Assert.assertEquals(Username1, Username);
	
	String Entries_Status_After=driver.findElement(By.xpath("//*[@id='orgListGrid_info']")).getText();
	
	String[] words1=Entries_Status_After.split("\\s");
	 for(int i=0; i<words1.length; i++){  
		 User_Count_After=words1[9];
	 }
	 System.out.println(User_Count_After);
	 
	 Assert.assertEquals(Integer.parseInt(User_Count)+1, Integer.parseInt(User_Count_After)); 
	 Thread.sleep(2000);
	 
	 WebElement User_Edit=driver.findElement(By.xpath("//*[@class='btn btn-primary btn-sm ']"));
	 User_Edit.click();
	 
	 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@name='default_language']")));
	 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));	
	 Thread.sleep(2000);
	 
	 Select Default_Language=new Select(driver.findElement(By.xpath("//*[@name='default_language']")));
	 Default_Language.selectByValue("es_es");
	 Thread.sleep(5000);
	 
	 driver.findElement(By.xpath("//*[@class='btn btn-primary btn-save ']")).click();
	 
	 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='orgListGrid']//tr[1]//td[1]")));
	 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
	 Thread.sleep(3000);
	 
	 WebElement User_Edit1=driver.findElement(By.xpath("//*[@class='btn btn-primary btn-sm ']"));
	 User_Edit1.click();
	 
	 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@name='default_language']")));
	 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));	
	 Thread.sleep(2000);
	 
	 WebElement Associate_Dealer=driver.findElement(By.xpath("//*[@role='tablist']//li[2]//a"));
	 Associate_Dealer.click();
	 Thread.sleep(5000);
	 
	 WebElement Search2=driver.findElement(By.xpath("//*[@type='search']"));
	 Search2.clear();
	 Search2.sendKeys("3973");
	 Thread.sleep(5000);
	 
	 driver.findElement(By.xpath("//*[@class=' select-checkbox']")).click();
	 Thread.sleep(2000);
	 
	 driver.findElement(By.xpath("//*[@class='btn btn-primary btn-save ']")).click();
	 
	 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='orgListGrid']//tr[1]//td[1]")));
	 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
	 Thread.sleep(3000);
	}
	
	public void Script() throws InterruptedException, IOException
	{
		WebElement Setting=driver.findElement(By.xpath("//*[@class='adminMenu']/a"));
		Setting.click();
		Thread.sleep(1000);
		
		WebElement Script_Call=driver.findElement(By.xpath("//*[@data-orgroute='callscripts']"));
		Script_Call.click();
		
		WebDriverWait wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='pull-right']//a")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
		Thread.sleep(3000);
		
		WebElement Script_Create=driver.findElement(By.xpath("//*[@class='pull-right']//a"));
		Script_Create.click();
		Thread.sleep(3000);
		
		String Script_Name="Auto_Script_"+date;
		
		excel.writeStringData(4,10,0,Script_Name);
		
		WebElement Script_Name_TextBox=driver.findElement(By.xpath("//*[@name='title']"));
		Script_Name_TextBox.sendKeys(Script_Name);
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//*[@class='pull-right btn btn-success createScriptBtn']")).click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='note-editable panel-body']")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
		Thread.sleep(2000);
		
		String Script_Description="Hi, Welcome to automatic world. "+date;
		
		excel.writeStringData(4,10,1,Script_Description);
		
		WebElement Call_Script=driver.findElement(By.xpath("//*[@class='note-editable panel-body']"));
		Call_Script.sendKeys(Script_Description);
		Thread.sleep(5000);
		
		WebElement Script_Save=driver.findElement(By.xpath("//*[@class='btn btn-primary btn-save saveScriptBtn']"));
		Script_Save.click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@type='search']")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
		Thread.sleep(2000);
		
		WebElement Script_Filter=driver.findElement(By.xpath("//*[@type='search']"));
		Script_Filter.sendKeys(Script_Name);
		Thread.sleep(2000);
		
		WebElement Script_Edit=driver.findElement(By.xpath("//*[@id='callscriptListGrid']//tr[1]//td[6]//a[2]"));
		Script_Edit.click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@name='status']")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
		Thread.sleep(2000);
		
		Select status=new Select(driver.findElement(By.xpath("//*[@name='status']")));
		status.selectByValue("published");
		Thread.sleep(5000);
		
		WebElement Script_Edit_Save=driver.findElement(By.xpath("//*[@class='btn btn-primary btn-save saveScriptBtn']"));
		Script_Edit_Save.click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='callscriptListGrid']//tr[1]//td[3]//p")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
		Thread.sleep(2000);
		
		String Script_Status=driver.findElement(By.xpath("//*[@id='callscriptListGrid']//tr[1]//td[3]//p")).getText();
		
		Assert.assertEquals(Script_Status, "published");
		
		Thread.sleep(2000);
	}
	
	public void Pricing() throws InterruptedException, IOException
	{
		WebElement Setting=driver.findElement(By.xpath("//*[@class='adminMenu']/a"));
		Setting.click();
		Thread.sleep(1000);
		
		WebElement Pricing_Call=driver.findElement(By.xpath("//*[@data-orgroute='pricing']"));
		Pricing_Call.click();
		
		WebDriverWait wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='btn btn-primary ct-context showPricingBtn']")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
		Thread.sleep(2000);
		
		WebElement Add_Pricing=driver.findElement(By.xpath("//*[@class='btn btn-primary ct-context showPricingBtn']"));
		Add_Pricing.click();
		Thread.sleep(5000);
		
		driver.findElement(By.xpath("(//*[@name='region_code'])[1]")).sendKeys("AUTO");
		driver.findElement(By.xpath("(//*[@name='region'])[1]")).sendKeys("Automatic Smoketest");
		driver.findElement(By.xpath("(//*[@name='manufacturer'])[1]")).sendKeys("MB");
		driver.findElement(By.xpath("(//*[@name='otrp'])[1]")).sendKeys("13.1");
		driver.findElement(By.xpath("(//*[@name='minotrp'])[1]")).sendKeys("10000");
		driver.findElement(By.xpath("(//*[@name='maxotrp'])[1]")).sendKeys("30000");
		driver.findElement(By.xpath("(//*[@name='frozen_charge'])[1]")).sendKeys("1995");
		driver.findElement(By.xpath("(//*[@name='admin_charge'])[1]")).sendKeys("99");
		
		WebElement Pricing_Save_Close=driver.findElement(By.xpath("//*[@id='saveClosePricingBtn']"));
		Pricing_Save_Close.click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='pricingListGrid']//tr[1]")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
		Thread.sleep(2000);
		
		String Region_Code=driver.findElement(By.xpath("//*[@id='pricingListGrid']//tr[1]//td[1]")).getText();
		String Region=driver.findElement(By.xpath("//*[@id='pricingListGrid']//tr[1]//td[2]")).getText();
		String manufacturer=driver.findElement(By.xpath("//*[@id='pricingListGrid']//tr[1]//td[3]")).getText();
		String otrp=driver.findElement(By.xpath("//*[@id='pricingListGrid']//tr[1]//td[4]")).getText();
		String minotrp=driver.findElement(By.xpath("//*[@id='pricingListGrid']//tr[1]//td[5]")).getText();
		String maxotrp=driver.findElement(By.xpath("//*[@id='pricingListGrid']//tr[1]//td[6]")).getText();		
		String frozen_charge=driver.findElement(By.xpath("//*[@id='pricingListGrid']//tr[1]//td[7]")).getText();
		String admin_charge=driver.findElement(By.xpath("//*[@id='pricingListGrid']//tr[1]//td[8]")).getText();
		
		Assert.assertEquals(Region_Code, "AUTO");
		Assert.assertEquals(Region, "Automatic Smoketest");
		Assert.assertEquals(manufacturer, "MB");
		Assert.assertEquals(otrp, "13.1");
		Assert.assertEquals(minotrp, "10000.0");
		Assert.assertEquals(maxotrp, "30000.0");
		Assert.assertEquals(frozen_charge, "1995.0");
		Assert.assertEquals(admin_charge, "99.0");
		
		Thread.sleep(2000);
		
		WebElement Pricing_Edit=driver.findElement(By.xpath("//*[@id='pricingEditBtn']"));
		Pricing_Edit.click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//*[@name='otrp'])[3]")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("(//*[@name='otrp'])[3]")).clear();
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//*[@name='otrp'])[3]")).sendKeys("14.5");
		Thread.sleep(2000);
		
		WebElement Pricing_Save=driver.findElement(By.xpath("//*[@class='btn btn-primary pull-right pricingSaveBtn']"));
		Pricing_Save.click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='pricingListGrid']//tr[1]//td[4]")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
		Thread.sleep(2000);
		
		String new_otrp=driver.findElement(By.xpath("//*[@id='pricingListGrid']//tr[1]//td[4]")).getText();
		Assert.assertEquals(new_otrp, "14.5");
		
		Thread.sleep(1000);
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
		
		Group_Create();
		Thread.sleep(3000);		
		
		Dealer_Create();
		Thread.sleep(3000); 	
		
		User_CSV_Create();
		Thread.sleep(5000);	
		
		User_Create();
		Thread.sleep(5000);
		
		Script();
		Thread.sleep(5000);		
		
		Pricing();
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
