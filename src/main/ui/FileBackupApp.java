package ui;

import model.FileBackup;
import org.apache.commons.io.FileUtils;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.util.Scanner;

// FileBackupApp:
// Console ui for FileBackup class
public class FileBackupApp {
    private static final String JSON_STORE = "./data/FileBackupAppLog.json";
    private FileBackup backup;
    private Scanner input;
    boolean quit;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: initializes FileBackup and Scanner for keyboard input,
    //              sets quit to false
    public FileBackupApp() {
        backup = new FileBackup();
        input = new Scanner(System.in);
        quit = false;
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // MODIFIES: this
    // EFFECTS: runs a loop that queries for user input
    //              user options are:
    //                  [i] for setting source and destination directory paths
    //                  [b] to create a backup
    //                  [q] to quit
    public void run() {
        String userInput;

        while (!quit) {
            System.out.println("Press [i] for setting source and destination directory paths");
            System.out.println("Press [b] to create a backup");
            System.out.println("Press [s] to save backup log");
            System.out.println("Press [l] to load backup log");
            System.out.println("Press [p] to print backup log");
            System.out.println("Press [q] to quit");
            userInput = input.nextLine();

            if (userInput.equals("i")) {
                readPaths();
            } else if (userInput.equals("b")) {
                backup();
            } else if (userInput.equals("s")) {
                save();
            } else if (userInput.equals("l")) {
                load();
            } else if (userInput.equals("p")) {
                backup.printLog();
            } else if (userInput.equals("q")) {
                quit = true;
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: Prompts user for source and destination directory for backup and
    //              sends paths as strings to FileBackup
    private void readPaths() {
        System.out.println("**Paths must be relative to project directory**");
        System.out.println("Enter source directory:");
        String src = input.nextLine();
        System.out.println("Enter destination directory:");
        String dest = input.nextLine();
        backup.inputFilePaths(src, dest);
    }

    // EFFECTS:
    // 1. Displays source and destination directory
    // 2. Asks user to confirm backup ([y] to confirm, any other key to cancel)
    // 3. Runs backup, prints error message and returns if exception thrown
    // 4. Prints success message if backup completes
    private void backup() {
        System.out.println("Source directory: " + backup.getSrc().toString());
        System.out.println("Destination directory: " + backup.getDest().toString());
        System.out.println("Press [y] to confirm or any other key to cancel");
        if (input.nextLine().equals("y")) {
            try {
                backup.backup();
            } catch (Exception e) {
                System.out.println("Backup failed");
                System.out.println("Error [backup]: " + e.getMessage());
                return;
            }
            System.out.println("Backup successful");
            System.out.println("See backup_timestamp.txt in destination directory for timestamp and source info");
        } else {
            System.out.println("Backup cancelled");
        }
    }

    // TODO: specs
    private void save() {
        try {
            jsonWriter.open();
            jsonWriter.write(backup);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error [json]: JSON file not found!");
        } catch (Exception e) {
            System.out.println("Error [json]: Unknown save error!");
        }
    }

    // TODO: specs
    private void load() {
        try {
            jsonReader.read(backup);
        } catch (Exception e) {
            System.out.println("Error [json]: Unknown load error!");
        }
    }
}
