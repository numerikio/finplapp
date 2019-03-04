package com.finplapp.service;

import com.finplapp.model.PeriodOfTime;
import com.finplapp.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface PeriodOfTimeService {

    void savePeriodOfTime(PeriodOfTime periodOfTime);

    void updatePeriodOfTime(PeriodOfTime periodOfTime);

    PeriodOfTime findByLocalDateAndUser(LocalDate localDate, User user);

    List<PeriodOfTime> findByUser(User user);

    List<PeriodOfTime> findEmptiesPeriods(User user);

    void deletePeriodOfTime(PeriodOfTime periodOfTime);

    PeriodOfTime findById(Long id);

    List<PeriodOfTime> findByLocalDate(LocalDate localDate);
}
