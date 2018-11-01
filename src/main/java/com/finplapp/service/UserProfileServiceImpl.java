package com.finplapp.service;

import java.util.List;

import com.finplapp.model.UserProfile;
import com.finplapp.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service("userProfileService")
@Transactional
public class UserProfileServiceImpl implements UserProfileService{
	
	@Autowired
	private UserProfileRepository repository;
	
	public UserProfile findById(int id) {
		return repository.findOne(id);
	}

	public UserProfile findByType(String type){
		return repository.findByType(type);
	}

	public List<UserProfile> findAll() {
		return repository.findAll();
	}

	@Override
	public void saveUserProfile(UserProfile userProfile) {
		repository.save(userProfile);
	}
}
