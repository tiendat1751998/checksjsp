package com.datdev.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.datdev.dao.ICategoryDAO;
import com.datdev.mapper.CategoryMapper;
import com.datdev.model.CategoryModel;


/**
 *
 */
public class CategoryDAO  extends AbstractDAO implements ICategoryDAO {


	@Override
	public List<CategoryModel> findAll() {
		// TODO Auto-generated method stub

		String sql = "SELECT  * FROM CATEGORY ";
		return query(sql, new CategoryMapper());
	}

}
