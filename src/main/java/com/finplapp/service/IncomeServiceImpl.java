package com.finplapp.service;

import com.finplapp.model.Income;
import com.finplapp.repository.LedgerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("incomeService")
public class IncomeServiceImpl implements IncomeService {

    @Autowired
    LedgerRepository<Income,Long> ledgerRepository;

    @Override
    public void saveIncome(Income income) {
        ledgerRepository.save(income);
    }

    @Override
    public Income findIncomeById(Long id) {
        return ledgerRepository.getOne(id);
    }

    @Override
    public void updateIncome(Income income) {
        Income entity = ledgerRepository.findOne(income.getId());
        if (entity != null) {
            entity.setTypeIncome(income.getTypeIncome());
            entity.setAmount(income.getAmount());
            entity.setMessage(income.getMessage());
        }
    }

    @Override
    public void deleteIncome(Long id) {
        ledgerRepository.delete(id);
    }
}
