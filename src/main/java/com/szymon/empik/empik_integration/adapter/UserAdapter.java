package com.szymon.empik.empik_integration.adapter;

import com.szymon.empik.empik_integration.model.User;
import com.szymon.empik.empik_integration.service.UserCalculationService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserAdapter implements Adapter<User> {

    @Autowired
    private UserCalculationService userCalculationService;

    @Override
    public User adapt(@NonNull User adaptationObject) {
        adaptationObject.setUserCalculation(userCalculationService.calculateUserStatistics(adaptationObject.getFollowers(), adaptationObject.getPublicRepos()));
        return adaptationObject;
    }
}
