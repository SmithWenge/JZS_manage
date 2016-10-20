package com.jzs.function.admin.region.service;

import com.jzs.function.admin.region.Region;
import com.jzs.function.admin.region.repository.RegionRepositoryI;
import com.jzs.function.support.log.LogContent;
import com.jzs.function.support.log.repository.LogRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionService implements RegionServiceI{
    @Autowired
    private RegionRepositoryI regionRepository;
    @Autowired
    private LogRepositoryI logRepository;

    @Override
    public List<Region> list() {
        return regionRepository.list();
    }

    @Override
    public Boolean add(Region region, String logUser) {

        if (regionRepository.add(region)) {
            LogContent logContent = new LogContent(logUser, "添加区" + region.getRegionName(), 1, 3);
            logRepository.insertLog(logContent);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Boolean edit(Region region, String logUser) {

        if (regionRepository.update(region)) {
            LogContent logContent = new LogContent(logUser, "编辑区" + region.getRegionName(), 1, 4);
            logRepository.insertLog(logContent);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Boolean cancle(int regionId,String logUser) {

        if (regionRepository.delete(regionId)) {
            LogContent logContent = new LogContent(logUser, "删除区的Id为" + regionId, 1, 2);
            logRepository.insertLog(logContent);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Region select(int regionId) {
        return regionRepository.select(regionId);
    }
}
