package com.E_Com;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class BuyProduct {
	
	private static Connection connection=null;
	private static PreparedStatement ps=null;
    
	public static void getBuyProduct(String email) throws Exception
	{
		Scanner sc=new Scanner(System.in);
	  
		
		
		   JDB_ConnectionDetails jdbc=new JDB_ConnectionDetails();
		   connection=jdbc.getConnectionDetails();
		   try
		   {
			   	   
		   ps= connection.prepareStatement("select product_Details.proId,proName from  product_Details  inner join  Cart on product_Details.proId=Cart.proId where email=?");
		   ps.setString(1, email);                //select proId from Cart where email=?
		   ResultSet rs= ps.executeQuery();
		   System.out.println("The list of Product Id  you added in to Cart >> ");
		   while(rs.next())
		   {
			   System.out.print("Id :-"+rs.getInt(1)+" ");
			   System.out.println("& Name of product :-"+rs.getString(2));
		   }
		   
		   }
		   catch(Exception e)
		   {
			   System.out.println(e);
		   }
		   System.out.println("How meny Products do you want to Buy");
		   int no=sc.nextInt();
		   int porId=0;
		   
		   int buyid=0;
		   int sum=0;
		   for(int i=1;i<=no;i++)
			   {
				   System.out.println("Select Id of Product you want to Buy!!");
				   porId=sc.nextInt();
				   ps= connection.prepareStatement("insert into BuyProduct(proId,email) values(?,?)");
				   ps.setInt(1, porId);
				   ps.setString(2, email);
				   ps.executeUpdate();
				   ps=connection.prepareStatement("select BuyProId from BuyProduct where proId=? ");
				     ps.setInt(1,  porId);
				     ResultSet rst=ps.executeQuery();
				       
			    	   while(rst.next())
			   	         {
			   		        buyid=rst.getInt(1);
			   		       
			   	         }
				     
			    	 ps=connection.prepareStatement("select sum(proPrice) from BuyProduct inner join product_Details on BuyProduct.proId=product_Details.proId where  BuyProId=? and email="+"'"+email+"'");
			    	 ps.setInt(1, buyid);
				     ResultSet rs=ps.executeQuery();
			         
			    	   while(rs.next())
			   	         {
			   		       //System.out.println(rs.getInt(1));
			   		       sum=sum+rs.getInt(1);
			   	         }
			    	 
			   }
		   System.out.println("Your total Payble Amount is >>> "+sum);
		   try 
		   {
			   System.out.println("Your payment is successfull !!");
			    System.out.println("Thank you!!");
		   } 
		   finally
		   {
			ps.close();
			connection.close();
		  }
		   Main.main(null);
		   
		   }
	 
	}
	

