package com.jzs.function.admin.place.repository;

import com.jzs.function.admin.place.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PlaceRepository implements PlaceRepositoryI{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Place> list() {
        String sql = "SELECT placeId,placeName FROM jzs_place WHERE deleteFlag = 0";

        try {
            return jdbcTemplate.query(sql, new ListRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Boolean add(Place place) {
        String sql = "INSERT INTO jzs_place (placeName) VALUES (?)";
        Object[] args = {
                place.getPlaceName()
        };

        return jdbcTemplate.update(sql,args) == 1 ? true:false;
    }

    @Override
    public Boolean update(Place place) {
        String sql = "UPDATE jzs_place SET placeName = ? WHERE placeId = ?";
        Object[] args = {
                place.getPlaceName(),
                place.getPlaceId()
        };

        return jdbcTemplate.update(sql,args) == 1 ? true:false;
    }

    @Override
    public Boolean delete(int placeId) {
        String sql = "UPDATE jzs_place SET deleteFlag = 1 WHERE placeId = ?";
        Object[] args = {
                placeId
        };

        return jdbcTemplate.update(sql,args) == 1 ? true:false;
    }

    @Override
    public Place select(int placeId) {
        String sql = "SELECT placeId,placeName FROM jzs_place WHERE deleteFlag = 0 AND placeId = ?";
        Object[] args = {
                placeId
        };

        try {
            return (Place)jdbcTemplate.queryForObject(sql,args,new ListRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

    private class ListRowMapper implements RowMapper {

        @Override
        public Object mapRow(ResultSet resultSet, int i) throws SQLException {
            Place place = new Place();
            place.setPlaceId(resultSet.getInt("placeId"));
            place.setPlaceName(resultSet.getString("placeName"));

            return place;
        }
    }
}
