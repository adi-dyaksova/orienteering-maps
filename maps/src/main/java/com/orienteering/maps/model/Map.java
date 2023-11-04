package com.orienteering.maps.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
//import org.hibernate.validator.constraints.NotBLank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;
@Entity
public class Map {

@Id
//@GeneratedValue
    private final UUID id;
   // @NotNull
    @Column(name="map_year")
    private final int year;
    //private final Date datee;
    //Date(int year, int month, int date)
  //  @NotNull
   // @NotBlank
    private final String country;
 //   @NotNull
   // @NotBlank
    private final String city;
    //@NotNull
    private final int scale;

    //String name
    //String description
    //coordinates - X km radius search
    //double latitude, double longitude

    // int controls
    // double distance
    // fileType?? - ocad, pdf
    public Map(@JsonProperty("id") UUID id, @JsonProperty("year") int year, @JsonProperty("country") String country, @JsonProperty("city") String city, @JsonProperty("scale") int scale) {
        this.id = id;
        this.year = year;
        this.country = country;
        this.city = city;
        this.scale = scale;
    }

    public UUID getId() {
        return id;
    }

    public int getYear() {
        return year;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }
    public int getScale() {
        return scale;
    }
    public Map copy(UUID id){
        return new Map(id,this.getYear(),this.getCountry(),this.getCity(), this.getScale());
    }
}
