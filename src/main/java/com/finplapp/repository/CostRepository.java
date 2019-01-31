package com.finplapp.repository;

import com.finplapp.model.Cost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CostRepository extends EventRepository<Cost,Long> {
}
