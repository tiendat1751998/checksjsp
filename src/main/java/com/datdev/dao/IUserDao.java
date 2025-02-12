package com.datdev.dao;

import com.datdev.model.NewsModel;
import com.datdev.model.UserModel;
import com.datdev.paging.Pageble;

import java.util.List;

public interface IUserDao extends IGenericDAO<UserModel> {
    UserModel findByUserNameAndPasswordAndStatus(String  userName, String password,  Integer status);

}
