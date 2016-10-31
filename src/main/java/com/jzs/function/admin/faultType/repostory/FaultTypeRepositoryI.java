package com.jzs.function.admin.faultType.repostory;

import com.jzs.function.admin.faultType.FaultType;

import java.util.List;

public interface FaultTypeRepositoryI {

    List<FaultType> list();
    Boolean add(FaultType faultType);
    FaultType select(int faultTypeId);
    Boolean update(FaultType faultType);
    Boolean delete(int faultTypeId);
}
