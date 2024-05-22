package Cell;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Cell extends JLabel {

    protected TypesOfCells type;

    private Color colorOfLine;
    public Cell(Color colorOfLine){
        setBackground(Color.white);
        setText("");
        setColorOfLine(colorOfLine);
    }



    public void setTypeOfCell(TypesOfCells typeOfCell){
        if(typeOfCell == TypesOfCells.BLANK){
            setOpaque(true);
            setBackground(Color.white);
            setForeground(Color.white);
        } else {
            setOpaque(true);
            setForeground(Color.white);
            setBackground(colorOfLine);
        }
       setType(typeOfCell);
    }

    public void getTypeOfCell(){

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
}
