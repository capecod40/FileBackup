package model;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FileBackupAppTest {
    private FileBackupApp app;

    public FileBackupAppTest() {
    }

    @BeforeEach
    public void setup() {
        app = new FileBackupApp();
    }

    @Test
    public void copyFolderTest() {
        File source = new File("src/inTest");
        File destination = new File("src/outTest");

        assertTrue(source.exists());
        assertTrue(app.copyFolder(source, destination));

    }

    @Test
    public void inputFilePathsTest() {
        app.inputFilePaths("src/inTest", "src/outTest");
        assertEquals("src\\inTest", app.getSrc().getPath());
        assertEquals("src\\outTest", app.getDest().getPath());
    }

    @Test
    public void createTimestampTest() {
        app.inputFilePaths("src/inTest", "src/outTest");
        assertTrue(app.getDest().exists());
        assertTrue(app.getSrc().exists());
        assertTrue(app.createTimeStamp());
    }

}