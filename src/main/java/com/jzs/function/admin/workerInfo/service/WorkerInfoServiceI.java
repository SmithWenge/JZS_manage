package com.jzs.function.admin.workerInfo.service;

import com.jzs.arc.exception.BatchRollbackException;
import com.jzs.function.admin.workerInfo.WorkerInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface WorkerInfoServiceI {
    List<WorkerInfo> selectFangHu ();
    List<WorkerInfo> selectZhiBan ();
    List<WorkerInfo> selectDiaoChe ();
    List<WorkerInfo> selectWorker ();
    boolean add(List<String> list,String logUser)throws BatchRollbackException;
    List<WorkerInfo> list(WorkerInfo workerInfo);
    List<WorkerInfo> listForPost(WorkerInfo workerInfo);
    Boolean selectCount();
    List<WorkerInfo> listForHome();
    Boolean temDelete();
}