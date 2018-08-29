package com.tkordas.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface FollowerRepo extends CrudRepository<Follower, Long> {

  @Query("select f from Follower f where f.target=:targetId and f.follower=:followerId")
  Follower getFollowerByTargetAndFollowed(@Param("targetId") Long targetId, @Param("followerId") Long followerId);

  @Query("select f.target from Follower f where f.follower=:followerId")
  Set<Long> getFollowedUsersIdByFollowingUserId(@Param("followerId") Long followerId);

}
