package com.finplapp.controller;

import com.finplapp.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    @Qualifier("costService")
    private CostService costService;

    @Autowired
    @Qualifier("incomeService")
    private IncomeService incomeService;

    @RequestMapping(value = "analyst", method = RequestMethod.GET)
    public String analystPage(ModelMap model) {

        model.addAttribute("loggedinuser", "analyst");
        return "analystPage";
    }
}
