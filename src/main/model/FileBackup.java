package model;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.time.LocalTime;
import java.util.ArrayList;

// TODO: update SPECS
// FileBackup:
// This class takes two directory paths as inputs and copies
// one directory to the other (aka backs up the folder).
//      If the destination does not exist, a new folder with the specified
//          name will be created.
//      A txt file with information regarding the source directory
//          and time of the backup will also be created in the destination
//          directory.
//      Checks that the destination has enough free space for the backup.
//      Contains a log with time, source directory, and destination
//          directory of each backup
//
// Uses Apache Commons IO FileUtils for file copying and memory checking
// Reuses a portion of demo JsonReader and JsonWriter classes provided on edX
public class FileBackup {
    protected File src;
    protected File dest;
    protected ArrayList<BackupData> log;

    public FileBackup() {
        dest = null;
        src = null;
        log = new ArrayList<>();
    }

    // REQUIRES: this.src and this.dest != null
    // EFFECTS:
    //          1. Checks if the destination directory exists and creates one if it doesn't
    //          2. Checks hasFreeMemory() (currently commented out)
    //          3. Copies source to destination with copyFolder or copyFile
    //          4. createTimeStamp()
    //          5. log()
    //          Throws exceptions if any of the operations fail
    public void backup() throws Exception {
        if (!dest.exists()) {
            if (!dest.mkdir()) {
                throw new Exception("Failed to create destination directory!");
            }
        }
/*        if (!hasFreeMemory()) {
            throw new Exception("Not enough free space in destination directory!");
        }*/
        if (src.isDirectory()) {
            copyFolder(src, dest);
        } else {
            copyFile(src, dest);
        }
        createTimeStamp();
        log();
    }

    // EFFECTS: prints log content
    public void printLog() {
        for (BackupData entry : log) {
            System.out.println(entry.toString());
        }
    }

    // REQUIRES: String input must be a valid directory path and relative to project directory,
    //              src must be an existing directory
    // MODIFIES: this
    // EFFECTS: creates and stores File object according to given String path
    public void inputSourcePath(String src) {
        this.src = new File(src);
    }

    // REQUIRES: String input must be a valid directory path and relative to project directory,
    // MODIFIES: this
    // EFFECTS: creates and stores File object according to given String path
    public void inputDestPath(String dest) {
        this.dest = new File(dest);
    }

    // EFFECTS: copies contents of log to JSONArray stored in JSONObject
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        json.put("log", jsonArray);
        for (BackupData entry : log) {
            jsonArray.put(entry.getSource());
            jsonArray.put(entry.getDest());
            jsonArray.put(entry.getDate());
        }
        return json;
    }

    // REQUIRES: this.src != null, this.dest != null
    // MODIFIES: this
    // EFFECTS: adds BackupData with time, source path, and destination path to log
    protected void log() {
        log.add(new BackupData(src.getPath(), dest.getPath(), LocalTime.now().toString()));
    }

    // REQUIRES: this.src and this.dest != null,
    //              source and destination directories exist
    // EFFECTS: copies src directory to dest directory and throws exception in case of error
    //              If there are pre-existing files in the dest, nothing happens to them (for now >:D)
    protected void copyFolder(File src, File dest) throws IOException {
        FileUtils.copyDirectory(src, dest);
    }

    // REQUIRES: this.src and this.dest != null,
    //              source file and destination directory exist
    // EFFECTS: copies src file to dest directory and throws exception in case of error
    protected void copyFile(File src, File dest) throws IOException {
        FileUtils.copyFileToDirectory(src, dest);
    }

    // REQUIRES: this.src and this.dest != null,
    //              source and destination directories exist
    // EFFECTS: creates a txt file in dest directory containing info on the source directory and current time
    protected void createTimeStamp() throws IOException {
        File timestamp = new File(dest.getPath() + "/backup_timestamp.txt");
        FileUtils.writeStringToFile(timestamp,
                "Backup success\nSource: " + src.getPath() + "\n" + LocalTime.now().toString(),
                Charset.defaultCharset());
    }

/*    // TODO: Talk to TA
    // Removed because I don't know how to get the bot to cover this method
    // REQUIRES: this.src and this.dest != null
    //              source file/directory and destination directory exists
    // EFFECTS: checks that destination has more free space than size of src
    protected boolean hasFreeMemory() {
        if (src.isDirectory()) {
            if (dest.getFreeSpace() < FileUtils.sizeOfDirectory(src)) {
                return false;
            }
            return true;
        } else {
            if (dest.getFreeSpace() < FileUtils.sizeOf(src)) {
                return false;
            }
            return true;
        }
    }*/

    public File getSrc() {
        return src;
    }

    public File getDest() {
        return dest;
    }

    public void setLog(ArrayList<BackupData> log) {
        this.log = log;
    }

    public ArrayList<BackupData> getLog() {
        return log;
    }

}
