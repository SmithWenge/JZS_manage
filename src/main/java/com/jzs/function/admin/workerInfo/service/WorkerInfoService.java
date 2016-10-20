package com.jzs.function.admin.workerInfo.service;

import com.jzs.arc.exception.BatchRollbackException;
import com.jzs.arc.utils.ChecksToList;
import com.jzs.function.admin.workerInfo.WorkerInfo;
import com.jzs.function.admin.workerInfo.repository.WorkerInfoRepositoryI;
import com.jzs.function.support.log.LogContent;
import com.jzs.function.support.log.repository.LogRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Service
public class WorkerInfoService implements WorkerInfoServiceI{

    @Autowired
    private LogRepositoryI logRepository;
    @Autowired
    private WorkerInfoRepositoryI workerInfoRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = BatchRollbackException.class)
    @Override
    public List<WorkerInfo> selectFangHu() {

        return workerInfoRepository.selectFangHu();
    }

    @Override
    public List<WorkerInfo> selectZhiBan() {
        return workerInfoRepository.selectZhiBan();
    }

    @Override
    public List<WorkerInfo> selectDiaoChe() {
        return workerInfoRepository.selectDiaoChe();
    }

    @Override
    public List<WorkerInfo> selectWorker() {
        return workerInfoRepository.selectWorker();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = BatchRollbackException.class)
    @Override
    public boolean add(List<String> list,String logUsesr) throws BatchRollbackException{
        Object[] args = new Object[3];
        Object[] args2 = new Object[1];
        WorkerInfo workerInfo = new WorkerInfo();
        workerInfo.setAttendanceDate(new Date());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        workerInfo.setAttendanceTime(sdf.format(new Date()));
        int successNum1 = 0;
        int successNum2 = 0;
        int successNum3 = 0;
        args[0] = workerInfo.getAttendanceDate();
        args[1] = workerInfo.getAttendanceTime();
        workerInfoRepository.temDelete();
        for (String userString : list) {
            String[] users = ChecksToList.getString(userString);
            for (String user : users) {
                args[2] = Integer.parseInt(user);
                args2[0] = Integer.parseInt(user);
                successNum3 += workerInfoRepository.temAdd(args2);
                successNum1 += workerInfoRepository.add(args);
                successNum2 += 1;
            }
        }
        if (successNum1 == successNum2 && successNum2 == successNum3) {
            LogContent logContent = new LogContent(logUsesr, "添加人员个数为" + String.valueOf(successNum1), 1, 3);
            logRepository.insertLog(logContent);
        } else {
            throw new BatchRollbackException();
        }

        return true;
    }

    @Override
    public List<WorkerInfo> list(WorkerInfo workerInfo) {
        return workerInfoRepository.list(workerInfo);
    }

    @Override
    public List<WorkerInfo> listForPost(WorkerInfo workerInfo) {
        return workerInfoRepository.listForPost(workerInfo);
    }

    @Override
    public Boolean selectCount() {
        return workerInfoRepository.selectCount();
    }

    @Override
    public List<WorkerInfo> listForHome() {
        return workerInfoRepository.listForHome();
    }

    @Override
    public Boolean temDelete() {
        return workerInfoRepository.temDelete();
    }
}
