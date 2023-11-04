package com.orienteering.maps.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Course {
    public Course(@JsonProperty("id") UUID id, @JsonProperty("mapId") UUID mapId, @JsonProperty("category") Category category,@JsonProperty("ageGroup") AgeGroup ageGroup,@JsonProperty("discipline") Discipline discipline,@JsonProperty("controls") int controls,@JsonProperty("distance") double distance,@JsonProperty("isCompetition") boolean isCompetition) {
        this.id = id;
        this.mapId = mapId;
        this.category = category;
        this.ageGroup = ageGroup;
        this.discipline = discipline;
        this.controls = controls;
        this.distance = distance;
        this.isCompetition = isCompetition;
    }

    public enum Discipline {
        NONE,
        FOOT_O,
        SKI_O,
        TRAIL_O,
        MOUNTAIN_BIKE_O
    }
    public enum Category {
        NONE,
        SPRINT,
        KNOCK_OUT,
        MIDDLE,
        LONG,
        RELAY,
        SPRINT_RELAY,
        NIGHT,
        MARATHON
    }
    public enum AgeGroup {
        NONE,
        M10,M12,M14,M16,M18,M20,M23,M21E,M35,M45,M55,M65,M70,M75,M80,
        W10,W12,W14,W16,W18,W20,W23,W21E,W35,W45,W55,W65,W70,W75,W80
    }
    private final UUID id;
    private final UUID mapId;
    private final Category category;
    private final AgeGroup ageGroup;
    private final Discipline discipline;
    private final int controls;

    public UUID getId() {
        return id;
    }
    public UUID getMapId() {
        return mapId;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public int getControls() {
        return controls;
    }

    public double getDistance() {
        return distance;
    }

    public boolean isCompetition() {
        return isCompetition;
    }

    public Category getCategory() {
        return category;
    }

    public AgeGroup getAgeGroup() {
        return ageGroup;
    }

    private final double distance;
    // fileType?? - ocad, pdf
    private final boolean isCompetition;


    public Course copy(UUID id){
        return new Course(id, this.mapId, this.category,this.ageGroup,this.discipline,this.controls,this.distance,this.isCompetition);
    }


}
