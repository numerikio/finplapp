package com.finplapp.service;

import com.finplapp.model.CostType;
import com.finplapp.repository.CostTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("costTypeService")
public class CostTypeServiceImpl implements CostTypeService {

    @Autowired
    private CostTypeRepository costTypeRepository;

    @Override
    public void saveType(CostType costType) {
        costTypeRepository.save(costType);
    }

    @Override
    public CostType findByType(String type) {
        return costTypeRepository.findByType(type);
    }

    @Override
    public List<CostType> findAll() {
        return costTypeRepository.findAll();
    }

    @Override
    public void deleteType(CostType costType) {
        costTypeRepository.delete(costType);
    }
}
