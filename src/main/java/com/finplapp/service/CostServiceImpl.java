package com.finplapp.service;

import com.finplapp.model.Cost;
import com.finplapp.repository.LedgerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("costService")
public class CostServiceImpl implements CostService {

    @Autowired
    LedgerRepository<Cost,Long> ledgerRepository;

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
}

