/**
 * This class is to create controller to
 * access data by SQL
 * @author Jeffery
 *
 */
package com.controller;

import java.sql.*;

public class Controller {
	//declare the connection components for database
	PreparedStatement pps = null;
	Connection con = null;
	ResultSet rls = null;
	String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String url = "jdbc:sqlserver://127.0.0.1:1433;databaseName=miniEMS";
	String user = "sa";
	String pswd = "******";
	//Find the data based on the query and return the data as result set
	public ResultSet getResult(String query){
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,user,pswd);
			//Creates a PreparedStatement object for sending
			//parameterized SQL statements to the database. 
			pps = con.prepareStatement(query);
			//Executes the SQL query in this PreparedStatement object
			//and returns the ResultSet object generated by the query.
			rls = pps.executeQuery();
			System.out.println(pps);
			
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(rls.equals(null)){
			return null;
		}else{
			return rls;	
		}
	}
	
	public boolean operateDB(String query){
		boolean b = true;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,user,pswd);
			//Creates a PreparedStatement object for sending
			//parameterized SQL statements to the database. 
			pps = con.prepareStatement(query);
			//Executes the SQL query in this PreparedStatement object
			//and returns the ResultSet object generated by the query.
			if(pps.executeUpdate()!=1){
				b = false;
			}
			
		}  catch (Exception e) {
			b = false;
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return b;
	}
	
	public void closeDB(){
		try {
			//result set is always not null, because the table is
			//always existing.
			if(rls != null) rls.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Result Set Not Closed Properly ");
			e.printStackTrace();
		} 
		try {
			if(pps != null) pps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Prepared Statement Not Closed Properly ");
			e.printStackTrace();
		} 
		try {
			if(con != null) con.close();
		} catch (SQLException e) {
			System.out.println("Connection Not Closed Properly ");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Disconnect Database");
		
	}
	
}
