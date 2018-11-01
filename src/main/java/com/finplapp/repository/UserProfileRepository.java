package com.finplapp.repository;

import com.finplapp.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserProfileRepository extends JpaRepository<UserProfile, Integer>{
    @Query(nativeQuery = true, value = "SELECT * FROM USER_PROFILE WHERE TYPE = ?1")
    UserProfile findByType(String type);
}
