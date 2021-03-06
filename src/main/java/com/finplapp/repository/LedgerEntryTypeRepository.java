package com.finplapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface LedgerEntryTypeRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {

    T findByType(String type);
}

