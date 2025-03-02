package com.ms.user.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.user.models.UserModel;
import com.ms.user.producers.UserProducer;
import com.ms.user.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private UserProducer producer;
	
	@Transactional
	public UserModel save(UserModel userModel) {
		userModel = repository.save(userModel);
		producer.publishMessageEmail(userModel);
		return userModel;
	}

}
