package com.zrip.google_map_service.configuration;

import com.google.maps.GeoApiContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GoogleMapsConfiguration {
    @Value("${google.api.key}")
    private String apiKey;

    @Bean
    public GeoApiContext getApiContext() {
        return new GeoApiContext.Builder()
                .apiKey(apiKey)
                .build();
    }
}
