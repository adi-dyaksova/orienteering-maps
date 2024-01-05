package com.orienteering.maps.model;
import java.util.Optional;

public class CourseSearchCriteria {

    private final Optional<Integer> mapId;
    private final Optional<Course.Category> category;
    private  final Optional<Course.AgeGroup> ageGroup;
    private final Optional<Course.Discipline> discipline;
    private final Optional<Integer> minControls;
    private final Optional<Integer> maxControls;
    private final Optional<Double> minDistance;
    private final Optional<Double> maxDistance;
    private final Optional<Boolean> isCompetition;

    public CourseSearchCriteria(Optional<Integer> mapId, Optional<Course.Category> category, Optional<Course.AgeGroup> ageGroup, Optional<Course.Discipline> discipline, Optional<Integer> minControls,Optional<Integer> maxControls, Optional<Double> minDistance,Optional<Double> maxDistance, Optional<Boolean> isCompetition) {
        this.mapId = mapId;
        this.category = category;
        this.ageGroup = ageGroup;
        this.discipline = discipline;
        this.minControls = minControls;
        this.maxControls = maxControls;
        this.minDistance = minDistance;
        this.maxDistance = maxDistance;
        this.isCompetition = isCompetition;
    }

    public Optional<Integer> getMapId() {
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

    public Optional<Integer> getMinControls() {
        return minControls;
    }

    public Optional<Integer> getMaxControls() {
        return maxControls;
    }
    public Optional<Double> getMinDistance() {
        return minDistance;
    }
    public Optional<Double> getMaxDistance() {
        return maxDistance;
    }

    public Optional<Boolean> isCompetition() {
        return isCompetition;
    }
}
