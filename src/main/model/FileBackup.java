package model;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.time.LocalTime;


// FileBackup:
// This class takes two directory paths as inputs and copies
// one directory to the other (aka backs up the folder).
// If the destination does not exist, a new folder with the specified
// name will be created. A txt file with information regarding the source directory
// and time of the backup will also be created in the destination directory.
// This class also checks that the destination has enough free space
// for the backup.
// Uses Apache Commons IO FileUtils for file copying and memory checking
public class FileBackup {
    private File src;
    private File dest;

    public FileBackup() {
        src = dest = null;
    }

    // REQUIRES: this.src and this.dest != null
    // EFFECTS:
    // 1. Checks if the destination directory exists and creates one if it doesn't
    // 2. Checks hasFreeMemory()
    // 3. Copies source to destination with copyFolder(src, dest)
    // 4. createTimeStamp()
    // Throws exceptions if any of the operations fail
    public void backup() throws Exception {
        if (!dest.exists()) {
            if (!dest.mkdir()) {
                throw new Exception("Failed to create destination directory!");
            }
        }
        hasFreeMemory();
        copyFolder(src, dest);
        createTimeStamp();
    }

    // REQUIRES: String inputs must be valid directory paths and are relative to project directory
    //              src must be an existing directory
    // MODIFIES: this
    // EFFECTS: creates and stores File objects according to given String paths
    public void inputFilePaths(String src, String dest) {
        this.src = new File(src);
        this.dest = new File(dest);
    }

    // REQUIRES: this.src and this.dest != null
    //              source and destination directories exist
    // EFFECTS: copies src directory to dest directory and throws exception in case of error
    // If there are pre-existing files in the dest, nothing happens to them (for now >:D)
    public void copyFolder(File src, File dest) throws IOException {
        FileUtils.copyDirectory(src, dest);
    }

    // REQUIRES: this.src and this.dest != null
    //              source and destination directories exist
    // EFFECTS: creates a txt file in dest directory containing info on the source directory and current time
    public void createTimeStamp() throws IOException {
        File timestamp = new File(dest.getPath() + "/backup_timestamp.txt");

        FileUtils.writeStringToFile(timestamp,
                "Backup success\nSource: " + src.getPath() + "\n" + LocalTime.now().toString(),
                Charset.defaultCharset());

    }

    // REQUIRES: this.src and this.dest != null
    // EFFECTS: checks that destination has more free space than size of src directory
    public void hasFreeMemory() throws Exception {
        if (dest.getFreeSpace() < FileUtils.sizeOfDirectory(src)) {
            throw new Exception("Not enough free space in destination directory!");
        }
    }

    public File getSrc() {
        return src;
    }

    public File getDest() {
        return dest;
    }

}
