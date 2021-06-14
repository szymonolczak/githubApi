package com.szymon.empik.empik_integration.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.szymon.empik.empik_integration.model.GithubUserView;
import com.szymon.empik.empik_integration.model.User;
import com.szymon.empik.empik_integration.service.GithubUserService;
import com.szymon.empik.empik_integration.service.UserStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class GithubUserController {

    @Autowired
    private GithubUserService githubUserService;

    @Autowired
    private UserStatService userStatService;

    @JsonView(GithubUserView.UserRepresentative.class)
    @GetMapping("/{username}")
    public User getUser(@PathVariable String username) {
        return githubUserService.getUser(username);
    }
}
