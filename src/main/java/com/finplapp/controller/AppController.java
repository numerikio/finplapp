package com.finplapp.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finplapp.DateMeasure;
import com.finplapp.model.*;
import com.finplapp.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/")
@SessionAttributes("roles")
@Transactional
public class AppController {

    static final Logger logger = LoggerFactory.getLogger(AppController.class);

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;

    @Autowired
    private AuthenticationTrustResolver authenticationTrustResolver;

    @Autowired
    private PersistentTokenRepository tokenRepository;

    @Autowired
    private PeriodOfTimeService periodOfTimeService;

    @Autowired
    @Qualifier("costService")
    private CostService costService;

    @Autowired
    @Qualifier("incomeService")
    private IncomeService incomeService;

    @Autowired
    private CostTypeService costTypeService;

    @Autowired
    private IncomeTypeService incomeTypeService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String mainPage(ModelMap model) throws IOException {
        ArrayList<PeriodOfTime> periodOfTimes = getSortedPeriodOfTimeList();
        updateDatasOfPeriod(periodOfTimes);

        ArrayList<ArrayList> arrayLists = new ArrayList<>();
        arrayLists.add(getBalanceListAllPeriods(periodOfTimes));

        DataForChart dataForChart = new DataForChart(getTimeStepAllPeriodForGraf(periodOfTimes), arrayLists);
        ObjectMapper objectMapper = new ObjectMapper();
        String jString = objectMapper.writeValueAsString(dataForChart);

        model.addAttribute("dateMeasure", DateMeasure.values());
        model.addAttribute("loggedinuser", getPrincipal());
        model.addAttribute("data", jString);
        return "mainPage";
    }

    @RequestMapping(value = "/mainRedact", method = RequestMethod.GET)
    public String mainPage(@RequestParam("date") String date,
                           @RequestParam("costTypes") String costTypes,
                           @RequestParam("amount") String amount,
                           @RequestParam("message") String message,
                           ModelMap model) throws IOException {
        ArrayList<PeriodOfTime> periodOfTimes = getSortedPeriodOfTimeList();
        updateDatasOfPeriod(periodOfTimes);

        ArrayList<ArrayList> arrayLists = new ArrayList<>();
        arrayLists.add(getBalanceListAllPeriods(periodOfTimes));

        DataForChart dataForChart = new DataForChart(getTimeStepAllPeriodForGraf(periodOfTimes), arrayLists);
        ObjectMapper objectMapper = new ObjectMapper();
        String jString = objectMapper.writeValueAsString(dataForChart);

        model.addAttribute("loggedinuser", getPrincipal());
        model.addAttribute("data", jString);
        return "mainPage";
    }

    @RequestMapping(value = "/userslist", method = RequestMethod.GET)
    public String listUsers(ModelMap model) {

        List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("loggedinuser", getPrincipal());
        return "userslist";
    }

    //======================Events=================================

    @RequestMapping("getEvents")
    public String getEvents() {
        return "getEvents";
    }

    @RequestMapping("getAllEventOfDate")
    public String getAllEventOfDate(@RequestParam("dates") String dates, ModelMap model) {
        List<PeriodOfTime> periodOfTimes = getActualPeriodsOfTime(getAllDatesOfPeriod(getFirstAndLastDaysWihtString(dates)));
        model.addAttribute("periodOfTimes", periodOfTimes);
        model.addAttribute("dates", dates);
        return "eventsList";

    }
    //====================End Events=============================

    //======================Cost=================================

    @RequestMapping(value = {"Cost"}, method = RequestMethod.GET)
    public String pageCost(ModelMap model) {
        model.addAttribute("costTypes", costTypeService.findAll());
        return "costPage";
    }

    @RequestMapping(value = "saveCost")
    public String saveCost(@RequestParam("date") String date,
                           @RequestParam("costTypes") String costTypes,
                           @RequestParam("amount") String amount,
                           @RequestParam("message") String message) {

        Cost cost = new Cost();
        cost.setAmount(Double.valueOf(amount));
        cost.setMessage(message);
        cost.setCostType(costTypeService.findByType(costTypes));

        User user = userService.findBySSO(getPrincipal());
        PeriodOfTime periodOfTime = getNewOrOldPeriodOfTime(getLocalDateWithString(date), user);
        cost.setPeriodOfTime(periodOfTime);
        List<Cost> costList = periodOfTime.getCostList();
        costList.add(cost);
        periodOfTime.setCostList(costList);
        periodOfTimeService.savePeriodOfTime(periodOfTime);
        return "redirect:/Cost";
    }

    @RequestMapping(value = "delete-cost")
    public String deleteCost(@RequestParam("dates") String dates,
                             @RequestParam("date") String date,
                             @RequestParam("cost.id") Long id,
                             ModelMap model) {
        PeriodOfTime periodOfTime = periodOfTimeService.findByLocalDateAndUser(getLocalDateWithString(date), userService.findBySSO(getPrincipal()));
        periodOfTime.getCostList().remove(costService.findCostById(id));
        costService.deleteCost(id);
        deleteAllEmptiesPeriodsOfUser(userService.findBySSO(getPrincipal()));
        model.addAttribute("dates", dates);
        return "redirect:/getAllEventOfDate";
    }

    //===================End Cost=================================

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

        User user = userService.findBySSO(getPrincipal());
        PeriodOfTime periodOfTime = getNewOrOldPeriodOfTime(getLocalDateWithString(date), user);
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
        PeriodOfTime periodOfTime = periodOfTimeService.findByLocalDateAndUser(getLocalDateWithString(date), userService.findBySSO(getPrincipal()));
        periodOfTime.getIncomeList().remove(incomeService.findIncomeById(id));
        incomeService.deleteIncome(id);
        deleteAllEmptiesPeriodsOfUser(userService.findBySSO(getPrincipal()));
        model.addAttribute("dates", dates);
        return "redirect:/getAllEventOfDate";
    }

    //===================End Income=================================

    /**
     * This method will provide the medium to add a new user.
     */
    @RequestMapping(value = {"/newuser"}, method = RequestMethod.GET)
    public String newUser(ModelMap model) {
        User user = new User();
        System.out.println(user.getUserProfiles());
        model.addAttribute("user", user);
        model.addAttribute("edit", false);
        model.addAttribute("loggedinuser", getPrincipal());
        return "registration";
    }

    /**
     * This method will be called on form submission, handling POST request for
     * saving user in database. It also validates the user input
     */
    @RequestMapping(value = {"/newuser"}, method = RequestMethod.POST)
    public String saveUser(@Valid User user, BindingResult result, ModelMap model) {
        System.out.println(user.getSsoId());
        if (result.hasErrors()) {
            logger.info(result.toString());
            return "registration";
        }

        if (!userService.isUserSSOUnique(user.getId(), user.getSsoId())) {
            FieldError ssoError = new FieldError("user", "ssoId", messageSource.getMessage("non.unique.ssoId", new String[]{user.getSsoId()}, Locale.getDefault()));
            result.addError(ssoError);
            return "registration";
        }
        Set<UserProfile> userProfiles = new HashSet<>();
        userProfiles.add(userProfileService.findByType("USER"));
        user.setUserProfiles(userProfiles);
        userService.saveUser(user);
        model.addAttribute("loggedinuser", getPrincipal());
        return "registrationsuccess";
    }

    /**
     * This method will provide the medium to update an existing user.
     */
    @RequestMapping(value = {"/edit-user-{ssoId}"}, method = RequestMethod.GET)
    public String editUser(@PathVariable String ssoId, ModelMap model) {
        User user = userService.findBySSO(ssoId);
        model.addAttribute("user", user);
        model.addAttribute("roles", userProfileService.findAll());
        model.addAttribute("edit", true);
        model.addAttribute("loggedinuser", getPrincipal());
        return "registration";
    }

    /**
     * This method will be called on form submission, handling POST request for
     * updating user in database. It also validates the user input
     */
    @RequestMapping(value = {"/edit-user-{ssoId}"}, method = RequestMethod.POST)
    public String updateUser(@Valid User user, BindingResult result,
                             ModelMap model, @PathVariable String ssoId) {

        if (result.hasErrors()) {
            return "registration";
        }
        userService.updateUser(user);
        model.addAttribute("loggedinuser", getPrincipal());
        return "registrationsuccess";
    }

    /**
     * This method will delete an user by it's SSOID value.
     */
    @RequestMapping(value = {"/delete-user-{ssoId}"}, method = RequestMethod.GET)
    public String deleteUser(@PathVariable String ssoId) {
        userService.deleteUserBySSO(ssoId);
        tokenRepository.removeUserTokens(ssoId);
        return "redirect:/userslist";
    }

    /**
     * This method will provide UserProfile list to views
     */
    @ModelAttribute("roles")
    public List<UserProfile> initializeProfiles() {
        return userProfileService.findAll();
    }

    /**
     * This method handles Access-Denied redirect.
     */
    @RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
    public String accessDeniedPage(ModelMap model) {
        model.addAttribute("loggedinuser", getPrincipal());
        return "accessDenied";
    }

    /**
     * This method handles login GET requests.
     * If users is already logged-in and tries to goto login page again, will be redirected to list page.
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        if (isCurrentAuthenticationAnonymous()) {
            return "login";
        } else {
            return "redirect:/";
        }
    }

    /**
     * This method handles logout requests.
     * Toggle the handlers if you are RememberMe functionality is useless in your app.
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            //new SecurityContextLogoutHandler().logout(request, response, auth);
            persistentTokenBasedRememberMeServices.logout(request, response, auth);
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        return "redirect:/login?logout";
    }

    /**
     * This method returns the principal[user-name] of logged-in user.
     */
    private String getPrincipal() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

    /**
     * This method returns true if users is already authenticated [logged-in], else false.
     */
    private boolean isCurrentAuthenticationAnonymous() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authenticationTrustResolver.isAnonymous(authentication);
    }

    private PeriodOfTime getNewOrOldPeriodOfTime(LocalDate localDate, User user) {
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

    private void updateDatasOfPeriod(ArrayList<PeriodOfTime> periodOfTimes) {
        for (int i = 0; i < periodOfTimes.size(); i++) {
            Double calculationOfPeriod = getSumEventsOfPeridofTime(periodOfTimes.get(i).getIncomeList()) - getSumEventsOfPeridofTime(periodOfTimes.get(i).getCostList());
            if (i != 0) {
                periodOfTimes.get(i).setBalance(periodOfTimes.get(i - 1).getBalance() + calculationOfPeriod);
            } else {
                periodOfTimes.get(i).setBalance(calculationOfPeriod);
            }
            periodOfTimeService.updatePeriodOfTime(periodOfTimes.get(i));
        }
    }

    private ArrayList<PeriodOfTime> getSortedPeriodOfTimeList() {
        ArrayList<PeriodOfTime> periodOfTimes = (ArrayList<PeriodOfTime>) periodOfTimeService.findByUser(userService.findBySSO(getPrincipal()));
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

    private ArrayList<Double> getBalanceListAllPeriods(ArrayList<PeriodOfTime> periodOfTimes) {
        ArrayList<Double> balanseList = new ArrayList<>();
        for (PeriodOfTime periodOfTime : periodOfTimes
        ) {
            balanseList.add(periodOfTime.getBalance());
        }
        return balanseList;
    }

    private ArrayList<String> getTimeStepAllPeriodForGraf(ArrayList<PeriodOfTime> periodOfTimes) {
        ArrayList<String> timeSteps = new ArrayList<>();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd MMM");
        for (PeriodOfTime periodOfTime : periodOfTimes
        ) {
            timeSteps.add(periodOfTime.getLocalDate().format(dateTimeFormatter));
        }
        return timeSteps;
    }


    private LocalDate getLocalDateWithString(String date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("[MM/dd/yyyy][yyyy-MM-dd]");
        LocalDate localDate = LocalDate.parse(date, dateTimeFormatter);
        return localDate;
    }

    private List<String> getFirstAndLastDaysWihtString(String datesSring) {
        return Stream.of(datesSring.split(" - "))
                .map(elem -> new String(elem))
                .collect(Collectors.toList());
    }

    private List<LocalDate> getAllDatesOfPeriod(List<String> dates) {
        ArrayList<LocalDate> localDates = new ArrayList<>();

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

    private List<PeriodOfTime> getActualPeriodsOfTime(List<LocalDate> localDates) {
        List<PeriodOfTime> periodOfTimes = new ArrayList<>();
        for (LocalDate localDate : localDates
        ) {
            PeriodOfTime periodOfTime = periodOfTimeService.findByLocalDateAndUser(localDate, userService.findBySSO(getPrincipal()));
            if (periodOfTime != null) {
                periodOfTimes.add(periodOfTime);
            }
        }
        return periodOfTimes;
    }

    private void deleteAllEmptiesPeriodsOfUser(User user) {
        List<PeriodOfTime> periodOfTimes = periodOfTimeService.findEmptiesPeriods(user);
        for (PeriodOfTime period : periodOfTimes
        ) {
            periodOfTimeService.deletePeriodOfTime(period);
        }
    }
}