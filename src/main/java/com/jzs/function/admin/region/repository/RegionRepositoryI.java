package com.jzs.function.admin.region.repository;

import com.jzs.function.admin.region.Region;

import java.util.List;

public interface RegionRepositoryI {
    List<Region> list();
    Boolean add(Region region);
    Boolean update(Region region);
    Boolean delete(int regionId);
    Region select(int regionId);
}
