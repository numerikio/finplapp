package com.finplapp.service;

import com.finplapp.model.*;

import java.util.List;

public interface ExpenditureService {

    void saveExpenditure(Expenditure expenditure);

    Expenditure findExpenditureById(Long id);

    void updateExpenditure(Expenditure expenditure);

    void deleteExpenditure(Long id);

    List<Expenditure> findByExpenditureType(ExpenditureType expenditureType);

    List<Expenditure> findByPeriodOfTimeAndExpenditureType(PeriodOfTime periodOfTime, LedgerEntryType ledgerEntryType);
}
