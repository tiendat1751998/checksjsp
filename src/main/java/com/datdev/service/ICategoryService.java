package com.datdev.service;

import com.datdev.model.CategoryModel;

import java.util.List;

public interface ICategoryService {
    List<CategoryModel> findAll();

    CategoryModel findOne(Long id);

    CategoryModel findOneByCategoryCode(Long categoryCode);
}
