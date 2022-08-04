package com.crm.vtiger.comcast.genericUtility;

import java.awt.Robot;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
/**
 * It contains all the WebDriver reusable methods
 * @author Imran
 *
 */
public class WebDriverUtility {
	
	JavaUtility java = new JavaUtility();
	
	/**
	 * It wait for page to load before indentifying any sychronized element in DOM [HTML-Docuent]
	 * @param driver
	 */
	public void implicitlyWaitForPageLoad(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	
	}
	/**
	 * wait for page to load before indentifying any assychronized[java scripts actions] element in DOM [HTML-Docuent]
	 * @param driver
	 */
	public void waitForPageLoadForJSElement(WebDriver driver)
	{
		driver.manage().timeouts().setScriptTimeout(20, TimeUnit.SECONDS);
	}
	/**
	 * This Method is wait till the element to be clickable
	 * @param driver
	 * @param element
	 */
	public void explictlyWaitTillElementClickAble(WebDriver driver, WebElement element) 
	{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	/**
	 * This Method is used to switch the focus of driver to any window based on window title
	 * @param driver
	 * @param partialWindowTitle
	 */
	public void switchToWindow(WebDriver driver, String expectedWindowID)
	{
		Set<String> set = driver.getWindowHandles();
		Iterator<String> it = set.iterator();
		while(it.hasNext())
		{
			String wid = it.next();
			driver.switchTo().window(wid);
			if(driver.getTitle().contains(expectedWindowID))
			{
				break;
			}
		}
	}
	/**
	 * This Method is used to Switch to Alert and Click on Accept 
	 * @param driver
	 */
	public void switchToAlertAndAccept(WebDriver driver)
	{
		driver.switchTo().alert().accept();
	}
	/**
	 * This Method is used to Switch to Alert and Click on Accept
	 * @param driver
	 */
	public void switchToAlertAndCancle(WebDriver driver)
	{
		driver.switchTo().alert().dismiss();
	}
	
	/**
	 * This Method is Used to Switch to Frame Window based on index
	 * @param driver
	 * @param index
	 */
	public void switchToFrame(WebDriver driver, int index)
	{
		driver.switchTo().frame(index);
	}
	
	/**
	 * This Method is Used to Switch to Frame Window based on id or name attribute
	 * @param driver
	 * @param id_name_attribute
	 */
	public void switchToFrame(WebDriver driver , String id_name_attribute )
	{
		driver.switchTo().frame(id_name_attribute);
	}
	
	/**
	 * This Method is used to select the value from the dropDwon  based on index
	 * @param element
	 * @param index
	 */
	public void select(WebElement element, int index)
	{
		Select sele=new Select(element);
		sele.selectByIndex(index);
		
	}
	/**
	 * This Method is used to select the value from the dropDwon  based on value / option avlaible in GUI
	 * @param element
	 * @param text
	 */
	public void select(WebElement element, String text)
	{
		Select sele=new Select(element);
		sele.selectByVisibleText(text);
	}
	/**
	 * This method is used to perform the mouse action
	 * @param driver
	 * @param ele 
	 */
	public void mouseAction(WebDriver driver, WebElement ele)
	{
		Actions act= new Actions(driver);
		act.moveToElement(ele).perform();
		
	} 
	
	
	/**
	 * This method is used to launch Browser(Chrome,Firefox & Edge)
	 * @param browser
	 * @return
	 */
	public WebDriver webDriverManagerToLaunchBrowser(String browser)
	{
		if(browser.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			return new ChromeDriver();
		}
		else if(browser.equals("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			return new FirefoxDriver();
		}
		else if(browser.equals("edge"))
		{
			WebDriverManager.edgedriver().setup();
			return new EdgeDriver();
		}
		System.out.println("Default browser name which launch");
		WebDriverManager.chromedriver().setup();
		return new ChromeDriver();
		}
		/**
		 * This Method is used for Robot Key Action
		 * @return
		 * @throws Throwable
		 */
	public WebDriver robotToUse() throws Throwable
	{
		Robot r= new Robot();
		r.keyPress(KeyEvent.VK_PAGE_DOWN);
		r.keyRelease(KeyEvent.VK_PAGE_DOWN);
		return null;
	}
	/**
	 * This method is used for Take screenShot
	 * @param driver
	 * @param screenShotName
	 * @throws Throwable
	 */
	
	public String takeScreenShot(WebDriver driver, String ScreenShotName) throws Throwable 
	{
	TakesScreenshot ts = (TakesScreenshot) driver;
	String dataAndName=java.getSystemDateWithFormate();
	File storage = ts.getScreenshotAs(OutputType.FILE);
	File location = new File(".//screenshot/"+ScreenShotName+dataAndName+".png");
	FileUtils.copyFile(storage,location);
	return location.getAbsolutePath();
	}
	
	public void javaScriptForScroolDown(WebDriver driver)
	{
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,500)");
	}
		
	
}
