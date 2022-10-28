package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BackupDataTest {
    private BackupData data;

    @BeforeEach
    public void setup() {
        data = new BackupData("testSrc", "testDest", "testTime");
    }

    @Test
    public void getterTests() {
        assertTrue(data.getSource().equals("testSrc"));
        assertTrue(data.getDest().equals("testDest"));
        assertTrue(data.getDate().equals("testTime"));
    }

    @Test
    public void toStringPrintTest() {
        System.out.println(data.toString());
    }

}
