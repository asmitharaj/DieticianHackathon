package com.ninja.dietician.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.ninja.dietician.entity.User;
import com.ninja.dietician.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userServiceImpl;

	// create a user
	@PostMapping(path = "", consumes = "application/json")
	public String createUser(@RequestBody User user) {
		try {
			userServiceImpl.createUser(user);
			return "User created Successfully.";
		} catch (Exception e) {

			e.printStackTrace();
			return "User creation failed!";
		}

	}

	// update a user
	@PutMapping(path = "/{dieticianId}/{userId}", consumes = "application/json")
	public String updateUser(@RequestBody User user, @PathVariable Map<String, String> pathVarsMap) {
		try {
			
			String dieticianId = pathVarsMap.get("dieticianId");
			String userId = pathVarsMap.get("userId");
			if(user == null || dieticianId==null || userId==null) return "Please provide user info to update.";
			if (dieticianId != null && userId != null) {
				userServiceImpl.updateUser(user, dieticianId, userId);
				return "User updated Successfully.";
			} else
				return "Invalid Dietician Id or User Id !";
		} catch (Exception e) {
			e.printStackTrace();
			return "User NOT updated!";
		}
	}

	@GetMapping(path = "")
	public PaginatedScanList<User> getAllUsers() throws Exception {
		try {
			return userServiceImpl.getAllUsers();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// create a user
	@DeleteMapping(path = "/{dieticianId}/{userId}")
	public String deleteUser(@PathVariable String dieticianId, @PathVariable String userId) {
		try {

			if (dieticianId != null && userId != null) {

				userServiceImpl.deleteUser(dieticianId, userId);
				return "User deleted Successfully.";
			} else
				return "Invalid Dietician Id or User Id !";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@GetMapping("/getUserbyFirstName/{FirstName}")
	public ResponseEntity<Object> getUserbyFirstName(@PathVariable("FirstName") String FirstName) {

		return ResponseEntity.ok(userServiceImpl.getUserbyFirstName(FirstName));
	}

	@GetMapping("/getUserbyEmail/{Email}")
	public ResponseEntity<Object> getUserbyEmail(@PathVariable("Email") String Email) {

		return ResponseEntity.ok(userServiceImpl.getUserbyEmail(Email));
	}

	@GetMapping("/getUserbyContact/{Contact}")
	public ResponseEntity<Object> getUserbyContact(@PathVariable("Contact") String Contact) {
		return ResponseEntity.ok(userServiceImpl.getUserbyContact(Contact));
		// return ResponseEntity.ok(service.getMorbiditybyMorbidityTestId(
		// morbidityTestId));
	}

}
