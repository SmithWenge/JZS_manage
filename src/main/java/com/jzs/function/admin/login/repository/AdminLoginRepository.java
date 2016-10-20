package com.jzs.function.admin.login.repository;

import com.jzs.function.admin.login.AdminUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class AdminLoginRepository implements AdminLoginRepositoryI {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public AdminUser selectUnique(AdminUser adminUser) {
        String sql = "SELECT userName, userLoginName, roleId, userLoginPass FROM jzs_userinfo AS I LEFT JOIN jzs_user AS U ON I.userId = U.userId WHERE U.deleteFlag = 0 AND I.deleteFlag = 0 AND I.userLoginName = ? AND I.userLoginPass = ?";
        Object[] args = {
                adminUser.getUserLoginName(),
                adminUser.getUserLoginPass()
        };

        AdminUser returnUser = null;

        try {
            returnUser = jdbcTemplate.queryForObject(sql, args, new SelectUniqueRowMapper());
        } catch (Exception e) {
            return null;
        }

        return returnUser != null ? returnUser : null;
    }

    public boolean resetPassword(AdminUser adminUser) {
        String sql = "UPDATE jzs_userinfo SET userLoginPass = ? WHERE userLoginName = ? AND userLoginPass = ? AND deleteFlag = 0";
        Object[] args = {
                adminUser.getAdminLoginPassNew(),
                adminUser.getUserLoginName(),
                adminUser.getUserLoginPass()
        };

        return jdbcTemplate.update(sql, args) == 1 ? true : false;
    }

    private class SelectUniqueRowMapper implements RowMapper<AdminUser> {

        @Override
        public AdminUser mapRow(ResultSet resultSet, int i) throws SQLException {
            AdminUser adminUser = new AdminUser();

            adminUser.setUserLoginName(resultSet.getString("userLoginName"));
            adminUser.setUserName(resultSet.getString("userName"));
            adminUser.setRoleId(resultSet.getString("roleId"));
            adminUser.setUserLoginPass(resultSet.getString("userLoginPass"));

            return adminUser;
        }
    }

    private class SelectUniqueEmailRowMapper implements RowMapper<AdminUser> {

        @Override
        public AdminUser mapRow(ResultSet resultSet, int i) throws SQLException {
            AdminUser adminUser = new AdminUser();

            adminUser.setUserLoginName(resultSet.getString("userLoginName"));
            adminUser.setUserName(resultSet.getString("userName"));
            adminUser.setUserEmail(resultSet.getString("userEmail"));

            return adminUser;
        }
    }
}
