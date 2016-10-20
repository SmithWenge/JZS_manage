package com.jzs.function.admin.device.repository;

import com.google.common.base.Optional;
import com.jzs.arc.utils.DateUtils;
import com.jzs.function.admin.device.Device;
import com.jzs.function.support.dictionary.impl.DefaultDictionaryManager;
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

@Repository
public class DeviceRepository implements DeviceRepositoryI{

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private RepositoryUtils repositoryUtils;

    public void insert(Device info) {
        String sql = "INSERT INTO jzs_devicemanagement (deviceState,deviceNumber, deviceFactory, deviceModel, deviceTime, place,track, region, seat, stationNum,stationName, cancelTime,cancelState, rollingTimes,remark) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?)";
        Object[] args = {
                info.getDeviceState(),
                info.getDeviceNumber(),
                info.getDeviceFactory(),
                info.getDeviceModel(),
                info.getDeviceTimeDate(),
                info.getPlaceNum(),
                info.getTrackNum(),
                info.getRegionNum(),
                info.getSeat(),
                info.getStationNum(),
                info.getStationName(),
                info.getCancelTimeDate(),
                info.getCancelStateNum(),
                info.getRollingTimes(),
                info.getRemark()
        };

        jdbcTemplate.update(sql, args);
    }

    public boolean selectDiff(Device info) {
        String sql = "SELECT count(1) FROM jzs_devicemanagement WHERE deviceNumber = ? AND cancelState = ?";
        Object[] args = {
                info.getDeviceNumber(),
                info.getCancelStateNum()
        };

        return jdbcTemplate.queryForObject(sql, args, Integer.class) > 0 ? true : false;
    }

    @Override
    public Page<Device> query4Page(Device device, Pageable pageable) {
        StringBuilder sql = new StringBuilder("select * from (SELECT deviceId,deviceState,deviceNumber, deviceFactory, deviceModel, deviceTime,P.placeId, placeName,T.trackId,trackName,R.regionId,regionName, D.seat, stationNum,stationName, cancelTime, rollingTimes,cancelState,remark,pinTime FROM jzs_devicemanagement AS D LEFT JOIN jzs_place AS P ON D.place = P.placeId LEFT JOIN jzs_track AS T ON D.track = T.trackId LEFT JOIN jzs_region AS R ON D.region = R.regionId LEFT JOIN jzs_faultregister AS F ON D.place = F.place AND F.track = F.track AND D.region = F.region AND D.seat = F.seat WHERE D.deleteFlag = 0");
        List<Object> list = new ArrayList<Object>();

        Optional<Device> optional = Optional.fromNullable(device);
        if (optional.isPresent()) {
            if (device.getPlaceNum() > 0) {
                sql.append(" AND D.place = ?");
                list.add(device.getPlaceNum());
            }

            if (device.getTrackNum() > 0) {
                sql.append(" AND D.track = ?");
                list.add(device.getTrackNum());
            }

            if (device.getRegionNum() > 0) {
                sql.append(" AND D.region = ?");
                list.add(device.getRegionNum());
            }

            if (device.getCancelStateNum() > 0) {
                sql.append(" AND cancelState = ?");
                list.add(device.getCancelStateNum());
            }

            if (device.getDeviceState() > 0) {
                sql.append(" AND deviceState = ?");
                list.add(device.getDeviceState());
            }

            if (device.getStartDate() != null) {
                list.add(device.getStartDate());
                sql.append(" AND DATE_FORMAT(deviceTime,'%Y-%m-%d') >= ?");
            }

            if (device.getStopDate() != null) {
                list.add(device.getStopDate());
                sql.append(" AND DATE_FORMAT(deviceTime,'%Y-%m-%d') <= ?");
            }
        }

        Object[] args = list.toArray();

        sql.append(" ORDER BY pinTime DESC ) as a group by deviceId");
        try {
            return repositoryUtils.select4Page(sql.toString(), pageable, args, new Query4PageRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Device select(int deviceId) {
        String sql ="SELECT deviceId,deviceState,deviceNumber, deviceFactory, deviceModel, deviceTime,P.placeId ,placeName,T.trackId, trackName,R.regionId, regionName,seat, stationNum,stationName, cancelTime, rollingTimes,cancelState,remark FROM jzs_devicemanagement AS D LEFT JOIN jzs_place AS P ON D.place = P.placeId LEFT JOIN jzs_track AS T ON D.track = T.trackId LEFT JOIN jzs_region AS R ON D.region = R.regionId WHERE D.deleteFlag = 0 AND D.deviceId = ? AND P.deleteFlag = 0 AND T.deleteFlag = 0 AND R.deleteFlag = 0";
        Object[] args = {
                deviceId
        };

        return jdbcTemplate.queryForObject(sql,args,new SelectRowMapper());
    }

    @Override
    public Boolean update(Device device) {
        String sql = "UPDATE jzs_devicemanagement SET deviceState = ?,deviceNumber = ?, deviceFactory = ?, deviceModel = ?, deviceTime = ?, place = ?,track = ?, region = ?, seat = ?, stationNum = ?,stationName = ?, cancelTime = ?, rollingTimes = ?,remark = ? WHERE deviceId = ? AND deleteFlag = 0";
        Object[] args = {
                device.getDeviceState(),
                device.getDeviceNumber(),
                device.getDeviceFactory(),
                device.getDeviceModel(),
                device.getDeviceTimeDate(),
                device.getPlaceNum(),
                device.getTrackNum(),
                device.getRegionNum(),
                device.getSeat(),
                device.getStationNum(),
                device.getStationName(),
                device.getCancelTimeDate(),
                device.getRollingTimes(),
                device.getRemark(),
                device.getDeviceId(),
        };

        return jdbcTemplate.update(sql,args) == 1 ? true:false;
    }

    @Override
    public Boolean add(Device info) {
        String sql = "INSERT INTO jzs_devicemanagement (deviceState,deviceNumber, deviceFactory, deviceModel, deviceTime, place,track, region, seat, stationNum,stationName, cancelTime,cancelState, rollingTimes,remark) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        Object[] args = {
                info.getDeviceState(),
                info.getDeviceNumber(),
                info.getDeviceFactory(),
                info.getDeviceModel(),
                info.getDeviceTimeDate(),
                info.getPlaceNum(),
                info.getTrackNum(),
                info.getRegionNum(),
                info.getSeat(),
                info.getStationNum(),
                info.getStationName(),
                info.getCancelTimeDate(),
                info.getCancelStateNum(),
                info.getRollingTimes(),
                info.getRemark() };

        return jdbcTemplate.update(sql, args) == 1 ? true:false;
    }

    @Override
    public Boolean cancle(Device device) {
        String sql = "UPDATE jzs_devicemanagement SET cancelTime = ?,cancelState = 2 WHERE deviceId = ? AND deleteFlag = 0";
        Object[] args = {
                device.getCancelTime(),
                device.getDeviceId()
        };

        return jdbcTemplate.update(sql, args) == 1 ? true:false;
    }

    @Override
    public boolean selectAddDiff(Device device) {
        String sql = "SELECT count(1) FROM jzs_devicemanagement WHERE deviceNumber = ? AND cancelState = 1";
        Object[] args = { device.getDeviceNumber() };

        return jdbcTemplate.queryForObject(sql, args, Integer.class) > 0 ? true : false;
    }

    @Override
    public List<Device> selectWarningDeviceId() {
        String sql = "select deviceId from (select * from (SELECT deviceId,deviceState,deviceNumber, deviceFactory, deviceModel, deviceTime,P.placeId, placeName,T.trackId,trackName,R.regionId,regionName, D.seat, stationNum,stationName, cancelTime, rollingTimes,cancelState,remark,pinTime FROM jzs_devicemanagement AS D LEFT JOIN jzs_place AS P ON D.place = P.placeId LEFT JOIN jzs_track AS T ON D.track = T.trackId LEFT JOIN jzs_region AS R ON D.region = R.regionId LEFT JOIN jzs_faultregister AS F ON D.place = F.place AND F.track = F.track AND D.region = F.region AND D.seat = F.seat WHERE D.deleteFlag = 0 AND D.cancelState = 1 AND D.deviceState != 3 ORDER BY pinTime DESC ) as a group by deviceId) AS b where pinTime < DATE_SUB(CURDATE(), INTERVAL 1 YEAR)";
        return jdbcTemplate.query(sql,new SelectWarningDeviceIdRowMapper());
    }

    private class SelectWarningDeviceIdRowMapper implements  RowMapper<Device> {

        @Override
        public Device mapRow(ResultSet resultSet, int i) throws SQLException {
            Device device = new Device();
            device.setDeviceId(resultSet.getInt("deviceId"));

            return device;
        }
    }

    @Override
    public Boolean updateWaringDevice(int deviceId) {
        String sql = "UPDATE jzs_devicemanagement SET deviceState = 2 WHERE deviceId = ? AND deleteFlag = 0";
        Object[] args = {
                deviceId
        };
        return jdbcTemplate.update(sql,args) > 0 ? true:false;
    }

    @Override
    public int selectNumOfWarning() {
        String sql = "SELECT count(1) FROM jzs_devicemanagement WHERE deviceState = 2 AND cancelState = 1 AND deleteFlag = 0";

        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    private class Query4PageRowMapper implements RowMapper<Device> {

        @Override
        public Device mapRow(ResultSet resultSet, int i) throws SQLException {
            Device device = new Device();
            device.setDeviceId(resultSet.getInt("deviceId"));
            device.setDeviceState(resultSet.getInt("deviceState"));
            device.setDeviceNumber(resultSet.getString("deviceNumber"));
            device.setDeviceFactory(resultSet.getString("deviceFactory"));
            device.setDeviceModel(resultSet.getString("deviceModel"));
            device.setDeviceTimeDate(resultSet.getDate("deviceTime"));
            device.setPlace(resultSet.getString("placeName"));
            device.setTrack(resultSet.getString("trackName"));
            device.setRegion(resultSet.getString("regionName"));
            device.setSeat(resultSet.getString("seat"));
            device.setStationNum(resultSet.getString("stationNum"));
            device.setStationName(resultSet.getString("stationName"));
            device.setCancelTimeDate(resultSet.getDate("cancelTime"));
            device.setRollingTimes(resultSet.getString("rollingTimes"));
            device.setCancelStateNum(resultSet.getInt("cancelState"));
            device.setRemark(resultSet.getString("remark"));
            device.setPlaceNum(resultSet.getInt("placeId"));
            device.setTrackNum(resultSet.getInt("trackId"));
            device.setRegionNum(resultSet.getInt("regionId"));
            device.setLatestTime(resultSet.getString("pinTime"));

            return device;
        }
    }

    private class SelectRowMapper implements RowMapper<Device> {

        @Override
        public Device mapRow(ResultSet resultSet, int i) throws SQLException {
            Device device = new Device();
            device.setDeviceId(resultSet.getInt("deviceId"));
            device.setDeviceState(resultSet.getInt("deviceState"));
            device.setDeviceNumber(resultSet.getString("deviceNumber"));
            device.setDeviceFactory(resultSet.getString("deviceFactory"));
            device.setDeviceModel(resultSet.getString("deviceModel"));
            device.setDeviceTimeDate(resultSet.getDate("deviceTime"));
            device.setPlace(resultSet.getString("placeName"));
            device.setTrack(resultSet.getString("trackName"));
            device.setRegion(resultSet.getString("regionName"));
            device.setSeat(resultSet.getString("seat"));
            device.setStationNum(resultSet.getString("stationNum"));
            device.setStationName(resultSet.getString("stationName"));
            device.setCancelTimeDate(resultSet.getDate("cancelTime"));
            device.setRollingTimes(resultSet.getString("rollingTimes"));
            device.setCancelStateNum(resultSet.getInt("cancelState"));
            device.setRemark(resultSet.getString("remark"));
            device.setPlaceNum(resultSet.getInt("placeId"));
            device.setTrackNum(resultSet.getInt("trackId"));
            device.setRegionNum(resultSet.getInt("regionId"));

            return device;
        }
    }
}
