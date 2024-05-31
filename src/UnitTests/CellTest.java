package UnitTests;

import Cell.Cell;
import Cell.TypesOfCells;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class CellTest {

    private Cell cell;

    @Before
    public void setUp() {
        cell = new Cell(Color.BLACK);
    }

    @Test
    public void testSetTypeOfCellBlank() {
        cell.setTypeOfCell(TypesOfCells.BLANK);
        assertEquals(TypesOfCells.BLANK, cell.getType());
        assertEquals(Color.WHITE, cell.getBackground());
        assertEquals(Color.WHITE, cell.getForeground());
    }

    @Test
    public void testSetTypeOfCellStartingPoint() {
        cell.setTypeOfCell(TypesOfCells.STARTINGPOINT);
        assertEquals(TypesOfCells.STARTINGPOINT, cell.getType());
        assertEquals(Color.RED, cell.getBackground());
        assertEquals(Color.RED, cell.getForeground());
    }

    @Test
    public void testSetTypeOfCellUp() {
        cell.setTypeOfCell(TypesOfCells.UP);
        assertEquals(TypesOfCells.UP, cell.getType());
        assertEquals(Color.BLACK, cell.getBackground());
        assertEquals(Color.BLACK, cell.getForeground());
    }

    @Test
    public void testSetColorOfLine() {
        Color newColor = Color.GREEN;
        cell.setColorOfLine(newColor);
        assertEquals(newColor, cell.getColorOfLine());
    }

    @Test
    public void testIsStartingPoint() {
        cell.setStartingPoint(true);
        assertTrue(cell.isStartingPoint());
        cell.setStartingPoint(false);
        assertFalse(cell.isStartingPoint());
    }

}
