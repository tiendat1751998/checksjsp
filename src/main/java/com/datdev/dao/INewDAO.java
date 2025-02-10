package com.datdev.dao;

import com.datdev.model.NewsModel;
import com.datdev.paging.Pageble;

import java.util.List;

public interface INewDAO extends  IGenericDAO {
	List<NewsModel> findAll(Pageble pageble);
	List<NewsModel> findByCategoryId(Long categoryId);
	 Long save(NewsModel newsModel);
	 void  delete(Long id);
	 void update(NewsModel newsModel);
	 NewsModel findOne(Long id);
	 int getTotalItem();

}
