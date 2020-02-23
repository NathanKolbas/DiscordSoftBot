package com.discordsoftwebhook;

import com.discordsoftwebhook.Rating;
import com.fasterxml.jackson.annotation.*;

public class Rubric {
    private String id;
    private long points;
    private String description;
    private String longDescription;
    private Object ignoreForScoring;
    private boolean criterionUseRange;
    private Rating[] ratings;

    @JsonProperty("id")
    public String getID() { return id; }
    @JsonProperty("id")
    public void setID(String value) { this.id = value; }

    @JsonProperty("points")
    public long getPoints() { return points; }
    @JsonProperty("points")
    public void setPoints(long value) { this.points = value; }

    @JsonProperty("description")
    public String getDescription() { return description; }
    @JsonProperty("description")
    public void setDescription(String value) { this.description = value; }

    @JsonProperty("long_description")
    public String getLongDescription() { return longDescription; }
    @JsonProperty("long_description")
    public void setLongDescription(String value) { this.longDescription = value; }

    @JsonProperty("ignore_for_scoring")
    public Object getIgnoreForScoring() { return ignoreForScoring; }
    @JsonProperty("ignore_for_scoring")
    public void setIgnoreForScoring(Object value) { this.ignoreForScoring = value; }

    @JsonProperty("criterion_use_range")
    public boolean getCriterionUseRange() { return criterionUseRange; }
    @JsonProperty("criterion_use_range")
    public void setCriterionUseRange(boolean value) { this.criterionUseRange = value; }

    @JsonProperty("ratings")
    public Rating[] getRatings() { return ratings; }
    @JsonProperty("ratings")
    public void setRatings(Rating[] value) { this.ratings = value; }
}
