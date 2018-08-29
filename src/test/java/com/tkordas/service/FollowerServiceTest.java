package com.tkordas.service;

import com.tkordas.model.Follower;
import com.tkordas.model.FollowerRepo;
import com.tkordas.model.User;
import com.tkordas.webApi.AlreadyFollowingException;
import com.tkordas.webApi.UserTargetsHimselfException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;

public class FollowerServiceTest {

  @InjectMocks
  private FollowerService followerService;
  @Mock
  private UserService userService;
  @Mock
  private FollowerRepo followerRepo;

  @BeforeMethod
  public void init() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testFollowingUser() {
    String targetName = "Bob";
    String followerName = "Alice";
    FollowerService mockedFollowerService = mock(FollowerService.class);

    mockedFollowerService.addFollower(targetName, followerName);
    verify(mockedFollowerService, times(1)).addFollower(targetName, followerName);
  }

  @Test
  public void testFollowedUsersIdByFollowingUserId(){
    Long userId = 1L;
    FollowerService mockedFollowerService = mock(FollowerService.class);

    mockedFollowerService.getFollowedUsersIdByFollowingUserId(userId);
    verify(mockedFollowerService, times(1)).getFollowedUsersIdByFollowingUserId(userId);
  }

  @Test(expectedExceptions = UserTargetsHimselfException.class)
  public void testUserTargetsHimselfException() {
    String username = "Alice";
    followerService.addFollower(username, username);
  }

  @Test(expectedExceptions = AlreadyFollowingException.class)
  public void testAlreadyFollowingException() {
    String targetName = "Bob";
    String followerName = "Alice";
    Long targetId = 1L;
    Long followerId = 2L;
    User userBob = mock(User.class);
    User userAlice = mock(User.class);
    Follower follower = mock(Follower.class);

    when(userBob.getId()).thenReturn(targetId);
    when(userAlice.getId()).thenReturn(followerId);
    when(userService.validateAndGetUser(targetName)).thenReturn(userBob);
    when(userService.validateAndGetUser(followerName)).thenReturn(userAlice);
    when(followerRepo.getFollowerByTargetAndFollowed(targetId, followerId)).thenReturn(follower);

    followerService.addFollower(targetName, followerName);
  }

}
