package com.jzs.function.admin.adminUser.repository;

import com.jzs.function.admin.adminUser.AdminManager;
import com.jzs.function.support.utils.RepositoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AdminUserRepository implements AdminUserRepositoryI{

    @Autowired
    private RepositoryUtils<AdminManager> repositoryUtils;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Boolean addUser(AdminManager adminManager) {
        String sql = "INSERT INTO jzs_user (userName,userGender,userPost,userTelOne,mark) VALUES (?,?,?,?,?)";
        Object[] args = {
                adminManager.getUserName(),
                adminManager.getUserGender(),
                adminManager.getUserPost(),
                adminManager.getUserTelOne(),
                adminManager.getMark()
        };

        return jdbcTemplate.update(sql,args) == 1 ? true:false;
    }

    public AdminManager selectMark(String mark) {
        String sql = "SELECT userId FROM jzs_user WHERE mark = ? AND deleteFlag = 0";
        Object[] args = {
                mark
        };

        try {
            return jdbcTemplate.queryForObject(sql,args,new SelectIdRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<AdminManager> selectRoleNames() {
        String sql = "SELECT roleId,roleName FROM jzs_role WHERE deleteFlag = 0";

        try {
            return jdbcTemplate.query(sql, new SelectRoleNamesRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Boolean nameIsUnique(String userLoginName) {
        String sql = "SELECT count(1) FROM jzs_userinfo WHERE userLoginName = ?";
        Object[] args = {
                userLoginName
        };

        return jdbcTemplate.queryForObject(sql, args, Integer.class) > 0 ? false : true;
    }

    private class SelectRoleNamesRowMapper implements RowMapper<AdminManager>{

        @Override
        public AdminManager mapRow(ResultSet resultSet, int i) throws SQLException {
            AdminManager adminManager = new AdminManager();
            adminManager.setRoleId(resultSet.getString("roleId"));
            adminManager.setRoleName(resultSet.getString("roleName"));

            return adminManager;
        }
    }

    private class SelectIdRowMapper implements RowMapper<AdminManager>{

        @Override
        public AdminManager mapRow(ResultSet resultSet, int i) throws SQLException {
            AdminManager adminManager = new AdminManager();
            adminManager.setUserId(resultSet.getInt("userId"));

            return adminManager;
        }
    }
    @Override
    public Boolean addUserInfo(AdminManager adminManager) {
            String sql = "INSERT INTO jzs_userinfo (userId,roleId,userLoginName,userLoginPass) VALUES (?,?,?,?)";
        Object[] args = {
                adminManager.getUserId(),
                adminManager.getRoleId(),
                adminManager.getUserLoginName(),
                adminManager.getUserLoginPass(),
        };

        return jdbcTemplate.update(sql,args) == 1 ? true:false;
    }

    @Override
    public AdminManager select(int userId) {
        String sql = "SELECT I.userId,I.roleId,roleName,userName,userGender,userPost,userTelOne,userLoginName,I.deleteFlag  FROM jzs_userinfo AS I LEFT JOIN jzs_user AS U ON I.userId = U.userId LEFT JOIN jzs_role AS R ON I.roleId = R.roleId WHERE I.userId = ? AND I.deleteFlag = 0 AND U.deleteFlag = 0";
        Object[] args = {
                userId
        };

        try {
            return jdbcTemplate.queryForObject(sql,args, new SelectRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

    private class SelectRowMapper implements RowMapper<AdminManager> {

        @Override
        public AdminManager mapRow(ResultSet resultSet, int i) throws SQLException {
            AdminManager adminManager = new AdminManager();
            adminManager.setUserId(resultSet.getInt("userId"));
            adminManager.setRoleId(resultSet.getString("roleId"));
            adminManager.setRoleName(resultSet.getString("roleName"));
            adminManager.setUserName(resultSet.getString("userName"));
            adminManager.setUserGender(resultSet.getInt("userGender"));
            adminManager.setUserPost(resultSet.getInt("userPost"));
            adminManager.setUserTelOne(resultSet.getString("userTelOne"));
            adminManager.setUserLoginName(resultSet.getString("userLoginName"));
            adminManager.setDeleteFlag(resultSet.getInt("deleteFlag"));

            return adminManager;
        }
    }

    @Override
    public Boolean updateUser(AdminManager adminManager) {
        String sql = "UPDATE jzs_user SET userName = ?,userGender = ?,userPost = ?,userTelOne = ? WHERE userId = ?";
        Object[] args = {
                adminManager.getUserName(),
                adminManager.getUserGender(),
                adminManager.getUserPost(),
                adminManager.getUserTelOne(),
                adminManager.getUserId()
        };

        return jdbcTemplate.update(sql,args) == 1 ? true:false;
    }

    @Override
    public Boolean updateUserInfo(AdminManager adminManager) {
        String sql = "UPDATE jzs_userinfo SET roleId = ?,userLoginName = ? WHERE userId = ?";
        Object[] args = {
                adminManager.getRoleId(),
                adminManager.getUserLoginName(),
                adminManager.getUserId()
        };

        return jdbcTemplate.update(sql,args) == 1 ? true:false;
    }

    @Override
    public Boolean resetPass(AdminManager adminManager) {
        String sql = "UPDATE jzs_userinfo SET userLoginPass = ? WHERE userId = ?";
        Object[] args = {
                adminManager.getUserLoginPass(),
                adminManager.getUserId()
        };

        return jdbcTemplate.update(sql,args) == 1 ? true:false;
    }

    @Override
    public Boolean deleteUser(int userId) {
        String sql = "UPDATE jzs_user SET deleteFlag = 1 WHERE userId = ?";
        Object[] args = {
                userId
        };

        return jdbcTemplate.update(sql,args) == 1 ? true:false;
    }

    @Override
    public Boolean deleteUserInfo(int userId) {
        String sql = "UPDATE jzs_userinfo SET deleteFlag = 1 WHERE userId = ?";
        Object[] args = {
                userId
        };

        return jdbcTemplate.update(sql,args) == 1 ? true:false;
    }

    @Override
    public Boolean removeUser(int userId) {
        String sql = "UPDATE jzs_user SET deleteFlag = 1,removeFlag = 1 WHERE userId = ?";
        Object[] args = {
                userId
        };

        return jdbcTemplate.update(sql,args) == 1 ? true:false;
    }

    @Override
    public Boolean removeUserInfo(int userId) {
        String sql = "UPDATE jzs_userinfo SET deleteFlag = 1,removeFlag = 1 WHERE userId = ?";
        Object[] args = {
                userId
        };

        return jdbcTemplate.update(sql,args) == 1 ? true:false;
    }

    @Override
    public Boolean reuseUser(int userId) {
        String sql = "UPDATE jzs_user SET deleteFlag = 0 WHERE userId = ?";
        Object[] args = {
                userId
        };

        return jdbcTemplate.update(sql,args) == 1 ? true:false;
    }

    @Override
    public Boolean reuseUserInfo(int userId) {
        String sql = "UPDATE jzs_userinfo SET deleteFlag = 0 WHERE userId = ?";
        Object[] args = {
                userId
        };

        return jdbcTemplate.update(sql, args) == 1 ? true : false;
    }

    @Override
    public Page<AdminManager> list(Pageable pageable) {
        String sql = "SELECT I.userId,I.roleId,roleName,userName,userGender,userPost,userTelOne,userLoginName,I.deleteFlag FROM jzs_userinfo AS I LEFT JOIN jzs_user AS U ON I.userId = U.userId LEFT JOIN jzs_role AS R ON I.roleId = R.roleId WHERE I.removeFlag = 0 AND U.removeFlag = 0";
        Object[] args = {

        };

        try {
            return repositoryUtils.select4Page(sql, pageable, args, new SelectRowMapper());
        } catch (Exception e) {
            return null;
        }
    }
}
