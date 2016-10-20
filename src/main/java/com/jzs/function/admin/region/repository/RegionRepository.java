package com.jzs.function.admin.region.repository;

import com.jzs.function.admin.region.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class RegionRepository implements RegionRepositoryI{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Region> list() {
        String sql = "SELECT regionId,regionName FROM jzs_region WHERE deleteFlag = 0";

        try {
            return jdbcTemplate.query(sql, new ListRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

    private class ListRowMapper implements RowMapper {

        @Override
        public Object mapRow(ResultSet resultSet, int i) throws SQLException {
            Region region = new Region();
            region.setRegionId(resultSet.getInt("regionId"));
            region.setRegionName(resultSet.getString("regionName"));

            return region;
        }
    }

    @Override
    public Boolean add(Region region) {
        String sql = "INSERT INTO jzs_region (regionName) VALUES (?)";
        Object[] args = {
                region.getRegionName()
        };

        return jdbcTemplate.update(sql,args) == 1 ? true:false;
    }

    @Override
    public Boolean update(Region region) {
        String sql = "UPDATE jzs_region SET regionName = ? WHERE regionId = ?";
        Object[] args = {
                region.getRegionName(),
                region.getRegionId()
        };

        return jdbcTemplate.update(sql,args) == 1 ? true:false;
    }

    @Override
    public Boolean delete(int regionId) {
        String sql = "UPDATE jzs_region SET deleteFlag = 1 WHERE regionId = ?";
        Object[] args = {
                regionId
        };

        return jdbcTemplate.update(sql,args) == 1 ? true:false;
    }

    @Override
    public Region select(int regionId) {
        String sql = "SELECT regionId,regionName FROM jzs_region WHERE deleteFlag = 0 AND regionId = ?";
        Object[] args = {
                regionId
        };

        try {
            return (Region)jdbcTemplate.queryForObject(sql,args,new ListRowMapper());
        } catch (Exception e) {
            return null;
        }
    }
}
