package model;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.time.LocalTime;

// Uses Apache Commons IO
public class FileBackupApp {
    private File src;
    private File dest;

    public FileBackupApp() {
        src = dest = null;
    }

    // REQUIRES: valid src and dest File objects
    // MODIFIES: file system
    // EFFECTS:
    // 1. hasFreeMemory
    // 2. copyFolder
    // 3. createTimeStamp
    public void backup() throws Exception {
        if (!hasFreeMemory()) {
            throw new Exception("Not enough free space!");
        }

        copyFolder(src, dest);
        createTimeStamp();
    }

    // REQUIRES: src and dest are relative to project directory
    // MODIFIES: this
    // EFFECTS: creates and stores File objects according to given String paths
    public void inputFilePaths(String src, String dest) {
        this.src = new File(src);
        this.dest = new File(dest);
    }

    // TODO: test for subfolder copying
    // REQUIRES: valid src
    // MODIFIES: file system
    // EFFECTS: copies src directory to dest directory and handles exceptions
    // If there are files in the dest, nothing happens to them (for now >:D)
    public boolean copyFolder(File src, File dest) {
        try {
            FileUtils.copyDirectory(src, dest);
        } catch (IOException e) {
            System.out.println("Error [copy folder]: " + e.getMessage());
            return false;
        }
        return true;
    }

    // TODO: make timestamp file hidden
    // REQUIRES: src and dest are not null (i.e. a backup has already been created)
    // MODIFIES: destination directory
    // EFFECTS: creates a txt file containing the source directory and current time
    public boolean createTimeStamp() {
        File timestamp = new File(dest.getPath() + "/backup_timestamp.txt");
        try {
            FileUtils.writeStringToFile(timestamp,
                    "Source: " + src.getPath() + "\n" + LocalTime.now().toString(),
                    Charset.defaultCharset());
        } catch (IOException e) {
            System.out.println("Error [timestamp]: " + e.getMessage());
            return false;
        }
        return true;
    }

    // REQUIRES: valid dest and src File objects
    // EFFECTS: checks that destination has enough space for copying src directory
    public boolean hasFreeMemory() {
        if (dest.getFreeSpace() < FileUtils.sizeOfDirectory(src)) {
            System.out.println("Error [memory check]: Not enough free space in destination directory!");
            return false;
        } else {
            return true;
        }
    }

    public File getSrc() {
        return src;
    }

    public File getDest() {
        return dest;
    }

}
