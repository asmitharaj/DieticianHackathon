package com.ninja.dietician.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.ninja.dietician.entity.Morbidity;

@Service
public class MorbidityService {
	
	@Autowired
	DynamoDBMapper dynamoDBMapper;
	
	// get all morbidities
    public List<Morbidity> getAllMorbidities() {
    	List<Morbidity> morbidities =  dynamoDBMapper.scan(Morbidity.class, new DynamoDBScanExpression());
    	return morbidities;
    }

}
