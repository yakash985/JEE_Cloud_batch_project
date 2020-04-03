package com.capgemini.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.capgemini.model.Passenger;

public class BookingServiceImpl implements BookingService {
	private static List<Passenger> pssgnList = new ArrayList<Passenger>();
	private static HashMap<String, String[][]> busList = new HashMap<String, String[][]>();

	private static String bus1[][] = new String[2][20];// bus1-->is name of bus which is having 20seats
	private static String bus2[][] = new String[2][20];// 2 rows are created so we can use 1st row for goining on route1
														// and while returning we will use 2nd row
	private static String bus3[][] = new String[2][20];
	private static String bus4[][] = new String[2][20];
	static {
		busList.put("Bus1", bus1);// static block--> code inside static block is executed only once: the first
									// time you make an object of that class or the first time you access a static
									// member of that class (even if you never make an object of that class).
		busList.put("Bus2", bus2);
		busList.put("Bus3", bus3);
		busList.put("Bus4", bus4);
		bus1[0][1] = "conductor1";// third seat not available
		bus1[1][1] = "conductor2";
		bus2[0][1] = "conductor3";// third seat not available
		bus2[1][1] = "conductor4";
		bus3[0][1] = "conductor5";// third seat not available
		bus3[1][1] = "conductor6";
		bus4[0][1] = "conductor7";// third seat not available
		bus4[1][1] = "conductor8";

	}

	private static String route1[] = { "Bus1", "Bus2", "Bus3" };// from Mumbai to Panvel
	private static String route2[] = { "Bus2", "Bus3" };// from Mumbai to Panvel to Lonavala
	private static String route3[] = { "Bus3" };// from Mumbai to Panvel to Lonavala to Pune

	private static boolean returnJourneyFlag = false;// we will use this in selection of bus

	@Override
	public boolean signUp(Passenger pssgn) {
		boolean result = pssgnList.add(pssgn);
		return result;
	}

	@Override
	public Passenger login(String userName) {
		Iterator<Passenger> itr = pssgnList.iterator();
		Passenger pssgnr = null;
		while (itr.hasNext()) {
			pssgnr = itr.next();
			if (userName.equals(pssgnr.getUserName())) {
				break;
			}
			pssgnr = null;
		}
		return pssgnr;
	}

	@Override
	public boolean passwordVerification(Passenger pssgn, String password) {
		if (password.equals(pssgn.getPassword())) {
			System.out.println("Correct Password");
			System.out.println("Go for booking!!!!!!!");
			System.out.println();
			return true;
		} else {
			System.out.println("Incorrect Password,Enter the password again");
			System.out.println();
			return false;
		}

	}

	@Override
	public String[] searchBus(String source, String destination) {
		String mumbai = "Mumbai";
		String panvel = "Panvel";
		String lonavala = "Lonavala";
		String pune = "Pune";
		boolean f;
		Scanner input1 = new Scanner(System.in);
		if ((f = (mumbai.equals(source) && panvel.equals(destination)))
				|| (mumbai.equals(destination) && panvel.equals(source))) {
			if (f) {
				for (int i = 0; i < route1.length; i++) {
					System.out.println((i + 1) + ". " + route1[i]);
				}

			} else {
				for (int i = 0; i < route1.length; i++) {
					System.out.println((i + 1) + ". " + route1[i] + "R");
				}
				returnJourneyFlag = true; // we set returnJourneyFlag to True in order to use it in selection seat
											// availability in that bus
			}
			return route1;

		} else if ((f = (((mumbai.equals(source) || panvel.equals(source))) && lonavala.equals(destination)))
				|| ((mumbai.equals(destination) || panvel.equals(destination)) && lonavala.equals(source))) {
			if (f) {
				for (int i = 0; i < route2.length; i++) {
					System.out.println((i + 1) + ". " + route2[i]);
				}
				returnJourneyFlag = false; // we set returnJourneyFlag to True in order to use it in selection seat
											// availability in that bus
			} else {
				for (int i = 0; i < route2.length; i++) {
					System.out.println((i + 1) + ". " + route2[i] + "R");
					returnJourneyFlag = true; // we set returnJourneyFlag to True in order to use it in selection seat
												// availability in that bus
				}
			}
			return route2;
		} else if ((f = ((mumbai.equals(source) || panvel.equals(source) || lonavala.equals(source))
				&& pune.equals(destination)))
				|| ((mumbai.equals(destination) || panvel.equals(destination) || lonavala.equals(destination))
						&& pune.equals(source))) {
			if (f) {
				for (int i = 0; i < route3.length; i++) {
					System.out.println((i + 1) + ". " + route3[i]);
				}
			} else {
				for (int i = 0; i < route3.length; i++) {
					System.out.println((i + 1) + ". " + route3[i] + "R");
				}
				returnJourneyFlag = true; // we set returnJourneyFlag to True in order to use it in selection seat
											// availability in that bus
			}
			return route3;
		} else {
			return null;
		}

	}

	@Override
	public int seatAvailability(String busName) {
		//return type is int--->because it returns number of seats in that bus 
		System.out.println("Seats available in " + busName);
		String bus[][] = busList.get(busName);
		if (returnJourneyFlag == false) {
			for (int i = 0; i < bus[0].length; i++) {
				if (bus[0][i] == null) {
					System.out.printf("%d  ", i + 1);
					if (i == 9) {
						System.out.println();
					}
				}
			}
			System.out.println();
			return bus[0].length;
		} else {
			for (int i = 0; i < bus[1].length; i++) {
				if (bus[1][i] == null) {
					System.out.printf("%d  ", i + 1);
					if (i == 9) {
						System.out.println();
					}
				}
			}
			System.out.println();
			return bus[1].length;
		}
	}

	@Override
	public boolean verfiySelectedSeatAvailable(String busName, int seatNumber) {
		String bus[][] = busList.get(busName);
		if (returnJourneyFlag == false) {
			if (bus[0][seatNumber - 1] == null) {
				System.out.println("You have selected seat number " + seatNumber + " of " + busName);
				return true;
			} else {
				System.out.println("This seat is not available sir!!!!");
				return false;
			}
		} else {
			if (bus[1][seatNumber - 1] == null) {
				System.out.println("You have selected seat number " + seatNumber + " of " + busName);
				return true;
			} else {
				System.out.println("This seat is not available sir!!!!");
				return false;
			}
		}

	}

	@Override
	public void bookTicket(String busName, int seatNumber, String pssgnName) {
		// TODO Auto-generated method stub
		String bus[][] = busList.get(busName);
		if (returnJourneyFlag == false) {
			bus[0][seatNumber-1] = pssgnName;
			busList.replace(busName, bus);
			System.out.println();
			System.out.println("You have successfully booked the ticket!!!");
			System.out.println("Your Ticket details are:");
			System.out.println("Passsenger Name :"+pssgnName);
			System.out.println("Bus Name :"+busName);
			System.out.println("Seat Number"+seatNumber);
		}else {
			bus[0][seatNumber-1] = pssgnName;
			busList.replace(busName, bus);
			System.out.println();
			System.out.println("You have successfully booked the ticket!!!");
			System.out.println("Your Ticket details are:");
			System.out.println("Passsenger Name :"+pssgnName);
			System.out.println("Bus Name :"+busName);
			System.out.println("Seat Number"+seatNumber);
		}

		returnJourneyFlag = false;
	}

}
