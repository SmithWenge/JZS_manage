package com.jzs.function.admin.maintain.repository;

import com.jzs.function.admin.maintain.Maintain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sun.applet.Main;

import java.util.List;

public interface MaintainRepositoryI {

    Maintain selectProtectApprovePeople();
    List<Maintain> selectProtectPeople();
    List<Maintain> selectProtectRequestPeople();
    List<Maintain> selectWorker();
    Boolean protectAdd(Maintain maintain);
    Page<Maintain> list(Maintain maintain,Pageable pageable);
    Boolean selectDiff(Maintain maintain);
    Boolean update (Maintain maintain);
    Boolean updateMaintain (Maintain maintain);
    Boolean maintainAdd(Maintain maintain);
    Boolean updateDevice(Maintain maintain,int state);
    Maintain selectPartMaintain(Maintain maintain);
    Page<Maintain> listMaintain(Maintain maintain,Pageable pageable);
    Boolean selectExitMaintain(Maintain maintain);
    Boolean selectExitCanclePro(Maintain maintain);
    Page<Maintain> listAll(Maintain maintain,Pageable pageable);
    Maintain selectAll(int faultRegisterId);
    int getInspectionState();

    Boolean insterInsp(Maintain maintain);
    Boolean updatePlacePro(int placeId);
    Boolean updatePlaceIns(int placeId);
    List<Maintain> selectInspTracks(int placeId);
    int selectFauNumOfOneTra(int placeId,int trackId);
    List<Maintain> selectStopInspTracks(int placeId);

    Maintain getInspectionId();
    Boolean updateInspStop(Maintain maintain);
    Boolean updatePlaceInsStop(Maintain maintain);
    Boolean updateTracksStop(Maintain maintain);
    Boolean updateTrack(Maintain maintain,int state);
    Maintain selectProtectId(Maintain maintain);
    Maintain selectInspectionById(int inspectionId);
    Boolean inspectionAddPro(Maintain maintain);

    int selectPlaceId(int trackId);
    Boolean updatePorTrack(int trackId);
    int seletFaultId(Maintain maintain);
    List<Maintain> listForMaintain();
    int selectTrackState(int trackId);
    Maintain selectMaintainById(int faultRegisterId);
    Page<Maintain> pageInspection(Maintain maintain,Pageable pageable);

    int selectFaultNum();
    Maintain selectPlaceAndTrack(int track);
    List<Maintain> selectTrackMaintains(int track);
    Maintain selectSeat(int faultRegisterId);
}
