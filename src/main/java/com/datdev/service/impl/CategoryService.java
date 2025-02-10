package com.datdev.service.impl;

import com.datdev.dao.ICategoryDAO;
import com.datdev.dao.impl.CategoryDAO;
import com.datdev.model.CategoryModel;
import com.datdev.service.ICategoryService;

import javax.inject.Inject;
import java.util.List;

public class CategoryService implements ICategoryService {
//    dependency injection
    @Inject
    private ICategoryDAO categoryDAO;
    public CategoryService(){
        categoryDAO = new CategoryDAO();
    }
    @Override
    public List<CategoryModel> findAll() {
        return categoryDAO.findAll();
    }
}
