package com.finplapp.service;

import com.finplapp.model.*;

import java.util.List;

public interface CostService {

    void saveCost(Cost cost);

    Cost findCostById(Long id);

    void updateCost(Cost cost);

    void deleteCost(Long id);

    List<Cost> findByCostType(CostType costType);

    List<Cost> findByPeriodOfTimeAndCostType (PeriodOfTime periodOfTime, LedgerEntryType ledgerEntryType);
}
