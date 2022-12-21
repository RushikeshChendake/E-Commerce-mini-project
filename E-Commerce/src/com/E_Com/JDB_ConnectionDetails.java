package com.E_Com;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDB_ConnectionDetails {

	
	Connection connection=null;
	public Connection getConnectionDetails()
	{
		try
		{
		  Class.forName("com.mysql.cj.jdbc.Driver");
		  connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/productjdb", "root","root");
		   
	
		}
		catch(Exception e)
		{
			System.out.println(e);
			System.out.println("Success");
		}
		return connection;
	}
	
}
