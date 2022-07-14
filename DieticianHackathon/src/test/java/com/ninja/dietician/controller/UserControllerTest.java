package com.ninja.dietician.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ninja.dietician.entity.User;
import com.ninja.dietician.service.UserService;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(User.class )
class UserControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;
	
	private List<User> userList;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	//private User morbidity1;
	
	@BeforeEach
	void setUp() throws Exception {
		String date = "07/13/2022";
//		User user1 = new User("MR#Hypothyroidism", "TEST#HYPO_T3", "Morbidity", "Hypothyroidism",
//				"HYPO_T3", "Triiodothyronine (T3)", "nanograms per decil", "between 60 and 180", date, date);
//		User user2 = new User("MR#Hypothyroidism", "TEST#THY_T4", "Morbidity", "Hypothyroidism",
//				"THY_T4", "Triiodothyronine (T3)", "nanograms per decil", "between 60 and 180", date, date);
//		userList = new ArrayList<User> ();
//		userList.add(user1);
//		userList.add(user2);
	}

}
