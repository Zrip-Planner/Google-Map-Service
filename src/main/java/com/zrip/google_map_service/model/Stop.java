package com.zrip.google_map_service.model;

import com.google.maps.model.LatLng;
import com.google.maps.model.PlacesSearchResult;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Stop {
    String name;
    LatLng location;
    Type transportType;

    public static Stop from(PlacesSearchResult result) {
        return Stop.builder()
                .name(result.name)
                .location(result.geometry.location)
                .build();
    }
}
