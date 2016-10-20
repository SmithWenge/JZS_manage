package com.jzs.function.admin.worker.repository;

import com.jzs.function.admin.worker.Worker;
import com.jzs.function.support.utils.RepositoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class WorkerRespository implements WorkerRespositoryI{

    @Autowired
    private RepositoryUtils<Worker> repositoryUtils;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Boolean add(Worker worker) {
        String sql = "INSERT INTO jzs_user (userName,userGender,userPost,userTelOne,userTelTwo) VALUES (?,?,?,?,?);";
        Object[] args = {
            worker.getUserName(),
            worker.getUserGender(),
            worker.getUserPost(),
            worker.getUserTelOne(),
            worker.getUserTelTwo()
        };

        return jdbcTemplate.update(sql,args) == 1 ? true:false;
    }

    @Override
    public Worker select(int userId) {
        String sql = "SELECT userId,userName,userGender,userPost,userTelOne,userTelTwo FROM jzs_user WHERE userId = ? AND deleteFlag = 0";
        Object[] args = {
               userId
        };

        try {
            return jdbcTemplate.queryForObject(sql, args, new SelectRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

    private class SelectRowMapper implements RowMapper<Worker> {

        @Override
        public Worker mapRow(ResultSet resultSet, int i) throws SQLException {
            Worker worker = new Worker();
            worker.setUserId(resultSet.getInt("userId"));
            worker.setUserName(resultSet.getString("userName"));
            worker.setUserGender(resultSet.getInt("userGender"));
            worker.setUserPost(resultSet.getInt("userPost"));
            worker.setUserTelOne(resultSet.getString("userTelOne"));
            worker.setUserTelTwo(resultSet.getString("userTelTwo"));

            return worker;
        }
    }

    @Override
    public Boolean update(Worker worker) {
        String sql = "UPDATE jzs_user SET userName = ?,userGender = ?,userPost = ?,userTelOne = ?,userTelTwo =? WHERE userId = ?";
        Object[] args = {
                worker.getUserName(),
                worker.getUserGender(),
                worker.getUserPost(),
                worker.getUserTelOne(),
                worker.getUserTelTwo(),
                worker.getUserId()
        };

        return jdbcTemplate.update(sql,args) == 1 ? true:false;
    }

    @Override
    public Boolean delete(int userId) {
        String sql = "UPDATE jzs_user SET deleteFlag = 1 WHERE userId = ?";
        Object[] args = {
                userId
        };

        return jdbcTemplate.update(sql,args) == 1 ? true:false;
    }

    @Override
    public Page<Worker> list( Pageable pageable) {
        String sql = "SELECT userId,userName,userGender,userPost,userTelOne,userTelTwo FROM jzs_user WHERE deleteFlag = 0 AND userPost = 5 OR deleteFlag = 0 AND userPost = 1";
        Object[] args = {

        };

        try {
            return repositoryUtils.select4Page(sql, pageable, args, new SelectRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

}
