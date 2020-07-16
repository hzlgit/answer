package com.hh.aws.service.impl;

import com.hh.aws.model.User;
import com.hh.aws.repository.UserRepository;
import com.hh.aws.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findUserByName(String userName) {
        return userRepository.findUserByName(userName);
    }
    @Override
    public List<User> getUserList() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void edit(User user) {
        userRepository.save(user);
    }

    @Override
    public void delete(long id) {
        User user = new User();
        user.setId(id);
        userRepository.save(user);
    }

    @Override
    public Set<String> findPermissions(String username) {
        Set<String> permissions = new HashSet<>();
        permissions.add("sys:user:view");
        permissions.add("sys:user:add");
        permissions.add("sys:user:edit");
        permissions.add("sys:user:delete");
        return permissions;
    }
}
