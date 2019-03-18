package com.finplapp;

import com.finplapp.model.Ledger;
import com.finplapp.model.PeriodOfTime;
import com.finplapp.model.User;
import com.finplapp.service.PeriodOfTimeService;
import com.finplapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service("periodOfTimeHandler")
public class PeriodOfTimeHandler {

    @Autowired
    private PeriodOfTimeService periodOfTimeService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserHandler userHandler;

    public PeriodOfTime getNewOrOldPeriodOfTime(LocalDate localDate, User user) {
        PeriodOfTime findPeriodOfTime = periodOfTimeService.findByLocalDateAndUser(localDate, user);
        if (findPeriodOfTime == null) {
            PeriodOfTime newPeriodOfTime = new PeriodOfTime();
            newPeriodOfTime.setUser(user);
            newPeriodOfTime.setLocalDate(localDate);
            return newPeriodOfTime;
        } else {
            return findPeriodOfTime;
        }
    }

    public void updateDatasOfPeriod(List<PeriodOfTime> periodOfTimes) {
        for (int i = 0; i < periodOfTimes.size(); i++) {
            Double calculationOfPeriod = getSumEventsOfPeridofTime(periodOfTimes.get(i).getIncomeList()) - getSumEventsOfPeridofTime(periodOfTimes.get(i).getExpenditureList());
            if (i != 0) {
                periodOfTimes.get(i).setBalance(periodOfTimes.get(i - 1).getBalance() + calculationOfPeriod);
            } else {
                periodOfTimes.get(i).setBalance(calculationOfPeriod);
            }
            periodOfTimeService.updatePeriodOfTime(periodOfTimes.get(i));
        }
    }

    public List<PeriodOfTime> getSortedPeriodOfTimeList() {
        List<PeriodOfTime> periodOfTimes = periodOfTimeService.findByUser(userService.findBySSO(userHandler.getPrincipal()));
        periodOfTimes.sort((o1, o2) -> o1.compareTo(o2));
        return periodOfTimes;
    }

    private Double getSumEventsOfPeridofTime(List<? extends Ledger> ledgersList) {
        Double sum = 0.0;
        for (Ledger ledger : ledgersList
        ) {
            sum += ledger.getAmount();
        }
        return sum;
    }

    public List<Double> getBalanceListAllPeriods(List<PeriodOfTime> periodOfTimes) {
        List<Double> balanseList = new ArrayList<>();
        for (PeriodOfTime periodOfTime : periodOfTimes
        ) {
            balanseList.add(periodOfTime.getBalance());
        }
        return balanseList;
    }

    public List<String> getTimeStepAllPeriodForGraf(List<PeriodOfTime> periodOfTimes) {
        List<String> timeSteps = new ArrayList<>();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy");
        for (PeriodOfTime periodOfTime : periodOfTimes
        ) {
            timeSteps.add(periodOfTime.getLocalDate().format(dateTimeFormatter));
        }
        return timeSteps;
    }

    public List<PeriodOfTime> getActualPeriodsOfTime(List<LocalDate> localDates) {
        List<PeriodOfTime> periodOfTimes = new ArrayList<>();
        for (LocalDate localDate : localDates
        ) {
            PeriodOfTime periodOfTime = periodOfTimeService.findByLocalDateAndUser(localDate, userService.findBySSO(userHandler.getPrincipal()));
            if (periodOfTime != null) {
                periodOfTimes.add(periodOfTime);
            }
        }
        return periodOfTimes;
    }

    public void deleteAllEmptiesPeriodsOfUser(User user) {
        List<PeriodOfTime> periodOfTimes = periodOfTimeService.findEmptiesPeriods(user);
        for (PeriodOfTime period : periodOfTimes
        ) {
            periodOfTimeService.deletePeriodOfTime(period);
        }
    }

    public List<PeriodOfTime> getLimitedListPeriodOfTime(List<PeriodOfTime> periodOfTimes, List<LocalDate> localDates) {
        List<PeriodOfTime> periods = new ArrayList<>();
        for (PeriodOfTime period : periodOfTimes
        ) {
            if (localDates.contains(period.getLocalDate())) {
                periods.add(period);
            }
        }
        return periods;
    }
}
