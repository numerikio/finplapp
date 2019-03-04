package com.finplapp.repository;

import com.finplapp.model.Income;
import com.finplapp.model.IncomeType;
import com.finplapp.model.LedgerEntryType;
import com.finplapp.model.PeriodOfTime;

import java.util.List;

public interface IncomeRepository extends LedgerRepository<Income, Long> {

    List<Income> findByIncomeType(IncomeType incomeType);

    List<Income> findByPeriodOfTimeAndIncomeType(PeriodOfTime periodOfTime, LedgerEntryType ledgerEntryType);
}
