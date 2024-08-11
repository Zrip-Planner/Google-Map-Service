package com.zrip.google_map_service.service;

import com.google.maps.model.LatLng;
import com.zrip.google_map_service.model.Stop;

import java.util.ArrayList;
import java.util.List;

public interface StopService {
    List<Stop> getBusStops(LatLng location, Integer radius);
    List<Stop> getMetroStations(LatLng location, Integer radius);

    default List<Stop> getAllStops(LatLng location, Integer radius) {
        List<Stop> allStops = new ArrayList<>();
        allStops.addAll(getBusStops(location, radius));
        allStops.addAll(getMetroStations(location, radius));
        return allStops;
    }
}
