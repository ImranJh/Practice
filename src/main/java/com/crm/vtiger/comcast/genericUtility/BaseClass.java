package com.crm.vtiger.comcast.genericUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import com.crm.vtiger.ObjectRepository.HomePage;
import com.crm.vtiger.ObjectRepository.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public WebDriver driver = null;
	public static WebDriver idriver;
	public ExcelUtility excel = new ExcelUtility();
	public FileUtility file = new FileUtility();
	public JavaUtility java = new JavaUtility();
	public WebDriverUtility web = new WebDriverUtility();
	
	
	@BeforeSuite(groups={"smokeTest", "systemTest", "functionalTest", "sanityTest", "regressionTest"})
	public void beforeSuite()
	{
		System.out.println("DataBase Connection");
	}
	
	@BeforeTest(groups={"smokeTest", "systemTest", "functionalTest", "sanityTest", "regressionTest"})
	public void beforeTest()
	{
		System.out.println("execute script in parallel mode");
	}
	//@Parameters("browser")
	@BeforeClass(groups={"smokeTest", "systemTest", "functionalTest", "sanityTest", "regressionTest"})
	public void beforeClass() throws Throwable
	{
		String BrowserName= file.accessPropertyData("browser");
		String url= file.accessPropertyData("url");
		if(BrowserName.equals("chrome"))
			{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			}
		else if (BrowserName.equals("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver= new FirefoxDriver();
		}
		else if(BrowserName.equals("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver= new EdgeDriver();
		}
		WebDriverManager.chromedriver().setup();
		web.implicitlyWaitForPageLoad(driver);
		driver.manage().window().maximize();
		idriver=driver;
		driver.get(url);
	}
	
	@BeforeMethod(groups={"smokeTest", "systemTest", "functionalTest", "sanityTest", "regressionTest"})
	public void beforeMethod() throws Throwable
	{
		String userName = file.accessPropertyData("username");
		String passWord = file.accessPropertyData("password");
		LoginPage lp = new LoginPage(driver);
		lp.LoginPage(userName, passWord);
	}
	
	@AfterMethod(groups={"smokeTest", "systemTest", "functionalTest", "sanityTest", "regressionTest"})
	public void afterMethod()
	{
		HomePage hp = new HomePage(driver);
		hp.clickOnLogout(driver);
	}
	
	@AfterClass(groups={"smokeTest", "systemTest", "functionalTest", "sanityTest", "regressionTest"})
	public void afterClass()
	{
		driver.quit();
	}
	
	@AfterTest(groups={"smokeTest", "systemTest", "functionalTest", "sanityTest", "regressionTest"})
	public void afterTest()
	{
		System.out.println("Close Parallel mode Execution");
	}
	
	@AfterSuite(groups={"smokeTest", "systemTest", "functionalTest", "sanityTest", "regressionTest"})
	public void afterSuite()
	{
		System.out.println("Close Data Base Connection");
	}

}
