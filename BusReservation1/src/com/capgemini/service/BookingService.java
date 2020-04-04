 package com.capgemini.service;

import com.capgemini.model.Passenger;

public interface BookingService {
	void setReturnJourneyFlag(boolean returnJourneyFlag);
	boolean signUp(Passenger pssgn);
	Passenger login(String userName);
	boolean passwordVerification(Passenger pssgn,String password);
	String[] searchBus(String source,String destination);
	int seatAvailability(String busName);
	boolean verfiySelectedSeatAvailable(String busName,int seatNumber);
	void bookTicket(String busName,int seatNumber,String pssgnName);
}
