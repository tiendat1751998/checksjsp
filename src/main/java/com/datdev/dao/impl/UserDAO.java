package com.datdev.dao.impl;

import com.datdev.dao.IUserDao;
import com.datdev.mapper.NewMapper;
import com.datdev.mapper.UserMapper;
import com.datdev.model.NewsModel;
import com.datdev.model.UserModel;

import java.util.List;

public class UserDAO extends  AbstractDAO<UserModel> implements IUserDao {
    @Override
    public UserModel findByUserNameAndPasswordAndStatus(String userName, String password, Integer status) {
        StringBuilder sql = new StringBuilder("SELECT  * FROM  user AS u ");
        sql.append(" INNER JOIN role AS r ON r.id = u.id  ");
        sql.append(" WHERE username =? and password =? and status =?");
        List<UserModel> userModels = query(sql.toString(), new UserMapper(), userName,password,status);

         return userModels.isEmpty() ? null : userModels.get(0);
    }
}
