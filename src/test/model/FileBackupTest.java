package model;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class FileBackupTest {
    private FileBackup app;

    public FileBackupTest() {
    }

    @BeforeEach
    public void setup() {
        app = new FileBackup();
        app.inputFilePaths("src/inTest", "src/outTest");
    }

    @Test
    public void backupTest() {
        assertTrue(app.getSrc().exists());
        try {
            app.backup();
        } catch (Exception e) {
            System.out.println("Error [backup]: " + e.getMessage());
        }
    }

    @Test
    public void copyFolderTest() {
        File source = new File("src/inTest");
        File destination = new File("src/outTest");

        assertTrue(source.exists());
        try {
            app.copyFolder(source, destination);
        } catch (Exception e) {
            System.out.println("Error [copy folder]: " + e.getMessage());
        }

    }

    @Test
    public void inputFilePathsTest() {
        app.inputFilePaths("src/inTest", "src/outTest");
        assertEquals("src\\inTest", app.getSrc().getPath());
        assertEquals("src\\outTest", app.getDest().getPath());
    }

    @Test
    public void createTimestampTest() {
        assertTrue(app.getDest().exists());
        assertTrue(app.getSrc().exists());
        try {
            app.createTimeStamp();
        } catch (IOException e) {
            System.out.println("Error [time stamp]: " + e.getMessage());
        }
    }

    @Test
    public void checkFreeMemoryPrintTest() {
        System.out.println("Destination space (GB): " + app.getDest().getFreeSpace() / 1000000000.0);
        System.out.println("Source size (bytes): " + FileUtils.sizeOfDirectory(app.getSrc()));
    }

}