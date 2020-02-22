import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class SlackWebhook {

    public static void  sendSlackMessage(String webhookUrl, String text) {
        URL url = null;
        try {
            url = new URL(webhookUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            // Default is GET
            connection.setRequestMethod("POST");
            // Add request header
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");
            // Add content type
            connection.setRequestProperty("Content-Type", "application/json");
            // If you are doing a POST you need to make sure doOutPut is enabled
            connection.setDoOutput(true);
            // Setup the JSON
            String json = "{ \"text\": \"" + text + "\" }";

            OutputStream stream = connection.getOutputStream();
            stream.write(json.getBytes());
            stream.flush();
            stream.close();
            connection.getInputStream().close(); //I'm not sure why but it doesn't work without getting the InputStream
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
            // Do not send a Slack message here. Something is wrong with Slack and how the message is being sent so it could put the program into a loop
        }
    }
}
