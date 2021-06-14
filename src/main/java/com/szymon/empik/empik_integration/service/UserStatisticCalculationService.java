package com.szymon.empik.empik_integration.service;

import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserStatisticCalculationService implements UserCalculationService {

    @Override
    public Double calculateUserStatistics(Integer followersCount, Integer publicReposCount) {
        if (checkCalculationArgsNonNull(followersCount, publicReposCount)) return 0.0;
        else if (followersCount == 0) return 0.0;

        return 6.0 / followersCount * (2 + publicReposCount);
    }

    private boolean checkCalculationArgsNonNull(Integer followersCount, Integer publicReposCount) {
        return Objects.isNull(followersCount) || Objects.isNull(publicReposCount);
    }
}
