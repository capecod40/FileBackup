package model;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

// Tests for FileBackup class
//      test for toJson() in JsonWriter test class
class FileBackupTest extends FileBackup {
    private FileBackup app;

    public FileBackupTest() {
    }

    @BeforeEach
    public void setup() {
        app = new FileBackup();
        app.inputFilePaths("src/test/inTest", "src/test/outTest");
    }

    @Test
    public void backupTest() {
        assertTrue(app.getSrc().exists());
        try {
            app.backup();
        } catch (Exception e) {
            System.out.println("Error [backup]: " + e.getMessage());
            Assertions.fail();
        }
    }

    @Test
    public void backupTestRandDest() {
        Random random = new Random();
        int rand = random.nextInt();
        app.inputFilePaths("src/test/inTest", "src/test/outTest_" + rand);
        assertTrue(app.getSrc().exists());
        try {
            app.backup();
        } catch (Exception e) {
            System.out.println("Error [backup]: " + e.getMessage());
            Assertions.fail();
        }
    }

    @Test
    public void backupFailTest() {
        app.inputFilePaths("<>", "<>\"/?|*");
        try {
            app.backup();
        } catch (Exception e) {
            System.out.println("Error [backup]: " + e.getMessage());
            return;
        }
        Assertions.fail();
    }

    @Test
    public void backupFileTest() {
        app.inputFilePaths("src/test/inTest/test.txt", "src/test/bluh");
        try {
            app.backup();
        } catch (Exception e) {
            System.out.println("Error [backup]: " + e.getMessage());
            Assertions.fail();
        }
    }

    @Test
    public void copyFolderTest() {
        File source = new File("src/test/inTest");
        File destination = new File("src/test/outTest");

        assertTrue(source.exists());
        try {
            app.copyFolder(source, destination);
        } catch (Exception e) {
            System.out.println("Error [copy folder]: " + e.getMessage());
            Assertions.fail();
        }
    }

    @Test
    public void copyFolderFailTest() {
        File source = new File("<>");
        File destination = new File("");

        try {
            app.copyFolder(source, destination);
        } catch (Exception e) {
            System.out.println("Error [copy folder]: " + e.getMessage());
            return;
        }
        Assertions.fail();
    }

    @Test
    public void inputFilePathsTest() {
        app.inputFilePaths("src/test/inTest", "src/test/outTest");
        assertEquals("src\\test\\inTest", app.getSrc().getPath());
        assertEquals("src\\test\\outTest", app.getDest().getPath());
    }

    @Test
    public void createTimestampTest() {
        assertTrue(app.getDest().exists());
        assertTrue(app.getSrc().exists());
        try {
            app.createTimeStamp();
        } catch (IOException e) {
            System.out.println("Error [time stamp]: " + e.getMessage());
            Assertions.fail();
        }
    }

    @Test
    public void hasFreeMemoryDummyPrintTest() {
        System.out.println("Destination space (GB): " + app.getDest().getFreeSpace()/ 1000000000.0);
        System.out.println("Source size (bytes): " + FileUtils.sizeOfDirectory(app.getSrc()));
    }

    @Test
    public void logPrintEmptyTest() {
        app.printLog();
    }

    @Test
    public void logPrintTest() {
        try {
            app.backup();
            app.backup();
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
        app.printLog();
    }

    @Test
    public void setLogTest() {
        ArrayList<BackupData> list = new ArrayList<>();
        list.add(new BackupData("testSource", "testTime"));
        app.setLog(list);
        assertEquals(list, app.getLog());
        assertEquals(1, app.getLog().size());
        assertEquals("testSource", app.getLog().get(0).getSource());
    }

    // Not possible to test
/*    @Test
    public void hasFreeMemoryFailTest() {
        hasFreeMemoryDummyPrintTest();
        assertTrue(app.getDest().getFreeSpace() < FileUtils.sizeOfDirectory(app.getSrc()));
        try {
            app.hasFreeMemory();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        Assertions.fail();
    }*/
}