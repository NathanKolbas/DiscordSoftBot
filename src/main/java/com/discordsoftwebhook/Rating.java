package com.discordsoftwebhook;

import com.fasterxml.jackson.annotation.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Rating {
    private String id;
    private long points;
    private String description;
    private String longDescription;

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
}
