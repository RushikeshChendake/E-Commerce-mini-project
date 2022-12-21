package com.E_Com;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class User_Registration {

	private static Connection connection=null;
	private static PreparedStatement ps=null;
	
    public static void insertLogInData() throws Exception
    {
    	JDB_ConnectionDetails jdbc=new   JDB_ConnectionDetails();
  	    connection=jdbc.getConnectionDetails();
  	  
  	  
  	      Scanner sc=new Scanner(System.in);
      	  System.out.println("Plesae login");
      	  System.out.println("Please enter email Id");
      	  String emailid = sc.next();
      	  System.out.println("Enter passwor");
      	  String password=sc.next();
      	  try
      	  {
      	  ps=connection.prepareStatement("select emailId,password from userRegistrationData  ");
//      	  ps.setString(1, emailid);
//      	  ps.setString(2, password);
      	  ResultSet rs=ps.executeQuery();
      	  int count=0;
      	   while(rs.next())
     	         {
      		         if(emailid.equalsIgnoreCase(rs.getString("emailId")) && password.equals(rs.getString("password")))
      		         {
      		        	count++;
        		         break;
      		         }
//     		         System.out.print(rs.getString(1)+" ");
//     		         System.out.println(rs.getString(2));
     		         
     	         }
                 if(count>0) {
              	   System.out.println("*****Login Successfull***** ");
              	   System.out.println("Press Y : If you want to Buy product which is all ready present in your Cart\nElse Press N : to see Product List");
              	   String y=sc.next();
              	   if(y.equalsIgnoreCase("Y"))
              	   {
              		 BuyProduct.getBuyProduct(emailid);
              	   }
              	   else
              	   {
              	   Dis_Product_List.getProductlist(emailid);
              	   }
                 }
              	  
                 else
                 {
              	   System.out.println("****Login Failure!! please try again****");
              	   insertLogInData();
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
    
 	
	
  public static void insert_userRegistrationData() throws Exception
  { 
	  JDB_ConnectionDetails jdbc=new   JDB_ConnectionDetails();
	  connection=jdbc.getConnectionDetails();
	  
	  Scanner sc=new Scanner(System.in);
      System.out.println("Please Register now");
	  System.out.println("Enter Your First name ");
	  String name= sc.next();
	  System.out.println("Enter your password");
	  String password1=sc.next();
	  System.out.println("Enter your email Id ");
	  String enailId1=sc.next();
	  
	  System.out.println("Enter your Mobile Number");
	  long umobileNo1=sc.nextLong();
	  try
	  {
	  ps=connection.prepareStatement("insert into userRegistrationData(uname,password,emailId,umobileNo)values(?,?,?,?)");
	  ps.setString(1,name );
	  ps.setString(2,password1 );
	  ps.setString(3, enailId1);
	  ps.setLong(4, umobileNo1);
	  
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
	  System.out.println("Your Registration is Successfull");
	  insertLogInData(); 
	
	
	
      
      
  }//end of insert_userRegistrationData() method
  



}
