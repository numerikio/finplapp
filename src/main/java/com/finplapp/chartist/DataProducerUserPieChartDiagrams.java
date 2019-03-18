package com.finplapp.chartist;

import com.finplapp.UserHandler;
import com.finplapp.model.Ledger;
import com.finplapp.model.LedgerEntryType;
import com.finplapp.model.User;
import com.finplapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

abstract public class DataProducerUserPieChartDiagrams implements DataProducer {

    @Autowired
    private UserService userService;

    @Autowired
    private UserHandler userHandler;

    public DataOfDiagrams getData(List<LocalDate> localDates) {
        Map<? extends LedgerEntryType, List<? extends Ledger>> map = getMapOfLedgerListsByTypes(localDates);
        List<String> labels = new ArrayList();
        List<Double> series = new ArrayList();
        List<LedgerEntryType> types = map.keySet().stream().collect(Collectors.toList());
        Collections.sort(types, (o1, o2) -> o1.getId().compareTo(o2.getId()));
        for (LedgerEntryType type : types
        ) {
            if (!map.get(type).isEmpty()) {
                labels.add(type.getType());
                series.add(getSumAmount(map.get(type)));
            }
        }
        return new DatasOfPieChartDiagrams(labels, series);
    }

    protected Double getSumAmount(List<? extends Ledger> ledgers) {

        Double sum = 0.0;

        for (Ledger ledger : ledgers
        ) {
            sum += ledger.getAmount();
        }
        return sum;
    }

    protected Map<? extends LedgerEntryType, List<? extends Ledger>> getMapOfLedgerListsByTypes(List<LocalDate> localDates) {
        User user = userService.findBySSO(userHandler.getPrincipal());

        Map<LedgerEntryType, List<? extends Ledger>> stringListMap = new HashMap<>();

        List<? extends LedgerEntryType> ledgerEntryTypeList = getLedgerEntryTypeList();

        for (LocalDate date : localDates
        ) {
            for (LedgerEntryType type : ledgerEntryTypeList
            ) {
                if (stringListMap.containsKey(type)) {
                    stringListMap.get(type).addAll(getListByPeriodOfTimeAndType(user, date, type));
                } else {
                    stringListMap.put(type, getListByPeriodOfTimeAndType(user, date, type));
                }
            }
        }
        return stringListMap;
    }

    abstract protected List<? extends LedgerEntryType> getLedgerEntryTypeList();

    abstract protected List getListByPeriodOfTimeAndType(User user, LocalDate date, LedgerEntryType type);
}
