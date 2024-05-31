package UnitTests;

import Cell.Cell;
import Play.ChooseFrame;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import static org.junit.Assert.*;

public class ChooseFrameTest {

    private ChooseFrame chooseFrame;

    @Before
    public void setUp() {

        File testDir = new File("pictures/testDir1");
        File testDir2 = new File("pictures/testDir2");
        testDir.mkdirs();
        testDir2.mkdirs();

        SwingUtilities.invokeLater(() -> chooseFrame = new ChooseFrame());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() {
        if (chooseFrame != null) {
            chooseFrame.dispose();
        }

        File testDir = new File("pictures/testDir1");
        File testDir2 = new File("pictures/testDir2");
        testDir.delete();
        testDir2.delete();
        new File("pictures").delete();
    }

    @Test
    public void testInitialization() {
        assertNotNull(chooseFrame);
        assertNotNull(chooseFrame.getPaneOfPictures());
        assertNotNull(chooseFrame.getButtons());
        assertNotNull(chooseFrame.getPictures());
        assertEquals(2, chooseFrame.getPictures().length);
        assertEquals("testDir1", chooseFrame.getPictures()[0]);
        assertEquals("testDir2", chooseFrame.getPictures()[1]);
    }

    @Test
    public void testDeserialization() throws IOException, ClassNotFoundException {
        Cell[][] cells = new Cell[10][10];
        String filePath = "testFile.ser";

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(cells);
        }

        Cell[][] deserializedCells = chooseFrame.deserialization(filePath);
        assertNotNull(deserializedCells);
        assertEquals(10, deserializedCells.length);
        assertEquals(10, deserializedCells[0].length);

        new File(filePath).delete();
    }

    @Test
    public void testListDirectoryNames() {
        String[] directories = chooseFrame.listDirectoryNames("pictures/");
        assertNotNull(directories);
        assertEquals(2, directories.length);
        assertEquals("testDir1", directories[0]);
        assertEquals("testDir2", directories[1]);
    }

    @Test
    public void testDeleteSer() {
        String fileName = "example.ser";
        String result = chooseFrame.deleteSer(fileName);
        assertEquals("example", result);
    }

    @Test
    public void testButtonClick() {
        JButton button = (JButton) chooseFrame.getListOfButtons()[0];
        assertNotNull(button);

        for (ActionListener al : button.getActionListeners()) {
            al.actionPerformed(new ActionEvent(button, ActionEvent.ACTION_PERFORMED, null));
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertFalse(chooseFrame.isVisible());
    }
}
