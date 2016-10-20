package com.jzs.function.admin.workerInfo.repository;

import com.jzs.function.admin.workerInfo.WorkerInfo;
import com.jzs.function.support.utils.RepositoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class WorkerInfoRepository implements WorkerInfoRepositoryI{

    @Autowired
    private RepositoryUtils<WorkerInfo> repositoryUtils;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<WorkerInfo> selectFangHu() {
        String sql = "SELECT userId,userName FROM jzs_user WHERE userPost = 1 AND deleteFlag = 0";

        try {
            return jdbcTemplate.query(sql,new WorkerInfoRowMapper());
        }catch (Exception e) {
            return new ArrayList<WorkerInfo>();
        }
    }

    private class WorkerInfoRowMapper implements RowMapper<WorkerInfo> {

        @Override
        public WorkerInfo mapRow(ResultSet resultSet, int i) throws SQLException {
            WorkerInfo workerInfo = new WorkerInfo();
            workerInfo.setUserId(resultSet.getInt("userId"));
            workerInfo.setUserName(resultSet.getString("userName"));

            return workerInfo;
        }
    }

    @Override
    public List<WorkerInfo> selectZhiBan() {
        String sql = "SELECT userId,userName FROM jzs_user WHERE userPost = 2 AND deleteFlag = 0";

        try {
            return jdbcTemplate.query(sql,new WorkerInfoRowMapper());
        }catch (Exception e) {
            return new ArrayList<WorkerInfo>();
        }
    }

    @Override
    public List<WorkerInfo> selectDiaoChe() {
        String sql = "SELECT userId,userName FROM jzs_user WHERE userPost = 3 AND deleteFlag = 0";

        try {
            return jdbcTemplate.query(sql,new WorkerInfoRowMapper());
        }catch (Exception e) {
            return new ArrayList<WorkerInfo>();
        }
    }

    @Override
    public List<WorkerInfo> selectWorker() {
        String sql = "SELECT userId,userName FROM jzs_user WHERE userPost = 5 AND deleteFlag = 0 or userPost = 1 AND deleteFlag = 0";

        try {
            return jdbcTemplate.query(sql,new WorkerInfoRowMapper());
        }catch (Exception e) {
            return new ArrayList<WorkerInfo>();
        }
    }

    @Override
    public int add(Object[] args) {
        String sql = "INSERT INTO jzs_attendance (attendanceDate,attendanceTime,userId) VALUES (?,?,?)";

        return jdbcTemplate.update(sql,args);
    }

    @Override
    public int temAdd(Object[] args) {
        String sql = "INSERT INTO jzs_temattendance (userId) VALUES (?)";

        return jdbcTemplate.update(sql,args);
    }

    @Override
    public Boolean temDelete() {
        String sql = "DELETE FROM jzs_temattendance";

        return jdbcTemplate.update(sql) >= 0;
    }

    @Override
    public List<WorkerInfo> list(WorkerInfo workerInfo) {
        String sql = "SELECT A.userId,userName,userGender,userPost,userTelOne,A.attendanceDate,A.attendanceTime FROM jzs_attendance AS A LEFT JOIN jzs_user AS U ON A.userId = U.userId WHERE A.deleteFlag = 0 AND A.attendanceDate = ? ORDER BY attendanceTime ASC";
        Object[] args = {
            workerInfo.getAttTime()
        };

        try {
            return jdbcTemplate.query(sql,args,new ListRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<WorkerInfo> listForPost(WorkerInfo workerInfo) {
        String sql = "SELECT A.userId,userName,userGender,userPost,userTelOne,A.attendanceDate,A.attendanceTime FROM jzs_attendance AS A LEFT JOIN jzs_user AS U ON A.userId = U.userId WHERE A.deleteFlag = 0 AND A.attendanceDate = ? ORDER BY attendanceTime ASC";
        Object[] args = {
                workerInfo.getAttendanceDate()
        };

        try {
            return jdbcTemplate.query(sql,args,new ListRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Boolean selectCount() {
        String sql = "SELECT COUNT(1) FROM jzs_temattendance WHERE deleteFlag = 0 ";

        try {
            return jdbcTemplate.queryForObject(sql,Integer.class) > 0 ? false : true;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<WorkerInfo> listForHome() {
        String sql = "SELECT A.userId,userName,userGender,userPost,userTelOne FROM jzs_temattendance AS A LEFT JOIN jzs_user AS U ON A.userId = U.userId WHERE A.deleteFlag = 0";

        try {
            return jdbcTemplate.query(sql,new ListForHomeRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private class ListRowMapper implements RowMapper<WorkerInfo>{

        @Override
        public WorkerInfo mapRow(ResultSet resultSet, int i) throws SQLException {
            WorkerInfo workerInfo = new WorkerInfo();
            workerInfo.setUserId(resultSet.getInt("userId"));
            workerInfo.setUserName(resultSet.getString("userName"));
            workerInfo.setUserGender(resultSet.getInt("userGender"));
            workerInfo.setUserPost(resultSet.getInt("userPost"));
            workerInfo.setUserTelOne(resultSet.getString("userTelOne"));
            workerInfo.setAttendanceDate(resultSet.getDate("attendanceDate"));
            workerInfo.setAttendanceTime(resultSet.getString("attendanceTime"));

            return workerInfo;
        }
    }

    private class ListForHomeRowMapper implements RowMapper<WorkerInfo> {

        @Override
        public WorkerInfo mapRow(ResultSet resultSet, int i) throws SQLException {
            WorkerInfo workerInfo = new WorkerInfo();
            workerInfo.setUserId(resultSet.getInt("userId"));
            workerInfo.setUserName(resultSet.getString("userName"));
            workerInfo.setUserGender(resultSet.getInt("userGender"));
            workerInfo.setUserPost(resultSet.getInt("userPost"));
            workerInfo.setUserTelOne(resultSet.getString("userTelOne"));

            return workerInfo;
        }
    }
}
