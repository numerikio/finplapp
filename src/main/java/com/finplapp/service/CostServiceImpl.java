package com.finplapp.service;

import com.finplapp.model.Cost;
import com.finplapp.repository.CostRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CostServiceImpl implements CostService {

    @Autowired
    CostRepository costRepository;

    @Override
    public void saveCost(Cost cost) {
        costRepository.save(cost);
    }
}
