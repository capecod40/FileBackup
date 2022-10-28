package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

import model.BackupData;
import model.FileBackup;
import org.json.*;

// TODO: rework specs
// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public void read(FileBackup backup) throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        backup.setLog(parseLog(jsonObject));
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private ArrayList<BackupData> parseLog(JSONObject jsonObject) {
        ArrayList<BackupData> logData = new ArrayList<>();
        JSONArray jsonArray = jsonObject.getJSONArray("log");

        for (int i = 0; i < jsonArray.length(); i += 2) {
            logData.add(new BackupData(jsonArray.getString(i), jsonArray.getString(i + 1)));
        }

        return logData;
    }
}
