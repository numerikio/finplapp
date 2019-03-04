package com.finplapp.service;

import com.finplapp.model.*;
import com.finplapp.repository.CostRepository;
import com.finplapp.repository.LedgerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("costService")
public class CostServiceImpl implements CostService {

    @Autowired
    private LedgerRepository<Cost, Long> ledgerRepository;

    @Autowired
    private CostRepository costRepository;

    @Override
    public void saveCost(Cost cost) {
        ledgerRepository.save(cost);
    }

    @Override
    public Cost findCostById(Long id) {
        return ledgerRepository.getOne(id);
    }

    @Override
    public void updateCost(Cost cost) {
        Cost entity = ledgerRepository.findOne(cost.getId());
        if (entity != null) {
            entity.setCostType(cost.getCostType());
            entity.setAmount(cost.getAmount());
            entity.setMessage(cost.getMessage());
        }
    }

    @Override
    public void deleteCost(Long id) {
        ledgerRepository.delete(id);
    }

    @Override
    public List<Cost> findByCostType(CostType costType) {

        return costRepository.findByCostType(costType);
    }

    @Override
    public List<Cost> findByPeriodOfTimeAndCostType(PeriodOfTime periodOfTime, LedgerEntryType ledgerEntryType) {
        return costRepository.findByPeriodOfTimeAndCostType(periodOfTime, ledgerEntryType);
    }

   /* @Override
    public List<Cost> findByPeriodOfTimeAndCostType(PeriodOfTime periodOfTime, CostType costType) {
        return costRepository.findByPeriodOfTimeAndCostType(periodOfTime, costType);
    }*/

}

