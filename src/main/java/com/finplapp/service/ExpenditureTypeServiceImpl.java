package com.finplapp.service;

import com.finplapp.model.ExpenditureType;
import com.finplapp.repository.ExpenditureTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("expenditureTypeService")
public class ExpenditureTypeServiceImpl implements ExpenditureTypeService {

    @Autowired
    private ExpenditureTypeRepository expenditureTypeRepository;

    @Override
    public void saveType(ExpenditureType expenditureType) {
        expenditureTypeRepository.save(expenditureType);
    }

    @Override
    public ExpenditureType findByType(String type) {
        return expenditureTypeRepository.findByType(type);
    }

    @Override
    public List<ExpenditureType> findAll() {
        return expenditureTypeRepository.findAll();
    }

    @Override
    public void deleteType(ExpenditureType expenditureType) {
        expenditureTypeRepository.delete(expenditureType);
    }
}
