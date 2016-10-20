package com.jzs.function.admin.worker.repository;

import com.jzs.function.admin.worker.Worker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WorkerRespositoryI {

    Boolean add(Worker worker);
    Worker select(int userId);
    Boolean update(Worker worker);
    Boolean delete(int userId);
    Page<Worker> list(Pageable pageable);
}
