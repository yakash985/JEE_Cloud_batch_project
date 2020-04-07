package com.capgemini.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.capgemini.model.Passenger;

public class BusDaoImpl implements BusDao{
	
	private static List<Passenger> pssgnList = new ArrayList<Passenger>();

	private static Map<String, String[][]> busList = new HashMap<String, String[][]>();
	private static String bus1[][] = new String[2][20];// bus1-->is name of bus which is having 20seats
	private static String bus2[][] = new String[2][20];// 2 rows are created so we can use 1st row for goining on route1
														// and while returning we will use 2nd row
	private static String bus3[][] = new String[2][20];
	private static String bus4[][] = new String[2][20];
	
	private static String route1[] = { "Bus1", "Bus2", "Bus3" };// from Mumbai to Panvel
	private static String route2[] = { "Bus2", "Bus3" };// from Mumbai to Panvel to Lonavala
	private static String route3[] = { "Bus3" };// from Mumbai to Panvel to Lonavala to Pune

	static {
		Passenger pssgn1 = new Passenger("Atharva", "AtharvaP1997", 21,'M', "22222222");
		Passenger pssgn2 = new Passenger("Arun", "Arun1997", 22,'M', "88888888");
		Passenger pssgn3 = new Passenger("Akash", "AkashY1997", 23,'M', "11111111");
		pssgnList.add(pssgn1);
		pssgnList.add(pssgn2);
		pssgnList.add(pssgn3);		
		
		busList.put("Bus1", bus1);// static block--> code inside static block is executed only once: the first
									// time you make an object of that class or the first time you access a static
									// member of that class (even if you never make an object of that class).
		busList.put("Bus2", bus2);
		busList.put("Bus3", bus3);
		busList.put("Bus4", bus4);
		bus1[0][1] = "conductor1";// third seat not available
		bus1[0][3] = "Akash Yadav";
		bus1[0][4] = "Arun Yadav";				
		bus1[1][1] = "conductor2";
		bus2[0][1] = "conductor3";// third seat not available
		bus2[1][1] = "conductor4";
		bus3[0][1] = "conductor5";// third seat not available
		bus3[1][1] = "conductor6";
		bus4[0][1] = "conductor7";// third seat not available
		bus4[1][1] = "conductor8";

	}
	
	@Override
	public List<Passenger> retrievePassgnList() {
		return pssgnList;
	}
	@Override
	public Map<String, String[][]> retrieveBusList() {
		return busList;
	}
	public static String[] getRoute1() {
		return route1;
	}
	public static void setRoute1(String[] route1) {
		BusDaoImpl.route1 = route1;
	}
	public static String[] getRoute2() {
		return route2;
	}
	public static void setRoute2(String[] route2) {
		BusDaoImpl.route2 = route2;
	}
	public static String[] getRoute3() {
		return route3;
	}
	public static void setRoute3(String[] route3) {
		BusDaoImpl.route3 = route3;
	}
	
	
}
