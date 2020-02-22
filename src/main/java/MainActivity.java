import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class MainActivity {
    final static int SOFT161_ID = 80912;
    final static String JSON_FILE_NAME = "CurrentToDos.json";

    public static void main(String[] args) {
        // This is where the program will start when ran.
        String token = args[0];

        CanvasJSON[] newToDos = null;
        try {
            newToDos = CanvasJSON.getToDoAPI(SOFT161_ID, token);
        } catch (Exception e) {
            // Was unable to GET from Canvas for some reason.
            e.printStackTrace();
        }

        // We check for the file and then IDs to see if this is the first time the assignment has been assigned.
        if (newToDos != null) {
            CanvasJSON[] pastToDos = Converter.readJsonFile(JSON_FILE_NAME);

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
                    // Even though the Date object is reporting that it is in CST it is actually in UTC time.
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                    Date dateNow = new Date();
                    try {
                        Date assignmentsDueDate = format.parse(currentToDo.getAssignment().getDueAt());
                        long diffInMillis = Math.abs(assignmentsDueDate.getTime() - dateNow.getTime());
                        long diffInDays = TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS);
                        if (diffInDays == 7 || diffInDays == 1) {
                            // Send out the message! It is a week or a day before it is due.
                            System.out.println("Send out the message! It is a week or a day before it is due.");
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                } else {
                    // This means it was the first time it was assigned.
                    // Send out the message!
                    System.out.println("Send out the message! This means it was the first time it was assigned.");
                }
            }

            // Add the new to dos to the JSON file
            Converter.writeJsonFile(JSON_FILE_NAME, newToDos);
        }
    }
}
