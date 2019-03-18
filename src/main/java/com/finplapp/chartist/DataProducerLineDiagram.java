package com.finplapp.chartist;

import com.finplapp.LocalDateHandler;
import com.finplapp.PeriodOfTimeHandler;
import com.finplapp.model.PeriodOfTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service("dataProducerLineDiagram")
public class DataProducerLineDiagram implements DataProducer {

    @Autowired
    private LocalDateHandler localDateHandler;

    @Autowired
    private PeriodOfTimeHandler periodOfTimeHandler;

    @Override
    public DataOfDiagrams getData(List<LocalDate> list) {
        List<PeriodOfTime> periodOfTimes = periodOfTimeHandler.getSortedPeriodOfTimeList();
        periodOfTimeHandler.updateDatasOfPeriod(periodOfTimes);
        periodOfTimes =periodOfTimeHandler.getLimitedListPeriodOfTime(periodOfTimes,
                list);

        List<List> lists = new ArrayList<>();
        lists.add(periodOfTimeHandler.getBalanceListAllPeriods(periodOfTimes));
        return new DatasOfLineDiagram(periodOfTimeHandler.getTimeStepAllPeriodForGraf(periodOfTimes), lists);
    }
}

