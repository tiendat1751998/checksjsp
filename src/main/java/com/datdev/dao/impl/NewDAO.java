package com.datdev.dao.impl;

import com.datdev.dao.INewDAO;
import com.datdev.mapper.NewMapper;
import com.datdev.model.NewsModel;
import com.datdev.paging.Pageble;

import java.util.List;

public class NewDAO extends AbstractDAO implements INewDAO {



    @Override
    public List<NewsModel> findAll(Pageble pageble) {
//        String sql = "SELECT * FROM news  LIMIT  ?,?";
        StringBuilder sql = new StringBuilder("SELECT * FROM news ");
        if (pageble.getSorter().getSortName()!=null && pageble.getSorter().getSortBy()!=null) {
            sql.append("ORDER BY " +pageble.getSorter().getSortName()+" "+ pageble.getSorter().getSortBy());
        }
        if (pageble.getOffSet() != null && pageble.getLimit() != null) {
            sql.append("  LIMIT  "+pageble.getOffSet()+ ","+pageble.getLimit()+"");

        }
            return query(sql.toString(),new NewMapper());

    }

    @Override
    public List<NewsModel> findByCategoryId(Long categoryId) {

        String sql = "SELECT * FROM news where categoryid=?";
        return query(sql, new NewMapper(), categoryId);

    }

    @Override
    public Long save(NewsModel newModel) {
//		StringBuilder sql = new StringBuilder("INSERT INTO news (title, content,categoryid)");
////		sql.append(" thumbnail, shortdescription, categoryid, createddate, createdby)");
//		sql.append(" VALUES(?, ?, ?)");
////		return insert(sql.toString(), newsModel.getTitle(), newsModel.getContent(),
////				newsModel.getThumbNail(), newsModel.getShortDescription(), newsModel.getCategoryid(),
////				newsModel.getCreateDate(), newsModel.getCreateBy());
//        return insert(sql.toString(), newsModel.getTitle(), newsModel.getContent(),newsModel.getCategoryid());
        StringBuilder sql = new StringBuilder("INSERT INTO news (title, content,");
        sql.append(" thumbnail, shortdescription, categoryid, createddate, createdby)");
        sql.append(" VALUES(?, ?, ?, ?, ?, ?, ?)");
        return insert(sql.toString(), newModel.getTitle(), newModel.getContent(),
                newModel.getThumbNail(), newModel.getShortDescription(), newModel.getCategoryid(),
                newModel.getCreateDate(), newModel.getCreateBy());
//        ResultSet resultSet = null;
//        Long id = null;
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        try {
//            String sql = "insert into news (title, content, categoryid) VALUES (?,?,?)";
//            connection = getConnection();
//            connection.setAutoCommit(false);
//            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//            preparedStatement.setString(1, newsModel.getTitle());
//            preparedStatement.setString(2, newsModel.getContent());
//            preparedStatement.setLong(3, newsModel.getCategoryid());
////			excute update dung co cac thao tac update insert delete
//            preparedStatement.executeUpdate();
//            resultSet = preparedStatement.getGeneratedKeys();
//            if (resultSet.next()) {
//                id = resultSet.getLong(1);
//            }
////			tat ca du lieu thanh cong thi moi commit
//            connection.commit();
//            return id;
//        } catch (SQLException e) {
//            try {
//                if (connection != null) {
//                    connection.rollback();
//                }
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//
//            return null;
//        } finally {
//            try {
//                if (connection != null) {
//                    connection.close();
//                }
//                if (preparedStatement != null) {
//                    preparedStatement.close();
//                }
//                if (resultSet != null) {
//                    resultSet.close();
//                }
//            } catch (SQLException e) {
//                return null;
//            }
//        }


    }


    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        String sql = "DELETE FROM news WHERE id = ?";
        update(sql,id);

    }

    @Override
    public void update(NewsModel updateNew) {
        // TODO Auto-generated method stub
//        StringBuilder sql = new StringBuilder("UPDATE news SET title = ?, ");
//        sql.append(" content = ?, categoryid = ?");
//        sql.append("  WHERE id = ?");
//        update(sql.toString(), newsModel.getTitle(), newsModel.getContent(),newsModel.getCategoryid() , newsModel.getId());
        StringBuilder sql = new StringBuilder("UPDATE news SET title = ?, thumbnail = ?,");
        sql.append(" shortdescription = ?, content = ?, categoryid = ?,");
        sql.append(" createddate = ?, createdby = ?, modifieddate = ?, modifiedby = ? WHERE id = ?");
        update(sql.toString(), updateNew.getTitle(), updateNew.getThumbNail(), updateNew.getShortDescription(),
                updateNew.getContent(), updateNew.getCategoryid(), updateNew.getCreateDate(),
                updateNew.getCreateBy(), updateNew.getModifireDate(),
                updateNew.getModifireBy(), updateNew.getId());


    }

    @Override
    public NewsModel findOne(Long id) {
        String sql = "SELECT  * FROM  news WHERE id =?";
        List<NewsModel>  news = query(sql, new NewMapper(), id);
        return news.isEmpty() ? null : news.get(0);
    }

    @Override
    public int getTotalItem() {
        String sql = "SELECT count(*) FROM news";
        return count(sql);
    }

}
