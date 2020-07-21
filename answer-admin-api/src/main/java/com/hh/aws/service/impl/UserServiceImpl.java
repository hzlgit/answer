package com.hh.aws.service.impl;

import com.hh.aws.model.User;
import com.hh.aws.repository.UserRepository;
import com.hh.aws.service.UserService;
import com.hh.aws.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Transactional(readOnly = true)
    public Optional<User> getUserWithAuthorities() {
        return SecurityUtils.getCurrentUsername().flatMap(userRepository::findOneWithAuthoritiesByUserName);
    }
    @Override
    public Page<User> getUserList(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.Direction.ASC, "id");
        return userRepository.findAll(pageable);
    }

    @Override
    public User findUserById(long id) {
        return null;
    }

    @Override
    public void save(User user) {
        if(user.getId()> 0) {
            User saveUser = userRepository.findById(user.getId()).get();
            if(!user.getUserName().isEmpty()) {
                saveUser.setUserName(user.getUserName());
            }
            if(!user.getAvatar().isEmpty()) {
                saveUser.setAvatar(user.getAvatar());
            }
            if(!user.getNickName().isEmpty()) {
                saveUser.setNickName(user.getNickName());
            }
            if(!user.getTrueName().isEmpty()) {
                saveUser.setTrueName(user.getTrueName());
            }
            if(!user.getSex().isEmpty()) {
                saveUser.setSex(user.getSex());
            }
            if(user.getClassId() > 0) {
                saveUser.setClassId(user.getClassId());
            }
            userRepository.save(saveUser);
        } else {
            userRepository.save(user);
        }

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
