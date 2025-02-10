package com.datdev.mapper;

import com.datdev.model.NewsModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NewMapper implements  RowMapper<NewsModel>{
    @Override
    public NewsModel mapRow(ResultSet resultSet) {
        try {
            NewsModel news = new NewsModel();
            news.setId(resultSet.getLong("id"));
            news.setTitle(resultSet.getString("title"));
            news.setContent(resultSet.getString("content"));
            news.setCategoryid(resultSet.getLong("categoryid"));
            news.setThumbNail(resultSet.getString("thumbnail"));
            news.setShortDescription(resultSet.getString("shortdescription"));
            news.setCreateDate(resultSet.getTimestamp("createddate"));
            news.setCreateBy(resultSet.getString("createdby"));
            if (resultSet.getTimestamp("modifieddate") != null) {
                news.setModifireDate(resultSet.getTimestamp("modifieddate"));
            }
            if (resultSet.getString("modifiedby") != null) {
                news.setModifireBy(resultSet.getString("modifiedby"));
            }
            return news;
        } catch (SQLException e) {
            return null;
        }
    }
}
