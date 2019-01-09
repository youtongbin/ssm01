package com.neuedu.service;

import com.neuedu.dao.UserDao;
import com.neuedu.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    @Resource
    private UserDao userDao;
    @Override
    public List<User> getLists() {
        return userDao.getLists();
    }

    @Override
    public User getOne(Integer id) {
        return userDao.getOneById(id);
    }

    @Override
    public User getOne(String username) {
        return userDao.getOneByName(username);
    }

    @Override
    public int insert(User user) {
        return userDao.insert(user);
    }

    @Override
    public int delete(Integer id) {
        return userDao.delete(id);
    }

    @Override
    public int update(User user) {
        return userDao.update(user);
    }
}
