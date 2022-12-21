package com.E_Com;

import java.util.Scanner;

public class Main {

	
	public static void main(String[] args) throws Exception {
		
		Scanner sc= new Scanner(System.in);
		System.out.println("For Customers: press 1  And For Admin : press 2");
		int custAd=sc.nextInt();
		
		
		if(custAd==1)
		{
			
			System.out.println("press N : For new Customer and Press O : For old Customer ");
			String st=sc.next();
			if(st.equalsIgnoreCase("N"))
			{
		      User_Registration.insert_userRegistrationData();
			}
			if(st.equalsIgnoreCase("O"))
			{  
				User_Registration.insertLogInData();
			}
		}
		else if(custAd==2)
		{
			int num=101;
		 System.out.println("Enter Admin password number ie is >>101");
		 int num1=sc.nextInt();	 
		 if(num1==num)
		 {
			 Admin.getDisInfoForAdmin();
		 }
		}
	}

}


