package com.finplapp.repository;

import com.finplapp.model.Ledger;
import com.finplapp.model.LedgerEntryType;
import com.finplapp.model.PeriodOfTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

@NoRepositoryBean
public interface LedgerRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {
}
