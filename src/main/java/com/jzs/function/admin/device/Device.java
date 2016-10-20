package com.jzs.function.admin.device;

import com.jzs.arc.Entry;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Device extends Entry {

    private int deviceState;
    private String deviceNumber;
    private String deviceModel;
    private String deviceFactory;
    private String deviceTime;
    private String rollingTimes;
    private String track;
    private String place;
    private String region;
    private String seat;
    private String cancelTime;
    private String cancelState;
    private String remark;
    private String stationNum;
    private String stationName;
    private int placeNum;
    private int trackNum;
    private int regionNum;
    private int cancelStateNum;
    private int deviceId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date deviceTimeDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date cancelTimeDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date stopDate;
    private String latestTime;

    public String getLatestTime() {
        return latestTime;
    }

    public void setLatestTime(String latestTime) {
        this.latestTime = latestTime;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getStopDate() {
        return stopDate;
    }

    public void setStopDate(Date stopDate) {
        this.stopDate = stopDate;
    }

    public int getCancelStateNum() {
        return cancelStateNum;
    }

    public void setCancelStateNum(int cancelStateNum) {
        this.cancelStateNum = cancelStateNum;
    }

    public Date getDeviceTimeDate() {
        return deviceTimeDate;
    }

    public void setDeviceTimeDate(Date deviceTimeDate) {
        this.deviceTimeDate = deviceTimeDate;
    }

    public Date getCancelTimeDate() {
        return cancelTimeDate;
    }

    public void setCancelTimeDate(Date cancelTimeDate) {
        this.cancelTimeDate = cancelTimeDate;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public int getPlaceNum() {
        return placeNum;
    }

    public void setPlaceNum(int placeNum) {
        this.placeNum = placeNum;
    }

    public int getTrackNum() {
        return trackNum;
    }

    public void setTrackNum(int trackNum) {
        this.trackNum = trackNum;
    }

    public int getRegionNum() {
        return regionNum;
    }

    public void setRegionNum(int regionNum) {
        this.regionNum = regionNum;
    }

    public String getDeviceTime() {
        return deviceTime;
    }

    public void setDeviceTime(String deviceTime) {
        this.deviceTime = deviceTime;
    }

    public String getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(String cancelTime) {
        this.cancelTime = cancelTime;
    }

    public String getTrack() {
        return track;
    }

    public void setTrack(String track) {
        this.track = track;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCancelState() {
        return cancelState;
    }

    public void setCancelState(String cancelState) {
        this.cancelState = cancelState;
    }

    public String getStationNum() {
        return stationNum;
    }

    public void setStationNum(String stationNum) {
        this.stationNum = stationNum;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public int getDeviceState() {
        return deviceState;
    }

    public void setDeviceState(int deviceState) {
        this.deviceState = deviceState;
    }

    public String getDeviceNumber() {
        return deviceNumber;
    }

    public void setDeviceNumber(String deviceNumber) {
        this.deviceNumber = deviceNumber;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public String getDeviceFactory() {
        return deviceFactory;
    }

    public void setDeviceFactory(String deviceFactory) {
        this.deviceFactory = deviceFactory;
    }

    public String getRollingTimes() {
        return rollingTimes;
    }

    public void setRollingTimes(String rollingTimes) {
        this.rollingTimes = rollingTimes;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Boolean isLegalImportData() {
        return this.getDeviceFactory() != null
                && this.getDeviceNumber() != null
                && this.getDeviceModel() != null
                && this.getDeviceTime() != null
                && this.getPlace() != null
                && this.getTrack() != null
                && this.getRegion() != null
                && this.getSeat() != null
                && this.getStationNum() != null
                && this.getStationName() != null
                && this.getCancelTime() != null
                && this.getRollingTimes() != null
                && this.getRemark() != null;

    }
}
