package com.jzs.function.admin.place.repository;

import com.jzs.function.admin.place.Place;

import java.util.List;

public interface PlaceRepositoryI {
    List<Place> list();
    Boolean add(Place place);
    Boolean update(Place place);
    Boolean delete(int placeId);
    Place select(int placeId);
}
