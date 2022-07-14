package com.ninja.dietician.util;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class UserIDGenerator {
	
	Random random = new Random();
	
	public String generateUserId(String type) {
		
		int nextVal = random.nextInt(1000,999999);
		if(type.equalsIgnoreCase("PATIENT"))
			return "PT"+nextVal;
		else
			return "DT"+nextVal;
	}
	
}
