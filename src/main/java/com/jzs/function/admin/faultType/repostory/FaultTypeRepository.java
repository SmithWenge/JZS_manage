package com.jzs.function.admin.faultType.repostory;

import com.jzs.function.admin.faultType.FaultType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class FaultTypeRepository implements FaultTypeRepositoryI {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<FaultType> list() {
        String sql = "SELECT faultTypeId,faultTypeName,faultSugestion FROM jzs_faulttype WHERE deleteFlag = 0";

        try {
            return jdbcTemplate.query(sql, new ListRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Boolean add(FaultType faultType) {
        String sql = "INSERT INTO jzs_faulttype (faultTypeName,faultSugestion) VALUES (?,?)";
        Object[] args = {
                faultType.getFaultTypeName(),
                faultType.getFaultSugestion()
        };

        return jdbcTemplate.update(sql,args) == 1 ? true:false;
    }

    @Override
    public FaultType select(int faultTypeId) {
        String sql = "SELECT faultTypeId,faultTypeName,faultSugestion FROM jzs_faulttype WHERE deleteFlag = 0 AND faultTypeId = ?";
        Object[] args = {
                faultTypeId
        };

        try {
            return jdbcTemplate.queryForObject(sql,args, new ListRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Boolean update(FaultType faultType) {
        String sql = "UPDATE jzs_faulttype SET faultTypeName = ?,faultSugestion = ? WHERE faultTypeId = ?";
        Object[] args = {
                faultType.getFaultTypeName(),
                faultType.getFaultSugestion(),
                faultType.getFaultTypeId()
        };

        return jdbcTemplate.update(sql,args) == 1 ? true:false;
    }

    @Override
    public Boolean delete(int faultTypeId) {
        String sql = "UPDATE jzs_faulttype SET deleteFlag = 1 WHERE faultTypeId = ?";
        Object[] args = {
                faultTypeId
        };

        return jdbcTemplate.update(sql,args) == 1 ? true:false;
    }

    private class ListRowMapper implements RowMapper<FaultType> {

        @Override
        public FaultType mapRow(ResultSet resultSet, int i) throws SQLException {
            FaultType faultType = new FaultType();
            faultType.setFaultTypeId(resultSet.getInt("faultTypeId"));
            faultType.setFaultTypeName(resultSet.getString("faultTypeName"));
            faultType.setFaultSugestion(resultSet.getString("faultSugestion"));

            return faultType;
        }
    }
}
