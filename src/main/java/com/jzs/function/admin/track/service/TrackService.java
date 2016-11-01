package com.jzs.function.admin.track.service;

import com.jzs.function.admin.track.Track;
import com.jzs.function.admin.track.repository.TrackRepositoryI;
import com.jzs.function.support.log.LogContent;
import com.jzs.function.support.log.repository.LogRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackService implements TrackServiceI{

    @Autowired
    private TrackRepositoryI trackRepository;
    @Autowired
    private LogRepositoryI logRepository;

    @Override
    public List<Track> list() {
        List<Track> list = trackRepository.list();

        for (Track forTrack : list) {
            forTrack.setFaultNum(trackRepository.selectNumOfFault(forTrack.getTrackId()));
        }
        return list;
    }

    @Override
    public Boolean add(Track track, String logUser) {
        if (trackRepository.add(track)) {
            LogContent logContent = new LogContent(logUser, "添加股道" + track.getTrackName(), 1, 3);
            logRepository.insertLog(logContent);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Boolean edit(Track track, String logUser) {
        if (trackRepository.update(track)) {
            LogContent logContent = new LogContent(logUser, "编辑股道" + track.getTrackName(), 1, 4);
            logRepository.insertLog(logContent);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Boolean cancle(int trackId, String logUser) {
        if (trackRepository.delete(trackId)) {
            LogContent logContent = new LogContent(logUser, "删除场的Id为" + trackId, 1, 2);
            logRepository.insertLog(logContent);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Track select(int trackId) {
        return trackRepository.select(trackId);
    }

    @Override
    public List<Track> selectJsonByFauId(int placeId) {
        return trackRepository.selectJsonByFauId(placeId);
    }
}
