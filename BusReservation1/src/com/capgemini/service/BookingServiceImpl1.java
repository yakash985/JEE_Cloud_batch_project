package com.capgemini.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.capgemini.dao.BusDao;
import com.capgemini.dao.BusDaoImpl;
import com.capgemini.model.Passenger;

public class BookingServiceImpl1 implements BookingService {
	
	private static Map<String, String[][]> busList1 = new HashMap<String, String[][]>();
	private static BusDao busDao = new BusDaoImpl();
	private static List<Passenger> pssgnList;
	
	////

	private static String route1[] = { "Bus1", "Bus2", "Bus3" };// from Mumbai to Panvel
	private static String route2[] = { "Bus2", "Bus3" };// from Mumbai to Panvel to Lonavala
	private static String route3[] = { "Bus3" };// from Mumbai to Panvel to Lonavala to Pune

	private static boolean returnJourneyFlag = false;// we will use this in selection of bus
	public void setReturnJourneyFlag(boolean returnJourneyFlag) {
		BookingServiceImpl1.returnJourneyFlag = returnJourneyFlag;
	}

	@Override
	public boolean signUp(Passenger pssgn) {
		pssgnList= busDao.retrievePassgnList();
		boolean result = pssgnList.add(pssgn);
		return result;
	}

	@Override
	public Passenger login(String userName) {
		pssgnList= busDao.retrievePassgnList();
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
		busList1 = busDao.retrieveBusList();
		String bus[][] = busList1.get(busName);		
		if (returnJourneyFlag == false) {
			System.out.println("Seats available in " + busName);
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
			System.out.println("Seats available in " + busName+"R");
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
		busList1 = busDao.retrieveBusList();
		
		String bus[][] = busList1.get(busName);
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
				System.out.println("You have selected seat number " + seatNumber + " of " + busName+"R");
				return true;
			} else {
				System.out.println("This seat is not available sir!!!!");
				return false;
			}
		}

	}

	@Override
	public void bookTicket(String busName, int seatNumber, String pssgnName) {
		
		busList1 = busDao.retrieveBusList();
		String bus[][] = busList1.get(busName);
		if (returnJourneyFlag == false) {
			bus[0][seatNumber-1] = pssgnName;
			busList1.replace(busName, bus);
			System.out.println();
			System.out.println("You have successfully booked the ticket!!!");
			System.out.println("Your Ticket details are:");
			System.out.println("Passsenger Name :"+pssgnName);
			System.out.println("Bus Name :"+busName);
			System.out.println("Seat Number"+seatNumber);
		}else {
			bus[1][seatNumber-1] = pssgnName;
			busList1.replace(busName, bus);
			System.out.println();
			System.out.println("You have successfully booked the ticket!!!");
			System.out.println("Your Ticket details are:");
			System.out.println("Passsenger Name :"+pssgnName);
			System.out.println("Bus Name :"+busName+"R");
			System.out.println("Seat Number :"+seatNumber);
		}
	}

	@Override
	public void listBusDisplay() {
		busList1 = busDao.retrieveBusList(); 
		String busName ="Bus1";
		String bus[][] = busList1.get(busName);
		for (int i = 0; i < bus.length; i++) {
			System.out.println("Bus journey code"+i);
			for (int j = 0; j < bus[i].length; j++) {
				System.out.println(j+" "+bus[i][j]);
			}
		}
		
	}

	@Override
	public void listPassgnDisplay() {
		pssgnList = busDao.retrievePassgnList();
		Iterator<Passenger> itr = pssgnList.iterator();
		Passenger pssgnr = null;
		while (itr.hasNext()) {
			pssgnr = itr.next();
			System.out.println(pssgnr.toString());
		}
		
	}

	



}
