package com.finplapp.service;

import com.finplapp.model.Cost;
import com.finplapp.repository.CostRepository;
import com.finplapp.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("costService")
public class CostServiceImpl implements CostService {

    /*@Autowired
    CostRepository costRepository;*/

    @Autowired
    EventRepository<Cost,Long> eventRepository;

    @Override
    public void saveCost(Cost cost) {
        eventRepository.save(cost);
    }

    @Override
    public Cost findCostById(Long id) {
        return eventRepository.getOne(id);
    }

    @Override
    public void updateCost(Cost cost) {
        Cost entity = eventRepository.findOne(cost.getId());
        if (entity != null) {
            entity.setTypeCost(cost.getTypeCost());
            entity.setAmount(cost.getAmount());
            entity.setMessage(cost.getMessage());
        }
    }

    @Override
    public void deleteCost(Long id) {
        eventRepository.delete(id);
    }
}

