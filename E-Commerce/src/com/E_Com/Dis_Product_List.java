package com.E_Com;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import com.mysql.cj.PreparedQuery;

public class Dis_Product_List  {
	
	private static PreparedStatement ps= null;
	private static Connection connection=null;
	public static void getProductlist(String email) throws Exception
	{
		JDB_ConnectionDetails jdbc=new JDB_ConnectionDetails();
		connection=jdbc.getConnectionDetails();
		Scanner sc=new Scanner(System.in);
		
		System.out.println("Please apply filter to see product By Order which you want to see");
		System.out.println("Enter 1 to get product list Assending order based on Product price \n "
				          +"                             (or)"
			           	+  "\nEnter 2 to get product list Assinding order based on product Id");
		int no=sc.nextInt();
		System.out.println("Now you can see the List of Our Proudects to Buy >>");
		System.out.println("");
	    
	   if(no==1)
	   {
	   try
	   {
	   ps=connection.prepareStatement("select*from product_Details order by proPrice");
	   
	   ResultSet rs=ps.executeQuery(); //description, price, name, quantity.
	   System.out.println("ID     Name of product     Description       price   quantity   ");
	   System.out.println(" ");
	   while(rs.next())
	   {
		   System.out.print(rs.getInt(1)+" ");
		   System.out.print(" "+rs.getString(2));
		   System.out.print(" "+rs.getString(3)+" ");
		   System.out.print(" "+rs.getLong(4)+" ");
		   System.out.println(" "+rs.getInt(5));
	   }
	   }
	   catch(Exception e)
	   {
		   System.out.println(e);
	   }
	   finally
	   {
		   ps.close();
		   connection.close();
	   }
	 }//end of if(no==1)
	   else if(no==2)
	   { 
		   try
		   {
		   ps=connection.prepareStatement("select*from product_Details");
		   
		   ResultSet rs=ps.executeQuery(); //description, price, name, quantity.
		   System.out.println("ID     Name of product     Description       price   quantity   ");
		   System.out.println(" ");
		   while(rs.next())
		   {
			   System.out.print(rs.getInt(1)+" ");
			   System.out.print(" "+rs.getString(2));
			   System.out.print(" "+rs.getString(3)+" ");
			   System.out.print(" "+rs.getLong(4)+" ");
			   System.out.println(" "+rs.getInt(5));
		   }
		   }
		   catch(Exception e)
		   {
			   System.out.println(e);
		   }
		   finally
		   {
			   ps.close();
			   connection.close();
		   }
		   
	   }
	   System.out.println("---------------------------------------------------------");
	   Product_AddTo_Cart pac=new Product_AddTo_Cart();
  	   pac.getSelctedproInCart(email);
	}
	
}
