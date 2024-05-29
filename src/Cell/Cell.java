package Cell;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class Cell extends JLabel implements Serializable {

    protected TypesOfCells type;
    private Color colorOfLine;
    private int x;
    private int y;
    private boolean isStartingPoint;

    public Cell(Color colorOfLine) {
        setBackground(Color.white);
        setColorOfLine(colorOfLine);
        setBorder(BorderFactory.createLineBorder(colorOfLine));
        setOpaque(true);
        setVisible(true);
    }

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
        repaint(); // Repaint to reflect changes
        setVisible(true);
    }

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

    @Override
    public int getY() {
        return y;
    }

    public boolean isStartingPoint() {
        return isStartingPoint;
    }

    public void setStartingPoint(boolean startingPoint) {
        isStartingPoint = startingPoint;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

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
