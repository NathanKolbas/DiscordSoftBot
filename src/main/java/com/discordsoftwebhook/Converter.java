package com.discordsoftwebhook;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.core.JsonProcessingException;

public class Converter {
    // Serialize/deserialize helpers

    public static CanvasJSON[] fromJsonString(String json) throws IOException {
        return getObjectReader().readValue(json);
    }

    public static String toJsonString(CanvasJSON[] obj) throws JsonProcessingException {
        return getObjectWriter().writeValueAsString(obj);
    }

    public static void writeJsonFile(String filePath, CanvasJSON[] canvasJSONS) throws IOException {
        Files.write(Paths.get(filePath), Converter.toJsonString(canvasJSONS).getBytes());
    }

    public static CanvasJSON[] readJsonFile(String filePath) throws IOException {
        StringBuilder jsonString = new StringBuilder();
        File file = new File(filePath);
        if (file.exists()) {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                jsonString.append(scanner.nextLine());
            }
        }
        return Converter.fromJsonString(jsonString.toString());
    }

    private static ObjectReader reader;
    private static ObjectWriter writer;

    private static void instantiateMapper() {
        ObjectMapper mapper = new ObjectMapper();
        reader = mapper.readerFor(CanvasJSON[].class);
        writer = mapper.writerFor(CanvasJSON[].class);
    }

    private static ObjectReader getObjectReader() {
        if (reader == null) instantiateMapper();
        return reader;
    }

    private static ObjectWriter getObjectWriter() {
        if (writer == null) instantiateMapper();
        return writer;
    }
}
