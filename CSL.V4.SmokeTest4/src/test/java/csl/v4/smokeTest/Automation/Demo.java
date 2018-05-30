package csl.v4.smokeTest.Automation;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



	public class Demo {
		public static String driverPath = "C:/new/";
		 public WebDriver driver;
		 int cc2;
		 
		 @BeforeMethod
		 public void DriverLaunch() throws InterruptedException 
		 {
			 System.out.println("launching chrome browser");
				System.setProperty("webdriver.chrome.driver", driverPath+"chromedriver.exe");
				driver = new ChromeDriver();
				driver.get("http://app.int.k2k.devthing.link/");
				driver.manage().window().maximize(); 
				Thread.sleep(20000);
		 }

		 public void campaign() throws InterruptedException
		 {
			 WebElement campaign=driver.findElement(By.xpath("//*[@class='showCampaign']"));
				campaign.click();
				Thread.sleep(5000);
				WebElement New_campaign=driver.findElement(By.xpath("//*[@id='campaignCreateView']//a[1][@class='list-group-item campaign-type']"));
				New_campaign.click();
				Thread.sleep(5000);
				WebElement New_campaign_AvailCustmer=driver.findElement(By.xpath("//*[@class='nav-level nav-level-two nav-level-two-create']//*[@id='grouplist']//a[1]"));
				New_campaign_AvailCustmer.click();
				Thread.sleep(5000);
				WebElement p1=driver.findElement(By.xpath("//input[@class='form-control empty']"));
				
				
				String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
				System.out.println(timeStamp );
				ExcelDataConfig excel=new ExcelDataConfig("C:\\Excel Data\\TestData.xlsx");
			p1.sendKeys("Test Auto"+timeStamp);
			WebElement p2=driver.findElement(By.xpath("//*[@id='startDatePicker']"));
			p2.click();
			WebElement start_Date=driver.findElement(By.xpath("//*[@class='today day']"));
			start_Date.click();
			Thread.sleep(2000);
			WebElement p3=driver.findElement(By.xpath("//input[@placeholder='End Date']"));
			p3.click();
			WebElement End_Date=driver.findElement(By.xpath("(//*[@class='day'])[1]"));
			End_Date.click();
			WebElement next=driver.findElement(By.xpath("//*[@class='pull-right']/a"));
			next.click();
			Thread.sleep(5000);
			
			
			WebElement next1=driver.findElement(By.xpath("//div[@class='form-group form-group-buttons clearfix']//a[1][@class='btn btn-primary processCampaign']"));
			next1.click();
			Thread.sleep(5000);
			
			WebElement View_Campaign=driver.findElement(By.xpath("//*[@class='nav-level nav-level-four nav-level-four-add']//a[1][@class='btn btn-primary viewCampaign']"));
			View_Campaign.click();
			Thread.sleep(10000);
			
			driver.navigate().refresh();
			Thread.sleep(10000);
			WebElement show_Activity=driver.findElement(By.xpath("//*[@id='campaignListGrid']/tbody//tr[1]/td[2]//a"));
			show_Activity.click();
			Thread.sleep(10000);
			cc2=Integer.parseInt(driver.findElement(By.xpath("//*[@id='campaignListGrid']/tbody//tr[1]/td[2]/div/span")).getText().substring(18, 21).replaceAll("[^a-zA-Z0-9\\s+]", ""));
			System.out.println(cc2);
			Thread.sleep(5000);
		 }
		 
		 public void CustomerDetail() throws InterruptedException
		 {
			 WebElement Customer=driver.findElement(By.xpath("//*[@id='customersListGrid']//tr[1]//td[3]//a"));
			 Customer.click();
			Thread.sleep(5000);
			 WebElement Assign_Customer=driver.findElement(By.xpath("//*[@class='dropdown dropdown-assign pull-right']"));
			 Assign_Customer.click();
			 Thread.sleep(10000);
			 System.out.println("SS");

			 WebElement Filter_Assign_Customer=driver.findElement(By.xpath("//*[@id='userListGrid_filter']//input"));
			 Filter_Assign_Customer.sendKeys("dealer_autotest@k2k.net");
			 Thread.sleep(1000);
			 WebElement Assign_Customer_Select=driver.findElement(By.xpath("//*[@id='userListGrid']//tr[1]//td[1]"));
			 WebElement Assign_Customer_Save=driver.findElement(By.xpath("//a[@id='saveUsers']"));
			 
			 List<WebElement> che=driver.findElements(By.xpath("//*[@id='userListGrid']//tr[@class='odd']"));
			 List<WebElement> che1=driver.findElements(By.xpath("//*[@id='userListGrid']//tr[@class='odd selected']"));
			 if(che.size()!=0)
			 {
				 Assign_Customer_Select.click();
				 Thread.sleep(1000);
				 Assign_Customer_Save.click();
				 Thread.sleep(5000);
			 }
			 else if(che1.size()!=0)
			 {
				 Assign_Customer_Save.click();
				 Thread.sleep(5000);
			 }
			 
			 driver.navigate().refresh();
			 Thread.sleep(10000);
			 JavascriptExecutor js = (JavascriptExecutor) driver;
			 js.executeScript("scroll(0,500)");
			 Thread.sleep(1000);
			 
			 WebElement Customer_check=driver.findElement(By.xpath("//a[contains(text(),'Customer Details')]"));
			 Customer_check.click();
			 Thread.sleep(5000);
			
			 String Assigned_Customer=driver.findElement(By.xpath("//div[11][@class='row']//*[@class='table table-striped table-condensed']//tr[7]/td[2]")).getText();
			 System.out.println(Assigned_Customer);
			 Assert.assertEquals("dealer autotest",Assigned_Customer);
			 System.out.println(Assigned_Customer);
			 
			 driver.findElement(By.xpath("//*[@class='breadcrumb-featured']/li[4]")).click();;
			 Thread.sleep(5000);
			 
			
			 Select select=new Select(driver.findElement(By.xpath("//*[@id='customersListGrid']//tr[1]//td[7]//select")));
			 select.selectByVisibleText("Sold");
			 Thread.sleep(5000);	 
		 }
		 
		public void Campaign_Export() throws InterruptedException
		{
			WebElement Menu=driver.findElement(By.xpath("//*[@data-original-title='Go-To Menu']"));
			Menu.click();
			Thread.sleep(1000);
			
			WebElement Menu_campaign=driver.findElement(By.xpath("//button[@id='campaigns']"));
			Menu_campaign.click();
			Thread.sleep(5000);
			
			WebElement Export=driver.findElement(By.xpath("//*[@id='campaignListGrid']/tbody//tr[1]//a[@id='exportCampaign']"));
			Export.click();
			Thread.sleep(2000);
			WebElement Export1=driver.findElement(By.xpath("//a[@class='btn btn-success kpiFilterBtn campaignExport']"));
			Export1.click();
			Thread.sleep(2000);
			WebElement View_Exports=driver.findElement(By.xpath("//a[contains(text(),'View Exports')]"));
			View_Exports.click();
			Thread.sleep(2000);
			String Export_Status=driver.findElement(By.xpath("//tr[1]//td[3]")).getText();
			System.out.println(Export_Status);
			Thread.sleep(5000);
				driver.navigate().refresh();
				Thread.sleep(10000);
			
				WebElement Download=driver.findElement(By.xpath("//tr[1]//a[contains(text(),'Download')]"));
				Download.click();
				Thread.sleep(5000);	
			
				WebElement org_branch=driver.findElement(By.xpath("//*[@class='breadcrumb-featured']//li[2]//a"));
				org_branch.click();
				Thread.sleep(10000);
				
		}
			
		public void Customer_Export() throws InterruptedException
		{
			WebElement CP_filter_Menu= driver.findElement(By.xpath("//*[@class='btn btn-primary dropdown-toggle searchButtonHide show']"));
			CP_filter_Menu.click();
			Thread.sleep(1000);
			
			WebElement CP_filter= driver.findElement(By.xpath("//*[@class='dropdown-menu pull-right animated fadeInUp']/li[3]"));
			CP_filter.click();
			Thread.sleep(1000);
			String Customer_Export_Filename="Test Auto"+java.time.LocalDateTime.now();
			WebElement Customer_Export=driver.findElement(By.xpath("//input[@id='exportName']"));
			Customer_Export.sendKeys(Customer_Export_Filename);
			
			WebElement Export=driver.findElement(By.xpath("//*[@id='campaignExportForm']//a[@id='campaignExport']"));
			Export.click();
			Thread.sleep(5000);
			System.out.println("passss");
			WebElement Export1=driver.findElement(By.xpath("//a[contains(text(),'View Exports')]"));
			Export1.click();
			Thread.sleep(5000);
			System.out.println("passss");
			String Export_Status=driver.findElement(By.xpath("//tr[1]//td[1]")).getText();
			
			Thread.sleep(5000);
				driver.navigate().refresh();
				Thread.sleep(10000);
			
				WebElement Download=driver.findElement(By.xpath("//tr[1]//a[contains(text(),'Download')]"));
				Download.click();
				Thread.sleep(5000);	
			
				WebElement org_branch=driver.findElement(By.xpath("//*[@class='breadcrumb-featured']//li[2]//a"));
				org_branch.click();
				Thread.sleep(10000);
				
		}	
		
		public void report() throws InterruptedException
		{
			WebElement Menu=driver.findElement(By.xpath("//*[@data-original-title='Go-To Menu']"));
			Menu.click();
			Thread.sleep(1000);
			
			WebElement Menu_campaign=driver.findElement(By.xpath("//button[@id='reports']"));
			Menu_campaign.click();
			Thread.sleep(5000);
			
			int KPI_Percentage=Integer.parseInt(driver.findElement(By.xpath("//table[@id='kpiGridTable']//td[2]")).getText());
			int Contacted_Sold_Percentage=Integer.parseInt(driver.findElement(By.xpath("//*[@id='campaignGridTable']//td[2]")).getText());
			int Active_Campaign=Integer.parseInt(driver.findElement(By.xpath("//*[@id='campaignGridTable']//td[3]")).getText());
			Thread.sleep(5000);
			if(KPI_Percentage>0 && Contacted_Sold_Percentage>0 && Active_Campaign>0)
			{
			WebElement KPI_Report=driver.findElement(By.xpath("//*[@data-report-route='kpireport']//a"));
			KPI_Report.click();
			Thread.sleep(5000);
			
			JavascriptExecutor js = (JavascriptExecutor) driver;
			 js.executeScript("scroll(0,500)");
			 Thread.sleep(5000);
			
			int KPI_Sold_Count=Integer.parseInt(driver.findElement(By.xpath("//*[@id='statusList']//td[9]")).getText());
			if(KPI_Sold_Count>0)
			{
				WebElement Report_Dashboard=driver.findElement(By.xpath("//a[contains(text(),'Reports Dashboard')]"));
				Report_Dashboard.click();
				Thread.sleep(5000);
				
				WebElement Campaign_Report=driver.findElement(By.xpath("//*[@data-report-route='campaignreport']//a"));
				Campaign_Report.click();
				Thread.sleep(5000);
				
				js.executeScript("scroll(0,700)");
				 Thread.sleep(5000);
				
				int Campaign_Sold_Count=Integer.parseInt(driver.findElement(By.xpath("//*[@id='statusList']//td[8]")).getText());
		
				if(Campaign_Sold_Count>0)
				{
					WebElement Report_Dashboard1=driver.findElement(By.xpath("//a[contains(text(),'Reports Dashboard')]"));
					Report_Dashboard1.click();
					Thread.sleep(5000);
					
					WebElement Usage_Report=driver.findElement(By.xpath("//*[@data-report-route='usagereport']//a"));
					Usage_Report.click();
					Thread.sleep(5000);
					
					int UR_Contacted_Count=Integer.parseInt(driver.findElement(By.xpath("//*[@id='statusList']//td[7]")).getText());
					int UR_Appointed_Count=Integer.parseInt(driver.findElement(By.xpath("//*[@id='statusList']//td[8]")).getText());
					int UR_Attended_Count=Integer.parseInt(driver.findElement(By.xpath("//*[@id='statusList']//td[9]")).getText());
					int UR_Sold_Count=Integer.parseInt(driver.findElement(By.xpath("//*[@id='statusList']//td[10]")).getText());
					int UR_Campaigns_Count=Integer.parseInt(driver.findElement(By.xpath("//*[@id='statusList']//td[11]")).getText());
					if(UR_Contacted_Count>0 && UR_Appointed_Count>0 && UR_Attended_Count>0 && UR_Sold_Count>0)
					{
						WebElement Report_Dashboard2=driver.findElement(By.xpath("//a[contains(text(),'Reports Dashboard')]"));
						Report_Dashboard2.click();
						Thread.sleep(5000);
						
						WebElement Status_Report=driver.findElement(By.xpath("//*[@data-report-route='statusreport']//a"));
						Status_Report.click();
						Thread.sleep(5000);
						String SR_Current_Status=driver.findElement(By.xpath("//*[@id='statusList']//td[5]")).getText();
						System.out.println(SR_Current_Status);
						Assert.assertEquals("Sold",SR_Current_Status);
					}
				}
			}
			}
			}
			
	
		
		
	 
	 @Test(dataProvider="loginData", description="Dashboard1")
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
			
			Thread.sleep(20000);
/*			WebElement search=driver.findElement(By.xpath("//input[@class='form-control input-sm']"));
			search.sendKeys("priya_MB");
			
			Thread.sleep(10000);
			WebElement user=driver.findElement(By.xpath("/html/body/div[2]/div/div/div[4]/div/div/div[2]/div/div/div[2]/div/table/tbody/tr[1]/td[3]"));
			user.click(); 
			Thread.sleep(30000);  */
			WebElement Early_G2G_Templet= driver.findElement(By.xpath("//*[@class='dashboard-stats dashboard-stats-1']//div[1]//a"));
			Early_G2G_Templet.click();
			Thread.sleep(10000);
			CustomerDetail();
			
			
		/*	int F_Customer=Integer.parseInt(driver.findElement(By.xpath("//*[@class='dashboard-title dashboard-title-list']//label[2]/span")).getText().replaceAll("[^a-zA-Z0-9\\s+]", ""));
			System.out.println(F_Customer);
			Thread.sleep(5000);
			campaign();
			Thread.sleep(5000);
			
			if(F_Customer==cc2)
			{
				WebElement show_Activity=driver.findElement(By.xpath("//*[@id='campaignListGrid']/tbody//tr[1]/td[1]//a"));
				show_Activity.click();
				Thread.sleep(10000);
			}
			
/*			WebElement Dashboard=driver.findElement(By.xpath("//*[@class='breadcrumb-featured']/li[3]"));
			Dashboard.click();
			Thread.sleep(10000);
	*/		
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


