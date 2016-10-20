package com.jzs.function.admin.workerInfo.repository;

import com.jzs.function.admin.workerInfo.WorkerInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface WorkerInfoRepositoryI {

    List<WorkerInfo> selectFangHu ();
    List<WorkerInfo> selectZhiBan ();
    List<WorkerInfo> selectDiaoChe ();
    List<WorkerInfo> selectWorker ();
    int add(Object[] args);
    int temAdd(Object[] args);
    Boolean temDelete();
    List<WorkerInfo> list(WorkerInfo workerInfo);
    List<WorkerInfo> listForPost(WorkerInfo workerInfo);
    Boolean selectCount();
    List<WorkerInfo> listForHome();
}
