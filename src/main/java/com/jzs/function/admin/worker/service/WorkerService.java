package com.jzs.function.admin.worker.service;

import com.jzs.function.admin.worker.Worker;
import com.jzs.function.admin.worker.repository.WorkerRespositoryI;
import com.jzs.function.support.log.LogContent;
import com.jzs.function.support.log.repository.LogRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class WorkerService implements WorkerServiceI{

    @Autowired
    private WorkerRespositoryI workerRespository;
    @Autowired
    private LogRepositoryI logRepository;

    @Override
    public Boolean add(Worker worker, String logUsesr) {
        boolean tmp = workerRespository.add(worker);

        if (tmp) {
            LogContent logContent = new LogContent(logUsesr, "添加工人" + worker.getUserName(), 1, 3);
            logRepository.insertLog(logContent);
        }
        return tmp;
    }

    @Override
    public Worker select(int userId) {
        return workerRespository.select(userId);
    }

    @Override
    public Boolean update(Worker worker, String LogUser) {
        boolean tmp = workerRespository.update(worker);

        if (tmp) {
            LogContent logContent = new LogContent(LogUser, "编辑工人" + worker.getUserName(), 1, 4);
            logRepository.insertLog(logContent);
        }
        return tmp;
    }

    @Override
    public Boolean delete(int userId, String logUser) {
        boolean tmp = workerRespository.delete(userId);

        if (tmp) {
            LogContent logContent = new LogContent(logUser, "删除工人" + "删除工人ID为" + userId, 1, 2);
            logRepository.insertLog(logContent);
        }
        return tmp;
    }

    @Override
    public Page<Worker> list( Pageable pageable) {
        return workerRespository.list(pageable);
    }
}
