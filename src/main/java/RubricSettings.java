import com.fasterxml.jackson.annotation.*;

public class RubricSettings {
    private long id;
    private String title;
    private long pointsPossible;
    private boolean freeFormCriterionComments;
    private boolean hideScoreTotal;
    private boolean hidePoints;

    @JsonProperty("id")
    public long getID() { return id; }
    @JsonProperty("id")
    public void setID(long value) { this.id = value; }

    @JsonProperty("title")
    public String getTitle() { return title; }
    @JsonProperty("title")
    public void setTitle(String value) { this.title = value; }

    @JsonProperty("points_possible")
    public long getPointsPossible() { return pointsPossible; }
    @JsonProperty("points_possible")
    public void setPointsPossible(long value) { this.pointsPossible = value; }

    @JsonProperty("free_form_criterion_comments")
    public boolean getFreeFormCriterionComments() { return freeFormCriterionComments; }
    @JsonProperty("free_form_criterion_comments")
    public void setFreeFormCriterionComments(boolean value) { this.freeFormCriterionComments = value; }

    @JsonProperty("hide_score_total")
    public boolean getHideScoreTotal() { return hideScoreTotal; }
    @JsonProperty("hide_score_total")
    public void setHideScoreTotal(boolean value) { this.hideScoreTotal = value; }

    @JsonProperty("hide_points")
    public boolean getHidePoints() { return hidePoints; }
    @JsonProperty("hide_points")
    public void setHidePoints(boolean value) { this.hidePoints = value; }
}
