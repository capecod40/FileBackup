package model;

import java.io.File;

// SrcFile class:
// Java's File class contains all necessary methods for my application's user story,
// such as copying a single file, multiple files, or a directory, so I made this
// wrapper class to adhere to the user story guidelines:
// "[...] there is at least one user story that involves the user adding an X to a Y
// (e.g. adding an item to a to-do list), where X and Y are classes that you will
// have to design yourself." (from edX Phase 0)
public class SrcFile {
    private File src;

    public SrcFile() {
        src = null;
    }

    public void setSrc(File file) {
        src = file;
    }

    public File getSrc() {
        return src;
    }

}
