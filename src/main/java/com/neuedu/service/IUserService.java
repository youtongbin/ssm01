package com.neuedu.service;

import com.neuedu.pojo.User;

import java.util.List;

public interface IUserService {
    List<User> getLists();
    User getOne(Integer id);
    int insert(User user);
    int delete(Integer id);
    int update(User user);
}