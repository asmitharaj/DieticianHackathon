package com.ninja.dietician.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.ninja.dietician.entity.User;
import com.ninja.dietician.exception.InvalidDataException;
import com.ninja.dietician.repository.UserRepository;
import com.ninja.dietician.util.UserIDGenerator;

@Service
public class UserService {
	
		private static final Logger LOGGER = LoggerFactory.getLogger(UserRepository.class);
		
		@Autowired
		private UserIDGenerator userIDGenerator;
		
		@Autowired
		private UserRepository userRepository;
		
		private final String  USER_PREFIX= "USER#";
		private final String  LOGIN_PREFIX= "LOGIN#";
		private final String  USER_INFO_TYPE="User";
		
		public void createUser(User user) throws InvalidDataException {
			try {
				if(user!=null) {
					 user.setUserId(userIDGenerator.generateUserId(user.getUserType()));
						if (user.getUserId() != null) {
							user.setSk(USER_PREFIX + user.getUserId());
							user.setPk(LOGIN_PREFIX + user.getDieticianId());
							SimpleDateFormat sd = new SimpleDateFormat("MM/dd/yyyy");
							user.setCreatedon(sd.format(new Date()));
							user.setModifiedon(sd.format(new Date()));

					userRepository.createUser(user);
				}
				else {
					throw new InvalidDataException("Could not generate user Id!");
				}
				
			}
			}catch (Exception e) {
				throw new InvalidDataException("Invalid User Data! "+e);
			}

		}

				
		public PaginatedScanList<User> getAllUsers()
		{
			return userRepository.getAllUsers();
		}
		
		
		public void updateUser(User user,String dieticianId, String userId) throws InvalidDataException {
			try {
				if (dieticianId != null && userId != null) {

					SimpleDateFormat sd = new SimpleDateFormat("MM/dd/yyyy");
				
					user.setModifiedon(sd.format(new Date()));

					userRepository.updateUser(user, builDynamoDBScanExpression(dieticianId, userId));
				}
				else {
					throw new InvalidDataException("Invalid User Data! ");
				}
				
			} catch (ConditionalCheckFailedException e) {
				LOGGER.error("Condition Failed "+e.getMessage());
				
			}
		}
		
		public DynamoDBScanExpression builDynamoDBScanExpression(String dieticianId, String userId) {

			DynamoDBScanExpression scanExpression = null;
			try {
				
				Map<String, AttributeValue> eav1 = new HashMap<String, AttributeValue>();
				eav1.put(":v1", new AttributeValue().withS(dieticianId));
				eav1.put(":v2", new AttributeValue().withS(userId));
				scanExpression = new DynamoDBScanExpression()
						.withFilterExpression("DieticianId = :v1 and UserId = :v2" )
						.withExpressionAttributeValues(eav1);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return scanExpression;
		}

				
		public String deleteUser(String dieticianId, String userId) throws InvalidDataException {
			
				if (dieticianId != null && userId != null) {

					userRepository.deleteUser(builDynamoDBScanExpression(dieticianId, userId));
					return "User "+userId +" deleted Successfully!";
				}
				else {
					return "User deletion unsuccessful";
				}
		
		}


}
