package com.tkordas.service;

import com.tkordas.model.User;
import com.tkordas.model.UserRepo;
import com.tkordas.webApi.UserNotFoundException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class UserServiceTest {

  @InjectMocks
  private UserService userService;
  @Mock
  private UserRepo userRepo;

  @BeforeMethod
  public void init() {
    MockitoAnnotations.initMocks(this);
  }


  @Test()
  public void testValidationWhenUserPersists() {
    String username = "Alice";
    User user = mock(User.class);

    when(user.getUsername()).thenReturn(username);
    when(userService.getUserByUsername(username)).thenReturn(user);

    User result = userService.validateAndGetUser(username);
    assertNotNull(result);
    assertEquals(result.getUsername(), username);
  }

  @Test(expectedExceptions = UserNotFoundException.class)
  public void testUserNotFoundException() {
    String username = "Alice";
    userService.validateAndGetUser(username);
  }

  @Test
  public void testCreatingUser() {
    String username = "Alice";
    UserService mockedUserService = mock(UserService.class);

    mockedUserService.createUser(username);
    verify(mockedUserService, times(1)).createUser(username);
  }

}
