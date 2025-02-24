package com.datdev.service;

import com.datdev.model.CategoryModel;
import com.datdev.model.NewsModel;

import java.util.List;

public interface ICategoryService {
    List<CategoryModel> findAll();
    CategoryModel findOne (Long  id);
}
