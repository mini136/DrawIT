package Cell;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

/**
 * Represents a cell in a grid, extending JLabel and implementing Serializable.
 * it can have type,color and position x and y.
 */
public class Cell extends JLabel implements Serializable {

    protected TypesOfCells type;
    private static final long serialVersionUID = 1L;
    private Color colorOfLine;
    private int x;
    private int y;
    private boolean isStartingPoint;

    /**
     * Constructs a new Cell with the color of its background and foreground.
     *
     * @param colorOfLine the color of the cells background.
     */
    public Cell(Color colorOfLine) {
        setBackground(Color.white);
        setColorOfLine(colorOfLine);
        setBorder(BorderFactory.createLineBorder(colorOfLine));
        setOpaque(true);
        setVisible(true);
    }

    /**
     * Sets the type of this cell and updates its Color - background and foreground.
     *
     * @param typeOfCell the type to set for this cell.
     */
    public void setTypeOfCell(TypesOfCells typeOfCell) {
        this.type = typeOfCell;
        if (typeOfCell == TypesOfCells.BLANK) {
            setBackground(Color.white);
            setForeground(Color.white);
        } else if (typeOfCell == TypesOfCells.STARTINGPOINT) {
            setColorOfLine(Color.red);
            setBackground(Color.red);
            setForeground(Color.red);
        } else if (typeOfCell == TypesOfCells.ENDINGPOINT) {
            setColorOfLine(Color.blue);
            setBackground(Color.blue);
            setForeground(Color.blue);
        } else {
            setBackground(colorOfLine);
            setForeground(colorOfLine);
        }
        repaint();
        setVisible(true);
    }

    // region getters and setters

    public TypesOfCells getType() {
        return type;
    }

    public void setType(TypesOfCells type) {
        this.type = type;
        setTypeOfCell(type);
    }

    public Color getColorOfLine() {
        return colorOfLine;
    }

    public void setColorOfLine(Color colorOfLine) {
        this.colorOfLine = colorOfLine;
    }

    @Override
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isStartingPoint() {
        return isStartingPoint;
    }

    public void setStartingPoint(boolean startingPoint) {
        isStartingPoint = startingPoint;
    }

    // endregion

    @Override
    public String toString() {
        return "Cell{" +
                "type=" + type +
                ", colorOfLine=" + colorOfLine +
                ", x=" + x +
                ", y=" + y +
                ", isStartingPoint=" + isStartingPoint +
                '}';
    }
}
