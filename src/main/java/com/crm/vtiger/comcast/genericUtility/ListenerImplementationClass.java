package com.crm.vtiger.comcast.genericUtility;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ListenerImplementationClass implements ITestListener{
	JavaUtility java = new JavaUtility();
	ExtentReports rep;
	ExtentTest test;
	String path;

	public void onTestStart(ITestResult result) 
	{
		/*step-3 Create a test method during the test execution start*/
		test=rep.createTest(result.getName());
		
	}

	public void onTestSuccess(ITestResult result) 
	{
		/*Step-4 Log the pass method*/
		test.log(Status.PASS, result.getName()); 
	}

	public void onTestFailure(ITestResult result) 
	{
		/*Step-5 Pass methods for on script fail*/
		test.log(Status.FAIL, result.getName());
		test.log(Status.FAIL, result.getThrowable());
		
		WebDriverUtility web = new WebDriverUtility();
		try {
			path=web.takeScreenShot(BaseClass.idriver, result.getName());
		} catch (Throwable e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.addScreenCaptureFromPath(path);
	}

	public void onTestSkipped(ITestResult result)
	{
		/*Step-6 Pass methods on script Skip*/
		test.log(Status.SKIP, result.getName());
		test.log(Status.SKIP, result.getThrowable());
	}

	public void onStart(ITestContext context) 
	{
		/*Step-1 Configuration of extent report*/
		ExtentSparkReporter report = new ExtentSparkReporter("./Report"+java.getSystemDateWithFormate()+".html");
		report.config().setReportName("Extent execution Report");
		report.config().setDocumentTitle("Automation execution report");
		report.config().setTheme(Theme.DARK);
		
		/*Attachment of Physicalreport*/
		rep = new ExtentReports();
		rep.attachReporter(report);
		rep.setSystemInfo("OS", "Windows 10");
		rep.setSystemInfo("Url", "http://localhost:8888/");
		rep.setSystemInfo("Author name", "Md Imran");
		rep.setSystemInfo("Environment", "Testing");
	}

	public void onFinish(ITestContext context) 
	{
		rep.flush();
	}

}
