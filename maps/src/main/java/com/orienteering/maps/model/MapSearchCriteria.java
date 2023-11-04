package com.orienteering.maps.model;

import java.util.Optional;

public class MapSearchCriteria {
    private final Optional<Integer> year;
    private final Optional<String> country;
    private final Optional<String> city;
    private  final Optional<Integer> scale;

    public MapSearchCriteria(Optional<Integer> year, Optional<String> country, Optional<String> city, Optional<Integer> scale) {
        this.year = year;
        this.country = country;
        this.city = city;
        this.scale = scale;
    }

    public Optional<Integer> getYear() {
        return year;
    }

    public Optional<String> getCountry() {
        return country;
    }

    public Optional<String> getCity() {
        return city;
    }

    public Optional<Integer> getScale() {
        return scale;
    }
}
