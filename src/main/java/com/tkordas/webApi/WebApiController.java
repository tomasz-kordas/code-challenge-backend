package com.tkordas.webApi;

import com.tkordas.model.Message;
import com.tkordas.service.FollowerService;
import com.tkordas.service.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Api(value = "endpoints", description = "All requested endpoints exposed through a web API")
public class WebApiController {

  private final MessageService messageService;
  private final FollowerService followerService;

  @Autowired
  public WebApiController(MessageService messageService, FollowerService followerService) {
    this.messageService = messageService;
    this.followerService = followerService;
  }

  /**
   * Posting
   * A user should be able to post a 140 character message.
   */
  @ApiOperation(value = "Post message on own wall")
  @RequestMapping(value = "/postMessage", method = RequestMethod.POST)
  @ResponseStatus(HttpStatus.CREATED)
  public void postMessage(
      @ApiParam(name = "u", value = "Username of requester.", required = true) @RequestParam(value = "u") String username,
      @ApiParam(name = "m", value = "User's message with 140 maximum possible characters.", required = true) @RequestParam(value = "m") String msgTxt) {
    messageService.postMessage(username, msgTxt);
  }

  /**
   * Wall
   * A user should be able to see a list of the messages they've posted, in reverse chronological order.
   */
  @ApiOperation(value = "View all messages posted by user on own wall")
  @RequestMapping(value = "/getWall/{username}", method = RequestMethod.GET, produces = "application/json")
  public List<Message> getWall(
      @ApiParam(name = "username", value = "Username of requester.", required = true) @PathVariable(value = "username") String username) {
    return messageService.getAllMessagesPostedByUser(username);
  }

  /**
   * Following
   * A user should be able to follow another user. Following doesn't have to be reciprocal: Alice can follow Bob without Bob having to follow Alice.
   * (using usernames instead of id, because there is no possibility to create users with same username and different id)
   */
  @ApiOperation(value = "Add a follower to user")
  @RequestMapping(value = "/addFollower", method = RequestMethod.POST)
  @ResponseStatus(HttpStatus.CREATED)
  public void addFollower(
      @ApiParam(name = "t", value = "Target a username which will be followed.", required = true) @RequestParam(value = "t") String target,
      @ApiParam(name = "f", value = "Username of following user.", required = true) @RequestParam(value = "f") String follower) {
    followerService.addFollower(target, follower);
  }

  /**
   * Timeline
   * A user should be able to see a list of the messages posted by all the people they follow, in reverse chronological order.
   */
  @ApiOperation(value = "View all messages posted by followed users")
  @RequestMapping(value = "/getTimeline/{username}", method = RequestMethod.GET, produces = "application/json")
  public List<Message> getTimeline(
      @ApiParam(name = "username", value = "Username of requester.", required = true) @PathVariable(value = "username") String username) {
    return messageService.getAllMessagesOfFollowedUsers(username);
  }

}
