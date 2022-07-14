package com.ninja.dietician.service;

import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.ninja.dietician.entity.User;
import com.ninja.dietician.exception.InvalidDataException;

public interface UserService {

	void createUser(User user) throws InvalidDataException;

	PaginatedScanList<User> getAllUsers();

	void updateUser(User user, String dieticianId, String userId) throws InvalidDataException;

	String deleteUser(String dieticianId, String userId) throws InvalidDataException;
	
	public List<User> getUserbyFirstName(String FirstName);
	public List<User> getUserbyEmail(String Email);
	public List<User> getUserbyContact(String Contact);
	

}