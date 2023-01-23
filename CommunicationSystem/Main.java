
package question;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {


	public static void main(String args[]) {

		Customer[] customers;
		Operator[] operators;

		int C, O, N;

		File inFile = new File(args[0]);  // args[0] is the input file
		File outFile = new File(args[1]);  // args[1] is the output file
		try {
			PrintStream outstream = new PrintStream(outFile);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		Scanner reader;
		try {
			reader = new Scanner(inFile);
		} catch (FileNotFoundException e) {
			System.out.println("Cannot find input file");
			return;
		}

		C = reader.nextInt();
		O = reader.nextInt();
		N = reader.nextInt();

		customers = new Customer[C];
		operators = new Operator[O];

		//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE
		PrintStream outstream1;
		try {
		        outstream1 = new PrintStream(outFile);
		}catch(FileNotFoundException e2) {
		        e2.printStackTrace();
		        return;
		}
		/*
		 * i have separated every line in input file and removed the whitespace from both sides with trim() method.
		 * then because of the first line is empty, i have continued
		 * i have splitted every value and i have given them name
		 * i grouped each value as it is written in the report(like string, integer, double)
		 * then according to the command, I created an object or called a method.
		 * i have set the object ID and index in the list to be the same.
		 */
		int cus = 0;
		int ope = 0;
		for (int i = 0; i < N+1; i++) {
			String str = reader.nextLine().trim();
			if (i==0) {
				continue;
			}
	        String[] arrOfStr = str.split(" ", 5);
	        String s0=arrOfStr[0];  
	        int intFirst=Integer.parseInt(s0);
	        
	        if (intFirst==1) {
	        	String s1=arrOfStr[1];
	        	String s2=arrOfStr[2];  
	        	int intAge=Integer.parseInt(s2);
	        	String s3=arrOfStr[3];  
	        	int intId=Integer.parseInt(s3);
	        	String s4=arrOfStr[4];  
	        	double doubleLimiting =Double.parseDouble(s4);
	        	Customer myCustomer = new Customer(cus, s1, intAge, operators[intId], doubleLimiting);
	        	customers[cus] = myCustomer;
	        	cus++;
	        }
	        
	        else if (intFirst==2) {
	        	String s1=arrOfStr[1];  
	        	double talkingCharge =Double.parseDouble(s1);
	        	String s2=arrOfStr[2];  
	        	double messageCost=Double.parseDouble(s2);
	        	String s3=arrOfStr[3];  
	        	double networkCharge =Double.parseDouble(s3);
	        	String s4=arrOfStr[4];  
	        	int discountRate=Integer.parseInt(s4);
	        	Operator myOperator = new Operator(ope,talkingCharge, messageCost, networkCharge, discountRate);
	        	operators[ope] = myOperator;
	        	ope++;
	        }
	        
	        else if (intFirst==3) {
	        	String s1=arrOfStr[1];  
	        	int firstCustomer =Integer.parseInt(s1);
	        	String s2=arrOfStr[2];  
	        	int secondCustomer=Integer.parseInt(s2);
	        	String s3=arrOfStr[3];  
	        	int time =Integer.parseInt(s3);
	        	customers[firstCustomer].talk(time, customers[secondCustomer]);
			}
	        
	        else if (intFirst==4) {
	        	String s1=arrOfStr[1];  
	        	int firstCustomer =Integer.parseInt(s1);
	        	String s2=arrOfStr[2];  
	        	int secondCustomer=Integer.parseInt(s2);
	        	String s3=arrOfStr[3];  
	        	int quantity =Integer.parseInt(s3);
	        	customers[firstCustomer].message(quantity, customers[secondCustomer]);
			}
	        
	        else if (intFirst==5) {
	        	String s1=arrOfStr[1];  
	        	int customerId =Integer.parseInt(s1);
	        	String s2=arrOfStr[2];  
	        	double amount =Double.parseDouble(s2);
	        	customers[customerId].connection(amount);
			}
	        
	        else if (intFirst==6) {
	        	String s1=arrOfStr[1];  
	        	int customerId =Integer.parseInt(s1);
	        	String s2=arrOfStr[2];  
	        	double amount =Double.parseDouble(s2);
	        	customers[customerId].getBill().pay(amount);
			}
	        
	        else if (intFirst==7) {
	        	String s1=arrOfStr[1];  
	        	int customerId =Integer.parseInt(s1);
	        	String s2=arrOfStr[2];  
	        	int operatorId =Integer.parseInt(s2);
	        	customers[customerId].setOperator(operators[operatorId]);
			}
	        
	        else if (intFirst==8) {
	        	String s1=arrOfStr[1];  
	        	int customerId =Integer.parseInt(s1);
	        	String s2=arrOfStr[2];  
	        	double amount =Double.parseDouble(s2);
	        	customers[customerId].getBill().changeTheLimit(amount);
			}
		}
		/*
		 * I assigned the first customer minute to be the mostMinute, and if there were more speakers than her, I assigned her minute to the variable.
		 * I used the same method for the customers who send the most messages and use the Internet.
		 */
		
		int mostMinute = customers[0].getMinutesTalked();
	    String mostTalkative = customers[0].name;
		for (int t = 0; t < customers.length; t++) {
		       if (customers[t].getMinutesTalked() > mostMinute) {
		           mostMinute = customers[t].getMinutesTalked();
			       mostTalkative = customers[t].name;
		        }
		    }
		int mostMessage = customers[0].getMessagesSent();
		String mostMessager = customers[0].name;
		for (int m = 0; m < customers.length; m++) {
			 if (customers[m].getMessagesSent() > mostMessage) {
		           mostMessage = customers[m].getMessagesSent();
			       mostMessager = customers[m].name;
		        }
			}
		double mostInternet = customers[0].getInternetUsed();
		String mostNetter = customers[0].name;
		for (int n = 0; n < customers.length; n++) {
			 if (customers[n].getInternetUsed() > mostInternet) {
		           mostInternet = customers[n].getInternetUsed();
			       mostNetter = customers[n].name;
		        }
			}
		
		for (int a = 0; a < operators.length; a++) {
			outstream1.println("Operator "+a+" : "+ operators[a].getMinuteServiced()+" "+operators[a].getMessageServiced()+" "+String.format("%.2f",operators[a].getInternetServiced()));
		}
		for (int b = 0; b < customers.length; b++) {
			outstream1.println("Customer "+b+" : "+ String.format("%.2f",customers[b].getBill().getCustomerPaid())+" "+String.format("%.2f",customers[b].getBill().getCurrentDebt()));
		}
		outstream1.println(mostTalkative+" : "+mostMinute);
		outstream1.println(mostMessager+" : "+mostMessage);
		outstream1.println(mostNetter+" : "+String.format("%.2f",mostInternet));
		//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE
	}
}

