package model;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.time.LocalTime;


// Contains all methods and data fields for basic functionality
// of file backup application. Additional features will
// probably be implemented in smaller separate classes.
// This class copies an existing directory to another directory.
// If the destination does not exist, a new folder with the specified
// name will be created. A txt file with information on the source directory
// and time of the backup will also be created alongside a backup.
// This class also checks that the destination has enough free space
// for the backup.
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
    // 1. Checks if the destination directory exists and creates one if it doesn't
    // 2. Checks hasFreeMemory()
    // 3. copyFolder(src, dest)
    // 4. createTimeStamp()
    // Throws exceptions if any of the operations fail
    public void backup() throws Exception {
        if (!dest.exists()) {
            if (!dest.mkdir()) {
                throw new Exception("Failed to create destination directory!");
            }
        }

        if (!hasFreeMemory()) {
            throw new Exception("Not enough free space!");
        }

        if (!copyFolder(src, dest)) {
            return;
        }
        createTimeStamp();
    }

    // REQUIRES: src and dest paths are relative to project directory
    // MODIFIES: this
    // EFFECTS: creates and stores File objects according to given String paths
    public void inputFilePaths(String src, String dest) {
        this.src = new File(src);
        this.dest = new File(dest);
    }

    // REQUIRES: valid src and dest File objects, both directories exist
    // MODIFIES: file system
    // EFFECTS: copies src directory to dest directory and handles exceptions
    // If there are pre-existing files in the dest, nothing happens to them (for now >:D)
    public boolean copyFolder(File src, File dest) {
        try {
            FileUtils.copyDirectory(src, dest);
        } catch (IOException e) {
            System.out.println("Error [copy folder]: " + e.getMessage());
            return false;
        }
        return true;
    }

    // REQUIRES: valid src and dest File objects
    // MODIFIES: destination directory
    // EFFECTS: creates a txt file containing the source directory and current time in dest directory
    public boolean createTimeStamp() {
        File timestamp = new File(dest.getPath() + "/backup_timestamp.txt");
        try {
            FileUtils.writeStringToFile(timestamp,
                    "Backup success\nSource: " + src.getPath() + "\n" + LocalTime.now().toString(),
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
