package com.szymon.empik.empik_integration.adapter;

import com.szymon.empik.empik_integration.model.User;
import com.szymon.empik.empik_integration.service.UserCalculationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserAdapterTest {

    @Mock
    private UserCalculationService userCalculationService;

    @InjectMocks
    private UserAdapter userAdapter;

    @Test
    public void userAdapterTest() {
        //GIVEN
        User user = User.builder().followers(1).publicRepos(2).build();

        when(userCalculationService.calculateUserStatistics(user.getFollowers(), user.getPublicRepos())).thenReturn(2.0);
        //WHEN
        User adaptedUser = userAdapter.adapt(user);

        //THEN
        assertEquals(2.0, adaptedUser.getUserCalculation());
    }
}
