package com.ninja.dietician.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ninja.dietician.entity.Morbidity;
import com.ninja.dietician.service.MorbidityService;

@RestController
@RequestMapping("/Morbidity")
public class MorbidityController {

	@Autowired
	MorbidityService  morbidityService;
	
	//get all morbidities
    @GetMapping("")
    public ResponseEntity<List<Morbidity>> getAllMorbidties() {
        return ResponseEntity.ok(this.morbidityService.getAllMorbidities());  
    }
    
}
