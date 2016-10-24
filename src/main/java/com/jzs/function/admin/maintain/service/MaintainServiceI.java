package com.jzs.function.admin.maintain.service;

import com.jzs.arc.exception.BatchRollbackException;
import com.jzs.function.admin.maintain.Maintain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sun.applet.Main;

import java.util.List;

public interface MaintainServiceI {

   Maintain selectProtectApprovePeople();
   List<Maintain> selectProtectPeople();
   Maintain selectProtectRequestPeople();
   List<Maintain> selectWorker();
   int protectAdd(Maintain maintain,String logUser)throws BatchRollbackException;
   Page<Maintain> listProtect(Maintain maintain,Pageable pageable);
//   boolean update(Maintain maintain,String logUser)throws BatchRollbackException;
   boolean updateMaintain(Maintain maintain,String logUser)throws BatchRollbackException;
   boolean maintainAdd(Maintain maintain,String logUser) throws BatchRollbackException;
   Page<Maintain> listMaintain(Maintain maintain,Pageable pageable);
   Boolean selectExitMaintain(Maintain maintain);
   Boolean selectExitCanclePro(Maintain maintain);
   Page<Maintain> listAll(Maintain maintain,Pageable pageable);
   Maintain selectAll(int faultRegisterId);
   int getInspectionState();
   List<Maintain> inspectionAdd(Maintain maintain,String logUser)throws BatchRollbackException;
   Maintain getInspectionId();
   Boolean inspectionStop(Maintain maintain,String logUser)throws BatchRollbackException;
   List<Maintain> selectInspTracks(int placeId);
   Boolean insCancleProtect(Maintain maintain,String logUser);
   int seletFaultId(Maintain maintain);
   List<Maintain> listForMaintain();
   Maintain selectMaintainById(int faultRegisterId);
   Page<Maintain> pageInspection(Maintain maintain,Pageable pageable);

   int selectFaultNum();
   Maintain selectPlaceAndTrack(int track);
   List<Maintain> selectTrackMaintains(int track);
   Maintain selectSeat(int faultRegisterId);
   int selectPlaceId(int trackId);

   Boolean isExitInspection();
   Maintain selectJsonByFauId(int id);
}
