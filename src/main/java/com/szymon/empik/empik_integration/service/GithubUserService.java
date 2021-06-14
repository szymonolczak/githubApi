package com.szymon.empik.empik_integration.service;

import com.szymon.empik.empik_integration.model.User;

public interface GithubUserService {
    User getUser(String login);
}
