package com.crm.vtiger.comcast.genericUtility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.cj.jdbc.Driver;

public class DataBaseUtility {
	Connection conn;
	/**
	 * This method is use to make connection with SQL DataBase
	 * @throws SQLException
	 */
	public void connectionToDB() throws SQLException
	{
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testyantra", "root", "root");
			
			} 
		catch (SQLException e) 
		{
		// TODO: handle exception
		e.printStackTrace();
		}	
	}
	/**
	 * This method is used to disconnect the connection from Database
	 * @throws SQLException
	 */
	public void closeDBConnecton() throws SQLException
	{
		conn.close();
	}
	/**
	 * This Method is used to fetch all data from DataBase
	 * @param query
	 * @throws SQLException
	 */
	public ResultSet getAllData(String query) throws SQLException
	{
		ResultSet result = conn.createStatement().executeQuery(query);
		return result;
	}
	
	public boolean insertData(String query) throws SQLException
	{
		int result = conn.createStatement().executeUpdate(query);
		boolean flag= false;
		if(result==1)
		{
			System.out.println("data added");
			flag=true;
			return flag;
		}
		else
		{
			System.out.println("data not added");
		}
		return flag;
	}
	
} 
