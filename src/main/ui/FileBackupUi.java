package ui;


import com.formdev.flatlaf.FlatDarkLaf;
import model.BackupData;
import model.FileBackup;
import org.apache.commons.io.FileUtils;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

// FileBackupUi:
// Graphical user interface for FileBackup class
// No starter files used; followed Oracle tutorials and googled lots of stack overflow ᕕ(ᐛ)ᕗ
public class FileBackupUi extends FileBackup implements Runnable  {
    private static final String JSON_STORE = "./data/FileBackupAppLog.json";
    private JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
    private JsonReader jsonReader = new JsonReader(JSON_STORE);

    private JFrame window = new JFrame("File Backup");
    private Container pane = window.getContentPane();

    private JTextField sourceField = new JTextField();
    private JTextField destField = new JTextField();
    private JButton sourceEnter = new JButton("Enter Source Path");
    private JButton destEnter = new JButton("Enter Destination Path");
    private JButton startBackup = new JButton("Start Backup");

    private boolean sourceInitialized;
    private boolean destInitialized;

    private JTextArea logText = new JTextArea();
    private JScrollPane logPane = new JScrollPane(logText);
    private JButton loadLog = new JButton("Load Log");
    private JButton saveLog = new JButton("Save Log");

/*    private JProgressBar progressBar = new JProgressBar();

    public class Progress extends SwingWorker<Void, Void> {
        @Override
        protected Void doInBackground() throws Exception {
            setProgress(0);
            progressBar.setIndeterminate(true);
            while (FileUtils.sizeOfDirectory(dest) < FileUtils.sizeOfDirectory(src)) {

            }
            progressBar.setIndeterminate(false);
            return null;
        }
    }*/

    private JCheckBox feelOld = new JCheckBox("I'm feeling old");

    //EFFECTS: creates splash screen and initializes JFrame components with GridBagLayout
    public FileBackupUi() {
        initSplash();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setMinimumSize(new Dimension(852, 480));
        window.setLocationRelativeTo(null);
        pane.setLayout(new GridBagLayout());
        initPane();
        sourceInitialized = destInitialized = false;
    }

    //EFFECTS: creates a splash screen that lasts three seconds O_O
    private void initSplash() {
        JFrame splash = new JFrame();
        splash.add(new JLabel(new ImageIcon("dependencies/splash.gif")));
        splash.setUndecorated(true);
        splash.setLocationRelativeTo(null);
        splash.pack();
        splash.setVisible(true);
        long initTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - initTime < 3000) {}
        splash.dispose();
    }

    //EFFECTS: initializes JFrame components
    private void initPane() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0.5;
        constraints.insets = new Insets(10, 10, 10, 10);

        initLabelsAndText(constraints);
        initButtons(constraints);
/*        initProgressBar(constraints);*/
        initFeel(constraints);
        initLog(constraints);
        initButtonListeners();
        initLogListeners();
    }

    //EFFECTS: initializes text labels
    private void initLabelsAndText(GridBagConstraints constraints) {
        constraints.gridx = 0;
        constraints.gridy = 0;
        pane.add(new JLabel("Source Directory"), constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        pane.add(new JLabel("Destination Directory"), constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        pane.add(sourceField, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        pane.add(destField, constraints);
    }

    //EFFECTS: initializes buttons
    private void initButtons(GridBagConstraints constraints) {
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.EAST;

        constraints.gridx = 0;
        constraints.gridy = 2;
        pane.add(sourceEnter, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        pane.add(destEnter, constraints);

        constraints.anchor = GridBagConstraints.WEST;
        constraints.gridx = 0;
        constraints.gridy = 3;
        pane.add(startBackup, constraints);

        constraints.anchor = GridBagConstraints.EAST;
        constraints.gridx = 1;
        constraints.gridy = 5;
        pane.add(loadLog, constraints);

        constraints.gridy = 6;
        pane.add(saveLog, constraints);
    }

    // Abandoned progress bar attempt
/*    private void initProgressBar(GridBagConstraints constraints) {
        constraints.gridx = 0;
        constraints.gridy = 5;
        pane.add(progressBar, constraints);
    }*/
/*    private void progressBar() {
    }*/

    //EFFECTS: initializes check box for changing look and feel
    private void initFeel(GridBagConstraints constraints) {
        constraints.anchor = GridBagConstraints.WEST;
        constraints.gridx = 0;
        constraints.gridy = 6;
        pane.add(feelOld, constraints);

        feelOld.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if (feelOld.isSelected()) {
                    try {
                        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
                        SwingUtilities.updateComponentTreeUI(window);
                    } catch (Exception e) {
                        System.out.println("Look and feel exception!");
                    }
                } else {
                    FlatDarkLaf.setup();
                    SwingUtilities.updateComponentTreeUI(window);
                }
            }
        });
    }

    //EFFECTS: initializes log area
    private void initLog(GridBagConstraints constraints) {
        constraints.anchor = GridBagConstraints.WEST;
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.insets = new Insets(10, 10, 0, 10);
        pane.add(new JLabel("Backup Log"), constraints);
        logText.setEditable(false);
        logPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.weighty = 0.1;
        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.insets = new Insets(0, 10, 10, 10);
        pane.add(logPane, constraints);
    }

    //EFFECTS: initializes ActionListeners for buttons
    private void initButtonListeners() {
        sourceEnter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                inputSource();
                JOptionPane.showMessageDialog(window, "Source path entered!");
            }
        });

        destEnter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                inputDest();
                JOptionPane.showMessageDialog(window, "Destination path entered!");
            }
        });

        startBackup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                backup();
            }
        });
    }

    //EFFECTS: sets source directory
    private void inputSource() {
        inputSourcePath(sourceField.getText());
        sourceInitialized = true;
    }

    //EFFECTS: sets destination directory
    private void inputDest() {
        inputDestPath(destField.getText());
        destInitialized = true;
    }

    //EFFECTS: performs backup with user confirmation
    //          if source or destination paths are blank, shows popup message and cancels backup
    public void backup() {
        inputSource();
        inputDest();
        if (!(sourceInitialized && destInitialized)
                || sourceField.getText().equals("")
                || destField.getText().equals("")) {
            JOptionPane.showMessageDialog(window, "Source or destination not initialized!");
            return;
        }
        if (confirmBackup() != 0) {
            return;
        }
/*        progressBar();*/
        try {
            super.backup();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(window, "Backup Failed:\n" + e.getMessage());
            return;
        }
        BackupData entry = log.get(log.size() - 1);
        logText.append(entry.toString() + '\n');
        JOptionPane.showMessageDialog(window, "Backup Successful!");
    }

    //EFFECTS: displays popup window to confirm backup
    private int confirmBackup() {
        Object[] options = {"Confirm", "Cancel"};
        return JOptionPane.showOptionDialog(window,
                "Confirm Backup?",
                "Confirm",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
    }

    //EFFECTS: initializes ActionListeners for log buttons
    private void initLogListeners() {
        loadLog.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                load();
            }
        });

        saveLog.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                save();
            }
        });
    }

    //EFFECTS: loads json data
    private void load() {
        try {
            jsonReader.read(FileBackupUi.this);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(window, "Error [json]: Unknown load error!");
            return;
        }

        for (BackupData entry : log) {
            logText.append(entry.toString() + '\n');
        }

        JOptionPane.showMessageDialog(window, "Backup log loaded!");
    }

    //EFFECTS: saves log data to json
    private void save() {
        try {
            jsonWriter.open();
            jsonWriter.write(FileBackupUi.this);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(window, "Error [json]: JSON file not found!");
            return;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(window, "Error [json]: Unknown save error!");
            return;
        }
        JOptionPane.showMessageDialog(window, "Backup log saved!");
    }

    @Override
    public void run() {
        window.pack();
        window.setVisible(true);
    }
}
