package com.zrip.google_map_service.service.Impl;

import com.google.maps.GeoApiContext;
import com.google.maps.PlacesApi;
import com.google.maps.model.LatLng;
import com.google.maps.model.PlacesSearchResponse;
import com.zrip.google_map_service.model.Stop;
import com.zrip.google_map_service.model.Type;
import com.zrip.google_map_service.service.StopService;
import com.zrip.google_map_service.util.Constants;
import com.zrip.google_map_service.util.exceptions.GoogleMapServiceException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StopServiceImpl implements StopService {
    private GeoApiContext context;

    @Override
    public List<Stop> getBusStops(LatLng location, Integer radius) {
        return getFromGoogle(Constants.BUS_STOP, location, radius);
    }

    @Override
    public List<Stop> getMetroStations(LatLng location, Integer radius) {
        return getFromGoogle(Constants.METRO_STATION, location, radius);
    }

    public List<Stop> getFromGoogle(String keyword, LatLng location, Integer radius) {
        try {
            PlacesSearchResponse response = PlacesApi.nearbySearchQuery(context, location)
                    .radius(radius)
                    .keyword(keyword)
                    .await();
            return Arrays.stream(response.results)
                    .map(Stop::from)
                    .peek(stop -> stop.setTransportType(getTransportType(keyword)))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new GoogleMapServiceException(e.getMessage(), HttpStatus.FAILED_DEPENDENCY);
        }
    }

    private Type getTransportType(String keyword) {
        return keyword.equals(Constants.BUS_STOP) ? Type.BUS_STOP : Type.METRO_STATION;
    }
}
