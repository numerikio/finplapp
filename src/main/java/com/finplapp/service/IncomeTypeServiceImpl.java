package com.finplapp.service;

import com.finplapp.model.IncomeType;
import com.finplapp.repository.IncomeTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("incomeTypeService")
public class IncomeTypeServiceImpl implements IncomeTypeService {

    @Autowired
    private IncomeTypeRepository incomeTypeRepository;

    @Override
    public void saveType(IncomeType incomeType) {
        incomeTypeRepository.save(incomeType);
    }

    @Override
    public IncomeType findByType(String type) {
        return incomeTypeRepository.findByType(type);
    }

    @Override
    public List<IncomeType> findAll() {
        return incomeTypeRepository.findAll();
    }

    @Override
    public void deleteType(IncomeType incomeType) {
       incomeTypeRepository.delete(incomeType);
    }
}
