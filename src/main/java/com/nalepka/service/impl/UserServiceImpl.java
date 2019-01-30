package com.nalepka.service.impl;

import com.nalepka.model.User;
import com.nalepka.repository.UserDao;
import com.nalepka.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Long addUser(String email, String password) {
        if (userDao.checkIfUserExist(email)) {
            return -1L;
        }

        //User user = userDao.save(new User(email, password));
        //System.out.println(user);
        return userDao.save(new User(email, password)).getId();
    }

    @Override
    public Long validateCredentials(String email, String password) {
        return userDao.validateCredentials(email,password);
    }


}
