package com.finplapp.service;

import com.finplapp.model.PeriodOfTime;
import com.finplapp.repository.PeriodOfTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("periodOfTimeService")
public class PeriodOfTimeServiceImpl implements PeriodOfTimeService {

    @Autowired
    PeriodOfTimeRepository periodOfTimeRepository;

    @Override
    public void savePeriodOfTime(PeriodOfTime periodOfTime) {
        periodOfTimeRepository.save(periodOfTime);
    }
}
