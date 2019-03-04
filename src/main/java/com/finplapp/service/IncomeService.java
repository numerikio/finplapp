package com.finplapp.service;

import com.finplapp.model.Income;
import com.finplapp.model.IncomeType;
import com.finplapp.model.LedgerEntryType;
import com.finplapp.model.PeriodOfTime;

import java.util.List;

public interface IncomeService {

    void saveIncome(Income income);

    Income findIncomeById(Long id);

    void updateIncome(Income income);

    void deleteIncome(Long id);

    List<Income> findByCostType(IncomeType incomeType);

    List<Income> findByPeriodOfTimeAndIncomeType (PeriodOfTime periodOfTime, LedgerEntryType ledgerEntryType);
}
