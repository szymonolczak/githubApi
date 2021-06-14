package com.szymon.empik.empik_integration.service;

import com.szymon.empik.empik_integration.model.User;
import com.szymon.empik.empik_integration.model.UserStats;
import com.szymon.empik.empik_integration.repository.GithubUserCalculationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserStatServiceImpl implements UserStatService {

    @Autowired
    private GithubUserCalculationRepository githubUserCalculationRepository;

    @Override
    public UserStats saveIncreasedRequestCount(User user) {
        UserStats userStats = new UserStats(user.getLogin(), getUserRequestCount(user.getLogin()).getRequestCount() + 1);
        return githubUserCalculationRepository.save(userStats);
    }

    private UserStats getUserRequestCount(String login) {
            return githubUserCalculationRepository.findById(login).orElse(new UserStats(login, 0L));
    }
}
