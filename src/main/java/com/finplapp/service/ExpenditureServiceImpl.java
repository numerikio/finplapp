package com.finplapp.service;

import com.finplapp.model.*;
import com.finplapp.repository.ExpenditureRepository;
import com.finplapp.repository.LedgerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("expenditureService")
public class ExpenditureServiceImpl implements ExpenditureService {

    @Autowired
    private LedgerRepository<Expenditure, Long> ledgerRepository;

    @Autowired
    private ExpenditureRepository expenditureRepository;

    @Override
    public void saveExpenditure(Expenditure expenditure) {
        ledgerRepository.save(expenditure);
    }

    @Override
    public Expenditure findExpenditureById(Long id) {
        return ledgerRepository.getOne(id);
    }

    @Override
    public void updateExpenditure(Expenditure expenditure) {
        Expenditure entity = ledgerRepository.findOne(expenditure.getId());
        if (entity != null) {
            entity.setExpenditureType(expenditure.getExpenditureType());
            entity.setAmount(expenditure.getAmount());
            entity.setMessage(expenditure.getMessage());
        }
    }

    @Override
    public void deleteExpenditure(Long id) {
        ledgerRepository.delete(id);
    }

    @Override
    public List<Expenditure> findByExpenditureType(ExpenditureType expenditureType) {
        return expenditureRepository.findByExpenditureType(expenditureType);
    }

    @Override
    public List<Expenditure> findByPeriodOfTimeAndExpenditureType(PeriodOfTime periodOfTime, LedgerEntryType ledgerEntryType) {
        return expenditureRepository.findByPeriodOfTimeAndExpenditureType(periodOfTime, ledgerEntryType);
    }

}

