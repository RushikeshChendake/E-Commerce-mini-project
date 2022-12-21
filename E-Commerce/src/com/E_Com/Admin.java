package com.E_Com;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Scanner;



public class Admin {
    
	private static Connection connection=null;
	private static PreparedStatement ps=null; 
	
	public static void getQuantityById () throws SQLException
	{
		JDB_ConnectionDetails jdbc=new JDB_ConnectionDetails();
		connection=jdbc.getConnectionDetails();
		Scanner sc=new Scanner(System.in);
		System.out.println();
		System.out.println("To see Quantity of product Enter the Id of Perticular product");
		int proId=sc.nextInt();
		try
		{
		ps=connection.prepareStatement("select proQuantity from product_Details where proId=?");
		ps.setInt(1, proId);
		ResultSet rs=ps.executeQuery();
		while(rs.next())
		{
			System.out.println("Id of Product >> "+proId +"\n --Total Quantity of product is >> "+rs.getInt(1)+" numbers");
			
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
		
	}//end of getQuantityById();method 
	public static void getproLidt() throws SQLException
	{
		JDB_ConnectionDetails jdbc=new JDB_ConnectionDetails();
		connection=jdbc.getConnectionDetails();
		
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
		
	}//end of getproLidt();
	public static void insertProByAdmin() throws SQLException
	{
		 JDB_ConnectionDetails jdbc=new   JDB_ConnectionDetails();
		  connection=jdbc.getConnectionDetails();
		  
		  Scanner sc=new Scanner(System.in);
	      
		  System.out.println("Enter number of Product you want to insert in to List");
		  int no= sc.nextInt();
	for(int i=1;i<=no;i++)
	   {
		  System.out.println("Enter product name ");
		  sc.nextLine();
		  String proname= sc.nextLine();
		  System.out.println("");
		  System.out.println("Enter Product Description ");
		  String prodescreption=sc.nextLine();
		  System.out.println("");
		  System.out.println("Enter Product Price "); 
		  int proprice=sc.nextInt();
		  System.out.println("Enter Product Quantity");
	      int proquantity=sc.nextInt();
		  
		  try
		  {
		  ps=connection.prepareStatement("insert into product_Details(proName,proDescription,proPrice,proQuantity)values(?,?,?,?)");
		  ps.setString(1,proname);
		  ps.setString(2, prodescreption);
		  ps.setInt(3,  proprice);
		  ps.setInt(4, proquantity);
		   ps.executeUpdate();
		  
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
	}
	public static void updateProQuantity() throws SQLException
	{
		JDB_ConnectionDetails jdbc=new   JDB_ConnectionDetails();
		  connection=jdbc.getConnectionDetails();
		  
		  Scanner sc=new Scanner(System.in);
	      
		  System.out.println("how meny updation you wnat to do Specify number");
		  int no= sc.nextInt();
	 for(int i=1;i<=no;i++)
	   {
		  System.out.println("Enter product Id to update that perticular product quantity ");
		  int prId= sc.nextInt();
		  System.out.println("Enter Updated Quantity of product");
	      int proquantit=sc.nextInt();
	    
		  
		  try
		  {
		  ps=connection.prepareStatement("update product_Details set proQuantity=? where proId=?");
		  ps.setInt(2,prId);
		  ps.setInt(1,  proquantit);
		  ps.executeUpdate();
		  
		  }
		  catch(Exception e)
		  {
			  System.out.println(e);
		  }
		  
		  
		}
	   ps.close();
	   connection.close();
	 System.out.println("Quantity Updatation is successfull !!");
	}//end of updateProQuantity(); method
	public static void updatePriceOfProduct() throws SQLException
	{
		  JDB_ConnectionDetails jdbc=new   JDB_ConnectionDetails();
		  connection=jdbc.getConnectionDetails();
		  
		  Scanner sc=new Scanner(System.in);
	      
		  System.out.println("how meny updation you wnat to do Specify number");
		  int no= sc.nextInt();
	 for(int i=1;i<=no;i++)
	   {
		  System.out.println("Enter product Id to update that perticular product Price ");
		  int prId= sc.nextInt();
		  System.out.println("Enter Updated Price of product");
	      int proquantit=sc.nextInt();
	    
		  
		  try
		  {
		  ps=connection.prepareStatement("update product_Details set proPrice=? where proId=?");
		  ps.setInt(2,prId);
		  ps.setInt(1,  proquantit);
		  ps.executeUpdate();
		  
		  }
		  catch(Exception e)
		  {
			  System.out.println(e);
		  }
		  ps.close();
		  connection.close();
		  System.out.println("Price Updatation is successfull !!");
	   }
	  
	}
	
	public static void disUserRegistrationDetails() throws SQLException
	{
		JDB_ConnectionDetails jdbc=new JDB_ConnectionDetails();
		connection=jdbc.getConnectionDetails();
		System.out.println("Now you can see the entire registered list of user");
		System.out.println("<<------------------------------------------------------------------------------------->>");
		System.out.printf("%14s %15s %18s %17s %19s %n","ID","Name","Password","Email-ID","MObile No");
		System.out.println("-------------------------------------------------------------------------------------");
		System.out.println("-------------------------------------------------------------------------------------");
		try
		{
		  ps=connection.prepareStatement("select uid,uname,password,emailId,umobileNo from userRegistrationData");
		  ResultSet st= ps.executeQuery();
		  ResultSetMetaData rsd=st.getMetaData();
		  int column=rsd.getColumnCount();
		  while(st.next())
		  {
			 for(int i=1;i<=column;i++)
			 {
				 System.out.print(String.format("%18s", st.getString(i) + "     "));
			 }
			 System.out.println("");
			 System.out.println("<<------------------------------------------------------------------------------------->>");

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
		
	}//end of disUserRegistrationDetails(); method
	
	public static void  orderDetail() throws SQLException {// imp task ahe
		
		  JDB_ConnectionDetails jdbc=new   JDB_ConnectionDetails();
		  connection=jdbc.getConnectionDetails();
		  Scanner sc=new Scanner(System.in);
		System.out.println("do u want all orders press all otherwise press no");
		String all=sc.next();
		if(all.equalsIgnoreCase("all")) 
		{
			ps=connection.prepareStatement("select product_Details.proId,proname,ProDescription,email,Proprice from product_Details inner join  BuyProduct on  product_Details.proId=BuyProduct.proId ");
			ResultSet rs=ps.executeQuery();
			int count=0;
			while(rs.next()) 
			{
				System.out.println("product id:"+rs.getString("proId"));
				System.out.println("product name:"+rs.getString("proname"));
				System.out.println("description:"+rs.getString("ProDescription"));
				System.out.println("email:"+rs.getString("email"));
				System.out.println("total bill:"+rs.getString("Proprice"));
				System.out.println("=====================================");
				count++;
			}
			if(count==0)
			{
				System.out.println("Not a single Buyer is found");
			}
			
		}
		else 
		{
			System.out.println("enter email to search perticular User order details");
			String email=sc.next();
			connection=jdbc.getConnectionDetails();
			ps=connection.prepareStatement("select product_Details.proId,proname,ProDescription,email,Proprice  from BuyProduct inner join product_Details on buyproduct.proId=product_Details.proId where email=?");
			ps.setString(1, email);
			ResultSet rs=ps.executeQuery();
			int count=0;
			while(rs.next()) {
				System.out.println("product id:"+rs.getString("proId")+" ,");
				System.out.println("product name:"+rs.getString("proname")+" ,");
				System.out.println("description:"+rs.getString("ProDescription")+" ,");
				System.out.println("email:"+rs.getString("email")+" ,");
				System.out.println("total bill:"+rs.getString("Proprice")+" ,");
				System.out.println("================*=======================");
				count++;
				
			}	
			if(count==0)
			{
				System.out.println("That perticular persion does not buy enething till now !!");
			}
		}

	}
	public static void getDisInfoForAdmin() throws Exception
	{
		  Scanner sc=new Scanner(System.in);
		 System.out.println("Welcome Admin !! please select your choise");
		 System.out.println("Press 1 : To see product Quantity");
		 System.out.println("Press 2 : To See Product List");
		 System.out.println("Press 3 : To Insert product in to List");
		 System.out.println("Press 4 : To update Quantity");
		 System.out.println("Press 5 : To update price");
	     System.out.println("Press 6 : To See User registration Details");
	     System.out.println("Press 7 : To See User History or Order Detail");
	     System.out.println("press 8 : To Return home page Or Exit or Log out");
	  System.out.println("-------------------------------------------------");
	     System.out.println("Enter your Choise Number");
	     int choise= sc.nextInt();
	     switch(choise)
	     {
	     case  1:
	    	 getQuantityById (); 
	    	 System.out.println("=============================");
	    	 getDisInfoForAdmin();
	    	 break;
	     case  2:
	    	 getproLidt();
	    	 System.out.println("=============================");
	    	 getDisInfoForAdmin();
	    	 break;
	     case  3:
	    	 insertProByAdmin();
	    	 System.out.println("=============================");
	    	 getDisInfoForAdmin();
	    	 break;
	     case  4:
	    	 updateProQuantity();
	    	 System.out.println("=============================");
	    	 getDisInfoForAdmin();
	    	 break;
	     case  5:
	    	 updateProQuantity();
	    	 System.out.println("=============================");
	    	 getDisInfoForAdmin();
	    	 break;
	    case  6:
	    	 disUserRegistrationDetails();
	    	 System.out.println("=============================");
	    	 getDisInfoForAdmin();
	    	 break;
	     case  7:
	    	 orderDetail();
	    	 System.out.println("=============================");
	    	 getDisInfoForAdmin();
	    	 break;
	     case  8:
	    	 Main.main(null);
	    	 break;
	     default :
	    		 System.out.println("Invalid choise please Try again !!");
	    		 getDisInfoForAdmin();
	     }

	}
	
	
	
	

}
