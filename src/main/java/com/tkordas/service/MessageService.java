package com.tkordas.service;

import com.tkordas.model.Message;
import com.tkordas.model.MessageRepo;
import com.tkordas.model.User;
import com.tkordas.webApi.EmptyFollowingListException;
import com.tkordas.webApi.MessageTooLongException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class MessageService {

  private static final int MSG_MAX_LENGTH = 140;

  private final MessageRepo messageRepo;
  private final UserService userService;
  private final FollowerService followerService;

  @Autowired
  public MessageService(MessageRepo messageRepo, UserService userService, FollowerService followerService) {
    this.messageRepo = messageRepo;
    this.userService = userService;
    this.followerService = followerService;
  }

  public void postMessage(String username, String txt) {
    validateMessageLength(txt);
    User user = userService.getUserByUsername(username);
    if (user == null) {
      //a user is created as soon as they post their first message
      user = userService.createUser(username);
    }
    Message message = new Message();
    message.setText(txt);
    message.setUser(user);
    messageRepo.save(message);
  }

  private void validateMessageLength(String msg) {
    if (msg.length() > MSG_MAX_LENGTH) {
      throw new MessageTooLongException(MSG_MAX_LENGTH);
    }
  }

  public List<Message> getAllMessagesPostedByUser(String username) {
    User user = userService.validateAndGetUser(username);
    return messageRepo.getPostsByUserId(user.getId());
  }

  public List<Message> getAllMessagesOfFollowedUsers(String username) {
    User user = userService.validateAndGetUser(username);
    Set<Long> followedUsers = followerService.getFollowedUsersIdByFollowingUserId(user.getId());
    if (followedUsers.size() > 0) {
      return messageRepo.getPostsOfFollowedUsers(followedUsers);
    } else {
      throw new EmptyFollowingListException();
    }
  }

}
