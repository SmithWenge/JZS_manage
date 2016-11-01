package com.jzs.function.admin.track.repository;

import com.jzs.function.admin.track.Track;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TrackRepository implements TrackRepositoryI{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Track> list() {
        String sql = "SELECT trackId,T.placeId,placeName,trackName,trackState,trackLength,carNumEle,carNumFou,useable FROM jzs_track AS T LEFT JOIN jzs_place as P ON T.placeId = P.placeId WHERE T.deleteFlag = 0 AND P.deleteFlag = 0 ORDER BY trackId ASC";

        try {
            return jdbcTemplate.query(sql, new ListRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Boolean add(Track track) {
        String sql = "INSERT INTO jzs_track (placeId,trackLength,trackName,carNumEle,carNumFou,useable) VALUES (?,?,?,?,?,?,?)";
        Object[] args = {
                track.getPlaceId(),
                track.getTrackLength(),
                track.getTrackName(),
                track.getCarNumEle(),
                track.getCarNumFou(),
                track.getUseable()
        };

        return jdbcTemplate.update(sql,args) == 1 ? true:false;
    }

    @Override
    public Boolean update(Track track) {
        String sql = "UPDATE jzs_track SET placeId = ?,trackLength = ?,trackName = ?,carNumEle = ?,carNumFou = ?,useable = ? WHERE trackId = ?";
        Object[] args = {
                track.getPlaceId(),
                track.getTrackLength(),
                track.getTrackName(),
                track.getCarNumEle(),
                track.getCarNumFou(),
                track.getUseable(),
                track.getTrackId()
        };

        return jdbcTemplate.update(sql,args) == 1 ? true:false;
    }

    @Override
    public Boolean delete(int trackId) {
        String sql = "UPDATE jzs_track SET deleteFlag = 1 WHERE trackId = ?";
        Object[] args = {
                trackId
        };

        return jdbcTemplate.update(sql,args) == 1 ? true:false;
    }

    @Override
    public Track select(int trackId) {
        String sql = "SELECT trackId,T.placeId,placeName,trackName,trackState,trackLength,carNumEle,carNumFou,useable FROM jzs_track AS T LEFT JOIN jzs_place as P ON T.placeId = P.placeId WHERE T.deleteFlag = 0 AND P.deleteFlag = 0 AND trackId = ?";
        Object[] args = {
                trackId
        };

        try {
            return (Track)jdbcTemplate.queryForObject(sql,args, new ListRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int selectNumOfFault(int trackId) {
        String sql = "SELECT COUNT(1) FROM jzs_faultregister WHERE faultState = 2 AND track = ? AND deleteFlag = 0";
        Object[] args = {
                trackId
        };

        return jdbcTemplate.queryForObject(sql,args,Integer.class);
    }

    @Override
    public List<Track> selectJsonByFauId(int placeId) {
        String sql = "SELECT trackId,T.placeId,placeName,trackName,trackState,trackLength,carNumEle,carNumFou,useable FROM jzs_track AS T LEFT JOIN jzs_place as P ON T.placeId = P.placeId WHERE T.deleteFlag = 0 AND P.deleteFlag = 0 AND T.placeId = ? ORDER BY trackId ASC";
        Object[] args = {
                placeId
        };

        try {
            return jdbcTemplate.query(sql,args, new ListRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

    private class ListRowMapper implements RowMapper{

        @Override
        public Object mapRow(ResultSet resultSet, int i) throws SQLException {
            Track track = new Track();
            track.setTrackId(resultSet.getInt("trackId"));
            track.setPlaceId(resultSet.getInt("placeId"));
            track.setPlaceName(resultSet.getString("placeName"));
            track.setTrackName(resultSet.getString("trackName"));
            track.setTrackLength(resultSet.getString("trackLength"));
            track.setCarNumEle(resultSet.getInt("carNumEle"));
            track.setCarNumFou(resultSet.getInt("carNumFou"));
            track.setUseable(resultSet.getString("useable"));
            track.setTrackState(resultSet.getInt("trackState"));

            return track;
        }
    }
}
