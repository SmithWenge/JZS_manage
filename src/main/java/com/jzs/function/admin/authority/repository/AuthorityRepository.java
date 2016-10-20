package com.jzs.function.admin.authority.repository;

import com.jzs.function.admin.authority.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AuthorityRepository implements AuthorityRepositoryI{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Authority> list(Authority authority) {
        String sql = "SELECT authorityTwoId FROM jzs_authorityrole WHERE roleId = ?";
        Object[] args = {
                authority.getRoleId()
        };

        return jdbcTemplate.query(sql,args,new ListRowMapper());
    }

    @Override
    public List<Authority> listFunction(Authority authority) {
        String sql = "SELECT functionId FROM jzs_authorityrolefunction WHERE roleId = ?";
        Object[] args = {
                authority.getRoleId()
        };

        return jdbcTemplate.query(sql,args,new ListFunctionRowMapper());
    }

    private class ListFunctionRowMapper implements  RowMapper {

        @Override
        public Object mapRow(ResultSet resultSet, int i) throws SQLException {
            Authority authority = new Authority();
            authority.setFunctionId(resultSet.getInt("functionId"));

            return authority;
        }
    }

    private class ListRowMapper implements RowMapper {

        @Override
        public Object mapRow(ResultSet resultSet, int i) throws SQLException {
            Authority authority = new Authority();
            authority.setAuthorityTwoId(resultSet.getInt("authorityTwoId"));

            return authority;
        }
    }
}
