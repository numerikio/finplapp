package com.finplapp.configuration;

import com.finplapp.model.UserProfile;
import com.finplapp.model.User;
import com.finplapp.model.UserProfileType;
import com.finplapp.service.UserProfileService;
import com.finplapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
@PropertySource("classpath:login_data.properties")
public class DataInit {

    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private Environment environment;

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    UserProfileType[] userProfileTypes = UserProfileType.values();

    @PostConstruct
    private void init() {
        addDataToDB();
        addAdminDataToDB();
    }


    private void addDataToDB() {
        for (UserProfileType uP : userProfileTypes) {
            if (userProfileService.findByType(uP.getUserProfileType()) == null) {
                UserProfile userProfile = new UserProfile();
                userProfile.setType(uP.getUserProfileType());
                userProfileService.saveUserProfile(userProfile);
            }
        }
    }

    private void addAdminDataToDB() {
        if (userService.findBySSO(environment.getProperty("data.admin.sso_id")) == null) {
            User user = new User();
            user.setSsoId(environment.getProperty("data.admin.sso_id"));
            user.setEmail(environment.getProperty("data.admin.email"));
            user.setPassword(environment.getProperty("data.admin.password"));

            Set<UserProfile> userProfiles = new HashSet<>();

            // for (UserProfileType uP : userProfileTypes) {
            // userProfileService.saveUserProfile(userProfile);

            userProfiles.add(userProfileService.findByType("ADMIN"));
            userProfiles.add(userProfileService.findByType("ANALYST"));
            userProfiles.add(userProfileService.findByType("USER"));
            user.setUserProfiles(userProfiles);
            userService.saveUser(user);
        }
    }
}


