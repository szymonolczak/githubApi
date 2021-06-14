package com.szymon.empik.empik_integration.controller;

import com.szymon.empik.empik_integration.model.User;
import com.szymon.empik.empik_integration.service.GithubUserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import javax.persistence.PersistenceException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private GithubUserService githubUserService;

    @InjectMocks
    private GithubUserController githubUserController;

    @Test
    public void getGithubUserTests_shouldReturnUser() {
        //GIVEN
        User expectedUser = User.builder().id(1L).login("TestUser").build();
        Mockito.when(githubUserService.getUser("TestUser")).thenReturn(expectedUser);

        //WHEN
        User actualUser = githubUserController.getUser("TestUser");

        //THEN
        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void getGithubUserTests_shouldThrowNotFoundException() {
        //GIVEN
        Mockito.when(githubUserService.getUser("TestUser")).thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND, "not found"));

        //THEN
        assertThrows(HttpClientErrorException.class, () -> githubUserController.getUser("TestUser"));
    }

    @Test
    public void getGithubUserTests_shouldThrowPersistanceException() {
        //GIVEN
        Mockito.when(githubUserService.getUser("TestUser")).thenThrow(new PersistenceException("persistance exception"));

        //THEN
        assertThrows(PersistenceException.class, () -> githubUserController.getUser("TestUser"));
    }
}
