package com.finplapp.service;

import com.finplapp.model.PeriodOfTime;
import com.finplapp.model.User;
import com.finplapp.repository.PeriodOfTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service("periodOfTimeService")
public class PeriodOfTimeServiceImpl implements PeriodOfTimeService {

    @Autowired
    PeriodOfTimeRepository periodOfTimeRepository;

    @Override
    public void savePeriodOfTime(PeriodOfTime periodOfTime) {
        periodOfTimeRepository.save(periodOfTime);
    }

    @Override
    public void updatePeriodOfTime(PeriodOfTime periodOfTime) {
PeriodOfTime entity = periodOfTimeRepository.findOne(periodOfTime.getId());
        if (entity != null) {
            entity.setBalance(periodOfTime.getBalance());
            entity.setLocalDate(periodOfTime.getLocalDate());
            entity.setUser(periodOfTime.getUser());
        }
    }

    @Override
    public PeriodOfTime findByLocalDateAndUser(LocalDate localDate, User user) {
        return periodOfTimeRepository.findByLocalDateAndUser(localDate,user);
    }

    @Override
    public List<PeriodOfTime> findByUser(User user) {
        return periodOfTimeRepository.findByUser(user);
    }

    @Override
    public List<PeriodOfTime> findEmptiesPeriods(User user) {
        return periodOfTimeRepository.findByCostListIsNullAndIncomeListIsNullAndUser(user);
    }

    @Override
    public void deletePeriodOfTime(PeriodOfTime periodOfTime) {
        periodOfTimeRepository.delete(periodOfTime);
    }

    @Override
    public PeriodOfTime findById(Long id) {
        return periodOfTimeRepository.findOne(id);
    }

}
