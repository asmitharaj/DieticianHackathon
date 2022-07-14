package com.ninja.dietician.entity;

import java.io.Serializable;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@DynamoDBDocument
public class Address implements Serializable{
	
	private static final Long serialVersionUID = 1L;
	
	@DynamoDBAttribute
	private String address1;
	
	@DynamoDBAttribute
	private String address2;
	
	@DynamoDBAttribute
	private String city;
	
	@DynamoDBAttribute
	private String state;
	
	@DynamoDBAttribute
	private String country;
	
	@DynamoDBAttribute
	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	
	@DynamoDBAttribute
	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	@DynamoDBAttribute
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	@DynamoDBAttribute
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@DynamoDBAttribute
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	
}
