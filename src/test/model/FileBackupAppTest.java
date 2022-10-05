package model;

import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
    public void copyFileTest() {
        File input = new File("in.txt");
        File output = new File("out.txt");
        assertTrue(input.exists());
        assertTrue(output.exists());

        try {
            app.copyFile(input, output);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void copyFolderTest() {
        File source = new File("src/outTest");
        File destination = new File("src/inTest");

        assertTrue(source.exists());
        assertTrue(destination.exists());

        for (String item : source.list()) {
            Path path = new Path(item);
            try {
                Files.copy(path, destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (Exception e) {

            }
        }
    }

    @Test
    public void inputPathsTest() {
        app.inputPaths("in.txt", "out.txt");
        if (!Files.exists(app.getSrc())) {
            System.out.println("File does not exist!");
        }
    }
}