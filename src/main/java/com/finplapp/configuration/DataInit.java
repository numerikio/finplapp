package com.finplapp.configuration;

import com.finplapp.model.*;
import com.finplapp.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
@PropertySource({
        "classpath:login_data.properties",
        "classpath:default_types.properties"
})
public class DataInit {

    static final Logger logger = LoggerFactory.getLogger(DataInit.class);

    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private Environment environment;

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @Autowired
    private CostTypeService costTypeService;

    @Autowired
    private IncomeTypeService incomeTypeService;

    private UserProfileType[] userProfileTypes = UserProfileType.values();

    @PostConstruct
    private void init() {
        addProfileTypesDataToDB();
        addSuperUserDataToDB();
        addAdminDataToDB();
        addAnalystDataToDB();
        addLedgerTipesToDB(costTypeService, new CostType(), "default.cost.type");
        addLedgerTipesToDB(incomeTypeService, new IncomeType(), "default.income.type");
    }

    private void addProfileTypesDataToDB() {
        for (UserProfileType uP : userProfileTypes) {
            if (userProfileService.findByType(uP.getUserProfileType()) == null) {
                UserProfile userProfile = new UserProfile();
                userProfile.setType(uP.getUserProfileType());
                userProfileService.saveUserProfile(userProfile);
            }
        }
    }

    private void addLedgerTipesToDB(LedgerTypeService ledgerTypeService, LedgerEntryType ledgerEntryType, String sourceDefaultTypes) {
        for (String s : getArrayOfProperties(environment.getProperty(sourceDefaultTypes))
        ) {
            if (ledgerTypeService.findByType(s) == null) {
                try {
                    Class c = Class.forName(ledgerEntryType.getClass().getName());
                    LedgerEntryType newledgerEntryType = (LedgerEntryType) c.newInstance();
                    newledgerEntryType.setType(s);
                    ledgerTypeService.saveType(newledgerEntryType);
                } catch (Exception e) {
                    logger.error(e.getLocalizedMessage());
                }
            }
        }
    }

    private String[] getArrayOfProperties(String s) {
        String regex = ",";
        return s.split(regex);
    }

    private void addSuperUserDataToDB() {
        if (userService.findBySSO(environment.getProperty("data.s.admin.sso_id")) == null) {
            User user = new User();
            user.setSsoId(environment.getProperty("data.s.admin.sso_id"));
            user.setEmail(environment.getProperty("data.s.admin.email"));
            user.setPassword(environment.getProperty("data.s.admin.password"));

            Set<UserProfile> userProfiles = new HashSet<>();
            userProfiles.add(userProfileService.findByType("ADMIN"));
            userProfiles.add(userProfileService.findByType("ANALYST"));
            userProfiles.add(userProfileService.findByType("USER"));
            user.setUserProfiles(userProfiles);
            userService.saveUser(user);
        }
    }

    private void addAdminDataToDB() {
        if (userService.findBySSO(environment.getProperty("data.admin.sso_id")) == null) {
            User user = new User();
            user.setSsoId(environment.getProperty("data.admin.sso_id"));
            user.setEmail(environment.getProperty("data.admin.email"));
            user.setPassword(environment.getProperty("data.admin.password"));

            Set<UserProfile> userProfiles = new HashSet<>();
            userProfiles.add(userProfileService.findByType("ADMIN"));
            user.setUserProfiles(userProfiles);
            userService.saveUser(user);
        }
    }

    private void addAnalystDataToDB() {
        if (userService.findBySSO(environment.getProperty("data.analyst.sso_id")) == null) {
            User user = new User();
            user.setSsoId(environment.getProperty("data.analyst.sso_id"));
            user.setEmail(environment.getProperty("data.analyst.email"));
            user.setPassword(environment.getProperty("data.analyst.password"));

            Set<UserProfile> userProfiles = new HashSet<>();
            userProfiles.add(userProfileService.findByType("ANALYST"));
            user.setUserProfiles(userProfiles);
            userService.saveUser(user);
        }
    }
}


