package com.neuedu.dao;

import com.neuedu.pojo.User;

import java.util.List;

public interface UserDao {
    List<User> getLists(User user);
    User getOneById(Integer id);
    User getOneByName(String username);
    int insert(User user);
    int delete(Integer id);
    int update(User user);
}
