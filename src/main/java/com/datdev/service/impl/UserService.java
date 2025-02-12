package com.datdev.service.impl;

import com.datdev.dao.IUserDao;
import com.datdev.model.UserModel;
import com.datdev.service.IUserService;

import javax.inject.Inject;

public class UserService implements IUserService {
    @Inject
    private IUserDao iUserDao;
    @Override
    public UserModel findByUserNameAndPasswordAndStatus(String userName, String password, Integer status) {
        return iUserDao.findByUserNameAndPasswordAndStatus(userName,password,status);
    }
}
