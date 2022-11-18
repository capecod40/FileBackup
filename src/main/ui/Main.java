package ui;

import com.formdev.flatlaf.FlatDarkLaf;

import static javax.swing.SwingUtilities.invokeLater;

public class Main {
    public static void main(String[] args) {
/*        FileBackupApp app = new FileBackupApp();
        app.run();*/
        FlatDarkLaf.setup();
        Runnable program = new FileBackupUi();
        invokeLater(program);
    }
}
