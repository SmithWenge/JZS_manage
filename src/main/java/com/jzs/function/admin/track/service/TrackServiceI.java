package com.jzs.function.admin.track.service;

import com.jzs.function.admin.track.Track;

import java.util.List;

public interface TrackServiceI {

    List<Track> list();
    Boolean add(Track track,String logUser);
    Boolean edit(Track track,String logUser);
    Boolean cancle(int trackId,String logUser);
    Track select(int trackId);
    List<Track> selectJsonByFauId(int placeId);
}
