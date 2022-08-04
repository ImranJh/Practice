package com.crm.vtiger.comcast.genericUtility;

import java.util.Date;
import java.util.Random;
/**
 * 
 * @author Imran
 *
 */
public class JavaUtility {
	/**
	 * This Method is used to Generate RanDom number in every execution
	 * @return
	 */
	public int getRanDomNumber()
	{
		Random r = new Random();
		int rNum= r.nextInt(1000);
		return rNum;	
	}
	
	public String getSystemDateAndtime()
	{
		Date d= new Date();
		return d.toString();
	}
	
	public String getSystemDateWithFormate()
	{
		Date d= new Date();
		String dateAndTime= d.toString();
		String time = dateAndTime.substring(11, 18).replace(":", "-");
		
		String YYYY=dateAndTime.split(" ")[5];
		String DD= dateAndTime.split(" ")[2];
		int MM = d.getMonth()+1;
		
		String date=YYYY+"-"+MM+"-"+DD;
		
		String dateTime=date+" "+time;
		return dateTime;
	}
}
