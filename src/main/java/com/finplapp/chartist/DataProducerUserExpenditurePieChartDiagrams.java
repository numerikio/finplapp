package com.finplapp.chartist;

import com.finplapp.model.LedgerEntryType;
import com.finplapp.model.User;
import com.finplapp.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service("dataProducerUserExpenditurePieChartDiagrams")
public class DataProducerUserExpenditurePieChartDiagrams extends DataProducerUserPieChartDiagrams {

    static final Logger logger = LoggerFactory.getLogger(DataProducerUserExpenditurePieChartDiagrams.class);

    @Autowired
    private PeriodOfTimeService periodOfTimeService;

    @Autowired
    @Qualifier("expenditureService")
    private ExpenditureService expenditureService;

    @Autowired
    private ExpenditureTypeService expenditureTypeService;

    @Override
    protected List<? extends LedgerEntryType> getLedgerEntryTypeList() {
        return expenditureTypeService.findAll();
    }

    protected List getListByPeriodOfTimeAndType(User user, LocalDate date, LedgerEntryType type) {
        return expenditureService.findByPeriodOfTimeAndExpenditureType(periodOfTimeService.findByLocalDateAndUser(date, user), type);
    }

}
