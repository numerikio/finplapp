package com.finplapp.repository;

import com.finplapp.model.PeriodOfTime;
import com.finplapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PeriodOfTimeRepository extends JpaRepository<PeriodOfTime,Long> {
    PeriodOfTime findByLocalDateAndUser (LocalDate localDate, User user);
    List<PeriodOfTime> findByUser (User user);
    List<PeriodOfTime> findByCostListIsNullAndIncomeListIsNullAndUser(User user);

}
