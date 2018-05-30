package csl.v4.smokeTest.Automation;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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


public class Dealer_User {
	public static String driverPath = "C:/new/";
	 public WebDriver driver;
	 String campaign_Cust;
	 int DContacted_Achieved, DAppointed_Achieved, DAttended_Achieved, DSold_Achieved;
	 int DContacted_Achieved_New, DAppointed_Achieved_New, DAttended_Achieved_New, DSold_Achieved_New;
	 String FOffer_View_OTRP, FOffer_View_Term, FOffer_View_Rate, FOffer_View_MPA;
	 String Customer_OTRP, Customer_MPA, Customer_Term;
	 String Car_year, Car_Make, Car_OR_Sign, Car_Model, Car_OTRP1;
	 int Total_Customers_No_Group, Total_Equity_No_Group, Total_Parity_No_Group, Total_Customers_No_Dealer, Total_Equity_No_Dealer, Total_Parity_No_Dealer, Total_Customers_No_GroupAll, Total_Equity_No_GroupAll, Total_Parity_No_GroupAll, Filtered_GroupAll_Customer_count;
	 ExcelDataConfig excel=new ExcelDataConfig("C:\\Excel Data\\TestData.xlsx");
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
	 
	 
	 public void KPI() throws InterruptedException, IOException
	 {
		 WebElement KPI_Toggle=driver.findElement(By.xpath("//*[@class='form form-inline form-switch pull-right']//div[2]//label"));
		 KPI_Toggle.click();
		 
		 WebDriverWait wait = new WebDriverWait(driver, 50);
		 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='contactedAchievedInt']")));
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
		 Thread.sleep(1000);
		 
		 DContacted_Achieved=Integer.parseInt(driver.findElement(By.xpath("//*[@id='contactedAchievedInt']")).getText());
		 DAppointed_Achieved=Integer.parseInt(driver.findElement(By.xpath("//*[@id='appointedAchievedInt']")).getText());
		 DAttended_Achieved=Integer.parseInt(driver.findElement(By.xpath("//*[@id='attendedAchievedInt']")).getText());
		 DSold_Achieved=Integer.parseInt(driver.findElement(By.xpath("//*[@id='soldAchievedInt']")).getText());

		 excel.writeData(3,8,0,DContacted_Achieved);
		 excel.writeData(3,8,1,DAppointed_Achieved);
		 excel.writeData(3,8,2,DAttended_Achieved);
		 excel.writeData(3,8,3,DSold_Achieved);  
		
		 WebElement KPI_Close=driver.findElement(By.xpath("//*[@class='btn btn-default close-kpi-pod pull-left']/span"));
		 KPI_Close.click();
		 
		 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h3[@id='early-good-to-go']")));
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='p1Loader']")));
		 Thread.sleep(2000);
	 }
	 
	 public void Dealer_Assigned_Customer() throws InterruptedException
	 {
		 WebElement Dealer_Menu = driver.findElement(By.xpath("//*[@data-original-title='Go-To Menu']"));
			Dealer_Menu.click();
			Thread.sleep(1000);
			
		WebElement Dealer_Assigned_Customer = driver.findElement(By.xpath("//button[@id='assignUsers']")); 
		Dealer_Assigned_Customer.click();
		
		WebDriverWait wait = new WebDriverWait(driver, 50);
		 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='btn btn-primary ct-context searchBtn searchButtonHide show']")));
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
		 Thread.sleep(2000);
		
		WebElement Search_Dealer_Assigned_Customer = driver.findElement(By.xpath("//*[@class='btn btn-primary ct-context searchBtn searchButtonHide show']")); 
		Search_Dealer_Assigned_Customer.click();

		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@placeholder='Keywords Include']")));
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
		 Thread.sleep(2000);
		
		String Cust_Name=excel.getData(2, 20, 0);
		
		WebElement Search_Dealer_Assigned_Customer_Box = driver.findElement(By.xpath("//*[@placeholder='Keywords Include']")); 
		Search_Dealer_Assigned_Customer_Box.sendKeys(Cust_Name);
		Thread.sleep(3000);
		
		WebElement Search_Apply = driver.findElement(By.xpath("//*[@class='v-align']//i")); 
		Search_Apply.click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='customersListGrid']//tr[1]")));
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
		 Thread.sleep(2000);
		
		String Dealer_Customer_Name = driver.findElement(By.xpath("//*[@id='customersListGrid']//tr[1]//td[3]")).getText();
		Assert.assertEquals(Cust_Name, Dealer_Customer_Name);
		Thread.sleep(3000);
	 }
	 
	 public void Dealer_Assigned_Campaign() throws InterruptedException
	 {
		 WebElement Dealer_Menu = driver.findElement(By.xpath("//*[@data-original-title='Go-To Menu']"));
			Dealer_Menu.click();
			Thread.sleep(1000);
			
		WebElement Dealer_Campaign = driver.findElement(By.xpath("//button[@id='campaigns']")); 
		Dealer_Campaign.click();
		
		WebDriverWait wait = new WebDriverWait(driver, 50);
		 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='nav nav-tabs']//li[2]")));
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
		 Thread.sleep(2000);
		
		WebElement Assigned_Campaign_Button = driver.findElement(By.xpath("//*[@class='nav nav-tabs']//li[2]")); 
		Assigned_Campaign_Button.click();

		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@aria-controls='asssignedCampaignListGrid']")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
		Thread.sleep(2000);
 
		
		String Camp_Name=excel.getData(2, 26, 0);
		
		WebElement Camp_Name_Filter = driver.findElement(By.xpath("//input[@aria-controls='asssignedCampaignListGrid']")); 
		Camp_Name_Filter.sendKeys(Camp_Name);
		Thread.sleep(1000);
		
		String Dealer_Campaign_Name = driver.findElement(By.xpath("//*[@id='asssignedCampaignListGrid']//tr[1]//td[1]")).getText();
		Assert.assertEquals(Camp_Name, Dealer_Campaign_Name);
		Thread.sleep(3000);
		
	 }
	 
	 public void search() throws InterruptedException
	 {
		 WebElement Dealer_Menu = driver.findElement(By.xpath("//*[@data-original-title='Go-To Menu']"));
		Dealer_Menu.click();
		Thread.sleep(1000);
			
		WebElement Dealer_Assigned_Customer = driver.findElement(By.xpath("//button[@id='customers']")); 
		Dealer_Assigned_Customer.click();
		
		WebDriverWait wait = new WebDriverWait(driver, 50);
		 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='total_count']")));
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
		 Thread.sleep(2000);
		 
		int Dealer_Total_Customer=Integer.parseInt(driver.findElement(By.xpath("//span[@class='total_count']")).getText().replaceAll("[^a-zA-Z0-9\\s+]", ""));
		int Dealer_Filtered_Customer_Before=Integer.parseInt(driver.findElement(By.xpath("//span[@class='total_count']")).getText().replaceAll("[^a-zA-Z0-9\\s+]", ""));
		
		
		WebElement Search_Icon = driver.findElement(By.xpath("//*[@class='searchCustomers']//a"));
		Search_Icon.click();
		
		 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@data-original-title='Asset']")));
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
		 Thread.sleep(2000);
		
		WebElement Search_Asset = driver.findElement(By.xpath("//a[@data-original-title='Asset']"));
		Search_Asset.click();
		Thread.sleep(2000);
		WebElement Asset_Model = driver.findElement(By.xpath("//*[@data-original-title='Model']"));
		Asset_Model.click();
		Thread.sleep(1000);
		WebElement Model_Search = driver.findElement(By.xpath("//*[@class='superSearch-options col-xs-8']//*[@data-type='model']//input"));
		Model_Search.sendKeys(" C CLASS");
		Thread.sleep(1000);
		WebElement Model_Select = driver.findElement(By.xpath("//div[@class='chosen-drop']/ul/li[1]"));
		Model_Select.click();
		Thread.sleep(1000);
		Search_Asset.click();
		Thread.sleep(2000);
	
		WebElement Search_Contract = driver.findElement(By.xpath("//a[@data-original-title='Contract']"));
		Search_Contract.click();
		Thread.sleep(2000);
		WebElement Contract_Payments = driver.findElement(By.xpath("//*[@data-original-title='Payments']"));
		Contract_Payments.click();
		Thread.sleep(2000);
		Select Payment_Made=new Select(driver.findElement(By.name("paymentMade")));
		Payment_Made.selectByValue("3 Made");
		Thread.sleep(1000);
		Select Payment_Left=new Select(driver.findElement(By.name("paymentRem")));
		Payment_Left.selectByValue("12 Left");
		Thread.sleep(1000);
		Search_Contract.click();
		Thread.sleep(2000);
		
		WebElement Search_Customer = driver.findElement(By.xpath("//a[@data-original-title='Customer']"));
		Search_Customer.click();
		Thread.sleep(2000);
		WebElement Customer_LeadCount = driver.findElement(By.xpath("//*[@data-original-title='Lead Type']"));
		Customer_LeadCount.click();
		Thread.sleep(1000);
		WebElement LeadCount_Early_G2G = driver.findElement(By.xpath("//div[22]/div/div[4]/div/label/div"));
		LeadCount_Early_G2G.click();
		Thread.sleep(1000);
		
		WebElement Customer_Parity = driver.findElement(By.xpath("//*[@data-original-title='Parity']"));
		Customer_Parity.click();
		Thread.sleep(2000);
		WebElement ParityStart = driver.findElement(By.name("parityStart"));
		ParityStart.sendKeys("-200");
		Thread.sleep(2000);
		Search_Customer.click();
		Thread.sleep(2000);
		
		WebElement Search_Apply = driver.findElement(By.xpath("//*[@class='v-align']//i")); 
		Search_Apply.click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='customersGrid']//tr[1]//td[3]")));
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
		 Thread.sleep(2000);
		
		Search_Icon.click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@data-original-title='Asset']")));
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
		 Thread.sleep(2000);
		
		WebElement Reset_Filter = driver.findElement(By.id("clearFilters")); 
		Reset_Filter.click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='customersGrid']//tr[1]//td[3]")));
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
		 Thread.sleep(2000);
		
		int Dealer_Filtered_Customer_After=Integer.parseInt(driver.findElement(By.xpath("//span[@class=\"total_count\"]")).getText().replaceAll("[^a-zA-Z0-9\\s+]", ""));
		
		Assert.assertEquals(Dealer_Filtered_Customer_Before, Dealer_Filtered_Customer_After);
		Thread.sleep(2000);
		
		Search_Icon.click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@data-original-title='Asset']")));
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
		 Thread.sleep(2000);
		
		WebElement Camp_Name_Filter = driver.findElement(By.xpath("(//*[@name='keywords'])[3]")); 
		Camp_Name_Filter.sendKeys("C Class, Premium");
		Thread.sleep(2000);
		WebElement Search_Asset1 = driver.findElement(By.xpath("//a[@data-original-title='Asset']"));
		Search_Asset1.click();
		Thread.sleep(2000);
		WebElement Search_Contract1 = driver.findElement(By.xpath("//a[@data-original-title='Contract']"));
		Search_Contract1.click();
		Thread.sleep(2000);
		WebElement Contract_date=driver.findElement(By.xpath("//*[@id='contractDate']"));
		Contract_date.click();
		Thread.sleep(1000);
		WebElement Contract_Expiry_From=driver.findElement(By.xpath("//*[@name='expiryFrom']"));
		Contract_Expiry_From.click();
		Thread.sleep(1000);
		Date today = new Date();  
		Calendar cal = Calendar.getInstance();
		
		int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
		System.out.print(dayOfMonth);
		
		WebElement Contract_Start_Date = driver.findElement(By.xpath("(//td[@class='day'][contains(text(),"+dayOfMonth+")])[1]"));
		Contract_Start_Date.click();
		Thread.sleep(1000);
		Search_Asset1.click();
		Thread.sleep(1000);
		Search_Contract1.click();
		Thread.sleep(1000);
		WebElement Search_Customer1 = driver.findElement(By.xpath("//a[@data-original-title='Customer']"));
		Search_Customer1.click();
		Thread.sleep(2000);
		WebElement Customer_LeadType = driver.findElement(By.xpath("//*[@data-original-title='Lead Type']"));
		Customer_LeadType.click();
		Thread.sleep(1000);
		WebElement Customer_LeadType_REM2 = driver.findElement(By.xpath("//*[@class='togglebox col-sm-4'][2]//label"));
		Customer_LeadType_REM2.click();
		Thread.sleep(1000);
		
		WebElement Search_Apply1 = driver.findElement(By.xpath("//*[@class='v-align']//i")); 
		Search_Apply1.click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='customersGrid']//tr[1]//td[3]")));
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
		 Thread.sleep(2000);
	 }
	
	public void calculator() throws InterruptedException
	{		
		WebElement Calculator_Button = driver.findElement(By.xpath("(//*[@class='searchCustomersCalculator'])[1]"));
		Calculator_Button.click();
		
		WebDriverWait wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@name='depositMinOther']")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
		Thread.sleep(2000);
		 
		driver.findElement(By.xpath("//*[@name='otrp']")).sendKeys("30000");
		driver.findElement(By.xpath("//*[@name='rate']")).sendKeys("5.41");
		Select term=new Select(driver.findElement(By.xpath("//*[@name='term']")));
		term.selectByValue("36");
		driver.findElement(By.xpath("//*[@name='rv']")).sendKeys("1000");
		driver.findElement(By.xpath("//*[@name='depositContribution']")).sendKeys("100");
		driver.findElement(By.xpath("//*[@name='cash']")).sendKeys("500");
		driver.findElement(By.xpath("(//*[@class='form-group form-group-border']//*[@class='radio-label'])[1]")).click();
		driver.findElement(By.xpath("//*[@name='depositMinOther']")).sendKeys("10");
		Thread.sleep(5000);
		WebElement Search_Apply1 = driver.findElement(By.xpath("//*[@class='v-align']//i")); 
		Search_Apply1.click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='customersGrid']//tr[1]//td[3]")));
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
		 Thread.sleep(2000);
	}
	 
	public void Customer_Page() throws InterruptedException
	{
	
	WebElement CP_Customer_name= driver.findElement(By.xpath("//*[@id='customersListGrid']//tr[2]//td[3]/a"));
	CP_Customer_name.click();
	
	WebDriverWait wait = new WebDriverWait(driver, 50);
	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='panel-collapse']//td//select")));
	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
	Thread.sleep(2000);
	
	Select CP_status= new Select(driver.findElement(By.xpath("//*[@class='panel-collapse']//td//select")));
	CP_status.selectByValue("Sold");
	Thread.sleep(2000);
	String CP_Status_changed=CP_status.getFirstSelectedOption().getText();
	Assert.assertEquals(CP_Status_changed, "Sold");
	
	WebElement CP_Contact_Preference= driver.findElement(By.xpath("//*[@class='badge call contact-status do']"));
	CP_Contact_Preference.click();
	Thread.sleep(1000);
	
	WebElement CP_History= driver.findElement(By.xpath("//*[@class='panel-collapse']//td[2]/a"));
	CP_History.click();
	
	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//*[@class='by pull-left'])[1]")));
	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
	Thread.sleep(2000);
	
	String x1=driver.findElement(By.xpath("(//*[@class='by pull-left'])[1]")).getText().substring(2);
	String x2=driver.findElement(By.xpath("(//*[@class='by pull-left'])[2]")).getText().substring(2);
	
	String x1_date=driver.findElement(By.xpath("(//*[@class='comment-date pull-right'])[1]")).getText().substring(0, 10).replaceAll("[^a-zA-Z0-9\\s+]", "-");
	String x2_date=driver.findElement(By.xpath("(//*[@class='comment-date pull-right'])[1]")).getText().substring(0, 10).replaceAll("[^a-zA-Z0-9\\s+]", "-");
	
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	LocalDate localDate = LocalDate.now();
	String ss=localDate.toString();
	Thread.sleep(2000);
	
	Assert.assertEquals(x1, "Do not Call");
	Assert.assertEquals(x1_date, ss);
	Assert.assertEquals(x2, "Sold");
	Assert.assertEquals(x2_date, ss);
	
	WebElement CP_Contact_History= driver.findElement(By.xpath("//*[@aria-controls='contactHistory']"));
	CP_Contact_History.click();
	Thread.sleep(1000);
	
	WebElement CP_Add_Comment= driver.findElement(By.xpath("//*[@class='add-comment-bar']"));
	CP_Add_Comment.click();
	Thread.sleep(1000);
	
	String message="Automation Test case Check";
	WebElement CP_Type_Comment= driver.findElement(By.xpath("//*[@id='newCommentBox']"));
	CP_Type_Comment.sendKeys(message);
	Thread.sleep(1000);
	
	WebElement Comment_Post= driver.findElement(By.xpath("//*[contains(text(),'Post')]"));
	Comment_Post.click();
	
	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='comments']//p[@class='by pull-left ']")));
	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
	Thread.sleep(7000);
	
	WebElement History_Close= driver.findElement(By.xpath("//*[@class='modal right commentsModal fade in']//button[@class='close']"));
	History_Close.click();
	
	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='panel-collapse']//td//select")));
	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
	Thread.sleep(2000);
	
	CP_History.click();
	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//*[@class='by pull-left'])[1]")));
	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
	Thread.sleep(2000);
	
	CP_Contact_History.click();
	Thread.sleep(1000);
	
	String CH_Comment_Dealer=driver.findElement(By.xpath("//*[@class='comments']//p[@class='by pull-left ']")).getText();
	String CH_Comment_Date=driver.findElement(By.xpath("//*[@class='comments']//p[@class='comment-date pull-right']")).getText().substring(0, 10).replaceAll("[^a-zA-Z0-9\\s+]", "-");
	String CH_Comment_Message=driver.findElement(By.xpath("//*[@class='comments']//p[@class='message comment-message']")).getText();
	
	Assert.assertEquals(CH_Comment_Dealer, "dealer autotest said..");
	Assert.assertEquals(CH_Comment_Date, ss);
	Assert.assertEquals(CH_Comment_Message, message);
	
	WebElement Comment_Edit= driver.findElement(By.xpath("//*[@class='btn-edit edit-comment ']"));
	Comment_Edit.click();
	Thread.sleep(1000);

	String New_Message="comment edited!!   ";

	WebElement Edit_Text= driver.findElement(By.xpath("//*[@id='editCommentBox']"));
	Edit_Text.sendKeys(New_Message);
	Thread.sleep(7000);
	
	WebElement Text_Save= driver.findElement(By.xpath("//*[@class='btn btn-sm btn-primary pull-right edit-save']"));
	Text_Save.click();
	
	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='comments']//p[@class='by pull-left ']")));
	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
	Thread.sleep(7000);
	
	History_Close.click();
	
	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='panel-collapse']//td//select")));
	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
	Thread.sleep(2000);
	
	CP_History.click();
	
	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//*[@class='by pull-left'])[1]")));
	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
	Thread.sleep(5000);
	
	CP_Contact_History.click();
	Thread.sleep(1000);
	
	String CH_Comment_Dealer_edited=driver.findElement(By.xpath("//*[@class='comments']//p[@class='by pull-left ']")).getText();
	String CH_Comment_Date_edited=driver.findElement(By.xpath("//*[@class='comments']//p[@class='comment-date pull-right']")).getText().substring(0, 10).replaceAll("[^a-zA-Z0-9\\s+]", "-");
	String CH_Comment_Message_edited=driver.findElement(By.xpath("//*[@class='comments']//p[@class='message comment-message']")).getText();
	
	Assert.assertEquals(CH_Comment_Dealer_edited, "dealer autotest said..");
	Assert.assertEquals(CH_Comment_Date_edited, ss);
	Assert.assertEquals(CH_Comment_Message_edited, New_Message+message);
	
	History_Close.click();
	
	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'Customer Details')]")));
	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
	Thread.sleep(2000);
	System.out.println("passs");
	}
	
	public void call_Script() throws InterruptedException 
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("scroll(0,500)");
		 Thread.sleep(2000);
		 
		 WebElement Customer_Detail=driver.findElement(By.xpath("//a[contains(text(),'Customer Details')]"));
		 Customer_Detail.click();
		 
		 WebDriverWait wait = new WebDriverWait(driver, 50);
		 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='btn btn-sm btn-primary showCallscripts']")));
		 Thread.sleep(2000);
		
		 WebElement Script_Call=driver.findElement(By.xpath("//*[@class='btn btn-sm btn-primary showCallscripts']"));
		 Script_Call.click();
		 
		 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@type='search']")));
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
		 Thread.sleep(2000);
		 
		 String Script_Name=excel.getData(4, 10, 0);
		 
		 WebElement Script_Filter=driver.findElement(By.xpath("//*[@type='search']"));
		 Script_Filter.sendKeys(Script_Name);
		 Thread.sleep(2000);
		 
		 WebElement Script_Name_Call=driver.findElement(By.xpath("//*[@id='scriptsGrid']//tr[1]//td[1]//a"));
		 Script_Name_Call.click();
		 
		 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='note-editable panel-body']")));
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
		 Thread.sleep(2000);
		 
		 String CallScript_Description=driver.findElement(By.xpath("//*[@class='note-editable panel-body']")).getText();
		 String AdminScript_Description=excel.getData(4, 10, 1);
		 Thread.sleep(1000);
		 
		 Assert.assertEquals(CallScript_Description, AdminScript_Description);
		 Thread.sleep(2000);
		 
		 driver.findElement(By.xpath("(//*[@class='close'])[4]")).click();
		 Thread.sleep(2000);
		 driver.findElement(By.xpath("(//*[@class='close'])[3]")).click();
		 Thread.sleep(2000);
	}
	
	public void Offer() throws InterruptedException, IOException
	{
		WebElement Offer_Icon=driver.findElement(By.xpath("//*[@class='showDeals']"));
		 Offer_Icon.click();
		 
		 WebDriverWait wait = new WebDriverWait(driver, 50);
		 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='confirmSearch']")));
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
		 Thread.sleep(2000);
		
		WebElement Offer_Apply_filter=driver.findElement(By.xpath("//*[@id='confirmSearch']"));
		Offer_Apply_filter.click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='offerListGrid']//tr[1]//td[10]")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
		Thread.sleep(2000);
	 
		 String FOffer_OTRP=driver.findElement(By.xpath("//*[@id='offerListGrid']//tr[1]//td[10]")).getText();
		 String FOffer_Term=driver.findElement(By.xpath("//*[@id='offerListGrid']//tr[1]//td[5]")).getText();
		 String FOffer_Rate=driver.findElement(By.xpath("//*[@id='offerListGrid']//tr[1]//td[6]")).getText().replaceAll("[^a-zA-Z0-9\\s+]", "");
		 String FOffer_MPA=driver.findElement(By.xpath("//*[@id='offerListGrid']//tr[1]//td[7]")).getText();
		 excel.writeStringData(3,12,0,FOffer_OTRP);
		 excel.writeStringData(3,12,1,FOffer_Term);
		 excel.writeStringData(3,12,2,FOffer_Rate);
		 excel.writeStringData(3,12,3,FOffer_MPA);
		 
		 WebElement FOffer_View=driver.findElement(By.xpath("//*[@id='offerListGrid']//tr[1]//a"));
		 FOffer_View.click();
		 
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//td[@class='deal-heading deal-heading-new']//span)[1]")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
		Thread.sleep(2000);
			 
		String New_OTRP=driver.findElement(By.xpath("(//*[@class='dropdown-toggle newDealOTRP']/span)[1]")).getText().replaceAll("[^a-zA-Z0-9\\s+]", "");
		String New_Term=driver.findElement(By.xpath("(//*[@class='payedit newDealTerm']/span)[1]")).getText().replaceAll("[^a-zA-Z0-9\\s+]", "");
		String New_Rate=driver.findElement(By.xpath("(//*[@class='rate rate-new dropdown dropdown-edit']//span[1])[2]")).getText().replaceAll("[^a-zA-Z0-9\\s+]", "");
		String New_MPA=driver.findElement(By.xpath("//*[@class='newMileageVal']/b/span")).getText().replaceAll("[^a-zA-Z0-9\\s+]", "");
		Thread.sleep(2000);

		Assert.assertEquals(FOffer_OTRP, New_OTRP);
		Assert.assertEquals(FOffer_Term, New_Term);
		Assert.assertEquals(FOffer_Rate, New_Rate);
		Assert.assertEquals(FOffer_MPA, New_MPA);
		Thread.sleep(2000);
		
		WebElement org_branch=driver.findElement(By.xpath("//*[@class='breadcrumb-featured']//li[2]//a"));
		org_branch.click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h3[@id='early-good-to-go']")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='p1Loader']")));
		Thread.sleep(5000);
	}
	
	public void KPI_AFter_Status() throws InterruptedException, IOException
	 {
		 WebElement KPI_Toggle=driver.findElement(By.xpath("//*[@class='form form-inline form-switch pull-right']//div[2]//label"));
		 KPI_Toggle.click();
		 
		 WebDriverWait wait = new WebDriverWait(driver, 50);
		 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='contactedAchievedInt']")));
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
		 Thread.sleep(1000);
		 
		 DContacted_Achieved_New=Integer.parseInt(driver.findElement(By.xpath("//*[@id='contactedAchievedInt']")).getText());
		 DAppointed_Achieved_New=Integer.parseInt(driver.findElement(By.xpath("//*[@id='appointedAchievedInt']")).getText());
		 DAttended_Achieved_New=Integer.parseInt(driver.findElement(By.xpath("//*[@id='attendedAchievedInt']")).getText());
		 DSold_Achieved_New=Integer.parseInt(driver.findElement(By.xpath("//*[@id='soldAchievedInt']")).getText());
		
		 Assert.assertEquals(DContacted_Achieved_New, DContacted_Achieved + 1);
		 Assert.assertEquals(DAppointed_Achieved_New, DAppointed_Achieved + 1 );
		 Assert.assertEquals(DAttended_Achieved_New, DAttended_Achieved + 1);
		 Assert.assertEquals(DSold_Achieved_New, DSold_Achieved + 1);
			
		 WebElement KPI_Close=driver.findElement(By.xpath("//*[@class='btn btn-default close-kpi-pod pull-left']/span"));
		 KPI_Close.click();
		 
		 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h3[@id='early-good-to-go']")));
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='p1Loader']")));
		 Thread.sleep(2000);
	 }
	
	public void Offer_CD_Page() throws InterruptedException, IOException
	{
		 WebElement Offer_Icon=driver.findElement(By.xpath("//*[@class='showDeals']"));
		 Offer_Icon.click();
		 
		 WebDriverWait wait = new WebDriverWait(driver, 50);
		 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='offerListGrid']//tr[1]")));
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
		 Thread.sleep(2000);
		 
		 String Vehicle_Name=driver.findElement(By.xpath("//*[@id='offerListGrid']//tr[1]//td[1]")).getText();
		 String OTRP=driver.findElement(By.xpath("//*[@id='offerListGrid']//tr[1]//td[2]")).getText().replaceAll("[^a-zA-Z0-9\\s+]", "");
		 String Term=driver.findElement(By.xpath("//*[@id=\"offerListGrid\"]//tr[1]//td[4]")).getText().replaceAll("[^a-zA-Z0-9\\s+]", "");
		 String MPA=driver.findElement(By.xpath("//*[@id=\"offerListGrid\"]//tr[1]//td[5]")).getText().replaceAll("[^a-zA-Z0-9\\s+]", "");
		 excel.writeStringData(3,16,0,Vehicle_Name);
		 excel.writeStringData(3,16,1,OTRP);
		 excel.writeStringData(3,16,2,Term);
		 excel.writeStringData(3,16,3,MPA);
		 Thread.sleep(1000);
		 
		 WebElement Customer_Button=driver.findElement(By.xpath("//*[@id='offerListGrid']//tr[1]//td[9]//a"));
		 Customer_Button.click();
		 
		 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li//*[@class='spacer']")));
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
		 Thread.sleep(2000);
		 
		 String CT_Vahicle_Name=driver.findElement(By.xpath("//*[@class='title']/span[1]")).getText();
		 String CT_Vahicle_Detail=driver.findElement(By.xpath("//li//*[@class='spacer']")).getText();
		 String[] words=CT_Vahicle_Detail.split("\\s");
		 for(int i=0; i<words.length; i++){  
			 Customer_OTRP=words[0];
			 Customer_MPA=words[2];
			 Customer_Term=words[8];
		 }
	
		 	Assert.assertEquals(CT_Vahicle_Name, Vehicle_Name);
			Assert.assertEquals(Customer_OTRP, "£"+OTRP);
			Assert.assertEquals(Customer_Term, Term);
			Assert.assertEquals(Customer_MPA, MPA);
			
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='customersListGrid']//tr[1]//td[3]/a")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
		Thread.sleep(2000);
			
		WebElement Customer_name=driver.findElement(By.xpath("//*[@id='customersListGrid']//tr[1]//td[3]/a"));
		Customer_name.click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//td[@class='deal-heading deal-heading-new']//span)[1]")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
		Thread.sleep(2000);
		
		String New_Vehicle_Name=driver.findElement(By.xpath("(//td[@class='deal-heading deal-heading-new']//span)[1]")).getText(); 
		String New_OTRP=driver.findElement(By.xpath("(//*[@class='dropdown-toggle newDealOTRP']/span)[1]")).getText().replaceAll("[^a-zA-Z0-9\\s+]", "");
		String New_Term=driver.findElement(By.xpath("(//*[@class='payedit newDealTerm']/span)[1]")).getText().replaceAll("[^a-zA-Z0-9\\s+]", "");		 
		String New_MPA=driver.findElement(By.xpath("//*[@class='newMileageVal']/b/span")).getText().replaceAll("[^a-zA-Z0-9\\s+]", "");
		Thread.sleep(2000);

		Assert.assertEquals(CT_Vahicle_Name, New_Vehicle_Name);
		Assert.assertEquals(Customer_OTRP, "£"+New_OTRP);
		Assert.assertEquals(Customer_Term, New_Term);
		Assert.assertEquals(Customer_MPA, New_MPA);
		Thread.sleep(2000);
	}
	
	public void Car_Offer() throws InterruptedException, IOException
	{
		
		WebElement Car_Offer_Icon=driver.findElement(By.xpath("//*[@class='showUsedCars']"));
		Car_Offer_Icon.click();
		
		WebDriverWait wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='usedcarsListGrid']//tr[1]")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
		Thread.sleep(2000);
			 
		 String Car_Vehicle=driver.findElement(By.xpath("//*[@id='usedcarsListGrid']//tr[1]//td[2]")).getText();
		 String Car_OTRP=driver.findElement(By.xpath("//*[@id='usedcarsListGrid']//tr[1]//td[7]")).getText().replaceAll("[^a-zA-Z0-9\\s+]", "");
			 
		 excel.writeStringData(3,20,0,Car_Vehicle);
		 excel.writeStringData(3,20,1,Car_OTRP);
		 Thread.sleep(1000);
		 
		 String[] wordss=Car_Vehicle.split("\\s");
		 for(int i=0; i<wordss.length; i++){  
			 Car_year=wordss[0];
			 Car_Make=wordss[2];
			 Car_OR_Sign=wordss[3];
			 Car_Model=wordss[4];
		 }
		 
		String Car_Vehicle_Name=Car_Make+" "+Car_OR_Sign+" "+Car_Model;
		String Car_Vehicle_Name_Year=Car_year+" "+Car_OR_Sign+" "+Car_Make+" "+Car_OR_Sign+" "+Car_Model+" "+Car_OR_Sign;
		
		WebElement Car_Find_Customer=driver.findElement(By.xpath("(//*[@id='usedcarsListGrid']//tr[1]//td[15]//a)[1]"));	
		Car_Find_Customer.click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li//*[@class='spacer']")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
		Thread.sleep(3000);
		
		String Car_Vahicle_Name1=driver.findElement(By.xpath("//*[@class='title']/span[1]")).getText();
		String Car_Vahicle_Detail1=driver.findElement(By.xpath("//li//*[@class='spacer']")).getText();
		
		String[] words=Car_Vahicle_Detail1.split("\\s");
		 for(int i=0; i<words.length; i++){  
			 Car_OTRP1=words[1];
		 }
		
		Assert.assertEquals(Car_Vahicle_Name1, Car_Vehicle_Name);
		Assert.assertEquals(Car_OTRP1, Car_OTRP);
		
		WebElement Car_Customer_name=driver.findElement(By.xpath("//*[@id='customersListGrid']//tr[1]//td[3]/a"));
		Car_Customer_name.click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//td[@class='deal-heading deal-heading-new']//span)[1]")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
		Thread.sleep(3000);
		
		String New_Car_Vehicle_Name=driver.findElement(By.xpath("(//td[@class='deal-heading deal-heading-new']//span)[1]")).getText(); 
		String New_Car_OTRP=driver.findElement(By.xpath("(//*[@class='dropdown-toggle newDealOTRP']/span)[1]")).getText().replaceAll("[^a-zA-Z0-9\\s+]", "");
		Thread.sleep(2000);

		Assert.assertEquals(New_Car_Vehicle_Name, Car_Vehicle_Name_Year);
		Assert.assertEquals(New_Car_OTRP, Car_OTRP);
		Thread.sleep(2000);
		}
	
	public void Call_Back() throws InterruptedException, IOException
	{
		WebElement Call_Dashboard=driver.findElement(By.xpath("//*[@class='breadcrumb-featured']//li[3]/a"));
		Call_Dashboard.click();
		
		WebDriverWait wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h3[@id='early-good-to-go']")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='p1Loader']")));
		Thread.sleep(5000);
		 
		String call_Back_Count_Before=driver.findElement(By.xpath("(//span[@class='badge activity-callbacks'])[1]")).getText();
		excel.writeStringData(3,23,0,call_Back_Count_Before);	
		
		WebElement E_G2G=driver.findElement(By.xpath("//*[@id='early-good-to-go']"));
		E_G2G.click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='customersListGrid']//tr[3]//td[3]//a")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
		Thread.sleep(3000);
		
		String Call_Customer_Name=driver.findElement(By.xpath("//*[@id='customersListGrid']//tr[3]//td[3]//a")).getText();

		Select Call_Status_Change= new Select(driver.findElement(By.xpath("//*[@id='customersListGrid']//tr[3]//td[7]/select")));
		Call_Status_Change.selectByValue("Call Back");
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//*[@id='start_date'])[2]")));
		Thread.sleep(5000);
	
		driver.findElement(By.xpath("(//*[@id='start_date'])[2]")).click();		//call back appointment start date 
		driver.findElement(By.xpath("//*[@class='today day']")).click();
		Thread.sleep(2000);
		
		Date today = new Date();  
		Calendar cal = Calendar.getInstance();
		
		int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
		
		driver.findElement(By.xpath("(//*[@id='end_date'])[2]")).click();							//call back appointment comment date
		driver.findElement(By.xpath("(//td[@class='day'][contains(text(),"+dayOfMonth+")])[1]")).click();
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//*[@id='comment']")).sendKeys("Call Back Status Change");		//call back appointment comment
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//*[@class='btn btn-primary saveStatusBtn']")).click();  //Call back appointment form submit button
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//*[@id='start_date'])[2]")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
		Thread.sleep(3000);
		
		WebElement Call_Dashboard1=driver.findElement(By.xpath("//*[@class='breadcrumb-featured']//li[3]/a"));
		Call_Dashboard1.click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h3[@id='early-good-to-go']")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='p1Loader']")));
		Thread.sleep(5000);
		
		for(int x=0; x<10; x++)
		{
		if(Integer.parseInt(driver.findElement(By.xpath("(//span[@class='badge activity-callbacks'])[1]")).getText())==0)
		{
			driver.navigate().refresh();
		}
		else
		{
			x=10;
		}
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h3[@id='early-good-to-go']")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='p1Loader']")));
		Thread.sleep(5000);
		}
		String call_Back_Count_After=driver.findElement(By.xpath("(//span[@class='badge activity-callbacks'])[1]")).getText();
		excel.writeStringData(3,26,0,call_Back_Count_After);

		Assert.assertEquals(Integer.parseInt(call_Back_Count_After), Integer.parseInt(call_Back_Count_Before)+1);
		
		WebElement Call_Back_Task=driver.findElement(By.xpath("//button[@data-activityid='4']"));
		Call_Back_Task.click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='customersListGrid']//tr[1]//td[1]/a")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
		Thread.sleep(3000);
		
		String Call_Customer_Name_CB=driver.findElement(By.xpath("//*[@id='customersListGrid']//tr[1]//td[1]/a")).getText();
		
		Assert.assertEquals(Call_Customer_Name,"Mrs. "+Call_Customer_Name_CB);
		
		WebElement Call_Customer=driver.findElement(By.xpath("//*[@id='customersListGrid']//tr[1]//td[1]/a"));
		Call_Customer.click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='panel-collapse']//td//select")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
		Thread.sleep(3000);
		
		String Call_Customer_Name_CD=driver.findElement(By.xpath("//p[@class='pull-left']//strong")).getText();
		String Call_Status=driver.findElement(By.xpath("//*[@class='panel-collapse']//td//select//option[@selected='true']")).getText();
	
		Assert.assertEquals(Call_Customer_Name, Call_Customer_Name_CD);
		Assert.assertEquals("Call Back", Call_Status);
	}
	
	 @Test(dataProvider="loginData", description="Dashboard2")
	 public void Dealer_login(String id, String pass) throws InterruptedException, IOException 
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
	 		
			//User Validation
		Assert.assertEquals("CT_Auto_Test", driver.findElement(By.xpath("//*[@class='breadcrumb-featured']/li[3]")).getText());
		Assert.assertEquals("Dashboard", driver.findElement(By.xpath("//*[@class='breadcrumb-featured']/li[4]")).getText());
		Thread.sleep(2000);
				
		//Data Validation
		String Total_Customers=driver.findElement(By.xpath("//*[@class='form-group']/label[1]")).getText().substring(1, 16);
		String Total_Equity=driver.findElement(By.xpath("//*[@class='form-group']/label[2]")).getText().substring(1, 13);
		String Total_Parity=driver.findElement(By.xpath("//*[@class='form-group']/label[3]")).getText().substring(1, 13);
		int Total_Customers_No=Integer.parseInt(driver.findElement(By.xpath("//*[@class='form-group']/label[1]")).getText().substring(19).replaceAll("[^a-zA-Z0-9\\s+]", ""));
		int Total_Equity_No=Integer.parseInt(driver.findElement(By.xpath("//*[@class='form-group']/label[2]")).getText().substring(16).replaceAll("[^a-zA-Z0-9\\s+]", ""));
		int Total_Parity_No=Integer.parseInt(driver.findElement(By.xpath("//*[@class='form-group']/label[3]")).getText().substring(16).replaceAll("[^a-zA-Z0-9\\s+]", ""));
		
		
		Assert.assertEquals("TOTAL CUSTOMERS", Total_Customers);
		Assert.assertEquals("TOTAL EQUITY", Total_Equity);
		Assert.assertEquals("TOTAL PARITY", Total_Parity);
		
		 excel.writeData(3,1,0,Total_Customers_No);
		 excel.writeData(3,1,1,Total_Equity_No);
		 excel.writeData(3,1,2,Total_Parity_No);   
		
	if(Total_Customers_No>0 && Total_Equity_No>0 && Total_Parity_No>0)
	{
		String Early_G2G=driver.findElement(By.xpath("//*[@class='card-inner card-inner-green text-center']/h4")).getText();
		String Early_HLs=driver.findElement(By.xpath("//*[@class='card-inner card-inner-orange text-center']/h4")).getText();
		String Maturing_G2G=driver.findElement(By.xpath("//*[@class='card-inner card-inner-blue text-center']/h4")).getText();
		String Maturing_HLs=driver.findElement(By.xpath("//*[@class='card-inner card-inner-grey-blue text-center']/h4")).getText();
		String Maturing_Others=driver.findElement(By.xpath("//*[@class='card-inner card-inner-red text-center']/h4")).getText();
		String Maturing_LC=driver.findElement(By.xpath("//*[@class='card-inner card-inner-dark-green text-center']/h4")).getText();
		
		Assert.assertEquals("1: EARLY - GOOD TO GO",Early_G2G);
		Assert.assertEquals("2: EARLY - HOT LEADS",Early_HLs);
		Assert.assertEquals("3: MATURING - GOOD TO GO",Maturing_G2G);
		Assert.assertEquals("4: MATURING - HOT LEADS",Maturing_HLs);
		Assert.assertEquals("5: MATURING - OTHER",Maturing_Others);
		Assert.assertEquals("6: MATURING - LAST CHANCE",Maturing_LC);
		
		int Early_G2G_No=Integer.parseInt(driver.findElement(By.xpath("//*[@class='card-top']/h3[@id='early-good-to-go']")).getText().replaceAll("[^a-zA-Z0-9\\s+]", ""));
		if(Early_G2G_No>0)
		{
		String task=driver.findElement(By.xpath("//*[@class='card-inner card-inner-grey text-center']/h4")).getText();
		Assert.assertEquals("TASKS",task);
		
		String Task_Maturing=driver.findElement(By.xpath("//button[@data-activityid='0']")).getText().substring(0, 8);
		Assert.assertEquals("MATURING",Task_Maturing);
		
		int Task_Maturing_No=Integer.parseInt(driver.findElement(By.xpath("//button[@data-activityid='0']")).getText().substring(9).replaceAll("[^a-zA-Z0-9\\s+]", ""));
		Thread.sleep(1000);
		
		excel.writeData(3,4,0,Task_Maturing_No);
		
		if(Task_Maturing_No>0) {
	 			KPI();
	 			Thread.sleep(2000);
	 			
	 			Dealer_Assigned_Customer();
	 			Thread.sleep(2000);
	 			
	 			Dealer_Assigned_Campaign();
	 			Thread.sleep(5000);	
	 			
	 			search();
	 			Thread.sleep(5000);
	 			
	 			calculator();
	 			Thread.sleep(5000); 
	 			
	 			Customer_Page();
				Thread.sleep(1000);
			
				call_Script();
				Thread.sleep(5000);
				
				Offer();
				Thread.sleep(5000);
				
				KPI_AFter_Status();
				Thread.sleep(5000);	
				
				Offer_CD_Page();
				Thread.sleep(5000);		
				
				Car_Offer();
				Thread.sleep(5000);	
				
				Call_Back();
				Thread.sleep(10000);	
	 		}	 	
		}
	}
	 }

	 @AfterMethod
	 public void logout() throws InterruptedException, NoSuchElementException
	 {
	 	 
	 	 WebElement logout=driver.findElement(By.xpath("//*[@data-status='profile']"));
	 	logout.click();
	 	Thread.sleep(10000);
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
	 	 int rows=excel.getRowCount(1);
	 	 Object[][] data=new Object[rows][2];
	 	 for(int i=0; i<rows; i++ )
	 	 {
	 		 
	 	 data[i][0]=excel.getData(1, i, 0);
	 	 data[i][1]=excel.getData(1, i, 1);
	 	 }
	 		return data;
	 }

	 }



