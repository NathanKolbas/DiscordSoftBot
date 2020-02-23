package com.discordsoftwebhook;

import com.fasterxml.jackson.annotation.*;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class CanvasJSON {
    private String contextType;
    private long courseID;
    private String type;
    private String ignore;
    private String ignorePermanently;
    private Assignment assignment;
    private String htmlURL;

    @JsonProperty("context_type")
    public String getContextType() { return contextType; }
    @JsonProperty("context_type")
    public void setContextType(String value) { this.contextType = value; }

    @JsonProperty("course_id")
    public long getCourseID() { return courseID; }
    @JsonProperty("course_id")
    public void setCourseID(long value) { this.courseID = value; }

    @JsonProperty("type")
    public String getType() { return type; }
    @JsonProperty("type")
    public void setType(String value) { this.type = value; }

    @JsonProperty("ignore")
    public String getIgnore() { return ignore; }
    @JsonProperty("ignore")
    public void setIgnore(String value) { this.ignore = value; }

    @JsonProperty("ignore_permanently")
    public String getIgnorePermanently() { return ignorePermanently; }
    @JsonProperty("ignore_permanently")
    public void setIgnorePermanently(String value) { this.ignorePermanently = value; }

    @JsonProperty("assignment")
    public Assignment getAssignment() { return assignment; }
    @JsonProperty("assignment")
    public void setAssignment(Assignment value) { this.assignment = value; }

    @JsonProperty("html_url")
    public String getHTMLURL() { return htmlURL; }
    @JsonProperty("html_url")
    public void setHTMLURL(String value) { this.htmlURL = value; }

    public static CanvasJSON[]  getToDoAPI(int courseID, String token) throws Exception {
        URL url = new URL("https://canvas.unl.edu/api/v1/courses/" + courseID + "/todo");
        StringBuilder response = new StringBuilder();
        try {
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            // Optional default is GET
            connection.setRequestMethod("GET");
            // Add request header
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");
            // Add authorization
            connection.setRequestProperty("Authorization", "Bearer " + token);

            int responseCode = connection.getResponseCode();
            System.out.println("Response Code : " + responseCode);
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Read JSON response and print
        return Converter.fromJsonString(response.toString());
    }
}
