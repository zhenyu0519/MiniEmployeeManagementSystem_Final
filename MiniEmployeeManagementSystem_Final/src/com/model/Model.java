/**
 * This class is to create the model. It will display the data fetched
 * from database
 * @author Jeffery
 *
 */
package com.model;

import com.controller.*;

import javax.swing.table.AbstractTableModel;

import java.sql.ResultSet;
import java.util.Vector;


public class Model extends AbstractTableModel{
	
	//To store the data of each row
	Vector rowData;
	//To create the column title
	Vector columnTitle;
	
	public void printTable(String query, String tableName){
//		System.out.println(query);
//		System.out.println(tableName);
		columnTitle = new Vector();
		//Declare a controller object to pass the query to database
		Controller crl = null;
		//Fill the title column with column name of employee table  
		if(tableName.equals("employee")){
			columnTitle.add("EmployeeId");
			columnTitle.add("LastName");
			columnTitle.add("FirstName");
			columnTitle.add("SIN#");
			columnTitle.add("DepartmentId");
			columnTitle.add("Position");
			columnTitle.add("BaseSalary");
			//Initialize a rowData vector to store data of row
			rowData = new Vector();
			
			//Fill each row with data fetched from database
			try {
				crl = new Controller();
				ResultSet rls = crl.getResult(query);
				while(rls.next()){
					Vector row = new Vector();
					row.add(rls.getString(1));
//					System.out.println(rls.getString(1));
//					System.out.println(rls.getString(2));
//					System.out.println(rls.getString(3));
//					System.out.println(rls.getString(4));
//					System.out.println(rls.getString(5));
//					System.out.println(rls.getString(6));
//					System.out.println(rls.getString(7));
					row.add(rls.getString(2));
					row.add(rls.getString(3));
					row.add(rls.getString(4));
					row.add(rls.getString(5));
					row.add(rls.getString(6));
					row.add(rls.getString(7));
					
					rowData.add(row);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("Fetch data failed from employee");
				e.printStackTrace();
			} finally{
				crl.closeDB();
			}	
		}else if(tableName.equals("person")){
			columnTitle.add("SIN#");
			columnTitle.add("Gender");
			columnTitle.add("Age");
			columnTitle.add("Email");
			columnTitle.add("Phone#");

			
			rowData = new Vector();

			//Fill each row with data fetched from database
			try {
				crl = new Controller();
				ResultSet rls = crl.getResult(query);
				while(rls.next()){
					Vector row = new Vector();
					row.add(rls.getString(1));
					row.add(rls.getString(2));
					row.add(rls.getString(3));
					row.add(rls.getString(4));
					row.add(rls.getString(5));

					
					rowData.add(row);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("Fetch data failed from person");
				e.printStackTrace();
			} finally{
				crl.closeDB();
			}
			
		}else if(tableName.equals("department")){
			columnTitle.add("DepartmentId");
			columnTitle.add("DepartmentName");
			columnTitle.add("DepartmentDuty");

			rowData = new Vector();
			//Fill each row with data fetched from database
			try {
				crl = new Controller();
				ResultSet rls = crl.getResult(query);
				while(rls.next()){
					Vector row = new Vector();
					row.add(rls.getString(1));
					row.add(rls.getString(2));
					row.add(rls.getString(3));
					
					rowData.add(row);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("Fetch data failed from department");
				e.printStackTrace();
			} finally{
				crl.closeDB();
			}
			
		}
	}

	@Override
	//Returns the number of columns in the model.
	//A JTable uses this method to determine
	//how many columns it should create and display by default.
	public int getColumnCount() {
		// TODO Auto-generated method stub
//		System.out.println(this.columnTitle.size());
		return this.columnTitle.size();
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.rowData.size();
	}

	@Override
	public Object getValueAt(int row, int column) {
		// TODO Auto-generated method stub
		
		return ((Vector)this.rowData.get(row)).get(column);
	}

	@Override
	public String getColumnName(int column) {
//		System.out.println(column);
		// TODO Auto-generated method stub
		//return (String) this.getColumnName(column); fint the bugger here
		return (String) this.columnTitle.get(column);
	}
	
	
	
}