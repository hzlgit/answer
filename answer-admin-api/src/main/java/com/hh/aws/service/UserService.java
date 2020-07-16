package com.hh.aws.service;

import com.hh.aws.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public Optional<User> getUserWithAuthorities();
    public List<User> getUserList();

    public User findUserById(long id);

    public void save(User user);

    public void edit(User user);

    public void delete(long id);
}
