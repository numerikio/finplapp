package com.finplapp.service;

import java.util.List;

import com.finplapp.model.UserProfile;


public interface UserProfileService {

	UserProfile findById(int id);

	UserProfile findByType(String type);
	
	List<UserProfile> findAll();

	void saveUserProfile(UserProfile userProfile);
	
}
