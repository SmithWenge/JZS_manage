package com.jzs.function.support.log.service;

import com.jzs.function.support.log.LogContent;
import com.jzs.function.support.log.repository.LogRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class LogService implements LogServiceI {
    @Autowired
    private LogRepositoryI repository;

    @Override
    public Page<LogContent> query4Page(LogContent logContent, Pageable pageable) {
        return repository.select4Page(logContent, pageable);
    }
}
