package model;

// BackupData:
// Represents an entry in FileBackup log: records time, source,
// and destination of one backup using Strings
public class BackupData {
    private String source;
    private String dest;
    private String time;

    public BackupData(String src, String d, String t) {
        source = src;
        dest = d;
        time = t;
    }

    // EFFECTS: pretty formatting
    @Override
    public String toString() {
        return "Source:[" + source + "] Destination:[" + dest + "] Time:[" + time + "]";
    }

    public String getSource() {
        return source;
    }

    public String getDest() {
        return dest;
    }

    public String getDate() {
        return time;
    }




}
