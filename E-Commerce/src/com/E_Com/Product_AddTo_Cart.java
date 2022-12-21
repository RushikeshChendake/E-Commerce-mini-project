package com.E_Com;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;



public class Product_AddTo_Cart {

	
	private static Connection connection=null;
	private static PreparedStatement ps=null;
	
	public static void getSelctedproInCart(String email) throws SQLException
	{
		JDB_ConnectionDetails jdbc=new JDB_ConnectionDetails();
		connection=jdbc.getConnectionDetails();
		Scanner sc=new Scanner(System.in);
		System.out.println();
		System.out.println("Please Enter Count of Proudect which you want to add it in to Cart ") ;
		int count=sc.nextInt();
	    
		for(int c=1;c<=count;c++)
		{
		   System.out.println("Plsase Select Product Id to Add it in to Cart for buying!!");
		   int proId= sc.nextInt();
		   int countofPro=0;
		   try
		   {
			   ps=connection.prepareStatement("select count(proId) as count from product_Details");
			   ResultSet st=ps.executeQuery();
			   
			   while(st.next())
			   {
				  // System.out.println("Product ct"+st.getInt(1));
				   countofPro=st.getInt(1);
			   }
		   }
		   catch(Exception e)
		   {
			   System.out.println(e);
		   }
		  
	     //System.out.println("=====================================================================================");
		   if(proId<=countofPro)
		   {
		   try
			{
			  // connection.createStatement("");
			   ps=connection.prepareStatement("insert into Cart(proId,email)values(?,?)");
			   ps.setInt(1, proId);
			   ps.setString(2, email);
			   ps.executeUpdate();
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		    
		   System.out.println();
	     }// end of if(proId<=countofPro)
		   else
		   {
			   System.out.println("Invalid Id input of Product Id that must be between 1 to "+countofPro);
			   System.out.println("Retry!!");
			   getSelctedproInCart(email);
		   }
		   
		  
		  
	}
		System.out.println("****You have successfully added products in to cart****"); 
	System.out.println("----------------------------------------------------------------");
		 System.out.println("You can see the total Amount of your selectated Products");
		 System.out.println();
		 try
		   {
			   ps=connection.prepareStatement("select sum(proPrice) from Cart inner join product_Details on Cart.proId=product_Details.proId where email="+"'"+email+"'" );
			   ResultSet rs=ps.executeQuery();
		     
		    	   while(rs.next())
		   	         {
		   		       System.out.println("Total amount of product you added in to Cart till now >> "+rs.getInt(1));
		   		       
		   	         }
		    	   //new
		    	System.out.println("For Buying press >> B"); 
		        String s=sc.next();
		        if(s.equalsIgnoreCase("B"))
		    	{
		        	BuyProduct.getBuyProduct(email);
		    	}
		    	   
//		    Statement st=connection.createStatement();
//		    st.executeUpdate("truncate table Cart");
		        
		   }
		   catch(Exception e)
		   {
			   System.out.println(e);
		   }
   }//End of  getSelctedproInCart() method
	
    
}
