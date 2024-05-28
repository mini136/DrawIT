package Cell;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class Cell extends JLabel implements Serializable {

    protected TypesOfCells type;
    private Color colorOfLine;
    private int x;
    private int y;
    private boolean isStrtingPoint;

    public Cell(Color colorOfLine) {
        setBackground(Color.white);
        setColorOfLine(colorOfLine);
        setTypeOfCell(TypesOfCells.BLANK);
        setVisible(true);
    }

    public void setTypeOfCell(TypesOfCells typeOfCell) {
        if (typeOfCell == TypesOfCells.BLANK) {
            setBackground(Color.white);
            setForeground(Color.black);
        } else {
            setForeground(colorOfLine);
            setBackground(colorOfLine);
        }

        if (isStrtingPoint) {
            setForeground(Color.red);
            setBackground(Color.red);
        }
        setType(typeOfCell);
    }

    public TypesOfCells getType() {
        return type;
    }

    public void setType(TypesOfCells type) {
        this.type = type;
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

    @Override
    public int getY() {
        return y;
    }

    public boolean isStrtingPoint() {
        return isStrtingPoint;
    }

    public void setStrtingPoint(boolean strtingPoint) {
        isStrtingPoint = strtingPoint;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}