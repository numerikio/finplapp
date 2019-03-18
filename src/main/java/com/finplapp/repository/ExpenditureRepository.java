package com.finplapp.repository;


import com.finplapp.model.Expenditure;
import com.finplapp.model.ExpenditureType;
import com.finplapp.model.LedgerEntryType;
import com.finplapp.model.PeriodOfTime;

import java.util.List;

public interface ExpenditureRepository extends LedgerRepository<Expenditure, Long> {

    List<Expenditure> findByExpenditureType(ExpenditureType expenditureType);

    List<Expenditure> findByPeriodOfTimeAndExpenditureType(PeriodOfTime periodOfTime, LedgerEntryType ledgerEntryType);
}
