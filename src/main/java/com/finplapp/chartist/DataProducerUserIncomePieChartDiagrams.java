package com.finplapp.chartist;

import com.finplapp.model.LedgerEntryType;
import com.finplapp.model.User;
import com.finplapp.service.IncomeService;
import com.finplapp.service.IncomeTypeService;
import com.finplapp.service.PeriodOfTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service("dataProducerUserIncomePieChartDiagrams")
public class DataProducerUserIncomePieChartDiagrams extends DataProducerUserPieChartDiagrams {

    @Autowired
    IncomeTypeService incomeTypeService;

    @Autowired
    IncomeService incomeService;

    @Autowired
    PeriodOfTimeService periodOfTimeService;

    @Override
    protected List<? extends LedgerEntryType> getLedgerEntryTypeList() {
        return incomeTypeService.findAll();
    }

    @Override
    protected List getListByPeriodOfTimeAndType(User user, LocalDate date, LedgerEntryType type) {
        return incomeService.findByPeriodOfTimeAndIncomeType(periodOfTimeService.findByLocalDateAndUser(date, user), type);
    }
}
