package com.jzs.function.admin.place.service;

import com.jzs.function.admin.place.Place;
import com.jzs.function.admin.place.repository.PlaceRepositoryI;
import com.jzs.function.support.log.LogContent;
import com.jzs.function.support.log.repository.LogRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceService implements PlaceServiceI{
    @Autowired
    private PlaceRepositoryI placeRepository;
    @Autowired
    private LogRepositoryI logRepository;

    @Override
    public List<Place> list() {
        return placeRepository.list();
    }

    @Override
    public Boolean add(Place place, String logUser) {
        if (placeRepository.add(place)) {
            LogContent logContent = new LogContent(logUser, "添加场" + place.getPlaceName(), 1, 3);
            logRepository.insertLog(logContent);

            return true;
        }else {

            return false;
        }
    }

    @Override
    public Boolean edit(Place place, String logUser) {
        if (placeRepository.update(place)) {
            LogContent logContent = new LogContent(logUser, "编辑场" + place.getPlaceName(), 1, 4);
            logRepository.insertLog(logContent);

            return true;
        }else {

            return false;
        }
    }

    @Override
    public Boolean cancle(int placeId,String logUser) {
        if (placeRepository.delete(placeId)) {
            LogContent logContent = new LogContent(logUser, "删除场的Id为" + placeId, 1, 2);
            logRepository.insertLog(logContent);

            return true;
        }else {

            return false;
        }
    }

    @Override
    public Place select(int placeId) {
        return placeRepository.select(placeId);
    }
}
