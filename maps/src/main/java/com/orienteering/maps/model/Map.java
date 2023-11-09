package com.orienteering.maps.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
//import org.hibernate.validator.constraints.NotBLank;

@Entity
public class Map {

@Id
@GeneratedValue()
@Column(name="map_id")
    private Integer mapId;
    @Column(unique=true)
    private String name;

   // @NotNull
    @Column(name="map_year")
    private int year;
    //private final Date datee;
    //Date(int year, int month, int date)
  //  @NotNull
   // @NotBlank
    private  String country;
 //   @NotNull
   // @NotBlank
    private  String city;
    //@NotNull
    private  int scale;

    private String folderName = "D:\\maps\\maps\\src\\main\\java\\com\\orienteering\\maps\\filesystem\\";

    //String description
    //coordinates - X km radius search
    //double latitude, double longitude
    // fileType?? - ocad, pdf


    public Map(@JsonProperty("id") Integer mapId,@JsonProperty("name") String name, @JsonProperty("year") int year, @JsonProperty("country") String country, @JsonProperty("city") String city, @JsonProperty("scale") int scale) {
        this.mapId = mapId;
        this.name=name;
        this.year = year;
        this.country = country;
        this.city = city;
        this.scale = scale;
        this.folderName="D:\\maps\\maps\\src\\main\\java\\com\\orienteering\\maps\\filesystem\\";
    }

 public Map(){
     this.mapId = 0;
     this.name="";
     this.year = 0;
     this.country = "";
     this.city = "";
     this.scale = 0;
     this.folderName="D:\\maps\\maps\\src\\main\\java\\com\\orienteering\\maps\\filesystem\\";
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

    public String getFolderName(){return folderName;}
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
    public  void setFolderName(String folderName){this.folderName=folderName;}
    //public Map copy(Integer id){
     //   return new Map(id,this.getYear(),this.getCountry(),this.getCity(), this.getScale());
    //}
}
