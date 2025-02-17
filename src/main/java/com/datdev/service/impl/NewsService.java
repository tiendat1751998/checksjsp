package com.datdev.service.impl;

import com.datdev.dao.INewDAO;
import com.datdev.model.NewsModel;
import com.datdev.paging.Pageble;
import com.datdev.service.INewsService;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.util.List;

public class NewsService implements INewsService {
    @Inject
    private INewDAO iNewDAO;



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


        Long newid =  iNewDAO.save(newsModel);
        return iNewDAO.findOne(newid);
    }

    @Override
    public NewsModel update(NewsModel newsModel) {
        NewsModel oldNews = iNewDAO.findOne(newsModel.getId());
        newsModel.setCreateDate(oldNews.getCreateDate());
        newsModel.setCreateBy(oldNews.getCreateBy());
        newsModel.setModifireBy("");
        newsModel.setModifireDate(new Timestamp(System.currentTimeMillis()));
        iNewDAO.update(newsModel);
        return iNewDAO.findOne(newsModel.getId());
    }

    @Override
    public void delete(long[] ids) {
        for (long id:ids) {
            iNewDAO.delete(id);
        }

    }

    @Override
    public int getTotalItem() {
        return iNewDAO.getTotalItem();
    }

    @Override
    public NewsModel findOne(long id) {
        return iNewDAO.findOne(id);
    }
}
