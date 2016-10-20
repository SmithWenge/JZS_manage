package com.jzs.function.admin.role.repository;

import com.jzs.function.admin.role.Role;
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
import java.util.Objects;

@Repository
public class RoleRepository implements RoleRepositoryI{

    @Autowired
    private RepositoryUtils<Role> repositoryUtils;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Boolean addRole(Role role) {
        String sql = "INSERT INTO jzs_role (roleId,roleName) VALUES (?,?)";
        Object[] args = {
                role.getRoleId(),
                role.getRoleName()
        };

        return jdbcTemplate.update(sql,args) == 1 ? true:false;
    }

    @Override
    public int addAuthorityRole(Object[] args) {
        String sql = "INSERT INTO jzs_authorityrole (authorityroleId,authorityTwoId,roleId) VALUES (?,?,?)";

        return jdbcTemplate.update(sql,args);
    }

    @Override
    public int addAuthorityRoleFunction(Object[] args) {
        String sql = "INSERT INTO jzs_authorityrolefunction (authorityroleId,functionId,roleId) VALUES (?,?,?)";

        return jdbcTemplate.update(sql,args);
    }

    private class SelectRoleRowMapper implements RowMapper<Role>{

        @Override
        public Role mapRow(ResultSet resultSet, int i) throws SQLException {
            Role role = new Role();
            role.setRoleId(resultSet.getString("roleId"));
            role.setRoleName(resultSet.getString("roleName"));

            return role;
        }
    }

    @Override
    public Boolean delete(String roleId) {
        String sql = "UPDATE jzs_role SET deleteFlag = 1 WHERE roleId = ?";
        Object[] args = {
                roleId
        };

        return jdbcTemplate.update(sql,args) == 1 ? true:false;
    }

    @Override
    public Page<Role> list(Pageable pageable) {
        String sql = "SELECT roleId,roleName FROM jzs_role WHERE deleteFlag = 0 ORDER BY time DESC";
        Object[] args = {

        };

        try {
            return repositoryUtils.select4Page(sql, pageable, args, new SelectRoleRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Role> selectFunctions(int authorityTwoId) {
        String sql = "SELECT functionId,functionName FROM jzs_function WHERE authorityTwoId = ? AND deleteFlag = 0";
        Object[] args = {
                authorityTwoId
        };
        try {
            return jdbcTemplate.query(sql,args,new SelectFunctionsRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Role selectRole(String roleId) {
        String sql = "SELECT roleId,roleName FROM jzs_role WHERE roleId = ? AND deleteFlag = 0";
        Object[] args = {
                roleId
        };
        try {
            return jdbcTemplate.queryForObject(sql,args,new SelectRoleRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Role> selectRoleFunction(Role role) {
        String sql = "SELECT F.functionId,F.functionName FROM jzs_function AS F LEFT JOIN jzs_authorityrolefunction AS A ON F.functionId = A.functionId LEFT JOIN jzs_authorityrole AS B ON A.authorityroleId = B.authorityroleId WHERE B.authorityTwoId = ? AND A.roleId = ?";
        Object[] args = {
                role.getAuthorityTwoId(),
                role.getRoleId()
        };

        try {
        return jdbcTemplate.query(sql,args,new SelectFunctionsRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Role> selectRoleFunctionId(Role role) {
        String sql = "SELECT functionId FROM jzs_authorityrolefunction WHERE roleId = ?";
        Object[] args = {
                role.getRoleId()
        };

        try {
            return jdbcTemplate.query(sql,args,new SelectRoleFunctionIdRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Role> selectRoleAuthorityTwoId(Role role) {
        String sql = "SELECT authorityTwoId FROM jzs_authorityrole WHERE roleId = ?";
        Object[] args = {
                role.getRoleId()
        };

        try {
            return jdbcTemplate.query(sql,args,new SelectRoleAuthorityTwoIdRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

    private class SelectRoleAuthorityTwoIdRowMapper implements RowMapper {

        @Override
        public Object mapRow(ResultSet resultSet, int i) throws SQLException {
            Role role = new Role();
            role.setAuthorityTwoId(resultSet.getInt("authorityTwoId"));

            return role;
        }
    }

    @Override
    public Boolean deleteRole(Role role) {
        String sql = "DELETE FROM jzs_authorityrolefunction WHERE roleId = ? AND functionId = ?";
        Object[] args = {
                role.getRoleId(),
                role.getFunctionId()
        };

        return jdbcTemplate.update(sql,args) == 1 ? true:false;
    }

    @Override
    public Boolean deleteAuthorityTwo(Role role) {
        String sql = "DELETE FROM jzs_authorityrole WHERE roleId = ? AND authorityTwoId = ?";
        Object[] args = {
                role.getRoleId(),
                role.getAuthorityTwoId()
        };

        return jdbcTemplate.update(sql,args) == 1 ? true:false;
    }

    @Override
    public Boolean updateRole(Role role) {
        String sql = "UPDATE jzs_role SET roleName = ? WHERE roleId = ?";
        Object[] args = {
                role.getRoleName(),
                role.getRoleId()
        };

        return jdbcTemplate.update(sql,args) == 1 ? true:false;
    }

    @Override
    public Boolean authorityTwoIsExit(Role role) {
        String sql = "SELECT COUNT(1) FROM jzs_authorityrole WHERE roleId = ? AND authorityTwoId = 6 AND deleteFlag = 0 OR roleId = ? AND authorityTwoId = 30 AND deleteFlag = 0;";
        Object[] args = {
                role.getRoleId(),
                role.getRoleId()
        };

        return jdbcTemplate.queryForObject(sql,args,Integer.class) == 2 ? true:false;
    }

    private class SelectRoleFunctionIdRowMapper implements RowMapper {

        @Override
        public Object mapRow(ResultSet resultSet, int i) throws SQLException {
            Role role = new Role();
            role.setFunctionId(resultSet.getInt("functionId"));

            return role;
        }
    }

    private class SelectFunctionsRowMapper implements RowMapper{

        @Override
        public Object mapRow(ResultSet resultSet, int i) throws SQLException {
            Role role = new Role();
            role.setFunctionIdS(String.valueOf(resultSet.getInt("functionId")));
            role.setFunctionName(resultSet.getString("functionName"));

            return role;
        }
    }
}
