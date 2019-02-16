package com.finplapp.repository;

import com.finplapp.model.CostType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CostTypeRepository extends LedgerEntryTypeRepository<CostType, Integer> {

}
