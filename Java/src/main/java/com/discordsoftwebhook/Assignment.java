package com.discordsoftwebhook;

import com.fasterxml.jackson.annotation.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Assignment {
    private long id;
    private String description;
    private String dueAt;
    private String unlockAt;
    private String lockAt;
    private long pointsPossible;
    private String gradingType;
    private long assignmentGroupID;
    private Object gradingStandardID;
    private String createdAt;
    private String updatedAt;
    private boolean peerReviews;
    private boolean automaticPeerReviews;
    private long position;
    private boolean gradeGroupStudentsIndividually;
    private boolean anonymousPeerReviews;
    private Object groupCategoryID;
    private boolean postToSis;
    private boolean moderatedGrading;
    private boolean omitFromFinalGrade;
    private boolean intraGroupPeerReviews;
    private boolean anonymousInstructorAnnotations;
    private boolean anonymousGrading;
    private boolean gradersAnonymousToGraders;
    private long graderCount;
    private boolean graderCommentsVisibleToGraders;
    private Object finalGraderID;
    private boolean graderNamesVisibleToFinalGrader;
    private long allowedAttempts;
    private String secureParams;
    private long courseID;
    private String name;
    private String[] submissionTypes;
    private boolean hasSubmittedSubmissions;
    private boolean dueDateRequired;
    private long maxNameLength;
    private boolean inClosedGradingPeriod;
    private boolean isQuizAssignment;
    private boolean canDuplicate;
    private Long originalCourseID;
    private Long originalAssignmentID;
    private String originalAssignmentName;
    private Object originalQuizID;
    private String workflowState;
    private boolean muted;
    private String htmlURL;
    private Long quizID;
    private Boolean anonymousSubmissions;
    private boolean published;
    private boolean onlyVisibleToOverrides;
    private boolean lockedForUser;
    private String submissionsDownloadURL;
    private boolean postManually;
    private boolean anonymizeStudents;
    private boolean requireLockdownBrowser;
    private Boolean useRubricForGrading;
    private Boolean freeFormCriterionComments;
    private Rubric[] rubric;
    private RubricSettings rubricSettings;

    @JsonProperty("id")
    public long getID() { return id; }
    @JsonProperty("id")
    public void setID(long value) { this.id = value; }

    @JsonProperty("description")
    public String getDescription() { return description; }
    @JsonProperty("description")
    public void setDescription(String value) { this.description = value; }

    @JsonProperty("due_at")
    public String getDueAt() { return dueAt; }
    @JsonProperty("due_at")
    public void setDueAt(String value) { this.dueAt = value; }

    @JsonProperty("unlock_at")
    public String getUnlockAt() { return unlockAt; }
    @JsonProperty("unlock_at")
    public void setUnlockAt(String value) { this.unlockAt = value; }

    @JsonProperty("lock_at")
    public String getLockAt() { return lockAt; }
    @JsonProperty("lock_at")
    public void setLockAt(String value) { this.lockAt = value; }

    @JsonProperty("points_possible")
    public long getPointsPossible() { return pointsPossible; }
    @JsonProperty("points_possible")
    public void setPointsPossible(long value) { this.pointsPossible = value; }

    @JsonProperty("grading_type")
    public String getGradingType() { return gradingType; }
    @JsonProperty("grading_type")
    public void setGradingType(String value) { this.gradingType = value; }

    @JsonProperty("assignment_group_id")
    public long getAssignmentGroupID() { return assignmentGroupID; }
    @JsonProperty("assignment_group_id")
    public void setAssignmentGroupID(long value) { this.assignmentGroupID = value; }

    @JsonProperty("grading_standard_id")
    public Object getGradingStandardID() { return gradingStandardID; }
    @JsonProperty("grading_standard_id")
    public void setGradingStandardID(Object value) { this.gradingStandardID = value; }

    @JsonProperty("created_at")
    public String getCreatedAt() { return createdAt; }
    @JsonProperty("created_at")
    public void setCreatedAt(String value) { this.createdAt = value; }

    @JsonProperty("updated_at")
    public String getUpdatedAt() { return updatedAt; }
    @JsonProperty("updated_at")
    public void setUpdatedAt(String value) { this.updatedAt = value; }

    @JsonProperty("peer_reviews")
    public boolean getPeerReviews() { return peerReviews; }
    @JsonProperty("peer_reviews")
    public void setPeerReviews(boolean value) { this.peerReviews = value; }

    @JsonProperty("automatic_peer_reviews")
    public boolean getAutomaticPeerReviews() { return automaticPeerReviews; }
    @JsonProperty("automatic_peer_reviews")
    public void setAutomaticPeerReviews(boolean value) { this.automaticPeerReviews = value; }

    @JsonProperty("position")
    public long getPosition() { return position; }
    @JsonProperty("position")
    public void setPosition(long value) { this.position = value; }

    @JsonProperty("grade_group_students_individually")
    public boolean getGradeGroupStudentsIndividually() { return gradeGroupStudentsIndividually; }
    @JsonProperty("grade_group_students_individually")
    public void setGradeGroupStudentsIndividually(boolean value) { this.gradeGroupStudentsIndividually = value; }

    @JsonProperty("anonymous_peer_reviews")
    public boolean getAnonymousPeerReviews() { return anonymousPeerReviews; }
    @JsonProperty("anonymous_peer_reviews")
    public void setAnonymousPeerReviews(boolean value) { this.anonymousPeerReviews = value; }

    @JsonProperty("group_category_id")
    public Object getGroupCategoryID() { return groupCategoryID; }
    @JsonProperty("group_category_id")
    public void setGroupCategoryID(Object value) { this.groupCategoryID = value; }

    @JsonProperty("post_to_sis")
    public boolean getPostToSis() { return postToSis; }
    @JsonProperty("post_to_sis")
    public void setPostToSis(boolean value) { this.postToSis = value; }

    @JsonProperty("moderated_grading")
    public boolean getModeratedGrading() { return moderatedGrading; }
    @JsonProperty("moderated_grading")
    public void setModeratedGrading(boolean value) { this.moderatedGrading = value; }

    @JsonProperty("omit_from_final_grade")
    public boolean getOmitFromFinalGrade() { return omitFromFinalGrade; }
    @JsonProperty("omit_from_final_grade")
    public void setOmitFromFinalGrade(boolean value) { this.omitFromFinalGrade = value; }

    @JsonProperty("intra_group_peer_reviews")
    public boolean getIntraGroupPeerReviews() { return intraGroupPeerReviews; }
    @JsonProperty("intra_group_peer_reviews")
    public void setIntraGroupPeerReviews(boolean value) { this.intraGroupPeerReviews = value; }

    @JsonProperty("anonymous_instructor_annotations")
    public boolean getAnonymousInstructorAnnotations() { return anonymousInstructorAnnotations; }
    @JsonProperty("anonymous_instructor_annotations")
    public void setAnonymousInstructorAnnotations(boolean value) { this.anonymousInstructorAnnotations = value; }

    @JsonProperty("anonymous_grading")
    public boolean getAnonymousGrading() { return anonymousGrading; }
    @JsonProperty("anonymous_grading")
    public void setAnonymousGrading(boolean value) { this.anonymousGrading = value; }

    @JsonProperty("graders_anonymous_to_graders")
    public boolean getGradersAnonymousToGraders() { return gradersAnonymousToGraders; }
    @JsonProperty("graders_anonymous_to_graders")
    public void setGradersAnonymousToGraders(boolean value) { this.gradersAnonymousToGraders = value; }

    @JsonProperty("grader_count")
    public long getGraderCount() { return graderCount; }
    @JsonProperty("grader_count")
    public void setGraderCount(long value) { this.graderCount = value; }

    @JsonProperty("grader_comments_visible_to_graders")
    public boolean getGraderCommentsVisibleToGraders() { return graderCommentsVisibleToGraders; }
    @JsonProperty("grader_comments_visible_to_graders")
    public void setGraderCommentsVisibleToGraders(boolean value) { this.graderCommentsVisibleToGraders = value; }

    @JsonProperty("final_grader_id")
    public Object getFinalGraderID() { return finalGraderID; }
    @JsonProperty("final_grader_id")
    public void setFinalGraderID(Object value) { this.finalGraderID = value; }

    @JsonProperty("grader_names_visible_to_final_grader")
    public boolean getGraderNamesVisibleToFinalGrader() { return graderNamesVisibleToFinalGrader; }
    @JsonProperty("grader_names_visible_to_final_grader")
    public void setGraderNamesVisibleToFinalGrader(boolean value) { this.graderNamesVisibleToFinalGrader = value; }

    @JsonProperty("allowed_attempts")
    public long getAllowedAttempts() { return allowedAttempts; }
    @JsonProperty("allowed_attempts")
    public void setAllowedAttempts(long value) { this.allowedAttempts = value; }

    @JsonProperty("secure_params")
    public String getSecureParams() { return secureParams; }
    @JsonProperty("secure_params")
    public void setSecureParams(String value) { this.secureParams = value; }

    @JsonProperty("course_id")
    public long getCourseID() { return courseID; }
    @JsonProperty("course_id")
    public void setCourseID(long value) { this.courseID = value; }

    @JsonProperty("name")
    public String getName() { return name; }
    @JsonProperty("name")
    public void setName(String value) { this.name = value; }

    @JsonProperty("submission_types")
    public String[] getSubmissionTypes() { return submissionTypes; }
    @JsonProperty("submission_types")
    public void setSubmissionTypes(String[] value) { this.submissionTypes = value; }

    @JsonProperty("has_submitted_submissions")
    public boolean getHasSubmittedSubmissions() { return hasSubmittedSubmissions; }
    @JsonProperty("has_submitted_submissions")
    public void setHasSubmittedSubmissions(boolean value) { this.hasSubmittedSubmissions = value; }

    @JsonProperty("due_date_required")
    public boolean getDueDateRequired() { return dueDateRequired; }
    @JsonProperty("due_date_required")
    public void setDueDateRequired(boolean value) { this.dueDateRequired = value; }

    @JsonProperty("max_name_length")
    public long getMaxNameLength() { return maxNameLength; }
    @JsonProperty("max_name_length")
    public void setMaxNameLength(long value) { this.maxNameLength = value; }

    @JsonProperty("in_closed_grading_period")
    public boolean getInClosedGradingPeriod() { return inClosedGradingPeriod; }
    @JsonProperty("in_closed_grading_period")
    public void setInClosedGradingPeriod(boolean value) { this.inClosedGradingPeriod = value; }

    @JsonProperty("is_quiz_assignment")
    public boolean getIsQuizAssignment() { return isQuizAssignment; }
    @JsonProperty("is_quiz_assignment")
    public void setIsQuizAssignment(boolean value) { this.isQuizAssignment = value; }

    @JsonProperty("can_duplicate")
    public boolean getCanDuplicate() { return canDuplicate; }
    @JsonProperty("can_duplicate")
    public void setCanDuplicate(boolean value) { this.canDuplicate = value; }

    @JsonProperty("original_course_id")
    public Long getOriginalCourseID() { return originalCourseID; }
    @JsonProperty("original_course_id")
    public void setOriginalCourseID(Long value) { this.originalCourseID = value; }

    @JsonProperty("original_assignment_id")
    public Long getOriginalAssignmentID() { return originalAssignmentID; }
    @JsonProperty("original_assignment_id")
    public void setOriginalAssignmentID(Long value) { this.originalAssignmentID = value; }

    @JsonProperty("original_assignment_name")
    public String getOriginalAssignmentName() { return originalAssignmentName; }
    @JsonProperty("original_assignment_name")
    public void setOriginalAssignmentName(String value) { this.originalAssignmentName = value; }

    @JsonProperty("original_quiz_id")
    public Object getOriginalQuizID() { return originalQuizID; }
    @JsonProperty("original_quiz_id")
    public void setOriginalQuizID(Object value) { this.originalQuizID = value; }

    @JsonProperty("workflow_state")
    public String getWorkflowState() { return workflowState; }
    @JsonProperty("workflow_state")
    public void setWorkflowState(String value) { this.workflowState = value; }

    @JsonProperty("muted")
    public boolean getMuted() { return muted; }
    @JsonProperty("muted")
    public void setMuted(boolean value) { this.muted = value; }

    @JsonProperty("html_url")
    public String getHTMLURL() { return htmlURL; }
    @JsonProperty("html_url")
    public void setHTMLURL(String value) { this.htmlURL = value; }

    @JsonProperty("quiz_id")
    public Long getQuizID() { return quizID; }
    @JsonProperty("quiz_id")
    public void setQuizID(Long value) { this.quizID = value; }

    @JsonProperty("anonymous_submissions")
    public Boolean getAnonymousSubmissions() { return anonymousSubmissions; }
    @JsonProperty("anonymous_submissions")
    public void setAnonymousSubmissions(Boolean value) { this.anonymousSubmissions = value; }

    @JsonProperty("published")
    public boolean getPublished() { return published; }
    @JsonProperty("published")
    public void setPublished(boolean value) { this.published = value; }

    @JsonProperty("only_visible_to_overrides")
    public boolean getOnlyVisibleToOverrides() { return onlyVisibleToOverrides; }
    @JsonProperty("only_visible_to_overrides")
    public void setOnlyVisibleToOverrides(boolean value) { this.onlyVisibleToOverrides = value; }

    @JsonProperty("locked_for_user")
    public boolean getLockedForUser() { return lockedForUser; }
    @JsonProperty("locked_for_user")
    public void setLockedForUser(boolean value) { this.lockedForUser = value; }

    @JsonProperty("submissions_download_url")
    public String getSubmissionsDownloadURL() { return submissionsDownloadURL; }
    @JsonProperty("submissions_download_url")
    public void setSubmissionsDownloadURL(String value) { this.submissionsDownloadURL = value; }

    @JsonProperty("post_manually")
    public boolean getPostManually() { return postManually; }
    @JsonProperty("post_manually")
    public void setPostManually(boolean value) { this.postManually = value; }

    @JsonProperty("anonymize_students")
    public boolean getAnonymizeStudents() { return anonymizeStudents; }
    @JsonProperty("anonymize_students")
    public void setAnonymizeStudents(boolean value) { this.anonymizeStudents = value; }

    @JsonProperty("require_lockdown_browser")
    public boolean getRequireLockdownBrowser() { return requireLockdownBrowser; }
    @JsonProperty("require_lockdown_browser")
    public void setRequireLockdownBrowser(boolean value) { this.requireLockdownBrowser = value; }

    @JsonProperty("use_rubric_for_grading")
    public Boolean getUseRubricForGrading() { return useRubricForGrading; }
    @JsonProperty("use_rubric_for_grading")
    public void setUseRubricForGrading(Boolean value) { this.useRubricForGrading = value; }

    @JsonProperty("free_form_criterion_comments")
    public Boolean getFreeFormCriterionComments() { return freeFormCriterionComments; }
    @JsonProperty("free_form_criterion_comments")
    public void setFreeFormCriterionComments(Boolean value) { this.freeFormCriterionComments = value; }

    @JsonProperty("rubric")
    public Rubric[] getRubric() { return rubric; }
    @JsonProperty("rubric")
    public void setRubric(Rubric[] value) { this.rubric = value; }

    @JsonProperty("rubric_settings")
    public RubricSettings getRubricSettings() { return rubricSettings; }
    @JsonProperty("rubric_settings")
    public void setRubricSettings(RubricSettings value) { this.rubricSettings = value; }
}
