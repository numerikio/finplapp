package com.finplapp.repository;

import com.finplapp.model.PersistentLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TokenRepository extends JpaRepository<PersistentLogin, String> {

    List<PersistentLogin> findByUsername(String username);
}
