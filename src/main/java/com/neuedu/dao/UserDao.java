package com.neuedu.dao;

import com.neuedu.pojo.User;

import java.util.List;

public interface UserDao {
    List<User> getLists();
    User getOne(Integer id);
    int insert(User user);
    int delete(Integer id);
    int update(User user);
}
