package com.finplapp.service;

import com.finplapp.model.Income;
import com.finplapp.model.IncomeType;
import com.finplapp.model.LedgerEntryType;
import com.finplapp.model.PeriodOfTime;
import com.finplapp.repository.IncomeRepository;
import com.finplapp.repository.LedgerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("incomeService")
public class IncomeServiceImpl implements IncomeService {

    @Autowired
    private LedgerRepository<Income, Long> ledgerRepository;

    @Autowired
    private IncomeRepository incomeRepository;

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

    @Override
    public List<Income> findByCostType(IncomeType incomeType) {
        return incomeRepository.findByIncomeType(incomeType);
    }

    @Override
    public List<Income> findByPeriodOfTimeAndIncomeType(PeriodOfTime periodOfTime, LedgerEntryType ledgerEntryType) {
        return incomeRepository.findByPeriodOfTimeAndIncomeType(periodOfTime, ledgerEntryType);
    }
}
