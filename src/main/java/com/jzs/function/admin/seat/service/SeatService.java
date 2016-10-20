package com.jzs.function.admin.seat.service;

import com.jzs.function.admin.seat.Seat;
import com.jzs.function.admin.seat.repository.SeatRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService implements SeatServiceI{

    @Autowired
    private SeatRepositoryI seatRepository;

    @Override
    public List<Seat> selectPlaces() {
        return seatRepository.selectPlaces();
    }

    @Override
    public List<Seat> selectTracks() {
        return seatRepository.selectTracks();
    }

    @Override
    public List<Seat> selectRegions() {
        return seatRepository.selectRegions();
    }

    @Override
    public Seat selecPlaceId(Seat seat) {
        return seatRepository.selecPlaceId(seat);
    }

    @Override
    public Seat selecTrackId(Seat seat) {
        return seatRepository.selecTrackId(seat);
    }

    @Override
    public Seat selecRegionId(Seat seat) {
        return seatRepository.selecRegionId(seat);
    }
}
