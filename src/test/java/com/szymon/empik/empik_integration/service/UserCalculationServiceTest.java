package com.szymon.empik.empik_integration.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserCalculationServiceTest {

    private UserStatisticCalculationService userCalculationService;

    @BeforeEach
    private void init() {
        userCalculationService = new UserStatisticCalculationService();
    }

    @Test
    public void calculateUserStatisticsTest_nonNullArgs() {
        //GIVEN
        Integer followersCount = 1;
        Integer publicReposCount = 2;

        //WHEN
        Double actualCalculation = userCalculationService.calculateUserStatistics(followersCount, publicReposCount);

        //THEN
        assertEquals(24, actualCalculation);
    }

    @Test
    public void calculateUserStatisticsTest_followersIsZero() {
        //GIVEN
        Integer followersCount = 0;
        Integer publicReposCount = 2;

        //WHEN
        Double actualCalculation = userCalculationService.calculateUserStatistics(followersCount, publicReposCount);

        //THEN
        assertEquals(0, actualCalculation);
    }

    @Test
    public void calculateUserStatisticsTest_followersIsNull() {
        //GIVEN
        Integer followersCount = null;
        Integer publicReposCount = 2;

        //WHEN
        Double actualCalculation = userCalculationService.calculateUserStatistics(followersCount, publicReposCount);

        //THEN
        assertEquals(0, actualCalculation);
    }
}