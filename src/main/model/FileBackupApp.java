package model;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

public class FileBackupApp {
    private Path src;
    private Path dest;


    public FileBackupApp() {

    }

    public void copyFolder(File src, File dest) {

    }

    public void copyFile(File src, File dest) throws IOException {
        Files.copy(src.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }

    // REQUIRES: src is a valid path, dest is a valid folder path
    // MODIFIES: this, filesystem?
    // EFFECTS: Validates and sets src and dest
    // dest must be a folder, src can be a folder or a file
    // if dest is a folder that doesn't exist, give option to create one
    public void inputPaths(String src, String dest) {
/*        this.src = Paths.get(src);
        this.dest = Paths.get(dest);
        if (!Files.exists(this.src)) {
           // warning
        }
        if (!Files.exists(this.dest)) {
            // warning
            // option to create folder
        }*/
    }

    public Path getSrc() {
        return src;
    }

    public void setSrc(Path src) {
        this.src = src;
    }

    public Path getDest() {
        return dest;
    }

    public void setDest(Path dest) {
        this.dest = dest;
    }

}
