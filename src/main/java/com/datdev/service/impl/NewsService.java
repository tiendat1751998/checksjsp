package com.datdev.service.impl;

import com.datdev.dao.ICategoryDAO;
import com.datdev.dao.INewDAO;
import com.datdev.model.CategoryModel;
import com.datdev.model.NewsModel;
import com.datdev.paging.Pageble;
import com.datdev.service.INewsService;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.util.List;

public class NewsService implements INewsService {
    @Inject
    private INewDAO iNewDAO;

    @Inject
    private ICategoryDAO iCategoryDAO;


    @Override
    public List<NewsModel> findAll(Pageble pageble) {
        return iNewDAO.findAll(pageble);
    }

    @Override
    public List<NewsModel> findByCategoryId(Long categoryId) {
        return iNewDAO.findByCategoryId(categoryId);
    }

    @Override
    public NewsModel save(NewsModel newsModel) {
        newsModel.setCreateDate(new Timestamp(System.currentTimeMillis()));


        Long newid = iNewDAO.save(newsModel);
        return iNewDAO.findOne(newid);
    }

    @Override
    public NewsModel update(NewsModel newsModel) {
        if (newsModel == null || newsModel.getId() == null) {
            throw new IllegalArgumentException("Error: NewsModel or ID cannot be null.");
        }

        NewsModel oldNews = iNewDAO.findOne(newsModel.getId());
        if (oldNews == null) {
            throw new IllegalArgumentException("Error: News item with ID " + newsModel.getId() + " not found.");
        }

        newsModel.setCreateDate(oldNews.getCreateDate());
        newsModel.setCreateBy(oldNews.getCreateBy());
        newsModel.setModifireBy(""); // Consider setting to the current user instead of an empty string
        newsModel.setModifireDate(new Timestamp(System.currentTimeMillis()));

        iNewDAO.update(newsModel);
        return iNewDAO.findOne(newsModel.getId());
    }

    @Override
    public void delete(long[] ids) {
        for (long id : ids) {
            iNewDAO.delete(id);
        }

    }

    @Override
    public int getTotalItem() {
        return iNewDAO.getTotalItem();
    }

    @Override
    public NewsModel findOne(Long id) {
        NewsModel newsModel = iNewDAO.findOne(id);
        CategoryModel categoryModel = iCategoryDAO.findOne(newsModel.getCategoryid());
        newsModel.setCategoryCode(categoryModel.getCode());

        return newsModel;
    }
}
