package ui;

import model.FileBackup;
import org.apache.commons.io.FileUtils;

import java.util.Scanner;

// FileBackupApp:
// Console ui for FileBackup class
public class FileBackupApp {
    private FileBackup backup;
    private Scanner input;
    boolean quit;

    // EFFECTS: initializes FileBackup and Scanner for keyboard input,
    //              sets quit to false
    public FileBackupApp() {
        backup = new FileBackup();
        input = new Scanner(System.in);
        quit = false;
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
            System.out.println("Press [q] to quit");
            userInput = input.nextLine();

            if (userInput.equals("i")) {
                readPaths();
            } else if (userInput.equals("b")) {
                backup();
            } else if (userInput.equals("q")) {
                quit = true;
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: Prompts user for source and destination directory for backup and
    //              sends paths as strings to FileBackup
    public void readPaths() {
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
    public void backup() {
        System.out.println("Source directory: " + backup.getSrc().toString());
        System.out.println("Destination directory: " + backup.getDest().toString());
        System.out.println("Press [y] to confirm or any other key to cancel");
        if (input.nextLine().equals("y")) {
            try {
                backup.backup();
            } catch (Exception e) {
                System.out.println("Backup failed");
                System.out.println("Error: " + e.getMessage());
                return;
            }
            System.out.println("Backup successful");
            System.out.println("See backup_timestamp.txt in destination directory for timestamp");
        } else {
            System.out.println("Backup cancelled");
        }
    }
}
