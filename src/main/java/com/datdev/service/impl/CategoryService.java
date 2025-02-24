package com.datdev.service.impl;

import com.datdev.dao.ICategoryDAO;
import com.datdev.dao.INewDAO;
import com.datdev.dao.impl.CategoryDAO;
import com.datdev.model.CategoryModel;
import com.datdev.model.NewsModel;
import com.datdev.service.ICategoryService;

import javax.inject.Inject;
import java.util.List;

public class CategoryService implements ICategoryService {
//    dependency injection
    @Inject
    private ICategoryDAO categoryDAO;
    @Inject
    private INewDAO iNewDAO;
    public CategoryService(){
        categoryDAO = new CategoryDAO();
    }
    @Override
    public List<CategoryModel> findAll() {
        return categoryDAO.findAll();
    }

    @Override
    public CategoryModel findOne(Long id) {
        NewsModel newsModel = iNewDAO.findOne(id);
        CategoryModel categoryModel = categoryDAO.findOne(newsModel.getCategoryid());
        newsModel.setCategoryCode(categoryModel.getCode());

        return categoryDAO.findOne(id);
    }
}
