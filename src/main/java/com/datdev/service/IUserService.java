package com.datdev.service;

import com.datdev.model.UserModel;

public interface IUserService {
    UserModel findByUserNameAndPasswordAndStatus(String userName, String password, Integer status);
}
