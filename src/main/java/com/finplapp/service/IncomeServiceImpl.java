package com.finplapp.service;

import com.finplapp.model.Income;
import com.finplapp.repository.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class IncomeServiceImpl implements IncomeService {

    @Autowired
    IncomeRepository incomeRepository;

    @Override
    public void saveIncome(Income income) {
        incomeRepository.save(income);
    }
}
