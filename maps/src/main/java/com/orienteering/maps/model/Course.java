package com.orienteering.maps.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
public class Course {
    public Course(@JsonProperty("id") Integer id, @JsonProperty("mapId") Integer mapId, @JsonProperty("category") Category category, @JsonProperty("ageGroup") AgeGroup ageGroup, @JsonProperty("discipline") Discipline discipline, @JsonProperty("controls") int controls, @JsonProperty("distance") double distance, @JsonProperty("isCompetition") boolean isCompetition) {
        this.courseId = id;
        this.mapId = mapId;
        this.category = category;
        this.ageGroup = ageGroup;
        this.discipline = discipline;
        this.controls = controls;
        this.distance = distance;
        this.isCompetition = isCompetition;
    }

    public Course(){
        this.courseId = 0;
        this.mapId= 0;
        this.category = Category.NONE;
        this.ageGroup = AgeGroup.NONE;
        this.discipline = Discipline.NONE;
        this.controls = 0;
        this.distance = 0;
        this.isCompetition = false;
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
    @Id
    @GeneratedValue()
    @Column(name = "course_id")
    private  Integer courseId;
    @ManyToOne(targetEntity = Map.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "map_id", insertable = false, updatable = false)

    private  Map map;

    @Column(name = "map_id")
    private  Integer mapId;
    private  Category category;
    private  AgeGroup ageGroup;
    private  Discipline discipline;
    private  int controls;

    public Integer getCourseId() {
        return courseId;
    }
    public Integer getMapId() {
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

    private  double distance;
    // fileType?? - ocad, pdf
    private  boolean isCompetition;


   // public Course copy(Integer id){
     //   return new Course(id, this.mapId, this.category,this.ageGroup,this.discipline,this.controls,this.distance,this.isCompetition);
   // }

    public void setCategory(Category category) {
        this.category = category;
    }
    public void setAgeGroup(AgeGroup ageGroup) {
        this.ageGroup = ageGroup;
    }
    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }
    public void setControls(int controls) {
        this.controls = controls;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
    public void setIsCompetition(boolean isCompetition) {
        this.isCompetition = isCompetition;
    }

}
