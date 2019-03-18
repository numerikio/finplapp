package com.finplapp;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service("localDateHandler")
public class LocalDateHandler {

    public String getLabelNowForGraf() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy");
        return LocalDate.now().format(dateTimeFormatter);
    }


    public LocalDate getLocalDateWithString(String date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("[MM/dd/yyyy][yyyy-MM-dd]");
        LocalDate localDate = LocalDate.parse(date, dateTimeFormatter);
        return localDate;
    }

    private List<String> getFirstAndLastDaysWihtString(String datesSring) {
        return Stream.of(datesSring.split(" - "))
                .map(elem -> new String(elem))
                .collect(Collectors.toList());
    }

    public List<LocalDate> getAllDatesOfPeriod(String datesSring) {
        ArrayList<LocalDate> localDates = new ArrayList<>();
        List<String> dates = getFirstAndLastDaysWihtString(datesSring);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate theFirstDay = LocalDate.parse(dates.get(0), dateTimeFormatter);
        LocalDate thelastDay = LocalDate.parse(dates.get(1), dateTimeFormatter);

        long days = ChronoUnit.DAYS.between(theFirstDay, thelastDay);
        long oneDeyForCorrectWork = 1L;
        for (long i = 0; i < days + oneDeyForCorrectWork; i++) {
            localDates.add(theFirstDay.plusDays(i));
        }

        return localDates;
    }


    public LocalDate getLocalDateByDateMeasure(DateMeasure dateMeasure, long quantity) {
        LocalDate localDate = LocalDate.now();
        switch (dateMeasure) {
            case DAY:
                return localDate.plusDays(quantity);
            case WEEK:
                return localDate.plusWeeks(quantity);
            case MONTH:
                return localDate.plusMonths(quantity);
            case YEAR:
                return localDate.plusYears(quantity);
            default:
                return null;
        }
    }

    public List<LocalDate> getAllDatesOfPeriod(LocalDate dayBefore, LocalDate dayAfter) {
        ArrayList<LocalDate> localDates = new ArrayList<>();
        LocalDate theDayNow = LocalDate.now();
        long day1 = ChronoUnit.DAYS.between(theDayNow, dayBefore);
        long day2 = ChronoUnit.DAYS.between(theDayNow, dayAfter);
        long oneDeyForCorrectWork = 1L;
        for (long i = day1; i < day2 + oneDeyForCorrectWork; i++) {
            localDates.add(theDayNow.plusDays(i));
        }
        return localDates;
    }

    public List<LocalDate> getTargetLocalDates(String dateMeaserePast, String dateMeasereFuture,
                                               long dateQuantityPast, long dateQuantityFuture) {
        List<LocalDate> targetLocalDates = getAllDatesOfPeriod(
                getLocalDateByDateMeasure(DateMeasure.valueOf(dateMeaserePast), -(dateQuantityPast)),
                getLocalDateByDateMeasure(DateMeasure.valueOf(dateMeasereFuture), dateQuantityFuture));
        return targetLocalDates;
    }
}
