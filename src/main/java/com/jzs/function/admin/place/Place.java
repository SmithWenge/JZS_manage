package com.jzs.function.admin.place;

import com.jzs.arc.Entry;

public class Place extends Entry{
    private int placeId;
    private String placeName;

    public int getPlaceId() {
        return placeId;
    }

    public void setPlaceId(int placeId) {
        this.placeId = placeId;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }
}
