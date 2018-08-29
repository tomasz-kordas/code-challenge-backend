package com.tkordas.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface MessageRepo extends CrudRepository<Message, Long> {

  @Query("Select m from Message m join m.user u where u.id=:userId order by m.createDate desc")
  List<Message> getPostsByUserId(@Param("userId") Long userId);

  @Query( "select m from Message m join m.user u where u.id in :followed order by m.createDate desc" )
  List<Message> getPostsOfFollowedUsers(@Param("followed") Set<Long> followed);

}
