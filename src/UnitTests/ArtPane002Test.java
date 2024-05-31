package UnitTests;

import Cell.Cell;
import Cell.TypesOfCells;
import NewImage.ArtPane002;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import static org.junit.Assert.*;

public class ArtPane002Test {
    private ArtPane002 artPane;
    private Color testColor = Color.BLACK;

    @Before
    public void setUp() {
        artPane = new ArtPane002(testColor);
        JFrame frame = new JFrame();
        frame.add(artPane);
        frame.pack();
        frame.setVisible(true);
    }

    @Test
    public void testInitialization() {
        assertNotNull(artPane.getLabels());
        assertEquals(20, artPane.getLabels().length);
        assertEquals(20, artPane.getLabels()[0].length);

        for (int i = 0; i < artPane.getRowsAndColumns(); i++) {
            for (int j = 0; j < artPane.getRowsAndColumns(); j++) {
                assertNotNull(artPane.getLabels()[i][j]);
                assertEquals(TypesOfCells.BLANK, artPane.getLabels()[i][j].getType());
            }
        }
    }

    @Test
    public void testLayoutCells() {
        artPane.setSize(800, 800);
        artPane.layoutCells();

        int cellWidth = 800 / artPane.getRowsAndColumns();
        int cellHeight = 800 / artPane.getRowsAndColumns();

        for (int i = 0; i < artPane.getRowsAndColumns(); i++) {
            for (int j = 0; j < artPane.getRowsAndColumns(); j++) {
                Cell cell = artPane.getLabels()[j][i];
                assertEquals(j * cellWidth, cell.getX());
                assertEquals(i * cellHeight, cell.getY());
                assertEquals(cellWidth, cell.getWidth());
                assertEquals(cellHeight, cell.getHeight());
            }
        }
    }

    @Test
    public void testMouseClick() {
        int cellX = 100 / (800 / artPane.getRowsAndColumns());
        int cellY = 100 / (800 / artPane.getRowsAndColumns());

        MouseEvent clickEvent = new MouseEvent(artPane, MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), 0, 100, 100, 1, false);
        artPane.dispatchEvent(clickEvent);

        assertEquals(TypesOfCells.STARTINGPOINT, artPane.getLabels()[cellX][cellY].getType());

        artPane.dispatchEvent(clickEvent);

        assertFalse(artPane.isCanDraw());
    }

    @Test
    public void testMouseMovement() {
        artPane.setCanDraw(true);

        int cellX = 50 / (800 / artPane.getRowsAndColumns());
        int cellY = 50 / (800 / artPane.getRowsAndColumns());

        MouseEvent moveEvent = new MouseEvent(artPane, MouseEvent.MOUSE_MOVED, System.currentTimeMillis(), 0, 50, 50, 0, false);
        artPane.dispatchEvent(moveEvent);

        assertEquals(cellX, artPane.getCellX());
        assertEquals(cellY, artPane.getCellY());
    }

    @Test
    public void testOutputText() {
        artPane.setCanDraw(true);

        MouseEvent moveEvent = new MouseEvent(artPane, MouseEvent.MOUSE_MOVED, System.currentTimeMillis(), 0, 50, 50, 0, false);
        artPane.dispatchEvent(moveEvent);
        assertTrue(artPane.getOutputText().isEmpty());

        moveEvent = new MouseEvent(artPane, MouseEvent.MOUSE_MOVED, System.currentTimeMillis(), 0, 60, 50, 0, false);
        artPane.dispatchEvent(moveEvent);

        assertFalse(artPane.getOutputText().isEmpty());
        assertEquals("→", artPane.getOutputText().get(0));
    }

    @Test
    public void testPaintComponent() {
        JPanel testPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                artPane.drawGrid(g);
            }
        };

        testPanel.setSize(800, 800);
        BufferedImage image = new BufferedImage(800, 800, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        testPanel.paint(g2d);
        g2d.dispose();

        int gridColor = Color.BLACK.getRGB();
        assertEquals(gridColor, image.getRGB(0, 0));
    }

    @Test
    public void testMoveMouseToCenter() {

        try {
            Robot robot = new Robot();
            JFrame frame = new JFrame();
            frame.add(artPane);
            frame.pack();
            frame.setVisible(true);

            Point location = artPane.getLocationOnScreen();
            int centerX = location.x + artPane.getWidth() / 2;
            int centerY = location.y + artPane.getHeight() / 2;

            artPane.moveMouseToCenter();

            Point mouseLocation = MouseInfo.getPointerInfo().getLocation();
            assertEquals(centerX, mouseLocation.x);
            assertEquals(centerY, mouseLocation.y);
        } catch (AWTException e) {
            fail("Robot creation failed.");
        }
    }
}
