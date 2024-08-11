package com.zrip.google_map_service.controller;

import com.google.maps.model.LatLng;
import com.zrip.google_map_service.model.Stop;
import com.zrip.google_map_service.service.StopService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/stop")
public class StopController {
    StopService service;
    @GetMapping("/bus")
    public List<Stop> getBusStops(@RequestParam double lat, @RequestParam double lng, @RequestParam Integer radius) {
        return service.getBusStops(new LatLng(lat, lng), radius);
    }

    @GetMapping("/metro")
    public List<Stop> getMetroStations(@RequestParam double lat, @RequestParam double lng, @RequestParam Integer radius) {
        return service.getMetroStations(new LatLng(lat, lng), radius);
    }

    @GetMapping("/all")
    public List<Stop> getAllStops(@RequestParam double lat, @RequestParam double lng, @RequestParam Integer radius) {
        return service.getAllStops(new LatLng(lat, lng), radius);
    }
}
