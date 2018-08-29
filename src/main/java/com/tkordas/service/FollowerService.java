package com.tkordas.service;

import com.tkordas.model.Follower;
import com.tkordas.model.FollowerRepo;
import com.tkordas.model.User;
import com.tkordas.webApi.AlreadyFollowingException;
import com.tkordas.webApi.UserTargetsHimselfException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class FollowerService {

  private final FollowerRepo followerRepo;
  private final UserService userService;

  @Autowired
  public FollowerService(FollowerRepo followerRepo, UserService userService) {
    this.followerRepo = followerRepo;
    this.userService = userService;
  }

  public void addFollower(String target, String follower) {
    checkIfFollowingHimself(target, follower);
    User targetUser = userService.validateAndGetUser(target);
    User followerUser = userService.validateAndGetUser(follower);
    checkIfAlreadyFollowing(targetUser.getId(), followerUser.getId());

    Follower f = new Follower();
    f.setTarget(targetUser.getId());
    f.setFollower(followerUser.getId());
    followerRepo.save(f);
  }

  public Set<Long> getFollowedUsersIdByFollowingUserId(Long userId) {
    return followerRepo.getFollowedUsersIdByFollowingUserId(userId);
  }

  private void checkIfFollowingHimself(String target, String follower) {
    if (target.equals(follower)) {
      throw new UserTargetsHimselfException();
    }
  }

  private void checkIfAlreadyFollowing(Long targetId, Long followerId) {
    Follower followerCheck = followerRepo.getFollowerByTargetAndFollowed(targetId, followerId);
    if (followerCheck != null) {
      throw new AlreadyFollowingException();
    }
  }

}
