package com.hh.aws.service;

import com.hh.aws.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {

     User findUserByName(String userName);

     List<User> getUserList();

     User findUserById(long id);

     void save(User user);

     void edit(User user);

     void delete(long id);
    /**
     * 查找用户的菜单权限标识集合
     * @param userName
     * @return
     */
    Set<String> findPermissions(String userName);
}
