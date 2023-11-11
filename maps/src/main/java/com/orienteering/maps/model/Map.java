package com.orienteering.maps.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
//import org.hibernate.validator.constraints.NotBLank;

@Entity
public class Map {

@Id
@GeneratedValue()
@Column(name="map_id")
    private Integer mapId;
    @Column(unique=true)
    private String name;

    @NotNull
    @Column(name="map_year")
    private Integer year;
   @NotNull
   @NotBlank
    private  String country;
  @NotNull
  @NotBlank
    private  String city;
  @NotNull
    private  Integer scale;

    private String description;

    public Map(@JsonProperty("id") Integer mapId,@JsonProperty("name") String name, @JsonProperty("year") Integer year, @JsonProperty("country") String country, @JsonProperty("city") String city, @JsonProperty("scale") Integer scale,@JsonProperty("description") String description) {
        this.mapId = mapId;
        this.name=name;
        this.year = year;
        this.country = country;
        this.city = city;
        this.scale = scale;
        this.description=description;
    }

 public Map(){
     this.mapId = 0;
     this.name="";
     this.year = 0;
     this.country = "";
     this.city = "";
     this.scale = 0;
     this.description="";
 }

    public Integer getMapId() {
        return mapId;
    }
    public String getName(){return name;}

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

    public String getDescription(){return description;}
    public void setName(String name) {this.name=name;}
    public void setYear(int year) {
        this.year = year;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public void setScale(int scale) {
        this.scale = scale;
    }
    public  void setDescription(String description){this.description=description;}

}
