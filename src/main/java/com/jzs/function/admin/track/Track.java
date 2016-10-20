package com.jzs.function.admin.track;

import com.jzs.arc.Entry;

public class Track extends Entry{
    private int trackId;
    private int trackState;
    private int placeId;
    private String trackLength;
    private String trackName;
    private int carNumEle;
    private int carNumFou;
    private String useable;
    private String placeName;
    private int faultNum;

    public int getFaultNum() {
        return faultNum;
    }

    public void setFaultNum(int faultNum) {
        this.faultNum = faultNum;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public int getTrackId() {
        return trackId;
    }

    public void setTrackId(int trackId) {
        this.trackId = trackId;
    }

    public int getTrackState() {
        return trackState;
    }

    public void setTrackState(int trackState) {
        this.trackState = trackState;
    }

    public int getPlaceId() {
        return placeId;
    }

    public void setPlaceId(int placeId) {
        this.placeId = placeId;
    }

    public String getTrackLength() {
        return trackLength;
    }

    public void setTrackLength(String trackLength) {
        this.trackLength = trackLength;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public int getCarNumEle() {
        return carNumEle;
    }

    public void setCarNumEle(int carNumEle) {
        this.carNumEle = carNumEle;
    }

    public int getCarNumFou() {
        return carNumFou;
    }

    public void setCarNumFou(int carNumFou) {
        this.carNumFou = carNumFou;
    }

    public String getUseable() {
        return useable;
    }

    public void setUseable(String useable) {
        this.useable = useable;
    }
}
