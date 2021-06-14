package com.szymon.empik.empik_integration.service;

import com.szymon.empik.empik_integration.adapter.Adapter;
import com.szymon.empik.empik_integration.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GithubUserServiceImpl implements GithubUserService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    @Qualifier("userAdapter")
    private Adapter<User> userAdapter;

    @Autowired
    private UserStatService userStatService;

    @Value("${empikintegration.github.base.url}")
    private String githubBaseUrl;

    @Value("${empikintegration.github.users.url}")
    private String githubUserUrl;

    @Override
    public User getUser(String login) {
        User githubUser = restTemplate.getForObject(githubBaseUrl + githubUserUrl + login, User.class);
        userStatService.saveIncreasedRequestCount(githubUser);
        return userAdapter.adapt(githubUser);
    }
}
