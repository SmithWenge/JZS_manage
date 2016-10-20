package com.jzs.function.admin.region.service;


import com.jzs.function.admin.region.Region;

import java.util.List;

public interface RegionServiceI {
    List<Region> list();
    Boolean add(Region region,String logUser);
    Boolean edit(Region region,String logUser);
    Boolean cancle(int regionId,String logUser);
    Region select(int regionId);
}
