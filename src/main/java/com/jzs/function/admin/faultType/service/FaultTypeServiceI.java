package com.jzs.function.admin.faultType.service;

import com.jzs.function.admin.faultType.FaultType;

import java.util.List;

public interface FaultTypeServiceI {

    List<FaultType> list();
    Boolean add(FaultType faultType,String logUser);
    FaultType select(int faultTypeId);
    Boolean edit(FaultType faultType,String logUser);
    Boolean delete(int faultTypeId,String logUser);
}
