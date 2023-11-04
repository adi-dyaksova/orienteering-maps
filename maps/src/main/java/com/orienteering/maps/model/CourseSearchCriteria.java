package com.orienteering.maps.model;
import java.util.Optional;
import java.util.UUID;

public class CourseSearchCriteria {

    private final Optional<UUID> mapId;
    private final Optional<Course.Category> category;
    private  final Optional<Course.AgeGroup> ageGroup;
    private final Optional<Course.Discipline> discipline;
    private final Optional<Integer> controls;
    private final Optional<Double> distance;
    private final Optional<Boolean> isCompetition;

    public CourseSearchCriteria(Optional<UUID> mapId, Optional<Course.Category> category, Optional<Course.AgeGroup> ageGroup, Optional<Course.Discipline> discipline, Optional<Integer> controls, Optional<Double> distance, Optional<Boolean> isCompetition) {
        this.mapId = mapId;
        this.category = category;
        this.ageGroup = ageGroup;
        this.discipline = discipline;
        this.controls = controls;
        this.distance = distance;
        this.isCompetition = isCompetition;
    }

    public Optional<UUID> getMapId() {
        return mapId;
    }

    public Optional<Course.Category> getCategory() {
        return category;
    }

    public Optional<Course.AgeGroup> getAgeGroup() {
        return ageGroup;
    }

    public Optional<Course.Discipline> getDiscipline() {
        return discipline;
    }

    public Optional<Integer> getControls() {
        return controls;
    }

    public Optional<Double> getDistance() {
        return distance;
    }

    public Optional<Boolean> isCompetition() {
        return isCompetition;
    }
}
