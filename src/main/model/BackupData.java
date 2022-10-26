package model;

public class BackupData {
    private String source;
    private String time;

    public BackupData(String src, String t) {
        source = src;
        time = t;
    }

    @Override
    public String toString() {
        return "Source:[" + source + "] Time:[" + time + "]";
    }

    public String getDate() {
        return time;
    }

    public String getSource() {
        return source;
    }
}
