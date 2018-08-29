package com.tkordas.service;

import com.tkordas.model.MessageRepo;
import com.tkordas.model.User;
import com.tkordas.webApi.EmptyFollowingListException;
import com.tkordas.webApi.MessageTooLongException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class MessageServiceTest {

  @InjectMocks
  private MessageService messageService;
  @Mock
  private UserService userService;
  @Mock
  private FollowerService followerService;
  @Mock
  private MessageRepo messageRepo;

  @BeforeMethod
  public void init() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testPostMessage() {
    String username = "Alice";
    String message = prepareMessageWithGivenLength(20);
    MessageService mockedMessageService = mock(MessageService.class);

    mockedMessageService.postMessage(username, message);
    verify(mockedMessageService, times(1)).postMessage(username, message);
  }

  @Test
  public void testGettingAllMessagesOfFollowedUsers() {
    String username = "Alice";
    User user = mock(User.class);
    MessageService mockedMessageService = mock(MessageService.class);

    when(user.getUsername()).thenReturn(username);
    when(userService.validateAndGetUser(username)).thenReturn(user);

    mockedMessageService.getAllMessagesOfFollowedUsers(username);
    verify(mockedMessageService, times(1)).getAllMessagesOfFollowedUsers(username);
  }


  @Test
  public void testGettingAllMessagesPostedByUser() {
    String username = "Alice";
    User user = mock(User.class);
    MessageService mockedMessageService = mock(MessageService.class);

    when(user.getUsername()).thenReturn(username);
    when(userService.validateAndGetUser(username)).thenReturn(user);

    mockedMessageService.getAllMessagesPostedByUser(username);
    verify(mockedMessageService, times(1)).getAllMessagesPostedByUser(username);
  }


  @Test(expectedExceptions = EmptyFollowingListException.class)
  public void testEmptyFollowingListException() {
    String username = "Alice";
    User user = mock(User.class);
    Set<Long> followedUsers = new HashSet<>();

    when(userService.validateAndGetUser(username)).thenReturn(user);
    when(followerService.getFollowedUsersIdByFollowingUserId(any())).thenReturn(followedUsers);
    messageService.getAllMessagesOfFollowedUsers(username);
  }

  @Test(expectedExceptions = MessageTooLongException.class)
  public void testMessageTooLongException() {
    int maxMsgLength = 140;
    String username = "Alice";
    String message = prepareMessageWithGivenLength(maxMsgLength + 1);
    User user = mock(User.class);
    Set<Long> followedUsers = new HashSet<>();

    when(userService.validateAndGetUser(username)).thenReturn(user);
    when(followerService.getFollowedUsersIdByFollowingUserId(any())).thenReturn(followedUsers);
    messageService.postMessage(username, message);
  }

  private String prepareMessageWithGivenLength(int length) {
    StringBuilder result = new StringBuilder("");
    final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    final int alphabetLength = alphabet.length();
    Random r = new Random();
    for (int i = 0; i < length; i++) {
      result.append(alphabet.charAt(r.nextInt(alphabetLength)));
    }
    return result.toString();
  }

}
