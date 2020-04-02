package com.capgemini.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.capgemini.model.Passenger;

public class BookingServiceImpl implements BookingService {
	private static List<Passenger> pssgnList = new ArrayList<Passenger>();
	private static String bus1[][] = new String[2][20];// bus1-->is name of bus which is having 20seats
	private static String bus2[][] = new String[2][20];// 2 rows are created so we can use 1st row for goining on route1
//	and while returning we will use 2nd row
	private static String bus3[][] = new String[2][20];
	private static String bus4[][] = new String[2][20];

	private static String route1[] = { "Bus1", "Bus2", "Bus3" };// from Mumbai to Panvel
	private static String route2[] = { "Bus2", "Bus3" };// from Mumbai to Panvel to Lonavala
	private static String route3[] = { "Bus3" };// from Mumbai to Panvel to Lonavala to Pune

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
	public void searchBus(String source, String destination) {
		String mumbai = "Mumbai";
		String panvel = "Panvel";
		String lonavala = "Lonavala";
		String pune = "Pune";
		boolean f;

		if ((f=(mumbai.equals(source) && panvel.equals(destination)))  ||  
						(mumbai.equals(destination) && panvel.equals(source))) {
			if(f) {
				for (int i = 0; i < route1.length; i++) {
					System.out.println((i + 1) + ". " + route1[i]);
				}
			}
			else {
				for (int i = 0; i < route1.length; i++) {
					System.out.println((i + 1) + ". " + route1[i]+"R");
				}
			}
			
		} else if ((f=(((mumbai.equals(source)||panvel.equals(source))) && lonavala.equals(destination)))  ||  
						((mumbai.equals(destination)||panvel.equals(destination)) && lonavala.equals(source))) {
			if(f) {
				for (int i = 0; i < route2.length; i++) {
					System.out.println((i + 1) + ". " + route2[i]);
				}
			}
			else {
				for (int i = 0; i < route2.length; i++) {
					System.out.println((i + 1) + ". " + route2[i]+"R");
				}
			}
		} else if ((f=((mumbai.equals(source)||panvel.equals(source)||lonavala.equals(source)) && pune.equals(destination)))  ||  
						((mumbai.equals(destination)||panvel.equals(destination)||lonavala.equals(destination)) && pune.equals(source))) {
			for (int i = 0; i < route3.length; i++) {
				System.out.println((i + 1) + ". " + route3[i]);
			}
		}

	}

	@Override
	public void seatAvailability(String[] busName) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean bookTicket(String[] busName, int seatNumber) {
		// TODO Auto-generated method stub
		return false;
	}

}
