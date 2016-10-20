package com.jzs.function.admin.seat.repository;

import com.jzs.function.admin.seat.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SeatRepository implements SeatRepositoryI{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Seat> selectPlaces() {
        String sql = "SELECT placeId,placeName FROM jzs_place WHERE deleteFlag = 0";
        try {
            return jdbcTemplate.query(sql,new SelectPlacesRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Seat> selectTracks() {
        String sql = "SELECT trackId,trackName FROM jzs_track WHERE deleteFlag = 0";
        try {
            return jdbcTemplate.query(sql,new SelectTracksRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Seat> selectRegions() {
        String sql = "SELECT regionId,regionName FROM jzs_region WHERE deleteFlag = 0";
        try {
            return jdbcTemplate.query(sql,new SelectRegionsRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Seat selecPlaceId(Seat seat) {
        String sql = "SELECT placeId FROM jzs_place WHERE deleteFlag = 0 AND placeName = ?";
        Object[] args = {
                seat.getPlaceName()
        };

        return (Seat)jdbcTemplate.queryForObject(sql,args,new SelecPlaceIdRowMapper());
    }

    @Override
    public Seat selecTrackId(Seat seat) {
        String sql = "SELECT trackId FROM jzs_track WHERE deleteFlag = 0 AND trackName = ?";
        Object[] args = {
                seat.getTrackName()
        };

        return (Seat)jdbcTemplate.queryForObject(sql,args,new SelecTrackIdRowMapper());
    }

    @Override
    public Seat selecRegionId(Seat seat) {
        String sql = "SELECT regionId FROM jzs_region WHERE deleteFlag = 0 AND regionName = ?";
        Object[] args = {
                seat.getRegionName()
        };

        return (Seat)jdbcTemplate.queryForObject(sql,args,new SelecRegionIdRowMapper());
    }

    private class SelecPlaceIdRowMapper implements RowMapper {

        @Override
        public Object mapRow(ResultSet resultSet, int i) throws SQLException {
            Seat seat = new Seat();
            seat.setPlaceId(resultSet.getInt("placeId"));

            return seat;
        }
    }

    private  class SelecTrackIdRowMapper implements RowMapper {

        @Override
        public Object mapRow(ResultSet resultSet, int i) throws SQLException {
            Seat seat = new Seat();
            seat.setTrackId(resultSet.getInt("trackId"));

            return seat;
        }
    }

    private class SelecRegionIdRowMapper implements  RowMapper {

        @Override
        public Object mapRow(ResultSet resultSet, int i) throws SQLException {
            Seat seat = new Seat();
            seat.setRegionId(resultSet.getInt("regionId"));

            return seat;
        }
    }

    private class SelectRegionsRowMapper implements RowMapper {

        @Override
        public Object mapRow(ResultSet resultSet, int i) throws SQLException {
            Seat seat = new Seat();
            seat.setRegionId(resultSet.getInt("regionId"));
            seat.setRegionName(resultSet.getString("regionName"));

            return seat;
        }
    }

    private class SelectTracksRowMapper implements RowMapper {

        @Override
        public Object mapRow(ResultSet resultSet, int i) throws SQLException {
            Seat seat = new Seat();
            seat.setTrackId(resultSet.getInt("trackId"));
            seat.setTrackName(resultSet.getString("trackName"));

            return seat;
        }
    }

    private class SelectPlacesRowMapper implements RowMapper {

        @Override
        public Object mapRow(ResultSet resultSet, int i) throws SQLException {
            Seat seat = new Seat();
            seat.setPlaceId(resultSet.getInt("placeId"));
            seat.setPlaceName(resultSet.getString("placeName"));

            return seat;
        }
    }
}
