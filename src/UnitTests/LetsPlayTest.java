package UnitTests;

import Cell.Cell;
import Cell.TypesOfCells;
import NewImage.SetUpBox;
import Play.LetsPlay;
import Play.PlayArtPane;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class LetsPlayTest {

    private LetsPlay letsPlay;
    private String testFilePath = "testFile";
    private Cell[][] testCells;
    private ArrayList<String> testTasks;

    @Before
    public void setUp() throws IOException, ClassNotFoundException {
        testCells = new Cell[5][5];
        for (int i = 0; i < testCells.length; i++) {
            for (int j = 0; j < testCells[i].length; j++) {
                testCells[i][j] = new Cell(Color.WHITE);
                if (i == 0 && j == 0) {
                    testCells[i][j].setType(TypesOfCells.STARTINGPOINT);
                } else {
                    testCells[i][j].setType(TypesOfCells.BLANK);
                }
            }
        }

        testTasks = new ArrayList<>();
        testTasks.add("Task 1");
        testTasks.add("Task 2");

        new File("pictures/" + testFilePath).mkdirs();

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("pictures/" + testFilePath + "/" + testFilePath + ".ser"))) {
            oos.writeObject(testCells);
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("pictures/" + testFilePath + "/" + testFilePath + "Txt.ser"))) {
            oos.writeObject(testTasks);
        }

        SwingUtilities.invokeLater(() -> letsPlay = new LetsPlay(testFilePath));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() {
        if (letsPlay != null) {
            letsPlay.dispose();
        }

        new File("pictures/" + testFilePath + "/" + testFilePath + ".ser").delete();
        new File("pictures/" + testFilePath + "/" + testFilePath + "Txt.ser").delete();
        new File("pictures/" + testFilePath).delete();
        new File("pictures").delete();
    }

    @Test
    public void testInitialization() {
        assertNotNull(letsPlay);
        assertNotNull(letsPlay.getContentPane().getComponent(0));
        assertTrue(letsPlay.getContentPane().getComponent(0) instanceof PlayArtPane);
        assertNotNull(letsPlay.getContentPane().getComponent(1));
        assertTrue(letsPlay.getContentPane().getComponent(1) instanceof SetUpBox);
    }

    @Test
    public void testStartingCell() {
        assertNotNull(letsPlay);
        PlayArtPane pane = (PlayArtPane) letsPlay.getContentPane().getComponent(0);
        Cell[][] paneLabels = pane.getLabels();
        assertEquals(TypesOfCells.STARTINGPOINT, paneLabels[0][0].getType());
    }

    @Test
    public void testButtonPress() {
        SetUpBox box = (SetUpBox) letsPlay.getContentPane().getComponent(1);
        JButton button = box.getButton();
        assertNotNull(button);

        for (ActionListener al : button.getActionListeners()) {
            al.actionPerformed(new ActionEvent(button, ActionEvent.ACTION_PERFORMED, null));
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertTrue(letsPlay.isPressed());
    }
}
