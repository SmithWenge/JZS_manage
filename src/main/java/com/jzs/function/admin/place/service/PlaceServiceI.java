package com.jzs.function.admin.place.service;

import com.jzs.function.admin.place.Place;

import java.util.List;

public interface PlaceServiceI {
    List<Place> list();
    Boolean add(Place place,String logUser);
    Boolean edit(Place place,String logUser);
    Boolean cancle(int placeId,String logUser);
    Place select(int placeId);
}
