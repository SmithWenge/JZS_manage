package com.jzs.function.admin.maintain.service;

import com.google.common.base.Optional;
import com.jzs.arc.exception.BatchRollbackException;
import com.jzs.function.admin.maintain.Maintain;
import com.jzs.function.admin.maintain.repository.MaintainRepositoryI;
import com.jzs.function.admin.place.repository.PlaceRepositoryI;
import com.jzs.function.admin.track.repository.TrackRepositoryI;
import com.jzs.function.support.log.LogContent;
import com.jzs.function.support.log.repository.LogRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class MaintainService implements MaintainServiceI{

    @Autowired
    private LogRepositoryI logRepository;
    @Autowired
    private MaintainRepositoryI maintainRepository;
    @Autowired
    private PlaceRepositoryI placeRepository;
    @Autowired
    private TrackRepositoryI trackRepository;

    @Override
    public Maintain selectProtectApprovePeople() {
        return maintainRepository.selectProtectApprovePeople();
    }

    @Override
    public List<Maintain> selectProtectPeople() {
        return maintainRepository.selectProtectPeople();
    }

    @Override
    public Maintain selectProtectRequestPeople() {
        return maintainRepository.selectProtectRequestPeople();
    }

    @Override
    public List<Maintain> selectWorker() {
        return maintainRepository.selectWorker();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = BatchRollbackException.class)
    @Override
    public int protectAdd(Maintain maintain, String logUser) throws BatchRollbackException{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        maintain.setProtectApproveTime(sdf.format(new Date()));
        maintain.setProtectDay(new Date());
        if(!maintainRepository.selectDiff(maintain)) {
            Boolean tmp1 = maintainRepository.protectAdd(maintain);
            Boolean tmp2 = maintainRepository.updateTrack(maintain,2);

            if (tmp1 && tmp2) {
                LogContent logContent = new LogContent(logUser, "添加防护", 1, 3);
                logRepository.insertLog(logContent);
                return 0;
            }
            return 1;
        } else {
            return 2;
        }
    }

    @Override
    public Page<Maintain> listProtect(Maintain maintain, Pageable pageable) {
        return maintainRepository.list(maintain,pageable);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = BatchRollbackException.class)
    @Override
    public boolean updateMaintain(Maintain maintain, String logUser) throws BatchRollbackException{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        maintain.setPinTime(sdf.format(new Date()));
        Boolean tmp1 = maintainRepository.updateMaintain(maintain);
        Boolean tmp2 = maintainRepository.updateDevice(maintain,1);
        if (tmp1 && tmp2) {
            LogContent logContent = new LogContent(logUser, "故障销记" + maintain.getFaultRegisterId(), 1, 4);
            logRepository.insertLog(logContent);
        }

        return tmp1 && tmp2;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = BatchRollbackException.class)
    @Override
    public boolean maintainAdd(Maintain maintain, String logUser) throws BatchRollbackException{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        maintain.setRegisterTime(sdf.format(new Date()));
        maintain.setLatestTime(maintain.getRegisterTime());
        SimpleDateFormat sdfTwo = new SimpleDateFormat("yyyy-MM-dd");
        maintain.setNewDate(sdfTwo.format(new Date()));
        maintain.setFaultState(2);
        Boolean tmp1 = maintainRepository.maintainAdd(maintain);
        Boolean tmp2 = maintainRepository.updateDevice(maintain,3);
        if (tmp1 && tmp2) {
            LogContent logContent = new LogContent(logUser, "添加故障维护", 1, 3);
            logRepository.insertLog(logContent);
        }

        return tmp1 && tmp2;
    }

    @Override
    public Page<Maintain> listMaintain(Maintain maintain, Pageable pageable) {
        return maintainRepository.listMaintain(maintain, pageable);
    }

    @Override
    public Boolean selectExitMaintain(Maintain maintain) {
        return maintainRepository.selectExitMaintain(maintain);
    }

    @Override
    public Boolean selectExitCanclePro(Maintain maintain) {
        return maintainRepository.selectExitCanclePro(maintain);
    }

    @Override
    public Page<Maintain> listAll(Maintain maintain, Pageable pageable) {
        return maintainRepository.listAll(maintain, pageable);
    }

    @Override
    public Maintain selectAll(int faultRegisterId) {
        return maintainRepository.selectAll(faultRegisterId);
    }

    @Override
    public int getInspectionState() {
        return maintainRepository.getInspectionState();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = BatchRollbackException.class)
    @Override
    public List<Maintain> inspectionAdd(Maintain maintain,String logUser) throws BatchRollbackException{
        SimpleDateFormat sdfTwo = new SimpleDateFormat("yyyy-MM-dd");
        maintain.setNewDate(sdfTwo.format(new Date()));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        maintain.setInspectionTime(sdf.format(new Date()));
        Maintain peo = maintainRepository.selectProtectApprovePeople();
        maintain.setDiaochePeo(peo.getUserName());
        maintain.setInspectionState(1);
        Boolean tmp1 = maintainRepository.insterInsp(maintain);
        Boolean tmp3 = maintainRepository.updatePlaceIns(maintain.getPlace());
        //Boolean tmp2 = maintainRepository.updatePlacePro(maintain.getPlace());
        List<Maintain> list = maintainRepository.selectInspTracks(maintain.getPlace());

        if (tmp1 && tmp3) {
            LogContent logContent = new LogContent(logUser, "添加巡检的场次为" + placeRepository.select(maintain.getPlace()).getPlaceName(), 1, 3);
            logRepository.insertLog(logContent);

            return list;
        } else {

            return null;
        }
    }

    @Override
    public Maintain getInspectionId() {
        return maintainRepository.getInspectionId();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = BatchRollbackException.class)
    @Override
    public Boolean inspectionStop(Maintain maintain,String logUser) throws BatchRollbackException{
//        List<Maintain> list = maintainRepository.selectStopInspTracks(maintain.getPlace());
//        Maintain stopMaintain = new Maintain();
//        stopMaintain.setProtectState(2);
//        Maintain inspection = maintainRepository.selectInspectionById(maintain.getInspectionId());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        stopMaintain.setProtectStopTime(sdf.format(new Date()));
        maintain.setInspectionStopTime(sdf.format(new Date()));
//        stopMaintain.setProtectPeople("巡检防护未记录");
//        stopMaintain.setProtectRemark("无");
//        stopMaintain.setProtectApproveTime(inspection.getInspectionTime());
//        stopMaintain.setProtectApprovePeople(inspection.getProtectApprovePeople());
//        stopMaintain.setProtectRequestPeople(inspection.getProtectRequestPeople());
//        stopMaintain.setPlace(inspection.getPlace());
//        stopMaintain.setNewDate(inspection.getNewDate());
//        for (Maintain forMaintain : list) {
//            stopMaintain.setTrack(forMaintain.getTrack());
//            maintainRepository.inspectionAddPro(stopMaintain);
//            LogContent logContent = new LogContent(logUser, "添加防护" + "巡检防护" + " " + forMaintain.getTrackName(), 1, 3);
//            logRepository.insertLog(logContent);
//        }

        Boolean tmp1 = maintainRepository.updateInspStop(maintain);
        Boolean tmp2 = maintainRepository.updatePlaceInsStop(maintain);
//        Boolean tmp3 = maintainRepository.updateTracksStop(maintain);

        if (tmp1 && tmp2) {
            LogContent logContent = new LogContent(logUser, "结束巡检的场次为" + placeRepository.select(maintain.getPlace()).getPlaceName(), 1, 4);
            logRepository.insertLog(logContent);

        } else {

            throw new BatchRollbackException();
        }

        return true;
    }

    @Override
    public List<Maintain> selectInspTracks(int placeId) {
        List<Maintain> list = maintainRepository.selectInspTracks(placeId);
        for (Maintain one : list) {
            one.setFaultNum(maintainRepository.selectFauNumOfOneTra(one.getPlace(),one.getTrack()));
        }

        return list;
    }

    @Override
    public Boolean insCancleProtect(Maintain maintain,String logUser) {
        if (maintainRepository.updateTrack(maintain, 1)) {
            Maintain tmp = maintainRepository.selectProtectId(maintain);
            Optional<Maintain> optional = Optional.fromNullable(tmp);

            if (optional.isPresent()) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                maintain.setProtectStopTime(sdf.format(new Date()));
                maintain.setProtectIdNum(tmp.getProtectIdNum());
                if (maintainRepository.update(maintain)) {
                     LogContent logContent = new LogContent(logUser, "取消防护的股道为" + trackRepository.select(maintain.getTrack()).getTrackName(), 1, 4);
                     logRepository.insertLog(logContent);

                     return true;
                } else {
                     return false;
                }
            } else {
                LogContent logContent = new LogContent(logUser, "取消防护的股道为" + trackRepository.select(maintain.getTrack()).getTrackName(), 1, 4);
                logRepository.insertLog(logContent);

                return true;
            }
        }
        return false;
    }

    @Override
    public int seletFaultId(Maintain maintain) {
        return maintainRepository.seletFaultId(maintain);
    }

    @Override
    public List<Maintain> listForMaintain() {
        List<Maintain> list =  maintainRepository.listForMaintain();
        for (Maintain maintain : list) {
            maintain.setTrackState(maintainRepository.selectTrackState(maintain.getTrack()));
        }

        return list;
    }

    @Override
    public Maintain selectMaintainById(int faultRegisterId) {
        return maintainRepository.selectMaintainById(faultRegisterId);
    }

    @Override
    public Page<Maintain> pageInspection(Maintain maintain, Pageable pageable) {
        return maintainRepository.pageInspection(maintain,pageable);
    }

    @Override
    public int selectFaultNum() {
        return maintainRepository.selectFaultNum();
    }

    @Override
    public Maintain selectPlaceAndTrack(int track) {
        return maintainRepository.selectPlaceAndTrack(track);
    }

    @Override
    public List<Maintain> selectTrackMaintains(int track) {
        return maintainRepository.selectTrackMaintains(track);
    }

    @Override
    public Maintain selectSeat(int faultRegisterId) {
        return maintainRepository.selectSeat(faultRegisterId);
    }

    @Override
    public int selectPlaceId(int trackId) {
        return maintainRepository.selectPlaceId(trackId);
    }

    @Override
    public Boolean isExitInspection() {
        return maintainRepository.isExitInspection();
    }

    @Override
    public Maintain selectJsonByFauId(int id) {
        return maintainRepository.selectMaintainById(id);
    }

}
