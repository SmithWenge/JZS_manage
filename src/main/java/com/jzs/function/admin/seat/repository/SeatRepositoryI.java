package com.jzs.function.admin.seat.repository;

import com.jzs.function.admin.seat.Seat;

import java.util.List;

public interface SeatRepositoryI {
    List<Seat> selectPlaces();
    List<Seat> selectTracks();
    List<Seat> selectRegions();
    Seat selecPlaceId(Seat seat);
    Seat selecTrackId(Seat seat);
    Seat selecRegionId(Seat seat);
}
