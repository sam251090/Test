package csl.v4.smokeTest.Automation;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

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


public class Admin_USER_CRM_Flow {
	public static String driverPath = "C:/new/";
	 public WebDriver driver;
	 String campaign_Cust;
	 int Contacted_Achieved, Appointed_Achieved, Attended_Achieved, Sold_Achieved;
	 int Contacted_Achieved_New, Appointed_Achieved_New, Attended_Achieved_New, Sold_Achieved_New;
	 int Total_Customers_No_Group, Total_Equity_No_Group, Total_Parity_No_Group, Total_Customers_No_Dealer, Total_Equity_No_Dealer, Total_Parity_No_Dealer, Total_Customers_No_GroupAll, Total_Equity_No_GroupAll, Total_Parity_No_GroupAll, Filtered_GroupAll_Customer_count;
	 static String Customer_Name;
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
		 Thread.sleep(1000);
		 
		 Contacted_Achieved=Integer.parseInt(driver.findElement(By.xpath("//*[@id='contactedAchievedInt']")).getText());
		 Appointed_Achieved=Integer.parseInt(driver.findElement(By.xpath("//*[@id='appointedAchievedInt']")).getText());
		 Attended_Achieved=Integer.parseInt(driver.findElement(By.xpath("//*[@id='attendedAchievedInt']")).getText());
		 Sold_Achieved=Integer.parseInt(driver.findElement(By.xpath("//*[@id='soldAchievedInt']")).getText());
		 
		 excel.writeData(2,8,0,Contacted_Achieved);
		 excel.writeData(2,8,1,Appointed_Achieved);
		 excel.writeData(2,8,2,Attended_Achieved);
		 excel.writeData(2,8,3,Sold_Achieved);
		 
		 WebElement KPI_Close=driver.findElement(By.xpath("//*[@class='btn btn-default close-kpi-pod pull-left']/span"));
		 KPI_Close.click();
		 
		 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h3[@id='early-good-to-go']")));
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='p1Loader']")));
		 Thread.sleep(1000);
	 }
	 
	 public void KPI_AFter_Status() throws InterruptedException, IOException
	 {
		 WebElement KPI_Toggle=driver.findElement(By.xpath("//*[@class='form form-inline form-switch pull-right']//div[2]//label"));
		 KPI_Toggle.click();
		 
		 WebDriverWait wait = new WebDriverWait(driver, 50);
		 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='contactedAchievedInt']")));
		 Thread.sleep(1000);
		 
		 Contacted_Achieved_New=Integer.parseInt(driver.findElement(By.xpath("//*[@id='contactedAchievedInt']")).getText());
		 Appointed_Achieved_New=Integer.parseInt(driver.findElement(By.xpath("//*[@id='appointedAchievedInt']")).getText());
		 Attended_Achieved_New=Integer.parseInt(driver.findElement(By.xpath("//*[@id='attendedAchievedInt']")).getText());
		 Sold_Achieved_New=Integer.parseInt(driver.findElement(By.xpath("//*[@id='soldAchievedInt']")).getText());
		
		 excel.writeData(2,30,0,Contacted_Achieved_New);
		 excel.writeData(2,30,1,Appointed_Achieved_New);
		 excel.writeData(2,30,2,Attended_Achieved_New);
		 excel.writeData(2,30,3,Sold_Achieved_New);
		 
		 WebElement KPI_Close=driver.findElement(By.xpath("//*[@class='btn btn-default close-kpi-pod pull-left']/span"));
		 KPI_Close.click();
		 
		 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h3[@id='early-good-to-go']")));
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='p1Loader']")));
		 Thread.sleep(1000);
	 }
	 
	 public void GroupAll_filter() throws InterruptedException
	 {
		 WebElement Filter_Button=driver.findElement(By.xpath("//a[@class='btn btn-primary ct-context btn-groupFilter']"));
			Filter_Button.click();
			
			WebDriverWait wait = new WebDriverWait(driver, 50);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='form-group clearfix action-btns']//*[@class='pull-right']")));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
			Thread.sleep(1000);
			
			WebElement Click_OK=driver.findElement(By.xpath("//*[@class='form-group clearfix action-btns']//*[@class='pull-right']"));
			Click_OK.click();
			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h3[@id='early-good-to-go']")));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='p1Loader']")));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
			Thread.sleep(2000);
	 }
	 
	 public void Dealer_Group_filter() throws InterruptedException
	 {
		 WebElement Filter_Button=driver.findElement(By.xpath("//a[@class='btn btn-primary ct-context btn-groupFilter']"));
			Filter_Button.click();
			
			WebDriverWait wait = new WebDriverWait(driver, 50);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='grouplist']/a[1]")));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
			Thread.sleep(1000);
			
			WebElement Group_Parent=driver.findElement(By.xpath("//*[@id='grouplist']/a[1]"));
			Group_Parent.click();
			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='form-group clearfix action-btns']//*[@class='pull-right']")));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
			Thread.sleep(1000);
			
			WebElement Click_OK=driver.findElement(By.xpath("//*[@class='form-group clearfix action-btns']//*[@class='pull-right']"));
			Click_OK.click();
			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h3[@id='early-good-to-go']")));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='p1Loader']")));
			Thread.sleep(2000);
	 }
	 
	 public void Dealer_filter() throws InterruptedException
	 {
		 WebElement Filter_Button=driver.findElement(By.xpath("//a[@class='btn btn-primary ct-context btn-groupFilter']"));
			Filter_Button.click();
			
			WebDriverWait wait = new WebDriverWait(driver, 50);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='grouplist']/a[1]")));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
			Thread.sleep(1000);
			
			WebElement Group_Parent=driver.findElement(By.xpath("//*[@id='grouplist']/a[1]"));
			Group_Parent.click();
			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='groupChildrenList']/a[2]")));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
			Thread.sleep(1000);
			
			WebElement Group_Children=driver.findElement(By.xpath("//*[@class='groupChildrenList']/a[2]"));
			Group_Children.click();

			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='childdealerlist']//a[3]")));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
			Thread.sleep(1000);
			
			WebElement Dealer_Name=driver.findElement(By.xpath("//*[@id='childdealerlist']//a[3]"));
			Dealer_Name.click();

			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='form-group clearfix action-btns']//*[@class='pull-right']")));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
			Thread.sleep(1000);
			
			WebElement Click_OK=driver.findElement(By.xpath("//*[@class='form-group clearfix action-btns']//*[@class='pull-right']"));
			Click_OK.click();
			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h3[@id='early-good-to-go']")));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='p1Loader']")));
			Thread.sleep(2000);
	 }
	 
	 public void CustomerPage_GroupAll_filter() throws InterruptedException
	 {
			WebElement CP_filter_Menu= driver.findElement(By.xpath("//*[@class='btn btn-primary dropdown-toggle searchButtonHide show']"));
			CP_filter_Menu.click();
			
			WebDriverWait wait = new WebDriverWait(driver, 50);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='dropdown-menu pull-right animated fadeInUp']/li[2]")));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
			Thread.sleep(1000);
			
			WebElement CP_filter= driver.findElement(By.xpath("//*[@class='dropdown-menu pull-right animated fadeInUp']/li[2]"));
			CP_filter.click();
			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='form-group clearfix action-btns']//div[3][@class='pull-right']")));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
			Thread.sleep(1000);
			
			WebElement CP_Click_OK=driver.findElement(By.xpath("//*[@class='form-group clearfix action-btns']//div[3][@class='pull-right']"));
			CP_Click_OK.click();
			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='customersGrid']//tr")));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
			Thread.sleep(2000);
	 }
	 
	 public void CustomerPage_Dealer_Group_filter() throws InterruptedException
	 {
			WebElement CP_filter_Menu= driver.findElement(By.xpath("//*[@class='btn btn-primary dropdown-toggle searchButtonHide show']"));
			CP_filter_Menu.click();
			
			WebDriverWait wait = new WebDriverWait(driver, 50);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='dropdown-menu pull-right animated fadeInUp']/li[2]")));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
			Thread.sleep(1000);
			
			WebElement CP_filter= driver.findElement(By.xpath("//*[@class='dropdown-menu pull-right animated fadeInUp']/li[2]"));
			CP_filter.click();
			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='grouplist']//a[1][@class='list-group-item grouptBtn']")));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
			Thread.sleep(1000);
			
			WebElement CP_Group_Parent=driver.findElement(By.xpath("//*[@id='grouplist']//a[1][@class='list-group-item grouptBtn']"));
			CP_Group_Parent.click();
			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='groupChildrenList']//a[2][@class='list-group-item grouptBtn']")));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
			Thread.sleep(1000);
			
			WebElement CP_Group_Children=driver.findElement(By.xpath("//*[@class='groupChildrenList']//a[2][@class='list-group-item grouptBtn']"));
			CP_Group_Children.click();
			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='form-group clearfix action-btns']//div[3][@class='pull-right']")));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
			Thread.sleep(1000);
			
			WebElement CP_Click_OK=driver.findElement(By.xpath("//*[@class='form-group clearfix action-btns']//div[3][@class='pull-right']"));
			CP_Click_OK.click();
			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='customersGrid']//tr")));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
			Thread.sleep(2000);
	 }
	 
	 public void CustomerPage_Dealer_filter() throws InterruptedException
	 {
			WebElement CP_filter_Menu= driver.findElement(By.xpath("//*[@class='btn btn-primary dropdown-toggle searchButtonHide show']"));
			CP_filter_Menu.click();
			
			WebDriverWait wait = new WebDriverWait(driver, 50);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='dropdown-menu pull-right animated fadeInUp']/li[2]")));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
			Thread.sleep(2000);
			
			WebElement CP_filter= driver.findElement(By.xpath("//*[@class='dropdown-menu pull-right animated fadeInUp']/li[2]"));
			CP_filter.click();
			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='grouplist']//a[1][@class='list-group-item grouptBtn']")));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
			Thread.sleep(2000);
			
			WebElement CP_Group_Parent=driver.findElement(By.xpath("//*[@id='grouplist']//a[1][@class='list-group-item grouptBtn']"));
			CP_Group_Parent.click();
			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='groupChildrenList']//a[2][@class='list-group-item grouptBtn']")));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
			Thread.sleep(2000);
			
			WebElement CP_Group_Children=driver.findElement(By.xpath("//*[@class='groupChildrenList']//a[2][@class='list-group-item grouptBtn']"));
			CP_Group_Children.click();
			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='childdealerlist']//a[3]")));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
			Thread.sleep(2000);
			
			WebElement CP_Dealer_Name=driver.findElement(By.xpath("//*[@id='childdealerlist']//a[3]"));
			CP_Dealer_Name.click();
			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='form-group clearfix action-btns']//div[3][@class='pull-right']")));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
			Thread.sleep(2000);
			
			WebElement CP_Click_OK=driver.findElement(By.xpath("//*[@class='form-group clearfix action-btns']//div[3][@class='pull-right']"));
			CP_Click_OK.click();
			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='customersGrid']//tr")));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
			Thread.sleep(2000);
			
	 }
	 
	 public void campaign() throws InterruptedException, IOException
	 {
		 String F_Customer=driver.findElement(By.xpath("//*[@class='dashboard-title dashboard-title-list']//label[2]/span")).getText().replaceAll("[^a-zA-Z0-9\\s+]", "");
			System.out.println(F_Customer);
			Thread.sleep(5000);
		 
		 WebElement campaign=driver.findElement(By.xpath("//*[@class='showCampaign']"));
			campaign.click();
			
			WebDriverWait wait = new WebDriverWait(driver, 50);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='campaignCreateView']//a[1][@class='list-group-item campaign-type']")));
			Thread.sleep(1000);
			
			WebElement New_campaign=driver.findElement(By.xpath("//*[@id='campaignCreateView']//a[1][@class='list-group-item campaign-type']"));
			New_campaign.click();
			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='nav-level nav-level-two nav-level-two-create']//*[@id='grouplist']//a[2]")));
			Thread.sleep(1000);
			
			WebElement New_campaign_AvailCustmer=driver.findElement(By.xpath("//*[@class='nav-level nav-level-two nav-level-two-create']//*[@id='grouplist']//a[1]"));
			New_campaign_AvailCustmer.click();
			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@class='form-control empty'and @placeholder='Campaign Name']")));
			Thread.sleep(1000);
			
			WebElement Campaign_Name=driver.findElement(By.xpath("//input[@class='form-control empty'and @placeholder='Campaign Name']"));			
			String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
			Campaign_Name.sendKeys("Test Auto"+timeStamp);
			
			excel.writeStringData(2, 33, 0, "Test Auto"+timeStamp);

			WebElement p2=driver.findElement(By.xpath("//*[@id='startDatePicker']"));
		p2.click();
		WebElement start_Date=driver.findElement(By.xpath("//*[@class='today day']"));
		start_Date.click();
		WebElement p3=driver.findElement(By.xpath("//input[@placeholder='End Date']"));
		p3.click();
		Thread.sleep(1000);
		WebElement End_Date=driver.findElement(By.xpath("(//*[@class='day'])[1]"));
		End_Date.click();
		WebElement description=driver.findElement(By.xpath("//input[@class='form-control empty'  and @placeholder='Description']"));			
		description.sendKeys("Test Campaign for Automation - Please Ignore");
		Thread.sleep(1000);
		WebElement next=driver.findElement(By.xpath("//*[@class='pull-right']/a"));
		next.click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='form-group form-group-buttons clearfix']//a[1][@class='btn btn-primary processCampaign']")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
		Thread.sleep(1000);
		
		WebElement next1=driver.findElement(By.xpath("//div[@class='form-group form-group-buttons clearfix']//a[1][@class='btn btn-primary processCampaign']"));
		next1.click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='nav-level nav-level-four nav-level-four-add']//a[1][@class='btn btn-primary viewCampaign']")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
		Thread.sleep(1000);
		
		WebElement View_Campaign=driver.findElement(By.xpath("//*[@class='nav-level nav-level-four nav-level-four-add']//a[1][@class='btn btn-primary viewCampaign']"));
		View_Campaign.click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='campaignListGrid']/tbody//tr[1]")));
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
		 Thread.sleep(2000);
		
		
		 for(int x=0; x<5; x++)
			{
			if(driver.findElement(By.xpath("//*[@id='campaignListGrid']/tbody//tr[1]/td[7]")).getText().equals("active"))
			{
				x=5;
			}
			else
			{
				driver.navigate().refresh();
			}
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='campaignListGrid']/tbody//tr[1]")));
			 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
			 Thread.sleep(3000);
			}
			
			WebElement show_Activity=driver.findElement(By.xpath("//*[@id='campaignListGrid']/tbody//tr[1]/td[2]//a"));
			show_Activity.click();
			Thread.sleep(5000);
					
		
		String ccc2=driver.findElement(By.xpath("//*[@id='campaignListGrid']/tbody//tr[1]/td[2]/div/span")).getText();
		Thread.sleep(5000);	
		
		String[] words1=ccc2.split("\\s");
		 for(int i=0; i<words1.length; i++){  
			 campaign_Cust=words1[3];
		 }
		 
		 Thread.sleep(10000);
		 
		 Assert.assertEquals(F_Customer,campaign_Cust);
		 
		WebElement Campaign_name=driver.findElement(By.xpath("//*[@id='campaignListGrid']/tbody//tr[1]/td[1]//a"));
		Campaign_name.click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='form-group']//label[2]//span")));
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
		 Thread.sleep(2000);
		 
		 String Filter_Cust=driver.findElement(By.xpath("//*[@class='form-group']//label[2]//span")).getText();
		 
		 Assert.assertEquals(campaign_Cust, Filter_Cust);
	 }
	 
	 public void CustomerDetail() throws InterruptedException, NumberFormatException, IOException
	 {	
		 Customer_Name=driver.findElement(By.xpath("//*[@id='customersListGrid']//tr[1]//td[3]//a")).getText();
		 
		 excel.writeStringData(2,20,0,Customer_Name);
		 
		 WebElement Customer=driver.findElement(By.xpath("//*[@id='customersListGrid']//tr[1]//td[3]//a"));
		 Customer.click();
		 
		 WebDriverWait wait = new WebDriverWait(driver, 50);
		 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='dropdown dropdown-assign pull-right']")));
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
		 Thread.sleep(2000);
			
		 WebElement Assign_Customer=driver.findElement(By.xpath("//*[@class='dropdown dropdown-assign pull-right']"));
		 Assign_Customer.click();
		 
		 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='userListGrid_filter']//input")));
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
		 Thread.sleep(1000);

		 WebElement Filter_Assign_Customer=driver.findElement(By.xpath("//*[@id='userListGrid_filter']//input"));
		 Filter_Assign_Customer.sendKeys("dealer_autotest@k2k.net");
		 
		 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='userListGrid']//tr[1]//td[1]")));
		 Thread.sleep(2000);
		 
		 WebElement Assign_Customer_Select=driver.findElement(By.xpath("//*[@id='userListGrid']//tr[1]//td[1]"));
		 WebElement Assign_Customer_Save=driver.findElement(By.xpath("//a[@id='saveUsers']"));
		 
		 List<WebElement> che=driver.findElements(By.xpath("//*[@id='userListGrid']//tr[@class='odd']"));
		 List<WebElement> che1=driver.findElements(By.xpath("//*[@id='userListGrid']//tr[@class='odd selected']"));
		 if(che.size()!=0)
		 {
			 Assign_Customer_Select.click();
			 Thread.sleep(2000);
			 Assign_Customer_Save.click();
			 
			 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'Customer Details')]")));
			 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
			 Thread.sleep(2000);
		 }
		 else if(che1.size()!=0)
		 {
			 Assign_Customer_Save.click();
			 
			 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'Customer Details')]")));
			 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
			 Thread.sleep(2000);
		 }
		 
		 driver.navigate().refresh();
		 
		 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'Customer Details')]")));
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
		 Thread.sleep(2000);
		 
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("scroll(0,500)");
		 Thread.sleep(2000);
		 
		 WebElement Customer_check=driver.findElement(By.xpath("//a[contains(text(),'Customer Details')]"));
		 Customer_check.click();
		 
		 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[11][@class='row']//*[@class='table table-striped table-condensed']//tr[7]")));
		 Thread.sleep(2000);
		
		 String Assigned_Customer=driver.findElement(By.xpath("//div[11][@class='row']//*[@class='table table-striped table-condensed']//tr[7]/td[2]")).getText();
		 Assert.assertEquals("dealer autotest",Assigned_Customer);
		 
		 driver.findElement(By.xpath("//*[@class='breadcrumb-featured']/li[4]")).click();;
		 
		 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='customersListGrid']//tr[1]")));
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
		 Thread.sleep(2000);
	 
		 Select select=new Select(driver.findElement(By.xpath("//*[@id='customersListGrid']//tr[1]//td[7]//select")));
		 select.selectByVisibleText("Sold");
		 Thread.sleep(3000);
	 }
	 
	 public void Customer_Export() throws InterruptedException, IOException
		{
			WebElement CP_filter_Menu= driver.findElement(By.xpath("//*[@class='btn btn-primary dropdown-toggle searchButtonHide show']"));
			CP_filter_Menu.click();
			Thread.sleep(1000);
			
			WebElement CP_filter= driver.findElement(By.xpath("//*[@class='dropdown-menu pull-right animated fadeInUp']/li[3]"));
			CP_filter.click();
			
			WebDriverWait wait = new WebDriverWait(driver, 50);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='campaignExportForm']//a[@id='campaignExport']")));
			Thread.sleep(1000);
			
			String Customer_Export_Filename="Test Auto"+java.time.LocalDateTime.now();
			WebElement Customer_Export=driver.findElement(By.xpath("//input[@id='exportName']"));
			Customer_Export.sendKeys(Customer_Export_Filename);
			
			excel.writeStringData(2,23,0,Customer_Export_Filename);
			
			WebElement Export=driver.findElement(By.xpath("//*[@id='campaignExportForm']//a[@id='campaignExport']"));
			Export.click();
			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'View Exports')]")));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
			Thread.sleep(2000);
			
			WebElement Export1=driver.findElement(By.xpath("//a[contains(text(),'View Exports')]"));
			Export1.click();
			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'Customers')]")));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
			Thread.sleep(2000);
			
			WebElement Customers_Export=driver.findElement(By.xpath("//a[contains(text(),'Customers')]"));
			Customers_Export.click();
			
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//tr[1]//a[contains(text(),'Download')])[2]")));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
			
			String Export_Status=driver.findElement(By.xpath("//tr[1]//td[1]")).getText();

				driver.navigate().refresh();
				
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'Customers')]")));
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
				Thread.sleep(2000);
				
				WebElement Customers_Export1=driver.findElement(By.xpath("//a[contains(text(),'Customers')]"));
				Customers_Export1.click();
				
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//tr[1]//a[contains(text(),'Download')])[2]")));
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
				Thread.sleep(2000);
			
				WebElement Download=driver.findElement(By.xpath("(//tr[1]//a[contains(text(),'Download')])[2]"));
				Download.click();
				Thread.sleep(5000);	
			
				WebElement org_branch=driver.findElement(By.xpath("//*[@class='breadcrumb-featured']//li[2]//a"));
				org_branch.click();
				
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h3[@id='early-good-to-go']")));
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='p1Loader']")));
				Thread.sleep(5000);
		}	
	 
	 public void Campaign_Export() throws InterruptedException, NumberFormatException, IOException
		{
			WebElement Menu=driver.findElement(By.xpath("//*[@data-original-title='Go-To Menu']"));
			Menu.click();
			Thread.sleep(1000);
			
			WebElement Menu_campaign=driver.findElement(By.xpath("//button[@id='campaigns']"));
			Menu_campaign.click();
			
			WebDriverWait wait = new WebDriverWait(driver, 50);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='campaignListGrid']/tbody//tr[1]")));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
			Thread.sleep(2000);
			
			WebElement Export=driver.findElement(By.xpath("//*[@id='campaignListGrid']/tbody//tr[1]//a[@id='exportCampaign']"));
			Export.click();
			
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='btn btn-success kpiFilterBtn campaignExport']")));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
			Thread.sleep(1000);
			
			WebElement Export1=driver.findElement(By.xpath("//a[@class='btn btn-success kpiFilterBtn campaignExport']"));
			Export1.click();
			
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'View Exports')]")));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
			Thread.sleep(1000);
			
			WebElement View_Exports=driver.findElement(By.xpath("//a[contains(text(),'View Exports')]"));
			View_Exports.click();
			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//tr[1]//td[3]")));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
			Thread.sleep(2000);
			
			String Export_Status=driver.findElement(By.xpath("//tr[1]//td[3]")).getText();
			System.out.println(Export_Status);
				
			driver.navigate().refresh();
				
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//tr[1]//td[1]")));
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
				Thread.sleep(2000);
				
				String Export_Customer_Name=driver.findElement(By.xpath("//tr[1]//td[1]")).getText();
				Thread.sleep(1000);
				
				excel.writeStringData(2,26,0,Export_Customer_Name);
				
				WebElement Download=driver.findElement(By.xpath("//tr[1]//a[contains(text(),'Download')]"));
				Download.click();
				Thread.sleep(5000);	
				
				WebElement org_branch=driver.findElement(By.xpath("//*[@class='breadcrumb-featured']//li[2]//a"));
				org_branch.click();
				
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h3[@id='early-good-to-go']")));
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='p1Loader']")));
				Thread.sleep(5000);
		}
	 
	 public void report() throws InterruptedException
		{
		 
			WebElement Menu=driver.findElement(By.xpath("//*[@data-original-title='Go-To Menu']"));
			Menu.click();
			Thread.sleep(1000);
			
			WebElement Menu_report=driver.findElement(By.xpath("//button[@id='reports']"));
			Menu_report.click();
			
			WebDriverWait wait = new WebDriverWait(driver, 50);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@id='kpiGridTable']//td[2]")));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
			Thread.sleep(2000);
			
			int KPI_Percentage=Integer.parseInt(driver.findElement(By.xpath("//table[@id='kpiGridTable']//td[2]")).getText());
			int Contacted_Sold_Percentage=Integer.parseInt(driver.findElement(By.xpath("//*[@id='campaignGridTable']//td[2]")).getText());
			int Active_Campaign=Integer.parseInt(driver.findElement(By.xpath("//*[@id='campaignGridTable']//td[3]")).getText());
			Thread.sleep(2000);
			if(KPI_Percentage>0 && Contacted_Sold_Percentage>0 && Active_Campaign>0)
			{
			WebElement KPI_Report=driver.findElement(By.xpath("//*[@data-report-route='kpireport']//a"));
			KPI_Report.click();
			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='statusList']//td[9]")));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
			Thread.sleep(2000);
			
			JavascriptExecutor js = (JavascriptExecutor) driver;
			 js.executeScript("scroll(0,500)");
			 Thread.sleep(3000);
			 
			 WebElement KPI_R_filter=driver.findElement(By.xpath("//*[@type='search']"));
			 KPI_R_filter.sendKeys("Mercedes-Benz of Boston");;
			 Thread.sleep(3000);
			
			int KPI_Sold_Count=Integer.parseInt(driver.findElement(By.xpath("//*[@id='statusList']//td[9]")).getText());
			if(KPI_Sold_Count>0)
			{
				WebElement Report_Dashboard=driver.findElement(By.xpath("//a[contains(text(),'Reports Dashboard')]"));
				Report_Dashboard.click();
				
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@data-report-route='campaignreport']//a")));
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
				Thread.sleep(3000);
				
				WebElement Campaign_Report=driver.findElement(By.xpath("//*[@data-report-route='campaignreport']//a"));
				Campaign_Report.click();
				
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='statusList']//td[8]")));
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
				Thread.sleep(3000);
				
				js.executeScript("scroll(0,700)");
				Thread.sleep(3000);
				
				String Camp_Name=excel.getData(2, 33, 0);
				 
				WebElement Campaign_R_filter=driver.findElement(By.xpath("//*[@type='search']"));
				Campaign_R_filter.sendKeys(Camp_Name);
				Thread.sleep(3000);
				 
				int Campaign_Sold_Count=Integer.parseInt(driver.findElement(By.xpath("//*[@id='statusList']//td[8]")).getText());
		
				if(Campaign_Sold_Count>0)
				{
					WebElement Report_Dashboard1=driver.findElement(By.xpath("//a[contains(text(),'Reports Dashboard')]"));
					Report_Dashboard1.click();
					
					wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@data-report-route='usagereport']//a")));
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
					Thread.sleep(3000);
					
					WebElement Usage_Report=driver.findElement(By.xpath("//*[@data-report-route='usagereport']//a"));
					Usage_Report.click();
					
					wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='statusList']//td[7]")));
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
					Thread.sleep(3000);
					
					int UR_Contacted_Count=Integer.parseInt(driver.findElement(By.xpath("//*[@id='statusList']//td[7]")).getText());
					int UR_Appointed_Count=Integer.parseInt(driver.findElement(By.xpath("//*[@id='statusList']//td[8]")).getText());
					int UR_Attended_Count=Integer.parseInt(driver.findElement(By.xpath("//*[@id='statusList']//td[9]")).getText());
					int UR_Sold_Count=Integer.parseInt(driver.findElement(By.xpath("//*[@id='statusList']//td[10]")).getText());
					int UR_Campaigns_Count=Integer.parseInt(driver.findElement(By.xpath("//*[@id='statusList']//td[11]")).getText());
					if(UR_Contacted_Count>0 && UR_Appointed_Count>0 && UR_Attended_Count>0 && UR_Sold_Count>0 && UR_Campaigns_Count>0)
					{
						WebElement Report_Dashboard2=driver.findElement(By.xpath("//a[contains(text(),'Reports Dashboard')]"));
						Report_Dashboard2.click();
						
						wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@data-report-route='statusreport']//a")));
						wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
						Thread.sleep(3000);
						
						WebElement Status_Report=driver.findElement(By.xpath("//*[@data-report-route='statusreport']//a"));
						Status_Report.click();
						
						wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='statusList']//td[5]")));
						wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
						Thread.sleep(3000);
						
						 js.executeScript("scroll(0,500)");
						 Thread.sleep(3000);
						 
						String SR_Current_Status=driver.findElement(By.xpath("//*[@id='statusList']//td[5]")).getText();
						System.out.println(SR_Current_Status);
						Assert.assertEquals("Sold",SR_Current_Status);
						Thread.sleep(2000);
					}
				}
			}
			}
			}

@Test(dataProvider="loginData", description="Dashboard2")
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
		
		//User Validation
		Assert.assertEquals("CT_Auto_Test", driver.findElement(By.xpath("//*[@class='breadcrumb-featured']/li[3]")).getText());
		Assert.assertEquals("Dashboard", driver.findElement(By.xpath("//*[@class='breadcrumb-featured']/li[4]")).getText());
		Thread.sleep(2000);
		//Data Validation
		String Total_Customers=driver.findElement(By.xpath("//*[@class='form-group']/label[1]")).getText().substring(1, 16);
		String Total_Equity=driver.findElement(By.xpath("//*[@class='form-group']/label[2]")).getText().substring(1, 13);
		String Total_Parity=driver.findElement(By.xpath("//*[@class='form-group']/label[3]")).getText().substring(1, 13);
		int Total_Customers_No=Integer.parseInt(driver.findElement(By.xpath("//*[@class='form-group']/label[1]/span")).getText().replaceAll("[^a-zA-Z0-9\\s+]", ""));
		int Total_Equity_No=Integer.parseInt(driver.findElement(By.xpath("//*[@class='form-group']/label[2]/span")).getText().replaceAll("[^a-zA-Z0-9\\s+]", ""));
		int Total_Parity_No=Integer.parseInt(driver.findElement(By.xpath("//*[@class='form-group']/label[3]/span")).getText().replaceAll("[^a-zA-Z0-9\\s+]", ""));
		
		
		Assert.assertEquals("TOTAL CUSTOMERS", Total_Customers);
		Assert.assertEquals("TOTAL EQUITY", Total_Equity);
		Assert.assertEquals("TOTAL PARITY", Total_Parity);
		
		 excel.writeData(2,1,0,Total_Customers_No);
		 excel.writeData(2,1,1,Total_Equity_No);
		 excel.writeData(2,1,2,Total_Parity_No);
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
		
		int Task_Maturing_No=Integer.parseInt(driver.findElement(By.xpath("//button[@data-activityid='0']/span")).getText().replaceAll("[^a-zA-Z0-9\\s+]", ""));
		Thread.sleep(1000);
		
		 excel.writeData(2,4,0,Task_Maturing_No);
		
		if(Task_Maturing_No>0) {
			KPI();
			Thread.sleep(2000);
			
			Dealer_Group_filter();
			Total_Customers_No_Group=Integer.parseInt(driver.findElement(By.xpath("//*[@class='form-group']/label[1]/span")).getText().replaceAll("[^a-zA-Z0-9\\s+]", ""));
			Total_Equity_No_Group=Integer.parseInt(driver.findElement(By.xpath("//*[@class='form-group']/label[2]/span")).getText().replaceAll("[^a-zA-Z0-9\\s+]", ""));
			Total_Parity_No_Group=Integer.parseInt(driver.findElement(By.xpath("//*[@class='form-group']/label[3]/span")).getText().replaceAll("[^a-zA-Z0-9\\s+]", ""));
			
			excel.writeData(2,12,0,Total_Customers_No_Group);
			excel.writeData(2,12,1,Total_Equity_No_Group);
			excel.writeData(2,12,2,Total_Parity_No_Group);
			Thread.sleep(2000);
			if(Total_Customers_No>Total_Customers_No_Group && Total_Equity_No>Total_Equity_No_Group && Total_Parity_No>Total_Parity_No_Group)
			{
				Dealer_filter();
				Total_Customers_No_Dealer=Integer.parseInt(driver.findElement(By.xpath("//*[@class='form-group']/label[1]/span")).getText().replaceAll("[^a-zA-Z0-9\\s+]", ""));
				Total_Equity_No_Dealer=Integer.parseInt(driver.findElement(By.xpath("//*[@class='form-group']/label[2]/span")).getText().replaceAll("[^a-zA-Z0-9\\s+]", ""));
				Total_Parity_No_Dealer=Integer.parseInt(driver.findElement(By.xpath("//*[@class='form-group']/label[3]/span")).getText().replaceAll("[^a-zA-Z0-9\\s+]", ""));
				
				excel.writeData(2,17,0,Total_Customers_No_Dealer);
				excel.writeData(2,17,1,Total_Equity_No_Dealer);
				excel.writeData(2,17,2,Total_Parity_No_Dealer);
				Thread.sleep(2000);
				if(Total_Customers_No_Dealer<Total_Customers_No_Group && Total_Equity_No_Dealer<Total_Equity_No_Group && Total_Parity_No_Dealer<Total_Parity_No_Group)
				{
					GroupAll_filter();
					Total_Customers_No_GroupAll=Integer.parseInt(driver.findElement(By.xpath("//*[@class='form-group']/label[1]/span")).getText().replaceAll("[^a-zA-Z0-9\\s+]", ""));
					Total_Equity_No_GroupAll=Integer.parseInt(driver.findElement(By.xpath("//*[@class='form-group']/label[2]/span")).getText().replaceAll("[^a-zA-Z0-9\\s+]", ""));
					Total_Parity_No_GroupAll=Integer.parseInt(driver.findElement(By.xpath("//*[@class='form-group']/label[3]/span")).getText().replaceAll("[^a-zA-Z0-9\\s+]", ""));
					Thread.sleep(2000);

					Assert.assertEquals(Total_Customers_No, Total_Customers_No_GroupAll);
					Assert.assertEquals(Total_Equity_No, Total_Equity_No_GroupAll);
					Assert.assertEquals(Total_Parity_No, Total_Parity_No_GroupAll);
					
					if(Total_Customers_No==Total_Customers_No_GroupAll && Total_Equity_No==Total_Equity_No_GroupAll && Total_Parity_No==Total_Parity_No_GroupAll)
					{
						WebElement Early_G2G_Templet= driver.findElement(By.xpath("//*[@class='dashboard-stats dashboard-stats-1']//div[1]//a"));
						Early_G2G_Templet.click();
						
						wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='form-group']/label[2]")));
						 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
						 Thread.sleep(2000);
						 
						Filtered_GroupAll_Customer_count=Integer.parseInt(driver.findElement(By.xpath("//*[@class='form-group']/label[2]")).getText().substring(20).replaceAll("[^a-zA-Z0-9\\s+]", ""));
						Thread.sleep(2000);
						
						Assert.assertEquals(Total_Customers_No, Total_Customers_No_GroupAll);
						Assert.assertEquals(Early_G2G_No, Filtered_GroupAll_Customer_count);
						
						if(Total_Customers_No==Total_Customers_No_GroupAll && Early_G2G_No==Filtered_GroupAll_Customer_count)
						{
							CustomerPage_Dealer_Group_filter();
							
							CustomerPage_Dealer_filter();
							Thread.sleep(2000);
							
							campaign();
							Thread.sleep(3000);
								
							CustomerDetail();   
							Thread.sleep(3000);
								
							Customer_Export();
							Thread.sleep(3000);
								
							KPI_AFter_Status();
							Thread.sleep(3000);
							
							Assert.assertEquals(Contacted_Achieved_New, Contacted_Achieved+1);
							Assert.assertEquals(Appointed_Achieved_New, Appointed_Achieved+1);
							Assert.assertEquals(Attended_Achieved_New, Attended_Achieved+1);
							Assert.assertEquals(Sold_Achieved_New, Sold_Achieved+1);
							
							if(Contacted_Achieved_New==Contacted_Achieved+1  && Appointed_Achieved_New==Appointed_Achieved+1 && Attended_Achieved_New==Attended_Achieved+1 && Sold_Achieved_New==Sold_Achieved+1)
							{
								Campaign_Export();
								Thread.sleep(3000);
								report();
							}							
						}
					}
				}
			}
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
