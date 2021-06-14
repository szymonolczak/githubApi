package com.szymon.empik.empik_integration.service;

import com.szymon.empik.empik_integration.model.User;
import com.szymon.empik.empik_integration.model.UserStats;

public interface UserStatService {
    UserStats saveIncreasedRequestCount(User user);
}
