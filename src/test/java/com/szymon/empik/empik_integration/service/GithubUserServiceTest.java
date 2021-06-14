package com.szymon.empik.empik_integration.service;

import com.szymon.empik.empik_integration.adapter.UserAdapter;
import com.szymon.empik.empik_integration.model.User;
import com.szymon.empik.empik_integration.model.UserStats;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GithubUserServiceTest {

    private final static String TEST_BASE_URL = "testBaseUrl/";
    private final static String TEST_USER_URL = "testUserUrl/";
    private final static String TEST_USER_LOGIN = "Test_User";

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private UserAdapter userAdapter;

    @Mock
    private UserStatService userStatService;

    @InjectMocks
    private GithubUserServiceImpl githubUserService;

    @BeforeEach
    private void init() {
        ReflectionTestUtils.setField(githubUserService, "githubBaseUrl", TEST_BASE_URL);
        ReflectionTestUtils.setField(githubUserService, "githubUserUrl", TEST_USER_URL);
    }

    @Test
    public void testGetUser() {
        //GIVEN
        User userWithoutCalculation = User.builder().login(TEST_USER_LOGIN).build();
        User userWithCalculation = User.builder().login(TEST_USER_LOGIN).userCalculation(2.0).build();
        UserStats userStats = new UserStats(TEST_USER_LOGIN, 1L);
        when(restTemplate.getForObject(TEST_BASE_URL + TEST_USER_URL + TEST_USER_LOGIN, User.class)).thenReturn(userWithoutCalculation);
        when(userStatService.saveIncreasedRequestCount(userWithoutCalculation)).thenReturn(userStats);
        when(userAdapter.adapt(userWithoutCalculation)).thenReturn(userWithCalculation);

        //WHEN
        User actualUser = githubUserService.getUser(TEST_USER_LOGIN);

        //THEN
        assertEquals(userWithCalculation, actualUser);

    }
}
