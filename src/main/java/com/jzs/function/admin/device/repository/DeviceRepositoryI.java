package com.jzs.function.admin.device.repository;

import com.jzs.function.admin.device.Device;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DeviceRepositoryI {

    void insert(Device device);
    boolean selectDiff(Device device);
    Page<Device> query4Page(Device device,Pageable pageable);
    Device select(int deviceId);
    Boolean update(Device device);
    Boolean add(Device device);
    Boolean cancle(Device device);
    boolean selectAddDiff(Device device);
    List<Device> selectWarningDeviceId();
    List<Device> selectWarningDeviceIdTwo();
    Boolean updateWaringDevice(int deviceId);
    int selectNumOfWarning();
}
