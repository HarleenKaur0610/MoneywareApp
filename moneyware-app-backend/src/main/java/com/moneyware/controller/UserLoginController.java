package com.moneyware.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moneyware.service.UserLoginService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1.0/login")
public class UserLoginController {
	
	@Autowired
	UserLoginService userLoginService;

}
