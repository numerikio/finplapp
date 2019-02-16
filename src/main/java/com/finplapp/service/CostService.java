package com.finplapp.service;

import com.finplapp.model.Cost;

public interface CostService {

    void saveCost(Cost cost);

    Cost findCostById(Long id);

    void updateCost(Cost cost);

    void deleteCost(Long id);
}
