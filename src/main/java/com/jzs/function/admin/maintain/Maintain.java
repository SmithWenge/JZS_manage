package com.jzs.function.admin.maintain;

import com.jzs.arc.Entry;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Maintain extends Entry{

    private int protectIdNum;
    private String protectId;
    private String protectProject;
    private String protectStopTime;
    private String protectApproveTime;
    private String newDate;
    private String protectApprovePeople;
    private String protectPeople;
    private String protectRequestPeople;
    private int track;
    private int place;
    private String protectRemark;
    private String userName;
    private int protectState;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date protectDay;

    private int faultRegisterId;
    private int faultState;
    private String registerTime;
    private String pinTime;
    private String faultFindPeople;
    private String faultFindPeopleText;
    private String faultRegisterPeople;
    private String faultDealPeople;
    private int faultType;
    private int faultResault;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date faultDay;
    private int region;
    private String seat;
    private int faultNum;


    private int inspectionType;
    private String diaochePeo;
    private String zhibanPeo;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date inspectionDate;
    private String inspectionTime;
    private String inspectionStopTime;
    private String placeName;
    private String trackName;
    private String regionName;
    private int trackState;
    private int inspectionState;
    private int inspectionId;
    private String placeAndTrack;
    private String maintainProject;
    private int faultReason;
    private String dealResult;
    private String maintainName;

    public int getFaultNum() {
        return faultNum;
    }

    public void setFaultNum(int faultNum) {
        this.faultNum = faultNum;
    }

    public String getMaintainName() {
        return maintainName;
    }

    public void setMaintainName(String maintainName) {
        this.maintainName = maintainName;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public int getFaultReason() {
        return faultReason;
    }

    public void setFaultReason(int faultReason) {
        this.faultReason = faultReason;
    }

    public String getDealResult() {
        return dealResult;
    }

    public void setDealResult(String dealResult) {
        this.dealResult = dealResult;
    }

    public String getMaintainProject() {
        return maintainProject;
    }

    public void setMaintainProject(String maintainProject) {
        this.maintainProject = maintainProject;
    }

    public String getFaultFindPeopleText() {
        return faultFindPeopleText;
    }

    public void setFaultFindPeopleText(String faultFindPeopleText) {
        this.faultFindPeopleText = faultFindPeopleText;
    }

    public String getPlaceAndTrack() {
        return placeAndTrack;
    }

    public void setPlaceAndTrack(String placeAndTrack) {
        this.placeAndTrack = placeAndTrack;
    }

    public int getInspectionId() {
        return inspectionId;
    }

    public void setInspectionId(int inspectionId) {
        this.inspectionId = inspectionId;
    }

    public int getInspectionState() {
        return inspectionState;
    }

    public void setInspectionState(int inspectionState) {
        this.inspectionState = inspectionState;
    }

    public int getTrackState() {
        return trackState;
    }

    public void setTrackState(int trackState) {
        this.trackState = trackState;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getInspectionTime() {
        return inspectionTime;
    }

    public void setInspectionTime(String inspectionTime) {
        this.inspectionTime = inspectionTime;
    }

    public String getInspectionStopTime() {
        return inspectionStopTime;
    }

    public void setInspectionStopTime(String inspectionStopTime) {
        this.inspectionStopTime = inspectionStopTime;
    }

    public Date getInspectionDate() {

        return inspectionDate;
    }

    public void setInspectionDate(Date inspectionDate) {
        this.inspectionDate = inspectionDate;
    }

    public String getDiaochePeo() {
        return diaochePeo;
    }

    public void setDiaochePeo(String diaochePeo) {
        this.diaochePeo = diaochePeo;
    }

    public String getZhibanPeo() {
        return zhibanPeo;
    }

    public void setZhibanPeo(String zhibanPeo) {
        this.zhibanPeo = zhibanPeo;
    }

    public int getInspectionType() {
        return inspectionType;
    }

    public void setInspectionType(int inspectionType) {
        this.inspectionType = inspectionType;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public int getFaultRegisterId() {
        return faultRegisterId;
    }

    public void setFaultRegisterId(int faultRegisterId) {
        this.faultRegisterId = faultRegisterId;
    }

    public int getFaultState() {
        return faultState;
    }

    public void setFaultState(int faultState) {
        this.faultState = faultState;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public String getPinTime() {
        return pinTime;
    }

    public void setPinTime(String pinTime) {
        this.pinTime = pinTime;
    }

    public String getFaultFindPeople() {
        return faultFindPeople;
    }

    public void setFaultFindPeople(String faultFindPeople) {
        this.faultFindPeople = faultFindPeople;
    }

    public String getFaultRegisterPeople() {
        return faultRegisterPeople;
    }

    public void setFaultRegisterPeople(String faultRegisterPeople) {
        this.faultRegisterPeople = faultRegisterPeople;
    }

    public String getFaultDealPeople() {
        return faultDealPeople;
    }

    public void setFaultDealPeople(String faultDealPeople) {
        this.faultDealPeople = faultDealPeople;
    }

    public int getFaultType() {
        return faultType;
    }

    public void setFaultType(int faultType) {
        this.faultType = faultType;
    }

    public int getFaultResault() {
        return faultResault;
    }

    public void setFaultResault(int faultResault) {
        this.faultResault = faultResault;
    }

    public Date getFaultDay() {
        return faultDay;
    }

    public void setFaultDay(Date faultDay) {
        this.faultDay = faultDay;
    }

    public int getRegion() {
        return region;
    }

    public void setRegion(int region) {
        this.region = region;
    }

    public String getProtectStopTime() {
        return protectStopTime;
    }

    public void setProtectStopTime(String protectStopTime) {
        this.protectStopTime = protectStopTime;
    }

    public String getProtectApproveTime() {
        return protectApproveTime;
    }

    public void setProtectApproveTime(String protectApproveTime) {
        this.protectApproveTime = protectApproveTime;
    }

    public Date getProtectDay() {
        return protectDay;
    }

    public void setProtectDay(Date protectDay) {
        this.protectDay = protectDay;
    }

    public int getProtectState() {
        return protectState;
    }

    public void setProtectState(int protectState) {
        this.protectState = protectState;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNewDate() {
        return newDate;
    }

    public void setNewDate(String newDate) {
        this.newDate = newDate;
    }

    public int getProtectIdNum() {
        return protectIdNum;
    }

    public void setProtectIdNum(int protectIdNum) {
        this.protectIdNum = protectIdNum;
    }

    public String getProtectId() {
        return protectId;
    }

    public void setProtectId(String protectId) {
        this.protectId = protectId;
    }

    public String getProtectProject() {
        return protectProject;
    }

    public void setProtectProject(String protectProject) {
        this.protectProject = protectProject;
    }

    public String getProtectApprovePeople() {
        return protectApprovePeople;
    }

    public void setProtectApprovePeople(String protectApprovePeople) {
        this.protectApprovePeople = protectApprovePeople;
    }

    public String getProtectPeople() {
        return protectPeople;
    }

    public void setProtectPeople(String protectPeople) {
        this.protectPeople = protectPeople;
    }

    public String getProtectRequestPeople() {
        return protectRequestPeople;
    }

    public void setProtectRequestPeople(String protectRequestPeople) {
        this.protectRequestPeople = protectRequestPeople;
    }

    public int getTrack() {
        return track;
    }

    public void setTrack(int track) {
        this.track = track;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public String getProtectRemark() {
        return protectRemark;
    }

    public void setProtectRemark(String protectRemark) {
        this.protectRemark = protectRemark;
    }
}
