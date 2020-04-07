package com.capgemini.ui;

import java.util.Scanner;

import com.capgemini.model.Passenger;
import com.capgemini.service.BookingService;
import com.capgemini.service.BookingServiceImpl1;

public class BusBooking {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		BookingService service = new BookingServiceImpl1();
		boolean flag =true;
		
		do {
			System.out.println("*******************Bus Reservation*******************");
			System.out.println();
			System.out.println("Welcome to Bus Reservation");
			System.out.println();
			System.out.println("1. Login");
			System.out.println("2. SignUp");
			System.out.println("3. Admin work for debuging");
			System.out.println("0. Exit Application");
			System.out.println("Enter your choice: ");			
			int choice = input.nextInt();//here also exception will come because if user enters input other than digit
			while(choice!=1 && choice!=2 && choice!=3 && choice!=0) {
				System.out.println("Inavlid input!!!----enter either\'1\' or\'2\' or\'0\'");
				choice = input.nextInt();
			}
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
				
				System.out.println("===========================================================================");
				String goback;
				String busName;
				int busChoice;
				int seatChoice;
				label1:
				while(true){
					//####
					System.out.println("Enter your Source");
					String source = input.next();
					System.out.println("Enter your destination");
					String destination = input.next();
					String route[] = service.searchBus(source, destination);
					//####@@@@
					System.out.println("Select the bus from above list in which you want to travel");
					busChoice = input.nextInt();
					while(busChoice>route.length) {
						System.out.println("Invalid input!!!!");
						System.out.println("Select the bus agian from above list in which you want to travel");
						busChoice = input.nextInt();
					}
					System.out.println();
					busName = route[busChoice-1];

					label2:
						while(true) {
							int numberOfSeats =service.seatAvailability(route[busChoice-1]);
							//####@@@@
							System.out.println("Enter the seat number you want from the above available seats");
							seatChoice = input.nextInt();
							while(seatChoice<1 || seatChoice>numberOfSeats) {
								System.out.println(seatChoice+" is not present in bus!!! ");
								System.out.println("Enter the seat number again which you want from the above available seats");
								seatChoice = input.nextInt();
							}
			
							while((service.verfiySelectedSeatAvailable(route[busChoice-1], seatChoice))==false) {
								System.out.println("Please enter the seat number again you want from the above available seats");
								seatChoice = input.nextInt();
							}
							System.out.println();
							//####
							System.out.println("To confirm the seat for booking press");
							System.out.println("1---->to confirm and book the seat");
							System.out.println("2---->to go to main menu");
							int conf = input.nextInt();
							while(conf!=1 && conf!=2) {
								System.out.println("Inavlid input!!!----enter either\'1\' or\' 2\'");
								conf = input.nextInt();
							}
							if(conf==1) {
								System.out.println(""
										+ "");
								System.out.println("Enter the passenger name ");
								input.nextLine();
								String pssgnName = input.nextLine();
								service.bookTicket(busName, seatChoice, pssgnName);
								System.out.println();
								System.out.println("Press following:");
								System.out.println("1.  to book again");
								System.out.println("2.  to go to main menu and book again in another bus");
								System.out.println("3.  to sign out ");
								int opt = input.nextInt();
								while(opt!=1 && opt!=2 && opt!=3) {
									System.out.println("Inavlid input!!!----enter either\'1\' or \'2\' or \'3\'");
									opt = input.nextInt();
								}
								if(opt==1) {
									continue label2;
								}else if(opt==2) {
									
								//set the return flag in bookserviceimpl to false since we are again searching for bus
									service.setReturnJourneyFlag(false);
									continue label1;
								}else{
									service.setReturnJourneyFlag(false);
									break label1;
								}
							}else{
								continue label1;						
							}
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
				goback = input.next();
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
				System.out.println("To see passengers in bus enter the bus name ");
				String busName1 = input.next();
				service.listBusDisplay(busName1);
				service.listPassgnDisplay();
				break;
			case 0:
				flag =false;
			
			}		
			
		} while (flag);	
		
		input.close();
	}

}
