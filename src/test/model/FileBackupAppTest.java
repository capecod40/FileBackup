package model;

import org.junit.*;
import org.junit.jupiter.api.*;

import java.nio.file.Files;

class FileBackupAppTest {
    private FileBackupApp app;

    public FileBackupAppTest() {
    }

    @BeforeEach
    public void setup() {
        app = new FileBackupApp();
    }

    @Test
    public void inputPathsTest() {
        app.inputPaths("in.txt", "out.txt");
        if (!Files.exists(app.getSrc())) {
            System.out.println("File does not exist!");
        }
    }
}