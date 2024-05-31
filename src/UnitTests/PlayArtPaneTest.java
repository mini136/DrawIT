package UnitTests;

import Cell.Cell;
import Cell.TypesOfCells;
import Play.PlayArtPane;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class PlayArtPaneTest {

    private PlayArtPane playArtPane;

    @Before
    public void setUp() {
        playArtPane = new PlayArtPane(Color.BLACK);
        JFrame frame = new JFrame();
        frame.add(playArtPane);
        frame.pack();
        frame.setVisible(true);
    }

    @Test
    public void testInitialization() {
        assertNotNull(playArtPane);
        assertEquals(20, playArtPane.getRowsAndColumms());
        assertEquals(Color.BLACK, playArtPane.getColorOfLine());
        assertEquals(20, playArtPane.getLabels().length);
    }

    @Test
    public void testStartingPoint() {
        playArtPane.startingPoint(0, 0);
        assertEquals(TypesOfCells.STARTINGPOINT, playArtPane.getLabels()[0][0].getType());
        assertEquals(Color.RED, playArtPane.getLabels()[0][0].getBackground());
    }

    @Test
    public void testMouseEvents() {
        MouseEvent pressEvent = new MouseEvent(playArtPane, MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), 0, 10, 10, 1, false);
        playArtPane.dispatchEvent(pressEvent);

        assertEquals(TypesOfCells.STARTINGPOINT, playArtPane.getLabels()[0][0].getType());
        assertTrue(playArtPane.isCanDraw());
        assertTrue(playArtPane.getOutputText().contains("START"));

        MouseEvent dragEvent = new MouseEvent(playArtPane, MouseEvent.MOUSE_MOVED, System.currentTimeMillis(), 0, 20, 10, 1, false);
        playArtPane.dispatchEvent(dragEvent);

        assertEquals(TypesOfCells.RIGHT, playArtPane.getLabels()[1][0].getType());
        assertTrue(playArtPane.getOutputText().contains("→"));
    }

}
