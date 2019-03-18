package com.finplapp.repository;

import com.finplapp.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserProfileRepository extends JpaRepository<UserProfile, Integer> {

    UserProfile findByType(String type);
}
