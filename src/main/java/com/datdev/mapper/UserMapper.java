package com.datdev.mapper;

import com.datdev.model.CategoryModel;
import com.datdev.model.RoleModel;
import com.datdev.model.UserModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements  RowMapper<UserModel>{
    @Override
    public UserModel mapRow(ResultSet rs) {

        try {
            UserModel userModel = new UserModel();
            userModel.setId(rs.getLong("id"));
            userModel.setUserName(rs.getString("username"));
            userModel.setFullName(rs.getString("fullname"));
            userModel.setPassWord(rs.getString("password"));
            userModel.setStatus(rs.getInt("status"));
            try{
                RoleModel roleModel = new RoleModel();
                roleModel.setCode(rs.getString("code"));
                roleModel.setName(rs.getString("name"));
                userModel.setRoleModel(roleModel);
            }catch (Exception e )
            {
                System.out.println(e.getMessage());
            }


            return userModel;
        } catch (SQLException e) {
            return null;
        }
    }
}
