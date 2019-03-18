package com.finplapp.controller;

import com.finplapp.model.*;
import com.finplapp.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/")
public class AnalystController {

    static final Logger logger = LoggerFactory.getLogger(AnalystController.class);

    @Autowired
    private ExpenditureTypeService expenditureTypeService;

    @Autowired
    private IncomeTypeService incomeTypeService;

    @Autowired
    private PeriodOfTimeService periodOfTimeService;

    @Autowired
    private ExpenditureService expenditureService;

    @Autowired
    private IncomeService incomeService;


    @RequestMapping(value = "analyst", method = RequestMethod.GET)
    public String analystPage(ModelMap model) {

        model.addAttribute("loggedinuser", "analyst");
        return "analystPage";
    }

    @RequestMapping(value = "categories", method = RequestMethod.GET)
    public String categories(ModelMap model) {
        List<ExpenditureType> expenditureTypeList = expenditureTypeService.findAll();
        List<IncomeType> incomeTypeList = incomeTypeService.findAll();

        model.addAttribute("expenditureType", expenditureTypeList);
        model.addAttribute("incomesType", incomeTypeList);
        model.addAttribute("loggedinuser", "analyst");
        return "categoriesPage";
    }

    @RequestMapping(value = "useCategories", method = RequestMethod.GET)
    public String addCategories(@RequestParam("action") String action,
                                @RequestParam("name") String name, ModelMap model) {
        if (name != null) {
            switch (action) {
                case "addExpenditureType": {
                    if (expenditureTypeService.findByType(name) == null) {
                        expenditureTypeService.saveType(new ExpenditureType(name));
                    }
                    break;
                }
                case "addIncomeType": {
                    if (incomeTypeService.findByType(name) == null) {
                        incomeTypeService.saveType(new IncomeType(name));
                    }
                    break;
                }
                case "deleteExpenditureType": {
                    if (expenditureTypeService.findByType(name) != null) {
                        deleteLedgerEntryType(expenditureService.findByExpenditureType(expenditureTypeService.findByType(name)));
                        expenditureTypeService.deleteType(expenditureTypeService.findByType(name));
                    }
                    break;
                }
                case "deleteIncomeType": {
                    if (incomeTypeService.findByType(name) != null) {
                        deleteLedgerEntryType(incomeService.findByCostType(incomeTypeService.findByType(name)));
                        incomeTypeService.deleteType(incomeTypeService.findByType(name));
                    }
                    break;
                }
            }
        }
        return "redirect:categories";
    }

    private void deleteLedgerEntryType(List<? extends Ledger> ledgers) {

        for (Ledger ledger : ledgers
        ) {
            if (ledger instanceof Expenditure) {
                ((Expenditure) ledger).setExpenditureType(expenditureTypeService.findByType("NOT_SELECTED"));
                expenditureService.saveExpenditure((Expenditure) ledger);
            }
            if (ledger instanceof Income) {
                ((Income) ledger).setTypeIncome(incomeTypeService.findByType("NOT_SELECTED"));
                incomeService.saveIncome((Income) ledger);
            }
        }
    }

    @RequestMapping(value = "getAll", method = RequestMethod.GET)
    public String getAll(@RequestParam("name") String name) {

        List<Expenditure> expenditureList = expenditureService.findByExpenditureType(expenditureTypeService.findByType(name));

        for (Expenditure expenditure : expenditureList
        ) {
            System.out.println(expenditure.getPeriodOfTime().getLocalDate() + "\t" + expenditure.getExpenditureType().getType() + "\t" +
                    expenditure.getAmount());
        }

        return "redirect:categories";
    }


}
