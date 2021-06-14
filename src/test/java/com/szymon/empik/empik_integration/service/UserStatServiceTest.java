package com.szymon.empik.empik_integration.service;

import com.szymon.empik.empik_integration.model.User;
import com.szymon.empik.empik_integration.model.UserStats;
import com.szymon.empik.empik_integration.repository.GithubUserCalculationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserStatServiceTest {

    private static final String TEST_USER_LOGIN = "TestUser";
    @Mock
    private GithubUserCalculationRepository githubUserCalculationRepository;
    @InjectMocks
    private UserStatServiceImpl userStatServiceImpl;

    @Test
    public void saveIncreasedRequestCountTest_userExistInDatabase() {
        //GIVEN
        User user = User.builder().login("TestUser").build();
        UserStats userStatsFromDatabase = new UserStats(TEST_USER_LOGIN, 1L);
        UserStats userStatsBeforeSave = new UserStats(TEST_USER_LOGIN, 2L);
        when(githubUserCalculationRepository.findById(TEST_USER_LOGIN)).thenReturn(Optional.of(userStatsFromDatabase));
        when(githubUserCalculationRepository.save(userStatsBeforeSave)).thenReturn(userStatsBeforeSave);

        //WHEN
        UserStats actualUserStats = userStatServiceImpl.saveIncreasedRequestCount(user);

        //THEN
        assertEquals(userStatsBeforeSave, actualUserStats);
    }

    @Test
    public void saveIncreasedRequestCountTest_userNotExistInDatabase() {
        //GIVEN
        User user = User.builder().login(TEST_USER_LOGIN).build();
        UserStats userStats = new UserStats(TEST_USER_LOGIN, 1L);
        when(githubUserCalculationRepository.findById(TEST_USER_LOGIN)).thenReturn(Optional.empty());
        when(githubUserCalculationRepository.save(userStats)).thenReturn(userStats);

        //WHEN
        UserStats actualUserStats = userStatServiceImpl.saveIncreasedRequestCount(user);

        //THEN
        assertEquals(userStats, actualUserStats);
    }
}
