package com.finplapp.repository;


import com.finplapp.model.Cost;
import com.finplapp.model.CostType;
import com.finplapp.model.LedgerEntryType;
import com.finplapp.model.PeriodOfTime;

import java.util.List;

public interface CostRepository extends LedgerRepository<Cost, Long> {

    List<Cost> findByCostType(CostType costType);

    List<Cost> findByPeriodOfTimeAndCostType(PeriodOfTime periodOfTime, LedgerEntryType ledgerEntryType);
}
