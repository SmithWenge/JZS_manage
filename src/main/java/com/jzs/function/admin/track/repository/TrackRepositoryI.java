package com.jzs.function.admin.track.repository;

import com.jzs.function.admin.track.Track;

import java.util.List;

public interface TrackRepositoryI {

    List<Track> list();
    Boolean add(Track track);
    Boolean update(Track track);
    Boolean delete(int trackId);
    Track select(int trackId);
    int selectNumOfFault(int trackId);
}
