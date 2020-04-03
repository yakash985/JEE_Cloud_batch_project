package com.capgemini.ui;

import java.util.Scanner;

import com.capgemini.model.Passenger;
import com.capgemini.service.BookingService;
import com.capgemini.service.BookingServiceImpl;

public class BusBooking {

	public static void main(String[] args) {
Scanner input = new Scanner(System.in);
		
		BookingService service = new BookingServiceImpl();
		boolean flag =true;
		
		Passenger pssgn1 = new Passenger("Atharva", "AtharvaP1997", 21,'M', "22222222");
		Passenger pssgn2 = new Passenger("Arun", "Arun1997", 22,'M', "88888888");
		Passenger pssgn3 = new Passenger("Akash", "AkashY1997", 23,'M', "11111111");
		service.signUp(pssgn1);
		service.signUp(pssgn2);
		service.signUp(pssgn3);
	//This is data which will automatically filed in database whenever we execute our project
	//so we don't need to signup again and again
	
		
		do {
			System.out.println("*******************Bus Reservation*******************");
			System.out.println();
			System.out.println("Welcome to Bus Reservation");
			System.out.println();
			System.out.println("1. Login");
			System.out.println("2. SignUp");
			System.out.println("3. For verifying whether the method of source to destination");
			System.out.println("0. Exit Application");
			System.out.println("Enter your choice: ");			
			int choice = input.nextInt();//here also exception will come because if user enters input other than digit
			System.out.println();
			
			switch(choice) {
		
			case 1:
				Passenger pssgn;
				boolean nextLoopflag;
				String addword ="";
				while(true) {
					System.out.println("===========================================================================");
					System.out.println("Enter your login Username "+addword+":");
					String userName = input.next();
					pssgn = service.login(userName);
					if(pssgn==null) {
						System.out.println("!!!!!!!!!!invalid user name");
						System.out.println();
						nextLoopflag = false;
						addword ="again";
						continue ;
					}
					else {
						nextLoopflag = true;
						System.out.println("Enter the password: ");
						break;
					}				
				}
				while(nextLoopflag) {
					String password = input.next();
					boolean result = service.passwordVerification(pssgn, password);
					if(result==false) {
						System.out.println("Enter the password again: ");
						continue;
					}
					else {
						nextLoopflag = false;
						//flag=true;//used for debuging
						break;
					}
				}	
				
				break;
			case 2:
				System.out.println("===========================================================================");
				System.out.println("Enter your Full Name:");
				input.nextLine(); // This line you have to add (It consumes the \n character which was not read by nextInt() earlier)
				String passangerName = input.nextLine();				
				System.out.println("Enter username:");
				String userName = input.next();
				System.out.println("Enter your age:");
				int age = input.nextInt();
				System.out.println("Enter your Gender M/F:");
				char gender = input.next().charAt(0);
				while(gender!='M'&&gender!='F') {
					System.out.println("!!!Enter either \"M\"->for male or \"F\"->for Female!!!");
					gender = input.next().charAt(0);
				}
				System.out.println("Set your password:");
				String password = input.next();
				Passenger pssgnr1 = new Passenger(passangerName, userName, age, gender, password);
				System.out.println();
				System.out.println("Your details are as follows:");
				service.signUp(pssgnr1);
				System.out.println(pssgnr1.toString());
				System.out.println();
				System.out.println("Enter \"back\" to go to login page ");
				String goback = input.next();
				while(!goback.equals("back")){
					System.out.println("Inavlid input!!!");
					System.out.println("###Enter \"back\" to go to login page ");
					goback = input.next();//here we have to add exception becuase if 
					//user enter like this"back k" the space will create inputmismatch exception 
					//will arise
					System.out.println();
					System.out.println("===========================================================================");
				}
				System.out.println();
				System.out.println("===========================================================================");

				break;
			case 3:
				label:
				System.out.println("Enter your Source");
				String source = input.next();
				System.out.println("Enter your destination");
				String destination = input.next();
				String route[] = service.searchBus(source, destination);
				
				System.out.println("Select the bus in which you want to travel");
				int busChoice = input.nextInt();
				System.out.println();
				service.seatAvailability(route[busChoice-1]);
				
				System.out.println("Enter the seat number you want from the above available seats");
				int seatChoice = input.nextInt();
//				booleanservice.verfiySelectedSeatAvailable(bus, seatChoice);
				while((service.verfiySelectedSeatAvailable(route[busChoice-1], seatChoice))==false) {
					System.out.println("Please enter the seat number again you want from the above available seats");
					seatChoice = input.nextInt();
				}
				System.out.println();
				
				
				break;
			case 0:
				flag =false;
			
			}		
			
		} while (flag);	

	
	
	
	
	
//	//login page
//		process:while(true) {
//	 
//		System.out.println("Enter your login Username:");
//		String userName = input.next();
//		Passenger pssgn = service.login(userName);
//		if(pssgn==null) {
//			System.out.println("!!!!!!!!!!invalid user name");
//			System.out.println("========================================================");
//			System.out.println();
//			continue process;
//		}
//		System.out.println("Enter the password: ");
//		String password = input.next();
//		boolean result = service.passwordVerification(pssgn, password);
//		
//		}
		
		
////		System.out.println(pssgn.getAge());
////		pssgn =null;
//		try {
//			System.out.println(pssgn.toString());
//		} catch (NullPointerException e) {
//			System.out.println("invalid user name");
//		}
//		
//		System.out.println(userName);
	
	}

}
