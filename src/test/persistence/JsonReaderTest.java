package persistence;

import model.BackupData;
import model.FileBackup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest {
    private static final String JSON_PATH = "./src/test/data/testData.json";
    private JsonReader reader;
    private JsonWriter writer;
    private FileBackup backup;

    @BeforeEach
    public void setup() {
        reader = new JsonReader(JSON_PATH);
        writer = new JsonWriter(JSON_PATH);
        backup = new FileBackup();
        backup.inputFilePaths("src/test/backupTests/inTest", "src/test/backupTests/outTest");
        try {
            backup.backup();
            writer.open();
            writer.write(backup);
            writer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail("Test setup failed!");
        }

    }

    @Test
    public void readTest() {
        ArrayList<BackupData> initData = backup.getLog();
        try {
            reader.read(backup);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail("Read test error!");
        }
        assertFalse(initData == backup.getLog());
    }
}
