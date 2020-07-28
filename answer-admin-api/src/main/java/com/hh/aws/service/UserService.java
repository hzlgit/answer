package com.hh.aws.service;

import com.hh.aws.model.PageData;
import com.hh.aws.model.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public interface UserService {

    Optional<User> getUserWithAuthorities();

    PageData<User> getUserList(Integer page, Integer size, Map<String, String> param, String sortName, String sortType);

     User findUserById(long id);

     void save(User user);

     void edit(User user);

     void delete(long id);
}
