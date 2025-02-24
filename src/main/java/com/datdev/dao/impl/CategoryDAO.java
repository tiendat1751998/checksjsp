package com.datdev.dao.impl;

import com.datdev.dao.ICategoryDAO;
import com.datdev.mapper.CategoryMapper;
import com.datdev.mapper.NewMapper;
import com.datdev.model.CategoryModel;
import com.datdev.model.NewsModel;

import java.util.List;


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

	@Override
	public CategoryModel findOne(Long id) {
		String sql = "SELECT  * FROM  category WHERE id =?";
		List<CategoryModel>  news = query(sql, new CategoryMapper(), id);
		return news.isEmpty() ? null : news.get(0);
	}

}
