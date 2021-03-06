package com.discordsoftwebhook;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class MainActivity {
    final static int SOFT161_ID = 80912;
    final static String JSON_FILE_NAME = "CurrentToDos.json";

    private static CanvasJSON[] newToDos = null;

    public static PrintStream previousConsole;
    public static PrintStream previousErrConsole;
    public static ByteArrayOutputStream newConsole;
    public static String webhookUrlDiscord;
    public static String webhookUrlSlack;

    private static void sendMessage(String webhookUrl, String title, String description, String dueDateUnformatted, String htmlUrl) {
        DateFormat dateFormatterCST = new SimpleDateFormat("yyyy-MM-dd' 'h:mm a");
        dateFormatterCST.setTimeZone(TimeZone.getTimeZone("CST")); // CST is Central Standard Time
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        dateFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date dueDate = null;
        try {
            dueDate = dateFormatter.parse(dueDateUnformatted);
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Was unable to parse the time it was due");
            writeErrFile();
            SlackWebhook.sendSlackMessage(webhookUrlSlack, "There has been an error. Please check the log files.");
        }
        String formattedDueDate = dueDate == null ? "sorry, was unable to get the due date..." : dateFormatterCST.format(dueDate);

        // Create the Discord message
        DiscordWebhook webhook = new DiscordWebhook(webhookUrl);
        webhook.setUsername("Software Engineering Assignments Bot");
        webhook.addEmbed(new DiscordWebhook.EmbedObject()
                .setTitle(title)
                .setDescription(description + formattedDueDate)
                .setColor(Color.RED)
                .setUrl(htmlUrl));

        try {
            webhook.execute();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Unable to send out the Discord message...");
            writeErrFile();
            SlackWebhook.sendSlackMessage(webhookUrlSlack, "There has been an error. Please check the log files.");
        }
    }

    public static int getDaysUntil(String date) {
        float diffInDays = 0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date assignmentsDueDate = format.parse(date);
            Date now = format.parse(format.format(new Date()));
            long diffInMillis = assignmentsDueDate.getTime() - now.getTime();
            diffInDays = TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS);
            System.out.println(diffInDays);
        } catch (ParseException e) {
            e.printStackTrace();
            writeErrFile();
            System.out.println("Was unable to parse the time it was due");
            SlackWebhook.sendSlackMessage(webhookUrlSlack, "There has been an error. Please check the log files.");
        }
        return (int) diffInDays;
    }

    public static void main(String[] args) {
        // This is where the program will start when ran.
        // Preserve current console. It will no longer send System.out.print to the console. To restore run `System.setOut(previousConsole);`
        previousConsole = System.out;
        previousErrConsole = System.err;
        // Set the standard output to use newConsole so we can send logs to Slack.
        newConsole = new ByteArrayOutputStream();
        System.setOut(new PrintStream(newConsole));
        System.setErr(new PrintStream(newConsole));

        // Getting arguments
        String token = args[0]; // Bearer token for Canvas API
        webhookUrlDiscord = args[1];
        webhookUrlSlack = args[2];

        // Getting Canvas ToDos
        try {
            newToDos = CanvasJSON.getToDoAPI(SOFT161_ID, token);
        } catch (Exception e) {
            // Was unable to GET from Canvas for some reason.
            e.printStackTrace();
            writeErrFile();
            SlackWebhook.sendSlackMessage(webhookUrlSlack, "There has been an error. Please check the log files.");
        }

        // We check for the file and then IDs to see if this is the first time the assignment has been assigned.
        if (newToDos != null) {
            CanvasJSON[] pastToDos = null;
            try {
                pastToDos = Converter.readJsonFile(JSON_FILE_NAME);
            } catch (IOException e) {
                e.printStackTrace();
                writeErrFile();
                SlackWebhook.sendSlackMessage(webhookUrlSlack, "There has been an error. Please check the log files.");
            }

            // Getting all the past IDs
            Set<Long> pastIds = new HashSet<>();
            // Check if the file exists or if there was an error.
            if (pastToDos != null) {
                for (CanvasJSON canvasJSON : pastToDos) {
                    pastIds.add(canvasJSON.getAssignment().getID());
                }
            }

            for (CanvasJSON currentToDo : newToDos) {
                // Check each to-do
                // Currently setup to send out when assigned, one week, and the day before.
                if (pastIds.contains(currentToDo.getAssignment().getID())) {
                    // Just a note: even though the Date object is reporting that it is in CST it is actually in UTC time.
                    int diffInDays = getDaysUntil(currentToDo.getAssignment().getDueAt());
                    if (diffInDays == 7) {
                        // Send out the message! It is a week before it is due.
                        System.out.println("Send out the message! It is a week before it is due.");
                        // Creating the description
                        String description = "The assignment is due in one week at ";
                        sendMessage(webhookUrlDiscord, currentToDo.getAssignment().getName(), description, currentToDo.getAssignment().getDueAt(), currentToDo.getAssignment().getHTMLURL());
                    } else if (diffInDays == 1) {
                        // Send out the message! It is a day before it is due
                        System.out.println("Send out the message! It is a day before it is due.");
                        // Creating the description
                        String description = "The assignment is due tomorrow at ";
                        sendMessage(webhookUrlDiscord, currentToDo.getAssignment().getName(), description, currentToDo.getAssignment().getDueAt(), currentToDo.getAssignment().getHTMLURL());
                    }
                } else {
                    // Send out the message! This means it was the first time it was assigned.
                    System.out.println("Send out the message! This means it was the first time it was assigned.");

                    // Finding out how many days till it is due
                    int diffInDays = getDaysUntil(currentToDo.getAssignment().getDueAt());

                    // Creating the description
                    String description = diffInDays == 0 ? "The assignment is due on " : "The assignment is due in " + diffInDays + " days on ";
                    // This is a custom case but it shouldn't happen. Just to make the text cleaner.
                    if (diffInDays == 1) {
                        description = "The assignment is due tomorrow at ";
                    }
                    sendMessage(webhookUrlDiscord, currentToDo.getAssignment().getName(), description, currentToDo.getAssignment().getDueAt(), currentToDo.getAssignment().getHTMLURL());
                }
            }

            // Add the new to dos to the JSON file
            try {
                Converter.writeJsonFile(JSON_FILE_NAME, newToDos);
            } catch (IOException e) {
                e.printStackTrace();
                writeErrFile();
                SlackWebhook.sendSlackMessage(webhookUrlSlack, "There has been an error. Please check the log files.");
            }
        }
    }

    public static void writeErrFile() {
        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd' 'hh-mm-ss a");
        try {
            // Log the Canvas JSON string we got (if any)
            System.out.println(Converter.toJsonString(newToDos));
            // Write all logs to file
            Files.write(Paths.get(format.format(now) + ".log"), newConsole.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
