package com.hh.aws.repository;

import com.hh.aws.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Long> {

    User findUserByName(String userName);
    User findById(long id);
}
