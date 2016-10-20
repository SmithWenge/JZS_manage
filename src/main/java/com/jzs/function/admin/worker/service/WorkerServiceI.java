package com.jzs.function.admin.worker.service;

import com.jzs.function.admin.worker.Worker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface WorkerServiceI {

    Boolean add(Worker worker,String logUsesr);
    Worker select(int userId);
    Boolean update(Worker worker,String LogUser);
    Boolean delete(int userId,String logUser);
    Page<Worker> list(Pageable pageable);
}
