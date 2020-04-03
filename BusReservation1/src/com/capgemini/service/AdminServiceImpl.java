package com.capgemini.service;

import java.util.HashMap;

public class AdminServiceImpl implements AdminService {
	
	private static HashMap<String, String[][]> busList = new HashMap<String, String[][]>();
	
	
	private static String bus1[][] = new String[2][20];// bus1-->is name of bus which is having 20seats
	private static String bus2[][] = new String[2][20];// 2 rows are created so we can use 1st row for goining on route1 and while returning we will use 2nd row
	private static String bus3[][] = new String[2][20];
	private static String bus4[][] = new String[2][20];
	static {
		busList.put("Bus1", bus1);// static block--> code inside static block is executed only once: the first time you make an object of that class or the first time you access a static member of that class (even if you never make an object of that class).
		busList.put("Bus2", bus2);
		busList.put("Bus3", bus3);
		busList.put("Bus4", bus4);

	}
	
	
	@Override
	public void addBus(String[][] bus,String busName) {
		busList.put(busName,bus);		
		// TODO Auto-generated method stub
		
	}





}
