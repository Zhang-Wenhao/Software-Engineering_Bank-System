/**
 * MyBank.java
 * Copyright (c) 2018-2028 Zhang Wenhao
 * All rights reserved.
 * @author Zhang Wenhao
 * @version 4.0
 * 
 */

import java.io.File;  
import java.util.Date;
import java.util.Scanner;
import java.io.FileWriter;
import java.util.ArrayList;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.io.FileNotFoundException;

/**
 * class MyBank
 * This class is the CUI interface and controls the service flow.
 */
public class MyBank {
	public String Acc1;
	Scanner sc = new Scanner(System.in);
	public MyBank()
	{
		Interface();
	}
	/**
	 * method Interface()
	 * Use this method to enter CUI interface(home page), then choose operation.
	 */
	public void Interface() {
		  System.out.println("Hello! Welcom to the bank system!");
		  System.out.println("Please enter your operation");
		  System.out.println("1.Create an account");
		  System.out.println("2.Log into your account");
		  System.out.println("3.Administrator interface");
		  System.out.println("4.exit the system");
		  int op1 = sc.nextInt();
		  if(op1==1||op1==2||op1==3||op1==4)
		   {
			   if(op1==1)
			   {  
				 CreateAccount();
				  
			   }
			   if(op1==2)
			   {  
				Login1();
			   }
			   if(op1==3)
			   {  
				Administrator1();
			   }
			   if(op1==4)
			   {
				System.exit(0);
			   }
		   }
		   else
		   {
		    System.out.println("Wrong input!");
		    Interface();
		    }
	}
	/**
	 * method CreateAccount
	 * Use this method to open an account, people enter the information then an account will be given and a txt file will be created.
	 */
	public void CreateAccount()
	{
		String date;
 		SimpleDateFormat yyyymmdd =   new SimpleDateFormat( "YYYY.MM.dd" );//format of date
	   	date=yyyymmdd.format(new Date());
		String accountnum;
		SimpleDateFormat yyyymmddhhmm = new SimpleDateFormat("yyyyMMddhhmm");//format of account number
		accountnum=yyyymmddhhmm.format(new Date());//assign account number by current time
		File F=new File("check.txt");
		String abc=sc.nextLine();
		System.out.println("Please enter your name");
		String name=sc.nextLine();
		if (readFile(F).contains(name)) //judge whether the user is on the list of people who have a bad credit
			{
			System.out.println("No, you have a bad credit record, so you can't open an account!");
			Interface();
		}
		else
	   {
		String date0 ="NULL";//initialize date
		System.out.println("Please enter your address");
		String addr=sc.nextLine();
		System.out.println("Please enter your birthday(eg.1997.09.03)");
		String birth=sc.nextLine();
		String[] str1 = date.split("\\.");//split the date using split function
		String[] str2 = birth.split("\\.");//split the birthday using split function
		if(Integer.parseInt(str1[0])-Integer.parseInt(str2[0])>=16) //judge the age of this user
	   		{ System.out.println("Please tell me which type you want to open:");
	   		  System.out.println("There are 3 types of account, but you only can open two of them.");
	   		  System.out.println("1 Saver Account");
	   		  System.out.println("3 Current Account");
	   		}
	   	if(Integer.parseInt(str1[0])-Integer.parseInt(str2[0])<16) // judge the age of this user
	   		{ System.out.println("Please tell me which type you want to open:");
	   		  System.out.println("1 Saver Account");
	   		  System.out.println("2 Junior Account");
	   		  System.out.println("3 Current Account");
	   		}
		int type=sc.nextInt();//choose type of the account he wants to open.
		if(type==1)
		{
			System.out.println("You can withdraw your money after 30 days when you deposit your money");
		}
		if(type==3) 
		{
			System.out.println("This is a current account, you have a 500 dollars overdraft limit");
		}
		String pin =Integer.toString((int)((Math.random()*9+1)*100000));
		Account acc=new Account(type,name,addr,birth,pin,accountnum,0,0,date0,date0,true);
		File f=new File(accountnum+".txt");
		if(f.exists())
		System.out.println("This account number has already existed!");
		else
		{
			writeFile(acc);//store all information into a user file
			System.out.println("Successfully create. Your account number is"+accountnum);
			System.out.println("Your initial pin is"+pin);
			Interface();
		}
	   }
	}
	/**
	 * Method setAcc()
	 * This method set content of account into an arraylist in the memory with the help of content read.
	 * @param list
	 * @return acct
	 */
	public Account setAcc (ArrayList<String> list){
		int type1;int fund11;int fund21;
		String name1;
		String pin1;
		String birth1;
		String addr1;
		String accountnum1;
		Boolean flag1;
		String date11;
		String date21;
		int i = 0;
		while (i < list.size()){
			
			type1= Integer.parseInt(list.get(i++));
			
			name1= list.get(i++);
			
			addr1= list.get(i++);
						
			birth1= list.get(i++);
			
			pin1= list.get(i++);
			
			accountnum1= list.get(i++);
			
			fund11= Integer.parseInt(list.get(i++));
			
			fund21= Integer.parseInt(list.get(i++));

			date11= list.get(i++);
			
			date21= list.get(i++);
			if(list.get(i).equals("true"))
				flag1=true;		
			else
				flag1=false;
			Account acct=new Account(type1,name1,addr1,birth1,pin1,accountnum1,fund11,fund21,date11,date21,flag1);
			return acct;
		}
		Account acc=new Account();
		return acc;
	}
	/**
	 * Method wirteFile
	 * This method generate file from the arraylist of account memorized in the memory.
	 * @param acct
	 */

   	public void writeFile(Account acct)
   	{
	   File f=new File(acct.getAccountnum()+".txt");
	   
			   FileWriter fw;
		try {
		
			 fw = new FileWriter(f.getAbsoluteFile(),false);
			 BufferedWriter bw = new BufferedWriter(fw);
			 String s = String.valueOf(acct.getType());
			 bw.write(s);
			 bw.newLine();
			 bw.write(acct.getName());
			 bw.newLine();
			 bw.write(acct.getAddr());
			 bw.newLine();
			 bw.write(acct.getBirth());
			 bw.newLine();
			 bw.write(acct.getPin());
			 bw.newLine();
			 bw.write(acct.getAccountnum());
			 bw.newLine();
			 String s1 = String.valueOf(acct.getFund1());
			 bw.write(s1);
			 bw.newLine();
			 String s2 = String.valueOf(acct.getFund2());
			 bw.write(s2);
			 bw.newLine();
			 bw.write(acct.getDate1());
			 bw.newLine();
			 bw.write(acct.getDate2());
			 bw.newLine();
			 String s3 = acct.getFlag().toString();
			 bw.write(s3);
			 bw.newLine();
			 bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
   	}
   	/**
   	 * Method readFile()
   	 * This method is used to read the account file to an arraylist and memorize the content in the memory. 
   	 * @param file
   	 * @return list
   	 */
   	public ArrayList<String> readFile(File file)
	{
		try{
			String encoding = "GBK";
			ArrayList<String> list = new ArrayList<String>();
			if(file.isFile() && file.exists()){
			InputStreamReader in = new InputStreamReader(new FileInputStream(file), encoding);
			BufferedReader bfr = new BufferedReader(in);
			String tempString = null;
			while ((tempString = bfr.readLine()) != null){
				list.add(tempString);
				}
			bfr.close();
			return list;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
   	/**
   	 * Method Login1()
   	 * This method is used to enter the interface to input account number and pin
   	 */
   	public void Login1()
   	{
	   String cde=sc.nextLine();
	   System.out.println("Please enter your account number:");
	   String Acc=sc.nextLine();
	   Acc1=Acc;
	   File ff=new File(Acc+".txt");
	   if(ff.exists())
	   {Account acc= setAcc(readFile(ff));
	     System.out.println("Please enter your login pin:"); 
	     String pin2=sc.nextLine();
	     if(pin2.equals(acc.getPin()))
	    {
	 		System.out.println("Log Successfully!");
		    System.out.println("What operation do you want to do?");
	    		Login2(acc);
	    }
	   else
	    	 System.out.println("pin is not right!"); 
	     		Interface();  
	   	}
	   else
		 System.out.println("This account doesn't exist!");   
	   		Interface();   
   	}
   	
   	/**
   	 * Method Login2()
   	 * This method is used to enter the interface of choosing operations after login
   	 * @param acc
   	 */
   	public void Login2(Account acc) {
		String date3;
 		SimpleDateFormat yyyymmdd =   new SimpleDateFormat( "YYYY.MM.dd" );
	   	date3=yyyymmdd.format(new Date());
		acc.setdate2(date3);//store this date into user file, this date is the last login date
		writeFile(acc);
   		System.out.println("1.Check status");
		System.out.println("2.Deposit money");
		System.out.println("3.Withdraw money");
		System.out.println("4.Suspend your account");
		System.out.println("5.Recover your account");
		System.out.println("6.Change your pin");
		System.out.println("7.Close account");
		System.out.println("8.Logout");
		int o=sc.nextInt();
		if(o!=1&&o!=2&&o!=3&&o!=4&&o!=5&&o!=6&&o!=7&&o!=8&&o!=9)
		{
		 System.out.println("Wrong input! Please input again.");	
		 Login2(acc);
		}
		switch(o)
		{
		  case 1:
			Lookup(acc);
			break;
		  case 2:
			Deposit(acc);
			break;
		  case 3:
			Withdraw(acc);
			break;
		  case 4:
			Suspend(acc);
			break;
	 	  case 5:
	 		Recover(acc);
	 		break;
	 	  case 6:
			Change(acc);
	 	  case 7:
			Close(acc);
			break;
		  case 8:
			Interface();
			break;
		}
   	}
   	/**
   	 * Method Lookup()
   	 * This method is used for a user to check balance, unclear money, state of account in the user file.
   	 * @param acc
   	 */
   	public void Lookup(Account acc) {
   		System.out.println("Now your balance is: "+acc.getFund2());
   		System.out.println("And you have unclear money: "+acc.getFund1());
   		if (acc.getFlag()==false)
   		System.out.println("Your account is in suspend state");
   		if (acc.getFlag()==true)
   		System.out.println("Your account is in an active state");
   		Login2(acc);
   		
   	}
   	/**
   	 * Method Deposit()
   	 * This method is used to deposit money to the account given by a user
   	 * @param acc
   	 */
   	public void Deposit(Account acc) {
       String def=sc.nextLine();
 	   System.out.println("Please enter the account number you want to deposit money to:");
 	   String Acc2=sc.nextLine();
 	   File ff=new File(Acc2+".txt");
 	   if(ff.exists())
 	   {
 	 		Account acct= setAcc(readFile(ff));
   		if(acct.getFlag()==true)//judge whether the account is suspended or not
   		{
 	 	 System.out.println("Yes, you can deposit to this account");
   		 System.out.println("How do you want to deposit money?");
       	 System.out.println("1 By cash");
       	 System.out.println("2 By check");
       	 int h=sc.nextInt();
       	 if (h!=1&&h!=2)
       	 {
       	 System.out.println("Wrong input, please input again.");
       	 int h1=sc.nextInt();
       	 h=h1;
       	 }
       	 if(!Acc1.equals(Acc2))//judge whether the account number given by the user is his account number or not 
       	 {
   		 System.out.println("Please enter the amount you want to deposit:");
    	 int am=sc.nextInt();
    	 if(h==1)
    	 {
    		 acct.setfund2(am);
    		 System.out.println("Successfully");
    	 }
    	 if(h==2)
    	 {
    		 acct.setfund1(am);
    		 System.out.println("Successfully");
    	 }
    	 writeFile(acct);
    	 Login2(acc);
       	 }
       	 if(Acc1.equals(Acc2))//judge whether the account number given by the user is his account number or not 
       	 {
       		 if(acc.getType()==1)
       		 {
       			System.out.println("You can only withdraw 30 days after depositing money"); 
       		 }
       		String date5;
   	 	 	SimpleDateFormat yyyymmdd =   new SimpleDateFormat( "YYYY.MM.dd" );
   	 		date5=yyyymmdd.format(new Date());
   	 		acc.setdate1(date5);//store this date into user file, this date is the last deposit date
   		 System.out.println("Please enter the amount you want to deposit:");
    	 int am1=sc.nextInt();
    	 if(h==1)
    	 {
    		 acc.setfund2(am1);
    		 System.out.println("Successfully");
    		 System.out.println("Now your balance is"+acc.getFund2()+"$");
    	 }
    	 if(h==2)
    	 {
    		 acc.setfund1(am1);
    		 System.out.println("Successfully");
       		 System.out.println("Now your balance is "+acc.getFund2()+"$");
       		 System.out.println("And you have unclear money "+acc.getFund1()+"$");
    	 }
    	 writeFile(acc);
    	 Login2(acc);
       	 }
    	}
    	else
    	System.out.println("Operation failed,your account has been suspended");
   		Login2(acc);
 	   }
 	   else
 		System.out.println("No.The account dosen't exist.");
 	    Login2(acc);
   	}
   	/**
   	 * Method Withdraw()
   	 * This method is used for a user to withdraw money from his account.
   	 * @param acc
   	 */
   	public void Withdraw(Account acc){
		String[] str3 = acc.getDate2().split("\\.");
		String[] str4 = acc.getDate1().split("\\.");
   		if(acc.getFlag()==true)//judge whether the account is suspended or not
    	{
    	System.out.println("You balance is: "+acc.getFund2()+"$  Please enter the amount you want to withdraw:");
    	int wm=sc.nextInt();
    		if(acc.getType()==1)//judge account type 
    		{
       			if(365*(Integer.parseInt(str3[0])-Integer.parseInt(str4[0]))+30*(Integer.parseInt(str3[1])-Integer.parseInt(str4[1]))+Integer.parseInt(str3[2])-Integer.parseInt(str4[2])>=30&&wm<=acc.getFund2())//judge the difference between today and last deposit date, judge whether money will be overdraft
       			{
       			acc.setfund2(-wm);
    	    	writeFile(acc);
    	        System.out.println("Done! Your cleared balance now have: "+acc.getFund2()+"$");
       			}
       			if(365*(Integer.parseInt(str3[0])-Integer.parseInt(str4[0]))+30*(Integer.parseInt(str3[1])-Integer.parseInt(str4[1]))+Integer.parseInt(str3[2])-Integer.parseInt(str4[2])>=30&&wm>acc.getFund2())//judge the difference between today and last deposit date, judge whether money will be overdraft
       			{
       			System.out.println("Operation failed,you don't have enough balance. Now your balance is:"+"$ "+acc.getFund2());
       			}
       			if(365*(Integer.parseInt(str3[0])-Integer.parseInt(str4[0]))+30*(Integer.parseInt(str3[1])-Integer.parseInt(str4[1]))+Integer.parseInt(str3[2])-Integer.parseInt(str4[2])<30)//judge the difference between today and last deposit date
       			{
       			System.out.println("Operation failed, you can't deposit money from an undue saver account");	
       			}
       			
    		}

    		if((wm>acc.getFund2()&&acc.getType()==2)||(wm>(acc.getFund2()+500)&&acc.getType()==3))  //judge the type of the account and whether money will be overdraft
    		{
    			System.out.println("Operation failed,you don't have enough balance. Now your balance is:"+"$ "+acc.getFund2());
    		}
    		if((wm<=acc.getFund2()&&acc.getType()==2)||(wm<=(acc.getFund2()+500)&&acc.getType()==3))  //judge the type of the account and whether money will be overdraft
    		{
    			acc.setfund2(-wm);
    	    	writeFile(acc);
    	        System.out.println("Done! Your cleared balance now have: "+acc.getFund2()+"$");
    	        Login2(acc);
    		}


    	}
    	else
    	System.out.println("Operation failed,your account has been suspended");
   		Login2(acc);
   	}
   	
   	
   	/**
   	 * Method Suspend()
   	 * This method is used for a user who wants to suspend the account. After this operation, no further transactions can be finished.
   	 * @param acc
   	 */
   	public void Suspend(Account acc) {
   		System.out.println("Please enter your pin to confirm your operation");
   		String efg=sc.nextLine();
	    String pin3=sc.nextLine();
	    if(pin3.equals(acc.getPin()))//verify the pin
	    {
       	acc.setflag(false);//change the boolean value in user file to false(suspend)
       	System.out.println("Done, now your account is suspended");
       	writeFile(acc);
	    }
		else
		  {
		   System.out.println("Operation failed, pin is not right!"); 
		   Login2(acc);  
		   }
       	Login2(acc);
   	}
   	/**
   	 * Method Recover()
   	 * This method is used to recover state of account from suspend state to active state. Then further transactions can be done.
   	 * @param acc
   	 */
   	public void Recover(Account acc){
   		System.out.println("Please enter your pin to confirm your operation");
   		String fgh=sc.nextLine();
	    String pin4=sc.nextLine();
	    if(pin4.equals(acc.getPin()))//verify the pin
	    {
    	acc.setflag(true);//change the boolean value in user file to true(recover the state to active state)
   	 	System.out.println("Done, now your account is has recovered");
   	 	writeFile(acc);
	    }
	    else
		  {
		   System.out.println("Operation failed, pin is not right!"); 
		   Login2(acc);  
		   }
   	 	Login2(acc);
   	}
   	/**
   	 * Method Change()
   	 * This method is used for user to change his pin.
   	 * @param acc
   	 */
   	public void Change(Account acc) {
   		System.out.println("Please enter your pin to confirm your operation");
   		String ghi=sc.nextLine();
   		String pin5=sc.nextLine();
	    if(pin5.equals(acc.getPin()))//verify the pin
	    {
	 	System.out.println("Confirmed, please enter your new pin.");
	    String newpin=sc.nextLine();
    	acc.setpin(newpin);//set the pin and change it in the file
   	 	System.out.println("Done, you have changed your pin");
   	 	writeFile(acc);
 	 	Interface();
	    }
	    else
		  {
		   System.out.println("Operation failed, pin is not right!"); 
		   Login2(acc);  
		   }
   	}
   	/**
   	 * Method Close()
   	 * This method is used for a user to close his account and delete his user file.
   	 * @param acc
   	 */
   	public void Close(Account acc) {
   		System.out.println("Please enter your pin to confirm your operation");
   		String hij=sc.nextLine();
	    String pin6=sc.nextLine();
	    if(pin6.equals(acc.getPin()))//verify the pin
	    {
	    File fff=new File(Acc1+".txt");
        if(fff.delete()) {
        System.out.println("Delete account"+Acc1+ " successfully!");
        Interface();
        }
	    }
	    else
		{
		System.out.println("Operation failed, pin is not right!"); 
		Login2(acc);
		}
   	}
   	/**
   	 * Method Administrator1()
   	 * This method is used to enter the interface to input administrator's secret key.
   	 */
   	public void Administrator1() {
		File F=new File("key.txt");
		String ijk=sc.nextLine();
   		System.out.println("Please input the safe key to veryfy your identity");
		String key=sc.nextLine();
		if (readFile(F).contains(key)) {
			System.out.println("Welcome!");
			Administrator2();
		}
		else
			System.out.println("Wrong key!");
   	}
   	/**
   	 * Method Administrator2()
   	 * This method is used to enter administrator interface and do further operations.
   	 */
   	public void Administrator2() {
  		System.out.println("1.Check user information");
  		System.out.println("2.Clear fund for a user");
		System.out.println("3.Delete user");
		System.out.println("4.Back to main page");
		int a=sc.nextInt();
		if(a!=1&&a!=2&&a!=3&&a!=4)
		{
		 System.out.println("Wrong input! Please input again.");	
		 Administrator2();
		}
		switch(a)
		{
		  case 1:
			Checkinfo();
			break;
		  case 2:
			Clear();
			break;
		  case 3:
			Delete();
			break;
		  case 4:
			Interface();
			break;
		 }
   	}
   	/**
   	 * Method Checkinfo()
   	 * This method is user for an administrator to check information of a particular user given by the administrator.
   	 */
   	public void Checkinfo(){
		String jkl=sc.nextLine();
   		System.out.println("Please input the account number that you want to check");
   		String ACC=sc.nextLine();
   		File ff=new File(ACC+".txt");
   		if(ff.exists())
   		{
   		Account ACC1= setAcc(readFile(ff));
   		 System.out.println("All the information of account"+ACC);
   		 System.out.println("Account type "+ACC1.getType()+"(1 is saver account. 2 is junior account. 3 is current account)");
   		 System.out.println("Name is "+ACC1.getName());
   		 System.out.println("Address is "+ACC1.getAddr());
   		 System.out.println("Birthday "+ACC1.getBirth());
   		 System.out.println("User pin "+ACC1.getPin());
   		 System.out.println("Account number "+ACC1.getAccountnum());
   		 System.out.println("Unclear money "+ACC1.getFund1());
   		 System.out.println("Balance "+ACC1.getFund2());
   		 System.out.println("Date of last time when depositing money "+ACC1.getDate1());
   		 System.out.println("Date of last login "+ACC1.getDate2());
   		 System.out.println("state of account "+ACC1.getFlag()+"(True is normal state and false is suspend state)");
   		}
   		else
   			System.out.println("This account number doesn't exist.");
   			Administrator2();
   	}
   	/**
   	 * Method Clear()
   	 * This method is used for an administrator to clear funds of a particular user given by the administrator.
   	 */
   	public void Clear() {
   		String klm=sc.nextLine();
   		System.out.println("Please input the account number that you want to clear money");
   		String ACC=sc.nextLine();
   		File ff=new File(ACC+".txt");
   		if(ff.exists())
   		{
   		Account ACC2= setAcc(readFile(ff));	
    		ACC2.setfund2(ACC2.getFund1());
    		ACC2.setfund1(-ACC2.getFund1());
    		System.out.println("Done, now the balance on this account is: "+ACC2.getFund2()+"$ ");
    		writeFile(ACC2);
    		Administrator2();

	    }
    	else
    	System.out.println("Operation failed, this account doesn't exist");
	    Administrator2();
   	}
   	/**
   	 * Method Delete()
   	 * This method is used for an administrator to delete account and user file of a user who doesn't have a good credit.
   	 */
   	public void Delete() {
   		String lmn=sc.nextLine();
   		System.out.println("Please input the account number that you want to delete");
   		String ACC=sc.nextLine();
   		File ff=new File(ACC+".txt");
 		if(ff.exists())
   		{
 	     if(ff.delete()) {
 	     System.out.println("Delete account"+ACC+ " successfully!");
   		}
   		}
   		else
   			System.out.println("This account number doesn't exist.");
 			Administrator2();
   	}
	         
}