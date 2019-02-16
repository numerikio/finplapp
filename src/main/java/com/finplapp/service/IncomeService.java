package com.finplapp.service;

import com.finplapp.model.Income;

public interface IncomeService {

    void saveIncome(Income income);

    Income findIncomeById(Long id);

    void updateIncome(Income income);

    void deleteIncome(Long id);
}
