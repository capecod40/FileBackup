package ui;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import misc.Splash;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

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
