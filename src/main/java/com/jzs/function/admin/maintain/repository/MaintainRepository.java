package com.jzs.function.admin.maintain.repository;

import com.google.common.base.Optional;
import com.jzs.function.admin.maintain.Maintain;
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
public class MaintainRepository implements MaintainRepositoryI{

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private RepositoryUtils repositoryUtils;

    @Override
    public Maintain selectProtectApprovePeople() {
        String sql = "SELECT userName FROM jzs_temattendance AS A LEFT JOIN jzs_user AS U ON A.userId = U.userId WHERE A.deleteFlag = 0 AND userPost = 3";

        try {
            return (Maintain)jdbcTemplate.queryForObject(sql,new SelectPeopleRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Maintain> selectProtectPeople() {
        String sql = "SELECT userName FROM jzs_temattendance AS A LEFT JOIN jzs_user AS U ON A.userId = U.userId WHERE A.deleteFlag = 0 AND A.attendanceDate = ? AND userPost = 1 OR A.deleteFlag = 0 AND userPost = 5 ";

        try {
            return jdbcTemplate.query(sql, new SelectPeopleRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Maintain selectProtectRequestPeople() {
        String sql = "SELECT userName FROM jzs_temattendance AS A LEFT JOIN jzs_user AS U ON A.userId = U.userId WHERE A.deleteFlag = 0 AND userPost = 2";

        try {
            return (Maintain)jdbcTemplate.queryForObject(sql, new SelectPeopleRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Maintain> selectWorker() {
        String sql = "SELECT userName FROM jzs_temattendance AS A LEFT JOIN jzs_user AS U ON A.userId = U.userId WHERE A.deleteFlag = 0 AND userPost = 1 OR A.deleteFlag = 0 AND userPost = 5 ";

        try {
            return jdbcTemplate.query(sql, new SelectPeopleRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Boolean protectAdd(Maintain maintain) {
        String sql = "INSERT INTO jzs_protectregister (protectApproveTime,protectDay,protectApprovePeople,protectPeople,protectRequestPeople,track,place,protectRemark) VALUES (?,?,?,?,?,?,?,?)";
        Object[] args = {
                maintain.getProtectApproveTime(),
                maintain.getProtectDay(),
                maintain.getProtectApprovePeople(),
                maintain.getProtectPeople(),
                maintain.getProtectRequestPeople(),
                maintain.getTrack(),
                maintain.getPlace(),
                maintain.getProtectRemark()
        };

        return jdbcTemplate.update(sql,args) == 1 ? true:false;
    }

    @Override
    public Page<Maintain> list(Maintain maintain, Pageable pageable) {
        StringBuilder sql = new StringBuilder("SELECT protectId,protectState,placeName,trackName,protectApprovePeople,protectPeople,protectRequestPeople,protectApproveTime,protectStopTime,protectRemark FROM jzs_protectregister AS P LEFT JOIN jzs_place AS L ON P.place = L.placeId LEFT JOIN jzs_track AS T ON P.track = T.trackId WHERE P.deleteFlag = 0 AND L.deleteFlag = 0 AND T.deleteFlag = 0");
        List<Object> list = new ArrayList<Object>();
        Optional<Maintain> optional = Optional.fromNullable(maintain);
        if (optional.isPresent()) {
            if (maintain.getProtectState() > 0) {
                sql.append(" AND protectState = ?");
                list.add(maintain.getProtectState());
            }
            if (maintain.getPlace() > 0) {
                sql.append(" AND place = ?");
                list.add(maintain.getPlace());
            }
            if (maintain.getTrack() > 0) {
                sql.append(" AND track = ?");
                list.add(maintain.getTrack());
            }
            if (maintain.getProtectDay() != null) {
                sql.append(" AND protectDay = ?");
                list.add(maintain.getProtectDay());
            }
        }

        Object[] args = list.toArray();

        sql.append(" ORDER BY protectApproveTime DESC");
        try {
            return repositoryUtils.select4Page(sql.toString(), pageable, args, new listRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Boolean selectDiff(Maintain maintain) {
        String sql = "SELECT count(1) FROM jzs_protectregister WHERE track = ? AND protectState = 1";
        Object[] args = {
                maintain.getTrack()
        };

        return jdbcTemplate.queryForObject(sql, args, Integer.class) > 0 ? true : false;
    }

    @Override
    public Boolean update(Maintain maintain) {
        String sql = "UPDATE jzs_protectregister SET protectState = 2,protectStopTime = ? WHERE protectId = ? AND deleteFlag = 0";
        Object[] args = {
                maintain.getProtectStopTime(),
                maintain.getProtectIdNum()
        };

        return jdbcTemplate.update(sql, args) == 1 ? true:false;
    }

    @Override
    public Boolean updateMaintain(Maintain maintain) {
        String sql = "UPDATE jzs_faultregister SET faultState = 1,faultDealPeople = ?,pinTime = ?,faultReason = ?,dealResult = ? WHERE faultRegisterId = ?";
        Object[] args = {
                maintain.getFaultDealPeople(),
                maintain.getPinTime(),
                maintain.getFaultReason(),
                maintain.getDealResult(),
                maintain.getFaultRegisterId()
        };

        return jdbcTemplate.update(sql,args) == 1 ? true:false;
    }

    @Override
    public Boolean maintainAdd(Maintain maintain) {
        String sql = "INSERT INTO jzs_faultregister (faultState,place,track,region,seat,faultFindPeople,faultRegisterPeople,faultDealPeople,registerTime,faultType,faultDay) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        Object[] args = {
                maintain.getFaultState(),
                maintain.getPlace(),
                maintain.getTrack(),
                maintain.getRegion(),
                maintain.getSeat(),
                maintain.getFaultFindPeople(),
                maintain.getFaultRegisterPeople(),
                maintain.getFaultDealPeople(),
                maintain.getRegisterTime(),
                maintain.getFaultType(),
                maintain.getNewDate()
        };
        return jdbcTemplate.update(sql,args) == 1 ? true:false;
    }

    @Override
    public Boolean updateDevice(Maintain maintain,int state) {
        String sql = "UPDATE jzs_devicemanagement SET deviceState = ?,latestTime = ? WHERE track = ? AND region = ? AND seat = ? AND cancelState = 1 AND deleteFlag = 0";
        Object[] args = {
                state,
                maintain.getLatestTime(),
                maintain.getTrack(),
                maintain.getRegion(),
                maintain.getSeat()
        };
        return jdbcTemplate.update(sql,args) > 0 ? true:false;
    }

    @Override
    public Maintain selectPartMaintain(Maintain maintain) {
        String sql = "SELECT place,track,protectRequestPeople FROM jzs_protectregister WHERE protectId = ? AND deleteFlag = 0";
        Object[] args = {
                maintain.getProtectIdNum()
        };

        try {
            return (Maintain)jdbcTemplate.queryForObject(sql, args, new SelectPartMaintainRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Page<Maintain> listMaintain(Maintain maintain, Pageable pageable) {
        StringBuilder sql = new StringBuilder("SELECT faultRegisterId,F.track,faultState,faultType,P.placeName,T.trackName,R.regionName,seat,faultFindPeople,faultRegisterPeople,faultDealPeople,registerTime,pinTime,faultReason,dealResult FROM jzs_faultregister AS F LEFT JOIN jzs_track AS T ON F.track = T.trackId LEFT JOIN jzs_region AS R ON F.region = R.regionId LEFT JOIN jzs_place AS P ON F.place = P.placeId WHERE F.deleteFlag = 0");
        List<Object> list = new ArrayList<Object>();
        Optional<Maintain> optional = Optional.fromNullable(maintain);
        if (optional.isPresent()) {
            if (maintain.getFaultState() > 0) {
                sql.append(" AND faultState = ?");
                list.add(maintain.getFaultState());
            }
            if (maintain.getPlace() > 0) {
                sql.append(" AND place = ?");
                list.add(maintain.getPlace());
            }
            if (maintain.getTrack() > 0) {
                sql.append(" AND track = ?");
                list.add(maintain.getTrack());
            }
            if (maintain.getRegion() > 0) {
                sql.append(" AND region = ?");
                list.add(maintain.getRegion());
            }
            if (maintain.getSeat() != null && maintain.getSeat().length() != 0) {
                sql.append(" AND seat = ?");
                list.add(maintain.getSeat());
            }
            if (maintain.getFaultType() > 0) {
                sql.append(" AND faultType = ?");
                list.add(maintain.getFaultType());
            }
            if (maintain.getFaultDay() != null) {
                sql.append(" AND faultDay = ?");
                list.add(maintain.getFaultDay());
            }
        }

        Object[] args = list.toArray();

        sql.append(" ORDER BY registerTime DESC");
        try {
            return repositoryUtils.select4Page(sql.toString(), pageable, args, new ListMaintainRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Boolean selectExitMaintain(Maintain maintain) {
        String sql = "SELECT count(1) FROM jzs_faultregister WHERE track = ? AND seat = ? AND region = ? AND faultState = 2";
        Object[] args = {
                maintain.getTrack(),
                maintain.getSeat(),
                maintain.getRegion()
        };

        return jdbcTemplate.queryForObject(sql, args, Integer.class) > 0 ? false : true;
    }

    @Override
    public Boolean selectExitCanclePro(Maintain maintain) {
        String sql = "SELECT count(1) FROM jzs_protectregister WHERE track = ? AND protectState = 1 AND deleteFlag = 0";
        Object[] args = {
                maintain.getTrack()
        };

        return jdbcTemplate.queryForObject(sql, args, Integer.class) > 0 ? false : true;
    }

    @Override
    public Page<Maintain> listAll(Maintain maintain, Pageable pageable) {
        StringBuilder sql = new StringBuilder("SELECT F.faultRegisterId,F.place,F.track,region,seat,protectState,faultState,faultType,protectDay,faultDay FROM jzs_faultregister AS F LEFT JOIN jzs_protectregister AS P ON F.protectId = P.protectId WHERE F.deleteFlag = 0");
        List<Object> list = new ArrayList<Object>();
        Optional<Maintain> optional = Optional.fromNullable(maintain);
        if (optional.isPresent()) {
            if (maintain.getFaultState() > 0) {
                sql.append(" AND faultState = ?");
                list.add(maintain.getFaultState());
            }
            if (maintain.getProtectState() > 0) {
                sql.append(" AND protectState = ?");
                list.add(maintain.getProtectState());
            }
            if (maintain.getFaultType() > 0) {
                sql.append(" AND faultType = ?");
                list.add(maintain.getFaultType());
            }
            if (maintain.getPlace() > 0) {
                sql.append(" AND F.place = ?");
                list.add(maintain.getPlace());
            }
            if (maintain.getTrack() > 0) {
                sql.append(" AND F.track = ?");
                list.add(maintain.getTrack());
            }
            if (maintain.getRegion() > 0) {
                sql.append(" AND region = ?");
                list.add(maintain.getRegion());
            }
            if (maintain.getSeat() != null && maintain.getSeat().length() != 0) {
                sql.append(" AND seat = ?");
                list.add(maintain.getSeat());
            }
            if (maintain.getProtectDay() != null) {
                sql.append(" AND protectDay = ?");
                list.add(maintain.getProtectDay());
            }
            if (maintain.getFaultDay() != null) {
                sql.append(" AND faultDay = ?");
                list.add(maintain.getFaultDay());
            }
        }

        Object[] args = list.toArray();

        sql.append(" ORDER BY F.registerTime DESC");
        try {
            return repositoryUtils.select4Page(sql.toString(), pageable, args, new ListAllRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Maintain selectAll(int faultRegisterId) {
        String sql = "SELECT F.place,F.track,region,seat,protectState,faultState,faultType,protectApprovePeople,protectPeople,protectRequestPeople,faultFindPeople,faultDealPeople,protectDay,faultDay,protectApproveTime,registerTime,protectStopTime,pinTime,protectRemark FROM jzs_faultregister AS F LEFT JOIN jzs_protectregister AS P ON F.protectId = P.protectId WHERE F.deleteFlag = 0 AND F.faultRegisterId = ?";
        Object[] args = {
                faultRegisterId
        };

        try {
            return (Maintain)jdbcTemplate.queryForObject(sql,args,new SelectAllRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int getInspectionState() {
        String sql = "SELECT COUNT(1) FROM jzs_place WHERE placeState = 2 AND deleteFlag = 0";

        return jdbcTemplate.queryForObject(sql,Integer.class);
    }

    @Override
    public Boolean insterInsp(Maintain maintain) {
        String sql = "INSERT INTO jzs_inspection (inspectionType,inspectionState,placeId,inspectionDate,diaochePeo,zhibanPeo,inspectionTime) VALUES (?,?,?,?,?,?,?)";
        Object[] args = {
                maintain.getInspectionType(),
                maintain.getInspectionState(),
                maintain.getPlace(),
                maintain.getNewDate(),
                maintain.getDiaochePeo(),
                maintain.getZhibanPeo(),
                maintain.getInspectionTime()
        };

        return jdbcTemplate.update(sql,args) == 1 ? true:false;
    }

    @Override
    public Boolean updatePlacePro(int placeId) {
        String sql = "UPDATE jzs_track SET trackState = 2 WHERE placeId = ? AND deleteFlag = 0";
        Object[] args = {
                placeId
        };

        return jdbcTemplate.update(sql,args) > 0 ? true:false;
    }

    @Override
    public Boolean updatePlaceIns(int placeId) {
        String sql = "UPDATE jzs_place SET placeState = 2 WHERE placeId = ? AND deleteFlag = 0";
        Object[] args = {
                placeId
        };

        return jdbcTemplate.update(sql,args) > 0 ? true:false;
    }

    @Override
    public List<Maintain> selectInspTracks(int placeId) {
        String sql = "SELECT T.placeId,trackId,trackState,placeName,trackName,trackState FROM jzs_track AS T LEFT JOIN jzs_place as P ON T.placeId = P.placeId WHERE T.placeId = ? AND T.deleteFlag = 0 ORDER BY trackId ASC";
        Object[] args = {
                placeId
        };

        try {
            return jdbcTemplate.query(sql,args, new SelectInspTracksRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Maintain> selectStopInspTracks(int placeId) {
        String sql = "SELECT T.placeId,trackId,trackState,placeName,trackName,trackState FROM jzs_track AS T LEFT JOIN jzs_place as P ON T.placeId = P.placeId WHERE T.placeId = ? AND T.trackState = 2 AND T.deleteFlag = 0 ORDER BY trackId ASC";
        Object[] args = {
                placeId
        };

        try {
            return jdbcTemplate.query(sql,args, new SelectInspTracksRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int selectFauNumOfOneTra(int placeId,int trackId) {
        String sql = "SELECT COUNT(1) FROM jzs_faultregister WHERE place = ? AND track = ? AND faultState = 2 AND deleteFlag = 0";
        Object[] args = {
                placeId,
                trackId
        };


        return jdbcTemplate.queryForObject(sql,args, Integer.class);
    }

    @Override
    public Maintain getInspectionId() {
        String sql = "SELECT inspectionId,placeId FROM jzs_inspection WHERE inspectionState = 1 AND deleteFlag = 0";

        return (Maintain)jdbcTemplate.queryForObject(sql,new GetInspectionIdRowMapper());
    }

    @Override
    public Boolean updateInspStop(Maintain maintain) {
        String sql = "UPDATE jzs_inspection SET inspectionState = 2,inspectionStopTime = ? WHERE inspectionId = ? AND deleteFlag = 0";
        Object[] args = {
                maintain.getInspectionStopTime(),
                maintain.getInspectionId()
        };

        return jdbcTemplate.update(sql,args) > 0 ? true:false;
    }

    @Override
    public Boolean updatePlaceInsStop(Maintain maintain) {
        String sql = "UPDATE jzs_place SET placeState = 1 WHERE placeId = ? AND deleteFlag = 0";
        Object[] args = {
                maintain.getPlace()
        };

        return jdbcTemplate.update(sql,args) > 0 ? true:true;
    }

    @Override
    public Boolean updateTracksStop(Maintain maintain) {
        String sql = "UPDATE jzs_track SET trackState = 1 WHERE placeId = ? AND deleteFlag = 0";
        Object[] args = {
                maintain.getPlace()
        };

        return jdbcTemplate.update(sql,args) > 0 ? true:false;
    }

    @Override
    public Boolean updateTrack(Maintain maintain,int state) {
        String sql = "UPDATE jzs_track SET trackState = ? WHERE trackId = ? AND deleteFlag = 0";
        Object[] args = {
                state,
                maintain.getTrack()
        };

        return jdbcTemplate.update(sql,args) > 0 ? true:false;
    }

    @Override
    public Maintain selectProtectId(Maintain maintain) {
        String sql = "SELECT protectId FROM jzs_protectregister WHERE track = ? AND protectState = 1 AND deleteFlag = 0";
        Object[] args = {
                maintain.getTrack()
        };

        try {
            return jdbcTemplate.queryForObject(sql, args, new SelectCountTracksRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Maintain selectInspectionById(int inspectionId) {
        String sql = "SELECT inspectionDate,inspectionTime,diaochePeo,zhibanPeo,placeId FROM jzs_inspection WHERE inspectionId = ? AND deleteFlag = 0";
        Object[] args = {
                inspectionId
        };

        try {
            return jdbcTemplate.queryForObject(sql, args, new SelectInspectionByIdRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Boolean inspectionAddPro(Maintain maintain) {
        String sql = "INSERT INTO jzs_protectregister (protectState,protectApproveTime,protectStopTime,protectDay,protectApprovePeople,protectPeople,protectRequestPeople,track,place,protectRemark) VALUES (?,?,?,?,?,?,?,?,?,?)";
        Object[] args = {
                maintain.getProtectState(),
                maintain.getProtectApproveTime(),
                maintain.getProtectStopTime(),
                maintain.getNewDate(),
                maintain.getProtectApprovePeople(),
                maintain.getProtectPeople(),
                maintain.getProtectRequestPeople(),
                maintain.getTrack(),
                maintain.getPlace(),
                maintain.getProtectRemark()
        };

        return jdbcTemplate.update(sql,args) == 1 ? true:false;
    }

    @Override
    public int selectPlaceId(int trackId) {
        String sql = "SELECT placeId FROM jzs_track WHERE trackId = ? AND deleteFlag = 0";
        Object[] args = {
                trackId
        };

        return jdbcTemplate.queryForObject(sql,args, Integer.class);
    }

    @Override
    public Boolean updatePorTrack(int trackId) {
        String sql = "UPDATE jzs_track SET trackState = 2 WHERE trackId = ? AND deleteFlag = 0";
        Object[] args = {
                trackId
        };

        return jdbcTemplate.update(sql,args) > 0 ? true:false;
    }

    @Override
    public int seletFaultId(Maintain maintain) {
        String sql = "SELECT faultRegisterId FROM jzs_faultregister WHERE track = ? AND region  = ? AND seat = ? AND faultState = 2 AND deleteFlag = 0";
        Object[] args = {
                maintain.getTrack(),
                maintain.getRegion(),
                maintain.getSeat()
        };

        return jdbcTemplate.queryForObject(sql,args, Integer.class);
    }

    @Override
    public List<Maintain> listForMaintain() {
        String sql = "SELECT faultRegisterId,F.track,faultState,faultType,P.placeName,T.trackName,R.regionName,seat,faultFindPeople,faultRegisterPeople,faultDealPeople,registerTime,pinTime,faultReason,dealResult FROM jzs_faultregister AS F LEFT JOIN jzs_track AS T ON F.track = T.trackId LEFT JOIN jzs_region AS R ON F.region = R.regionId LEFT JOIN jzs_place AS P ON F.place = P.placeId WHERE F.deleteFlag = 0 AND F.faultState = 2 ORDER BY registerTime DESC";

        try {
            return jdbcTemplate.query(sql, new ListMaintainRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int selectTrackState(int trackId) {
        String sql = "SELECT trackState FROM jzs_track WHERE trackId = ? AND deleteFlag = 0";
        Object[] args = {
                trackId
        };

        return jdbcTemplate.queryForObject(sql,args, Integer.class);
    }

    @Override
    public Maintain selectMaintainById(int faultRegisterId) {
        String sql = "SELECT faultRegisterId,F.track,faultState,faultType,P.placeName,T.trackName,R.regionName,seat,faultFindPeople,faultRegisterPeople,faultDealPeople,registerTime,pinTime,faultReason,dealResult FROM jzs_faultregister AS F LEFT JOIN jzs_track AS T ON F.track = T.trackId LEFT JOIN jzs_region AS R ON F.region = R.regionId LEFT JOIN jzs_place AS P ON F.place = P.placeId WHERE F.deleteFlag = 0 AND faultRegisterId = ?";
        Object[] args = {
                faultRegisterId
        };

        try {
            return (Maintain)jdbcTemplate.queryForObject(sql,args, new ListMaintainRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Page<Maintain> pageInspection(Maintain maintain, Pageable pageable) {
        StringBuilder sql = new StringBuilder("SELECT inspectionId,placeName,inspectionState,inspectionType,diaochePeo,zhibanPeo,inspectionTime,inspectionStopTime FROM jzs_inspection AS I LEFT JOIN jzs_place AS P ON I.placeId = P.placeId WHERE I.deleteFlag = 0");
        List<Object> list = new ArrayList<Object>();
        Optional<Maintain> optional = Optional.fromNullable(maintain);
        if (optional.isPresent()) {
            if (maintain.getInspectionState() > 0) {
                sql.append(" AND inspectionState = ?");
                list.add(maintain.getInspectionState());
            }
            if (maintain.getInspectionType() > 0) {
                sql.append(" AND inspectionType = ?");
                list.add(maintain.getInspectionType());
            }
            if (maintain.getPlace() > 0) {
                sql.append(" AND I.placeId = ?");
                list.add(maintain.getPlace());
            }
            if (maintain.getInspectionDate() != null) {
                sql.append(" AND inspectionDate = ?");
                list.add(maintain.getInspectionDate());
            }
        }

        Object[] args = list.toArray();

        sql.append(" ORDER BY inspectionTime DESC");
        try {
            return repositoryUtils.select4Page(sql.toString(), pageable, args, new PageInspectionRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int selectFaultNum() {
        String sql = "SELECT COUNT(1) FROM jzs_faultregister WHERE faultState = 2 AND deleteFlag = 0";

        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public Maintain selectPlaceAndTrack(int track) {
        String sql = "SELECT trackId,trackName,P.placeId,P.placeName FROM jzs_track AS T LEFT JOIN jzs_place AS P ON T.placeId = P.placeId WHERE T.trackId = ? AND T.deleteFlag = 0";
        Object[] args = {
                track
        };

        try {
            return (Maintain)jdbcTemplate.queryForObject(sql,args, new SelectPlaceAndTrackRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Maintain> selectTrackMaintains(int track) {
        String sql = "SELECT F.faultRegisterId,T.trackName,R.regionName,F.seat FROM jzs_track AS T LEFT JOIN jzs_faultregister AS F ON T.trackId = F.track LEFT JOIN jzs_region AS R ON F.region = R.regionId WHERE F.faultState = 2 AND T.trackId = ? AND T.deleteFlag = 0";
        Object[] args = {
                track
        };

        try {
            return jdbcTemplate.query(sql,args, new SelectTrackMaintainsRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Maintain selectSeat(int faultRegisterId) {
        String sql = "SELECT track,region,seat FROM jzs_faultregister WHERE faultRegisterId = ?";
        Object[] args = {
                faultRegisterId
        };

        try {
            return (Maintain)jdbcTemplate.queryForObject(sql,args, new SelectSeatRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Boolean isExitInspection() {
        String sql = "SELECT COUNT(0) FROM jzs_place WHERE placeState = 2 AND deleteFlag = 0";

        return jdbcTemplate.queryForObject(sql, Integer.class) > 0 ? true:false;
    }

    private class SelectSeatRowMapper implements RowMapper<Maintain> {

        @Override
        public Maintain mapRow(ResultSet resultSet, int i) throws SQLException {
            Maintain maintain = new Maintain();
            maintain.setTrack(resultSet.getInt("track"));
            maintain.setRegion(resultSet.getInt("region"));
            maintain.setSeat(resultSet.getString("seat"));

            return maintain;
        }
    }

    private class SelectTrackMaintainsRowMapper implements RowMapper<Maintain> {

        @Override
        public Maintain mapRow(ResultSet resultSet, int i) throws SQLException {
            Maintain maintain = new Maintain();
            maintain.setFaultRegisterId(resultSet.getInt("faultRegisterId"));
            String trackName = resultSet.getString("trackName");
            String regionName = resultSet.getString("regionName");
            String seat = resultSet.getString("seat");
            maintain.setMaintainName(trackName + regionName + seat);

            return maintain;
        }
    }

    private class SelectPlaceAndTrackRowMapper implements RowMapper<Maintain> {

        @Override
        public Maintain mapRow(ResultSet resultSet, int i) throws SQLException {
            Maintain maintain = new Maintain();
            maintain.setTrack(resultSet.getInt("trackId"));
            maintain.setTrackName(resultSet.getString("trackName"));
            maintain.setPlace(resultSet.getInt("placeId"));
            maintain.setPlaceName(resultSet.getString("placeName"));

            return maintain;
        }
    }

    private class PageInspectionRowMapper implements RowMapper<Maintain> {

        @Override
        public Maintain mapRow(ResultSet resultSet, int i) throws SQLException {
            Maintain maintain = new Maintain();
            maintain.setInspectionId(resultSet.getInt("inspectionId"));
            maintain.setPlaceName(resultSet.getString("placeName"));
            maintain.setInspectionState(resultSet.getInt("inspectionState"));
            maintain.setInspectionType(resultSet.getInt("inspectionType"));
            maintain.setDiaochePeo(resultSet.getString("diaochePeo"));
            maintain.setZhibanPeo(resultSet.getString("zhibanPeo"));
            maintain.setInspectionTime(resultSet.getString("inspectionTime"));
            maintain.setInspectionStopTime(resultSet.getString("inspectionStopTime"));

            return maintain;
        }
    }

    private class SelectCountTracksRowMapper implements RowMapper<Maintain> {

        @Override
        public Maintain mapRow(ResultSet resultSet, int i) throws SQLException {
            Maintain maintain = new Maintain();
            maintain.setProtectIdNum(resultSet.getInt("protectId"));

            return maintain;
        }
    }

    private class GetInspectionIdRowMapper implements RowMapper {

        @Override
        public Object mapRow(ResultSet resultSet, int i) throws SQLException {
            Maintain maintain = new Maintain();
            maintain.setInspectionId(resultSet.getInt("inspectionId"));
            maintain.setPlace(resultSet.getInt("placeId"));

            return maintain;
        }
    }

    private class SelectInspTracksRowMapper implements RowMapper {

        @Override
        public Object mapRow(ResultSet resultSet, int i) throws SQLException {
            Maintain maintain = new Maintain();
            maintain.setPlaceName(resultSet.getString("placeName"));
            maintain.setTrackName(resultSet.getString("trackName"));
            maintain.setTrackState(resultSet.getInt("trackState"));
            maintain.setTrack(resultSet.getInt("trackId"));
            maintain.setTrackState(resultSet.getInt("trackState"));
            maintain.setPlace(resultSet.getInt("placeId"));

            return maintain;
        }
    }

    private class SelectAllRowMapper implements RowMapper {

        @Override
        public Object mapRow(ResultSet resultSet, int i) throws SQLException {
            Maintain maintain = new Maintain();
            maintain.setPlace(resultSet.getInt("place"));
            maintain.setTrack(resultSet.getInt("track"));
            maintain.setRegion(resultSet.getInt("region"));
            maintain.setSeat(resultSet.getString("seat"));
            maintain.setProtectState(resultSet.getInt("protectState"));
            maintain.setFaultState(resultSet.getInt("faultState"));
            maintain.setFaultType(resultSet.getInt("faultType"));
            maintain.setProtectApprovePeople(resultSet.getString("protectApprovePeople"));
            maintain.setProtectPeople(resultSet.getString("protectPeople"));
            maintain.setProtectRequestPeople(resultSet.getString("protectRequestPeople"));
            maintain.setFaultFindPeople(resultSet.getString("faultFindPeople"));
            maintain.setFaultDealPeople(resultSet.getString("faultDealPeople"));
            maintain.setProtectDay(resultSet.getDate("protectDay"));
            maintain.setFaultDay(resultSet.getDate("faultDay"));
            maintain.setProtectApproveTime(resultSet.getString("protectApproveTime"));
            maintain.setRegisterTime(resultSet.getString("registerTime"));
            maintain.setProtectStopTime(resultSet.getString("protectStopTime"));
            maintain.setPinTime(resultSet.getString("pinTime"));
            maintain.setProtectRemark(resultSet.getString("protectRemark"));

            return maintain;
        }
    }

    private class ListAllRowMapper implements RowMapper {
        //F.place,F.track,region,seat,protectState,faultState,faultType,protectDay,faultDay
        @Override
        public Object mapRow(ResultSet resultSet, int i) throws SQLException {
            Maintain maintain = new Maintain();
            maintain.setFaultRegisterId(resultSet.getInt("faultRegisterId"));
            maintain.setPlace(resultSet.getInt("place"));
            maintain.setTrack(resultSet.getInt("track"));
            maintain.setRegion(resultSet.getInt("region"));
            maintain.setSeat(resultSet.getString("seat"));
            maintain.setProtectState(resultSet.getInt("protectState"));
            maintain.setFaultState(resultSet.getInt("faultState"));
            maintain.setFaultType(resultSet.getInt("faultType"));
            maintain.setProtectDay(resultSet.getDate("protectDay"));
            maintain.setFaultDay(resultSet.getDate("faultDay"));

            return maintain;
        }
    }

    private class ListMaintainRowMapper implements RowMapper {

        @Override
        public Object mapRow(ResultSet resultSet, int i) throws SQLException {
            Maintain maintain = new Maintain();
            maintain.setFaultRegisterId(resultSet.getInt("faultRegisterId"));
            maintain.setTrack(resultSet.getInt("track"));
            maintain.setFaultType(resultSet.getInt("faultType"));
            maintain.setPlaceName(resultSet.getString("placeName"));
            maintain.setTrackName(resultSet.getString("trackName"));
            maintain.setRegionName(resultSet.getString("regionName"));
            maintain.setSeat(resultSet.getString("seat"));
            maintain.setFaultFindPeople(resultSet.getString("faultFindPeople"));
            maintain.setFaultRegisterPeople(resultSet.getString("faultRegisterPeople"));
            maintain.setFaultDealPeople(resultSet.getString("faultDealPeople"));
            maintain.setFaultState(resultSet.getInt("faultState"));
            maintain.setRegisterTime(resultSet.getString("registerTime"));
            maintain.setPinTime(resultSet.getString("pinTime"));
            maintain.setFaultReason(resultSet.getInt("faultReason"));
            maintain.setDealResult(resultSet.getString("dealResult"));

            return maintain;
        }
    }

    private class SelectPartMaintainRowMapper implements RowMapper {

        @Override
        public Object mapRow(ResultSet resultSet, int i) throws SQLException {
            Maintain maintain = new Maintain();
            maintain.setPlace(resultSet.getInt("place"));
            maintain.setTrack(resultSet.getInt("track"));
            maintain.setProtectRequestPeople(resultSet.getString("protectRequestPeople"));

            return maintain;
        }
    }
    private class SelectPeopleRowMapper implements RowMapper {

        @Override
        public Object mapRow(ResultSet resultSet, int i) throws SQLException {
            Maintain maintain = new Maintain();
            maintain.setUserName(resultSet.getString("userName"));

            return maintain;
        }
    }

    private class listRowMapper implements RowMapper {

        @Override
        public Object mapRow(ResultSet resultSet, int i) throws SQLException {
            Maintain maintain = new Maintain();
            maintain.setProtectIdNum(resultSet.getInt("protectId"));
            maintain.setProtectState(resultSet.getInt("protectState"));
            maintain.setPlaceName(resultSet.getString("placeName"));
            maintain.setTrackName(resultSet.getString("trackName"));
            maintain.setProtectApprovePeople(resultSet.getString("protectApprovePeople"));
            maintain.setProtectPeople(resultSet.getString("protectPeople"));
            maintain.setProtectRequestPeople(resultSet.getString("protectRequestPeople"));
            maintain.setProtectApproveTime(resultSet.getString("protectApproveTime"));
            maintain.setProtectStopTime(resultSet.getString("protectStopTime"));
            maintain.setProtectRemark(resultSet.getString("protectRemark"));

            return maintain;
        }
    }

    private class SelectInspectionByIdRowMapper implements RowMapper<Maintain> {

        @Override
        public Maintain mapRow(ResultSet resultSet, int i) throws SQLException {
            Maintain maintain = new Maintain();
            maintain.setInspectionTime(resultSet.getString("inspectionTime"));
            maintain.setNewDate(resultSet.getString("inspectionDate"));
            maintain.setPlace(resultSet.getInt("placeId"));
            maintain.setProtectApprovePeople(resultSet.getString("diaochePeo"));
            maintain.setProtectRequestPeople(resultSet.getString("zhibanPeo"));

            return maintain;
        }
    }
}
