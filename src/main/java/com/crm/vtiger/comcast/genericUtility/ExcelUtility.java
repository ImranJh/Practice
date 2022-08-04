package com.crm.vtiger.comcast.genericUtility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
/**
 * it's Developed by using ApachePOI, To handle MicroSoft sheet
 * @author Imran
 *
 */
public class ExcelUtility {


	/**
	 * This method is used to read the data from Excel based on Billow Argument
	 * @param sheetName
	 * @param rowNum
	 * @param cellNum
	 * @return
	 * @throws Throwable 
	 * @throws IOException
	 */
	
	public String fetchDataFromExcel(String sheetName, int rowNum, int cellNum) throws Throwable  {
		FileInputStream fis = new FileInputStream("src/main/resources/Commondata/Java.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		DataFormatter format =new DataFormatter();
		Sheet sh= wb.getSheet(sheetName);
		Row r= sh.getRow(rowNum);
		Cell c = r.getCell(cellNum);
		String data = format.formatCellValue(c);
		wb.close();
		return data;
	}

	/**
	 * This method is used to add data into the Excel based on Billow Argument
	 * @param sheetName
	 * @param rowNum
	 * @param celNum
	 * @throws Throwable 
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public void setDataExcel(String sheetName, int rowNum, int celNum, String data) throws Throwable
	{
		FileInputStream fis = new FileInputStream("src/main/resources/Commondata/Java.xlsx");
		Workbook wb= WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetName);
		Row row =sh.getRow(rowNum);
		Cell cel= row.createCell(celNum);
		cel.setCellValue(data);
		FileOutputStream foi = new FileOutputStream("src/main/resources/Commondata/Java.xlsx");
		wb.write(foi);
		wb.close();
	}
	
	/**
	 * This method is used to get the last row number of the specified sheet
	 * @param sheetName
	 * @return
	 * @throws Throwable
	 */
	public int getRowCount(String sheetName) throws Throwable
	{
		FileInputStream fis = new FileInputStream("src/main/resources/Commondata/Java.xlsx");
		Workbook wb= WorkbookFactory.create(fis);
		Sheet sh= wb.getSheet(sheetName);
		wb.close();
		return sh.getLastRowNum();
	}
}