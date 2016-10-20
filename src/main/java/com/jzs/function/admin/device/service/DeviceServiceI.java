package com.jzs.function.admin.device.service;

import com.jzs.function.admin.device.Device;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.File;
import java.util.List;
import java.util.Map;

public interface DeviceServiceI {

    Map<String, List<Device>> importData(File file);
    Page<Device> list(Device device,Pageable pageable);
    Device select(int deviceId);
    Boolean update(Device device,String LogUser);
    Boolean add(Device device,String LogUser);
    Boolean cancle(Device device,String LogUser);
    Boolean selectState(Device device);
    Boolean selectRepeat(Device device);
    Boolean updateWarning();
    int selectNumOfWarning();
}
