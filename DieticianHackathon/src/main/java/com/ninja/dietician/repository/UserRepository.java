package com.ninja.dietician.repository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.amazonaws.services.dynamodbv2.model.AmazonDynamoDBException;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.ninja.dietician.entity.User;


@Repository
public class UserRepository {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserRepository.class);
	
	@Autowired
	private DynamoDBMapper mapper;
	
	public void createUser(User user)  {
			mapper.save(user);
					
	}

		
	public PaginatedScanList<User> getAllUsers(DynamoDBScanExpression scanExpression)
	{
		return mapper.scan(User.class, scanExpression);
	}
	
	
	public void updateUser(User user,DynamoDBScanExpression scanExpression) throws AmazonDynamoDBException {
		try {
				SimpleDateFormat sd = new SimpleDateFormat("MM/dd/yyyy");
				//Load specific user
				List<User> userList = mapper.scan(User.class,scanExpression);
				
				userList.forEach(user1 -> {
					user1.setFirstName(user.getFirstName());
					user1.setLastName(user.getLastName());
					user1.setAddress(user.getAddress());
					user1.setContact(user.getContact());
					user1.setEmail(user.getEmail());
					user1.setFoodCategory(user.getFoodCategory());
					user1.setAllergy(user.getAllergy());
					
					user1.setModifiedon(sd.format(new Date()));
					
					//Update user
					mapper.save(user1);
				});
						
		} catch (AmazonDynamoDBException e) {
			LOGGER.error("Update failed! "+e.getMessage());
			
		}
	}
	
	
	public void deleteUser(DynamoDBScanExpression scanExpression) throws AmazonDynamoDBException{
		
		List<User> userList = mapper.scan(User.class,scanExpression);
		userList.forEach(user -> {
			mapper.delete(user);
		});		
	}
	
	public List<User> getUserbyFirstName(String FirstName) {
		  HashMap<String, AttributeValue> eav1 = new HashMap<String, AttributeValue>();
		  eav1.put(":v1", new AttributeValue().withS(FirstName));
		  

		  DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
		    // Filter Expression
		    .withFilterExpression("FirstName = :v1 ")
		    .withExpressionAttributeValues(eav1);

		  List<User> UserList = mapper.scan(User.class, scanExpression);
		  
		  return UserList;
		 }
	
	
	public List<User> getUserbyEmail(String Email) {
		  HashMap<String, AttributeValue> eav1 = new HashMap<String, AttributeValue>();
		  eav1.put(":v2", new AttributeValue().withS(Email));
		  

		  DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
		    // Filter Expression
		    .withFilterExpression("Email = :v2 ")
		    .withExpressionAttributeValues(eav1);

		  List<User> UserList = mapper.scan(User.class, scanExpression);
		  
		  return UserList;
		 }
	
	public List<User> getUserbyContact(String Contact) {
		  HashMap<String, AttributeValue> eav1 = new HashMap<String, AttributeValue>();
		  eav1.put(":v3", new AttributeValue().withS(Contact));
		  

		  DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
		    // Filter Expression
		    .withFilterExpression("Contact = :v3 ")
		    .withExpressionAttributeValues(eav1);

		  List<User> UserList = mapper.scan(User.class, scanExpression);
		  
		  return UserList;
		 }
	

}
