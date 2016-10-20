package com.jzs.function.admin.device.service;

import com.jzs.arc.excel.into.DeviceExcelMapper;
import com.jzs.arc.excel.into.ExcelConverter;
import com.jzs.arc.utils.DeviceNumUtils;
import com.jzs.function.admin.device.Device;
import com.jzs.function.admin.device.repository.DeviceRepositoryI;
import com.jzs.function.admin.seat.Seat;
import com.jzs.function.admin.seat.service.SeatServiceI;
import com.jzs.function.support.dictionary.impl.DefaultDictionaryManager;
import com.jzs.function.support.log.LogContent;
import com.jzs.function.support.log.repository.LogRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class DeviceService implements DeviceServiceI{

    @Autowired
    private LogRepositoryI logRepository;
    @Autowired
    private DeviceRepositoryI repository;
    @Autowired
    private SeatServiceI seatService;

    @Transactional
    @Override
    public Map<String, List<Device>> importData(File file) {
        ExcelConverter<Device> excelConverter = new ExcelConverter<Device>();
        List<Device> importDevices = excelConverter.readFromExcel(file, 1, new DeviceExcelMapper());
        List<Device> errorData = new ArrayList<Device>();
        List<Device> repeatData = new ArrayList<Device>();
        List<Device> rightData = new ArrayList<Device>();

        Seat seat = new Seat();
        for (Device info : importDevices) {
            if (info.isLegalImportData()) {
                seat.setPlaceName(info.getPlace());
                seat.setTrackName(info.getTrack());
                seat.setRegionName(info.getRegion());
                info.setPlaceNum(seatService.selecPlaceId(seat).getPlaceId());
                info.setTrackNum(seatService.selecTrackId(seat).getTrackId());
                info.setRegionNum(seatService.selecRegionId(seat).getRegionId());
                if (repository.selectDiff(info)) {
                    repeatData.add(info);
                } else {
                    repository.insert(info);
                    rightData.add(info);
                }
            } else {
                errorData.add(info);
            }
        }

        Map<String, List<Device>> map = new HashMap<String, List<Device>>();
        map.put("errorData", errorData);
        map.put("repeatData", repeatData);
        map.put("rightData", rightData);

//		reduce heap size
        importDevices = null;
        rightData = null;
        errorData = null;
        repeatData = null;

        return map;
    }

    @Override
    public Page<Device> list(Device device, Pageable pageable) {
        Page<Device> page = repository.query4Page(device, pageable);
        List<Device> list = page.getContent();
        List<Device> listNew = repository.selectLatestTimes();
        for (Device device1 : list) {
            for (Device device2 : listNew) {
                if (device1.getDeviceId() == device2.getDeviceId()) {
                    device1.setLatestTime(device2.getLatestTime());
                } else {
                    device1.setLatestTime("无最新维修记录");
                }
            }
        }

        Page<Device> pageNew = new PageImpl<Device>(list, pageable, list.size());
        return pageNew;
    }

    @Override
    public Device select(int deviceId) {
        return repository.select(deviceId);
    }

    @Override
    public Boolean update(Device device, String LogUser) {
        Boolean tmp = repository.update(device);

        if (tmp) {
            LogContent logContent = new LogContent(LogUser, "编辑设备" + device.getDeviceNumber(), 1, 4);
            logRepository.insertLog(logContent);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Boolean add(Device device, String LogUser) {
        device.setCancelStateNum(1);
        Boolean tmp = repository.add(device);
        if (tmp) {
            LogContent logContent = new LogContent(LogUser, "添加设备" + device.getDeviceNumber(), 1, 3);
            logRepository.insertLog(logContent);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Boolean cancle(Device device, String LogUser) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        device.setCancelTime(sdf.format(new Date()));
        Boolean tmp = repository.cancle(device);
        if (tmp) {
            LogContent logContent = new LogContent(LogUser, "撤销设备" + device.getDeviceId(), 1, 4);
            logRepository.insertLog(logContent);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Boolean selectState(Device device) {
        Device model = repository.select(device.getDeviceId());
        return model.getCancelStateNum() == 1;
    }

    @Override
    public Boolean selectRepeat(Device device) {
        return repository.selectAddDiff(device);
    }

    @Override
    public Boolean updateWarning() {
        List<Device> listOne = repository.selectWarningDeviceId();
        List<Device> listTwo = repository.selectWarningDeviceIdTwo();
        for (int i = 0;i < listOne.size();i++) {
            listTwo.add(listOne.get(i));
        }
        for (Device device : listTwo) {
            repository.updateWaringDevice(device.getDeviceId());
        }
        return true;
    }

    @Override
    public int selectNumOfWarning() {
        return repository.selectNumOfWarning();
    }
}
