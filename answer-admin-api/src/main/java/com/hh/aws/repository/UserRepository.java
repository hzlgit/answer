package com.hh.aws.repository;

import com.hh.aws.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

   @EntityGraph(attributePaths = "authorities")
   Optional<User> findOneWithAuthoritiesByUserName(String username);

   @Modifying(clearAutomatically=true)
   @Transactional
   @Query("update User set avatar=:avatar where id=:id")
   int setAvatar(@Param("avatar") String avatar, @Param("id") Long id);

}
