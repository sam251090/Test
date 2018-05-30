package csl.v4.smokeTest.Automation;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

public class Spanish_User {
	public static String driverPath = "C:/new/";
	public WebDriver driver;
	String campaign_Cust;
	 
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
	
	public void Password_Reset() throws InterruptedException, IOException
	{
		WebElement Pass=driver.findElement(By.xpath("//*[@name='password']"));
		Pass.sendKeys("Test123$$");
		Thread.sleep(1000);
		
		WebElement Confirm_Pass=driver.findElement(By.xpath("//*[@name='passwordconfirm']"));
		Confirm_Pass.sendKeys("Test123$$");
		Thread.sleep(1000);
		
		excel.writeStringData(4, 16, 1, "Test123$$");
		
		WebElement Reset_Password_Button=driver.findElement(By.xpath("//*[@id='resetPassword']"));
		Reset_Password_Button.click();
		
		WebDriverWait wait = new WebDriverWait(driver, 50);
		 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h3[@id='early-good-to-go']")));
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='p1Loader']")));
		 Thread.sleep(5000);
	}
	
	public void Group_Filter() throws InterruptedException
	{			 
		String Total_Customers=driver.findElement(By.xpath("//*[@class='form-group']/label[1]//span")).getText();
		String Total_Equity=driver.findElement(By.xpath("//*[@class='form-group']/label[2]//span")).getText();
		String Total_Parity=driver.findElement(By.xpath("//*[@class='form-group']/label[3]//span")).getText();
		int Total_Customers_No=Integer.parseInt(Total_Customers.replaceAll("[^a-zA-Z0-9\\s+]", ""));
		int Total_Equity_No=Integer.parseInt(Total_Equity.replaceAll("[^a-zA-Z0-9\\s+]", ""));
		int Total_Parity_No=Integer.parseInt(Total_Parity.replaceAll("[^a-zA-Z0-9\\s+]", ""));
	
		WebElement filter=driver.findElement(By.xpath("//*[@class='btn btn-primary ct-context btn-groupFilter']"));
		filter.click();
		
		WebDriverWait wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='dealerlist']//a")));
		Thread.sleep(2000);
		
		WebElement DealerList=driver.findElement(By.xpath("//*[@id='dealerlist']//a"));
		DealerList.click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='btn btn-primary btn-success filterBtn']")));
		Thread.sleep(1000);
		
		WebElement Filter_Ok=driver.findElement(By.xpath("//*[@class='btn btn-primary btn-success filterBtn']"));
		Filter_Ok.click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h3[@id='early-good-to-go']")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='p1Loader']")));
		Thread.sleep(2000);
		
		String Total_Customers1=driver.findElement(By.xpath("//*[@class='form-group']/label[1]//span")).getText();
		String Total_Equity1=driver.findElement(By.xpath("//*[@class='form-group']/label[2]//span")).getText();
		String Total_Parity1=driver.findElement(By.xpath("//*[@class='form-group']/label[3]//span")).getText();
		int Total_Customers_No1=Integer.parseInt(Total_Customers1.replaceAll("[^a-zA-Z0-9\\s+]", ""));
		int Total_Equity_No1=Integer.parseInt(Total_Equity1.replaceAll("[^a-zA-Z0-9\\s+]", ""));
		int Total_Parity_No1=Integer.parseInt(Total_Parity1.replaceAll("[^a-zA-Z0-9\\s+]", ""));
		
		Assert.assertEquals(Total_Customers_No, Total_Customers_No1);
		Assert.assertEquals(Total_Equity_No, Total_Equity_No1);
		Assert.assertEquals(Total_Parity_No, Total_Parity_No1);
		
		Thread.sleep(2000);
		
	}
	
	public void Dealer_Details() throws InterruptedException
	{
	WebElement Early_G2G=driver.findElement(By.xpath("//*[@id='early-good-to-go']"));
	Early_G2G.click();
	
	WebDriverWait wait = new WebDriverWait(driver, 50);
	 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='customersListGrid']//tr[1]")));
	 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
	 Thread.sleep(2000);
	 
	WebElement Customer=driver.findElement(By.xpath("//*[@id='customersListGrid']//tr[1]//td[3]//a"));
	 Customer.click();
	
	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'Detalles ampliados')]")));
	 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
	 Thread.sleep(2000);
	 
	 JavascriptExecutor js = (JavascriptExecutor) driver;
	 js.executeScript("scroll(0,500)");
	 Thread.sleep(1000);
	 
	 WebElement Extended_Details=driver.findElement(By.xpath("//a[contains(text(),'Detalles ampliados')]"));
	 Extended_Details.click();
	 
	 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='extendedDetails']//tr[15]//td[2]//p")));
	 Thread.sleep(2000);
	 
	 ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//*[@id='extendedDetails']//tr[15]//td[2]//p")));
	 Thread.sleep(3000);
	 String Dealer_Name=driver.findElement(By.xpath("//*[@id='extendedDetails']//tr[15]//td[2]//p")).getText();
	 String Dealer_Code=driver.findElement(By.xpath("//*[@id='extendedDetails']//tr[16]//td[2]//p")).getText();
	
	Assert.assertEquals(Dealer_Name, "Mercedes-Benz Brentford");
	Assert.assertEquals(Dealer_Code, "3973");
	 
	WebElement Customer_List=driver.findElement(By.xpath("//*[@class='breadcrumb-featured']//li[4]//a"));
	Customer_List.click();
	
	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='customersListGrid']//tr[1]")));
	 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
	 Thread.sleep(2000);
	}
	
	public void campaign() throws InterruptedException
	{
		
	List<WebElement> els = driver.findElements(By.xpath("//*[@class=' select-checkbox']"));
	for ( WebElement el : els ) {
	    if ( !el.isSelected() ) {
	        el.click();
	    }
	}
	
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
		
		WebElement New_campaign_AvailCustmer=driver.findElement(By.xpath("//*[@class='nav-level nav-level-two nav-level-two-create']//*[@id='grouplist']//a[2]"));
		New_campaign_AvailCustmer.click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@class='form-control empty'and @placeholder='Nombre de campaña']")));
		Thread.sleep(1000);
		
		WebElement Campaign_Name=driver.findElement(By.xpath("//input[@class='form-control empty'and @placeholder='Nombre de campaña']"));			
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		Campaign_Name.sendKeys("Test Auto Spanish"+timeStamp);
		
		WebElement p2=driver.findElement(By.xpath("//*[@id='startDatePicker']"));
		p2.click();
		WebElement start_Date=driver.findElement(By.xpath("//*[@class='today day']"));
		start_Date.click();
		WebElement p3=driver.findElement(By.xpath("//input[@placeholder='Fecha final']"));
		p3.click();
		Thread.sleep(1000);
		WebElement End_Date=driver.findElement(By.xpath("(//*[@class='day'])[1]"));
		End_Date.click();
		WebElement description=driver.findElement(By.xpath("//input[@class='form-control empty'  and @placeholder='DescripciÃ³n']"));			
		description.sendKeys("Test Campaign - Please Ignore");
		Thread.sleep(1000);
		WebElement Customer_Selection=driver.findElement(By.xpath("//*[@id='selectedCustomers']"));
		Customer_Selection.click();
		Thread.sleep(2000);
		
		Select status=new Select(driver.findElement(By.xpath("//*[@name='c_status']")));
		status.selectByValue("Contacted");
		Thread.sleep(2000);
		
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
//	campaign_Cust=driver.findElement(By.xpath("//*[@id='campaignListGrid']/tbody//tr[1]/td[2]/div/span")).getText().substring(18, 20).replaceAll("[^a-zA-Z0-9\\s+]", "");
	
	
	String ccc2=driver.findElement(By.xpath("//*[@id='campaignListGrid']/tbody//tr[1]/td[2]/div/span")).getText();
	Thread.sleep(2000);	
	
	String[] words1=ccc2.split("\\s");
	 for(int i=0; i<words1.length; i++){  
		 campaign_Cust=words1[3];
	 }

	 Assert.assertEquals(campaign_Cust, "10");
	 Thread.sleep(2000);
	 
	
	 WebElement Camp_name=driver.findElement(By.xpath("//*[@id='campaignListGrid']//tr[1]//td[1]//a"));
	 Camp_name.click();
	 
	 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='form-group']//label[2]//span")));
	 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mainLoader']")));
	 Thread.sleep(2000);
	 
	 String Filter_Cust=driver.findElement(By.xpath("//*[@class='form-group']//label[2]//span")).getText();
	 
	 Assert.assertEquals(campaign_Cust, Filter_Cust);
	 
	 List<WebElement> Status_contact = driver.findElements(By.xpath("//*[@class='statusSelect form-control Contacted']"));
	 int Contacted_Count=Status_contact.size();
	 
	 Assert.assertEquals(Contacted_Count, 10);

	 Thread.sleep(5000);
		
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
		 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@name='password']")));		
		 Thread.sleep(3000);
			
		 Password_Reset();
		 Thread.sleep(5000);	
		 
		 Group_Filter();
		 Thread.sleep(5000);	
		
		 Dealer_Details();
		 Thread.sleep(5000);	
		 
		 campaign();
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
			 
		 data[i][0]=excel.getData(4, 13, 0);
		 data[i][1]=excel.getData(4, 13, 1);
		 }
			return data;
	 }
}

