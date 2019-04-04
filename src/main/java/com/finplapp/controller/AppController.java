package com.finplapp.controller;

import java.util.*;
import com.finplapp.DateMeasure;
import com.finplapp.LocalDateHandler;
import com.finplapp.PeriodOfTimeHandler;
import com.finplapp.UserHandler;
import com.finplapp.chartist.*;
import com.finplapp.model.*;
import com.finplapp.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/")
public class AppController {

    static final Logger logger = LoggerFactory.getLogger(AppController.class);

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @Autowired
    private PeriodOfTimeService periodOfTimeService;

    @Autowired
    @Qualifier("expenditureService")
    private ExpenditureService expenditureService;

    @Autowired
    @Qualifier("incomeService")
    private IncomeService incomeService;

    @Autowired
    private ExpenditureTypeService expenditureTypeService;

    @Autowired
    private IncomeTypeService incomeTypeService;

    @Autowired
    private DataConverter dataConverter;

    @Autowired
    private DataProducer dataProducerUserExpenditurePieChartDiagrams;

    @Autowired
    private DataProducer dataProducerUserIncomePieChartDiagrams;

    @Autowired
    private DataProducer dataProducerLineDiagram;

    @Autowired
    private LocalDateHandler localDateHandler;

    @Autowired
    private PeriodOfTimeHandler periodOfTimeHandler;

    @Autowired
    private UserHandler userHandler;

    private final String DEFAULT_DATE_MEASER_PAST = "WEEK";
    private final String DEFAULT_DATE_MEASER_FUTURE = "MONTH";
    private final long DEFAULT_DATE_QUANTITY_PAST = 1L;
    private final long DEFAULT_DATE_QUANTITY_FUTURE = 9L;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String mainPage(ModelMap model) {

        String jString = dataConverter.getConvertedData(dataProducerLineDiagram.getData(localDateHandler.getTargetLocalDates(
                DEFAULT_DATE_MEASER_PAST, DEFAULT_DATE_MEASER_FUTURE, DEFAULT_DATE_QUANTITY_PAST, DEFAULT_DATE_QUANTITY_FUTURE)));

        model.addAttribute("dateNow", localDateHandler.getLabelNowForGraf());
        model.addAttribute("dateMeasure", DateMeasure.values());
        model.addAttribute("loggedinuser", userHandler.getPrincipal());
        model.addAttribute("data", jString);
        return "mainPage";
    }

    @RequestMapping(value = "/mainRedact", method = RequestMethod.GET)
    public String mainPage(@RequestParam("measureTypeP") String measureTypeP,
                           @RequestParam("beforeNow") Long beforeNow,
                           @RequestParam("afterNow") Long afterNow,
                           @RequestParam("measureTypeF") String measureTypeF,
                           ModelMap model) {

        if (measureTypeP == null || beforeNow == null) {
            measureTypeP = DEFAULT_DATE_MEASER_PAST;
            beforeNow = DEFAULT_DATE_QUANTITY_PAST;
        }
        if (measureTypeF == null || afterNow == null) {
            measureTypeF = DEFAULT_DATE_MEASER_FUTURE;
            afterNow = DEFAULT_DATE_QUANTITY_FUTURE;
        }

        String jString = new JsonDataConverter()
                .getConvertedData(dataProducerLineDiagram.getData(localDateHandler.getTargetLocalDates(
                        measureTypeP, measureTypeF, beforeNow, afterNow)));


        model.addAttribute("beforeNow", beforeNow);
        model.addAttribute("afterNow", afterNow);
        model.addAttribute("measureTypeP", DateMeasure.valueOf(measureTypeP));
        model.addAttribute("measureTypeF", DateMeasure.valueOf(measureTypeF));
        model.addAttribute("dateNow", localDateHandler.getLabelNowForGraf());
        model.addAttribute("dateMeasure", DateMeasure.values());
        model.addAttribute("loggedinuser", userHandler.getPrincipal());
        model.addAttribute("data", jString);
        return "mainPage";
    }

    @RequestMapping(value = "/userslist", method = RequestMethod.GET)
    public String listUsers(ModelMap model) {

        List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("loggedinuser", userHandler.getPrincipal());
        return "userslist";
    }

    //======================Events================================

    @RequestMapping("getEvents")
    public String getEvents() {
        periodOfTimeHandler.updateDatasOfPeriod(periodOfTimeHandler.getSortedPeriodOfTimeList());
        return "getEvents";
    }

    @RequestMapping("getAllEventOfDate")
    public String getAllEventOfDate(@RequestParam("dates") String dates, ModelMap model) {
        List<PeriodOfTime> periodOfTimes = periodOfTimeHandler.getActualPeriodsOfTime(localDateHandler.getAllDatesOfPeriod(dates));
        model.addAttribute("periodOfTimes", periodOfTimes);
        model.addAttribute("dates", dates);
        return "eventsList";

    }
    //====================End Events=============================

    //======================Expenditure==========================

    @RequestMapping(value = {"Expenditure"}, method = RequestMethod.GET)
    public String pageExpenditure(ModelMap model) {
        model.addAttribute("expenditureTypes", expenditureTypeService.findAll());
        return "expenditurePage";
    }

    @RequestMapping(value = "saveExpenditure")
    public String saveExpenditure(@RequestParam("date") String date,
                                  @RequestParam("expenditureTypes") String costTypes,
                                  @RequestParam("amount") String amount,
                                  @RequestParam("message") String message) {
        Expenditure expenditure = new Expenditure();
        expenditure.setAmount(Double.valueOf(amount));
        expenditure.setMessage(message);
        expenditure.setExpenditureType(expenditureTypeService.findByType(costTypes));

        User user = userService.findBySSO(userHandler.getPrincipal());
        PeriodOfTime periodOfTime = periodOfTimeHandler.getNewOrOldPeriodOfTime(localDateHandler.getLocalDateWithString(date), user);
        expenditure.setPeriodOfTime(periodOfTime);
        List<Expenditure> costList = periodOfTime.getExpenditureList();
        costList.add(expenditure);
        periodOfTime.setExpenditureList(costList);
        periodOfTimeService.savePeriodOfTime(periodOfTime);
        return "redirect:/Expenditure";
    }

    @RequestMapping(value = "delete-expenditure")
    public String deleteExpenditure(@RequestParam("dates") String dates,
                                    @RequestParam("date") String date,
                                    @RequestParam("expenditure.id") Long id,
                                    ModelMap model) {
        PeriodOfTime periodOfTime = periodOfTimeService.findByLocalDateAndUser(localDateHandler.getLocalDateWithString(date), userService.findBySSO(userHandler.getPrincipal()));
        periodOfTime.getExpenditureList().remove(expenditureService.findExpenditureById(id));
        expenditureService.deleteExpenditure(id);
        periodOfTimeHandler.deleteAllEmptiesPeriodsOfUser(userService.findBySSO(userHandler.getPrincipal()));
        model.addAttribute("dates", dates);
        return "redirect:/getAllEventOfDate";
    }

    //===================End Expenditure===========================

    //======================Income=================================

    @RequestMapping(value = {"Income"}, method = RequestMethod.GET)
    public String pageIncome(ModelMap model) {
        model.addAttribute("incomeTypes", incomeTypeService.findAll());
        return "incomePage";
    }

    @RequestMapping(value = "saveIncome")
    public String saveIncome(@RequestParam("date") String date,
                             @RequestParam("incomeTypes") String incomeTypes,
                             @RequestParam("amount") String amount,
                             @RequestParam("message") String message) {

        Income income = new Income();
        income.setAmount(Double.valueOf(amount));
        income.setMessage(message);
        income.setTypeIncome(incomeTypeService.findByType(incomeTypes));

        User user = userService.findBySSO(userHandler.getPrincipal());
        PeriodOfTime periodOfTime = periodOfTimeHandler.getNewOrOldPeriodOfTime(localDateHandler.getLocalDateWithString(date), user);
        income.setPeriodOfTime(periodOfTime);
        List<Income> incomeList = periodOfTime.getIncomeList();
        incomeList.add(income);
        periodOfTime.setIncomeList(incomeList);
        periodOfTimeService.savePeriodOfTime(periodOfTime);
        return "redirect:/Income";
    }

    @RequestMapping(value = "delete-income")
    public String deleteIncome(@RequestParam("dates") String dates,
                               @RequestParam("date") String date,
                               @RequestParam("income.id") Long id,
                               ModelMap model) {
        PeriodOfTime periodOfTime = periodOfTimeService.findByLocalDateAndUser(localDateHandler.getLocalDateWithString(date), userService.findBySSO(userHandler.getPrincipal()));
        periodOfTime.getIncomeList().remove(incomeService.findIncomeById(id));
        incomeService.deleteIncome(id);
        periodOfTimeHandler.deleteAllEmptiesPeriodsOfUser(userService.findBySSO(userHandler.getPrincipal()));
        model.addAttribute("dates", dates);
        return "redirect:/getAllEventOfDate";
    }

    //===================End Income=================================

    @RequestMapping(value = "userStatistics")
    public String userStatistics(ModelMap model) {
        return "userStatistics";
    }

    @RequestMapping(value = "getUserStatistics")
    public String userPrintStatistics(@RequestParam("dates") String dates,
                                      ModelMap model) {

        String jString = dataConverter.getConvertedData(dataProducerUserExpenditurePieChartDiagrams.getData(localDateHandler.getAllDatesOfPeriod(dates)));
        String jString2 = dataConverter.getConvertedData(dataProducerUserIncomePieChartDiagrams.getData(localDateHandler.getAllDatesOfPeriod(dates)));

        model.addAttribute("dates", dates);
        model.addAttribute("data", jString);
        model.addAttribute("data2", jString2);
        return "userStatistics";
    }

}