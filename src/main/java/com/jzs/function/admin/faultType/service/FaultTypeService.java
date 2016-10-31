package com.jzs.function.admin.faultType.service;

import com.jzs.function.admin.faultType.FaultType;
import com.jzs.function.admin.faultType.repostory.FaultTypeRepositoryI;
import com.jzs.function.support.log.LogContent;
import com.jzs.function.support.log.repository.LogRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FaultTypeService implements FaultTypeServiceI {

    @Autowired
    private FaultTypeRepositoryI faultTypeRepository;
    @Autowired
    private LogRepositoryI logRepository;

    @Override
    public List<FaultType> list() {
        return faultTypeRepository.list();
    }

    @Override
    public Boolean add(FaultType faultType, String logUser) {
        if (faultTypeRepository.add(faultType)) {
            LogContent logContent = new LogContent(logUser, "添加故障" + faultType.getFaultTypeName(), 1, 3);
            logRepository.insertLog(logContent);

            return true;
        }else {

            return false;
        }
    }

    @Override
    public FaultType select(int faultTypeId) {
        return faultTypeRepository.select(faultTypeId);
    }

    @Override
    public Boolean edit(FaultType faultType, String logUser) {
        if (faultTypeRepository.update(faultType)) {
            LogContent logContent = new LogContent(logUser, "编辑故障类型" + faultType.getFaultTypeName(), 1, 4);
            logRepository.insertLog(logContent);

            return true;
        }else {

            return false;
        }
    }

    @Override
    public Boolean delete(int faultTypeId, String logUser) {
        if (faultTypeRepository.delete(faultTypeId)) {
            LogContent logContent = new LogContent(logUser, "删除故障类型的Id为" + faultTypeId, 1, 2);
            logRepository.insertLog(logContent);

            return true;
        }else {

            return false;
        }
    }
}
