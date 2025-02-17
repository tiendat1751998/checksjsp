package com.datdev.service;

import com.datdev.model.NewsModel;
import com.datdev.paging.Pageble;

import java.util.List;

public interface INewsService {
    List<NewsModel> findAll(Pageble pageble);
    List<NewsModel> findByCategoryId(Long categoryId);
    NewsModel save ( NewsModel newsModel);
    NewsModel update( NewsModel newsModel);
    void delete(long[] ids);
    int getTotalItem();
    NewsModel findOne ( long  id);
}
