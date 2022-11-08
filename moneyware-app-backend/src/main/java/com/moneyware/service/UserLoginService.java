package com.moneyware.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moneyware.model.UserDetails;
import com.moneyware.repository.IUserLoginRepository;

@Service
public class UserLoginService {

	@Autowired
	IUserLoginRepository userLoginRepository;
	
	public UserDetails createUser(UserDetails user) {
	    return userLoginRepository.save(user);
	}

	public Optional<UserDetails> getUser(long userId) {
	    return userLoginRepository.findById(userId);
	}

}
