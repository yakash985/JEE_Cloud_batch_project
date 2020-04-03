 package com.capgemini.service;

import com.capgemini.model.Passenger;

public interface BookingService {
	boolean signUp(Passenger pssgn);
	Passenger login(String userName);
	boolean passwordVerification(Passenger pssgn,String password);
	String[] searchBus(String source,String destination);
	void seatAvailability(String busName);
	boolean verfiySelectedSeatAvailable(String busName,int seatNumber);
	boolean bookTicket(String busName[][],int seatNumber);
}
