package com.datdev.dao;
import java.util.List;

import com.datdev.model.*;
public interface ICategoryDAO  extends   IGenericDAO {
	List<CategoryModel> findAll();
	CategoryModel findOne(Long id);
	CategoryModel findOneByCode(String categoryCode);
}
