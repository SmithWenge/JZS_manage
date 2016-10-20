package com.jzs.function.support.log.repository;

import com.jzs.function.support.log.LogContent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LogRepositoryI {
    void insertLog(LogContent logContent);
    Page<LogContent> select4Page(LogContent logContent, Pageable pageable);
}
