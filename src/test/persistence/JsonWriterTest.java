package persistence;

import model.FileBackup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest {
    private static final String JSON_PATH = "./src/test/data/testData.json";
    private JsonWriter writer;
    private FileBackup backup;

    @BeforeEach
    public void setup() {
        writer = new JsonWriter(JSON_PATH);
        backup = new FileBackup();
        backup.inputFilePaths("src/test/inTest", "src/test/outTest");
        try {
            backup.backup();
        } catch (Exception e) {
            fail("Test setup: backup failed!");
        }
    }

    @Test
    public void openTest() {
        try {
            writer.open();
        } catch (IOException e) {
            fail("JsonWriter failed to open file!");
        } catch (Exception e) {
            fail("JsonWriter unknown exception!");
        }
        writer.close();
    }

    @Test
    public void writeTest() {
        try {
            writer.open();
            writer.write(backup);
            writer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail("Write test error!");
        }
    }
}
