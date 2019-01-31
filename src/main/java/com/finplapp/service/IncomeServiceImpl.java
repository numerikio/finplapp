package com.finplapp.service;

import com.finplapp.model.Income;
import com.finplapp.repository.EventRepository;
import com.finplapp.repository.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("incomeService")
public class IncomeServiceImpl implements IncomeService {

    @Autowired
    EventRepository<Income,Long> eventRepository;

    @Override
    public void saveIncome(Income income) {
        eventRepository.save(income);
    }

    @Override
    public Income findIncomeById(Long id) {
        return eventRepository.getOne(id);
    }

    @Override
    public void updateIncome(Income income) {
        Income entity = eventRepository.findOne(income.getId());
        if (entity != null) {
            entity.setTypeIncome(income.getTypeIncome());
            entity.setAmount(income.getAmount());
            entity.setMessage(income.getMessage());
        }
    }

    @Override
    public void deleteIncome(Long id) {
        eventRepository.delete(id);
    }
}
