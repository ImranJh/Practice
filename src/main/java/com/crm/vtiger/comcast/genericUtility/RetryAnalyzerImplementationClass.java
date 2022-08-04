package com.crm.vtiger.comcast.genericUtility;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzerImplementationClass implements IRetryAnalyzer{

	int count =0;
	int reCount=3;
	public boolean retry(ITestResult result) {
		
		if(count<reCount)
		{
			count++;
			return true;
		}
		return false;
	}
}
