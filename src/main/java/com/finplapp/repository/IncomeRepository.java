package com.finplapp.repository;

import com.finplapp.model.Income;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncomeRepository extends EventRepository<Income,Long> {
}
