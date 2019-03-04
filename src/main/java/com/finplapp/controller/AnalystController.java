package com.finplapp.controller;

import com.finplapp.model.*;
import com.finplapp.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class AnalystController {

    static final Logger logger = LoggerFactory.getLogger(AnalystController.class);

    @Autowired
    private CostTypeService costTypeService;

    @Autowired
    private IncomeTypeService incomeTypeService;

    @Autowired
    private PeriodOfTimeService periodOfTimeService;

    @Autowired
    private CostService costService;

    @Autowired
    private IncomeService incomeService;


    @RequestMapping(value = "analyst", method = RequestMethod.GET)
    public String analystPage(ModelMap model) {

        model.addAttribute("loggedinuser", "analyst");
        return "analystPage";
    }

    @RequestMapping(value = "categories", method = RequestMethod.GET)
    public String categories(ModelMap model) {
        List<CostType> costTypeList = costTypeService.findAll();
        List<IncomeType> incomeTypeList = incomeTypeService.findAll();

        model.addAttribute("costsType", costTypeList);
        model.addAttribute("incomesType", incomeTypeList);
        model.addAttribute("loggedinuser", "analyst");
        return "categoriesPage";
    }

    @RequestMapping(value = "useCategories", method = RequestMethod.GET)
    public String addCategories(@RequestParam("action") String action,
                                @RequestParam("name") String name, ModelMap model) {
        if (name != null) {
            switch (action) {
                case "addCostType": {
                    if (costTypeService.findByType(name) == null) {
                        costTypeService.saveType(new CostType(name));
                    }
                    break;
                }
                case "addIncomeType": {
                    if (incomeTypeService.findByType(name) == null) {
                        incomeTypeService.saveType(new IncomeType(name));
                    }
                    break;
                }
                case "deleteCostType": {
                    if (costTypeService.findByType(name) != null) {
                        deleteLedgerEntryType(costService.findByCostType(costTypeService.findByType(name)));
                        costTypeService.deleteType(costTypeService.findByType(name));
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
                default:
                    model.addAttribute("errorMessage", "not found...");
            }
        }
        return "redirect:categories";
    }

    private void deleteLedgerEntryType(List<? extends Ledger> ledgers) {

        for (Ledger ledger : ledgers
        ) {
            if (ledger instanceof Cost) {
                ((Cost) ledger).setCostType(costTypeService.findByType("NOT_SELECTED"));
                costService.saveCost((Cost) ledger);
            }
            if (ledger instanceof Income) {
                ((Income) ledger).setTypeIncome(incomeTypeService.findByType("NOT_SELECTED"));
                incomeService.saveIncome((Income) ledger);
            }
        }
    }

    @RequestMapping(value = "getAll", method = RequestMethod.GET)
    public String getAll(@RequestParam("name") String name) {

        List<Cost> costList = costService.findByCostType(costTypeService.findByType(name));

        for (Cost cost : costList
        ) {
            System.out.println(cost.getPeriodOfTime().getLocalDate() + "\t" + cost.getCostType().getType() + "\t" +
                    cost.getAmount());
        }

        return "redirect:categories";
    }


}
