package com.capgemini.dao;

import java.util.List;
import java.util.Map;

import com.capgemini.model.Passenger;

public interface BusDao {
	List<Passenger> retrievePassgnList();
	Map<String, String[][]> retrieveBusList();	
	
}
