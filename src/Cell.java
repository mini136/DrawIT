import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Cell extends JLabel {

    private TypesOfCells type;
    private boolean startingPoint;
    public Cell(boolean startingPoint){
        setStartingPoint(startingPoint);
       setBackground(Color.white);
       setText("");
    }



    public void setTypeOfCell(TypesOfCells typeOfCell){
        switch(typeOfCell){
            case BLANK -> setIcon(new ImageIcon("pictures/BLANK.png"));
            case STARTINGPOINT -> setIcon(new ImageIcon("pictures/startingPoint.png"));
            case SVISLAZEZHORA, SVISLAZEZDOLA -> setIcon(new ImageIcon("pictures/svislaCara.png"));
            case VODOROVNAZLEVA, VODOROVNAZPRAVA -> setIcon(new ImageIcon("pictures/vodorovnaCara.png"));
            case ZEZHORADOPRAVA -> setIcon(new ImageIcon("pictures/zeZhoraVpravo.png"));
            case ZEZHORADOLEVA -> setIcon(new ImageIcon("pictures/zeZhoraVlevo.png"));
            case ZEZDOLADOLEVA -> setIcon(new ImageIcon("pictures/zeZdolaVlevo.png"));
            case ZEZDOLADOPRAVA -> setIcon(new ImageIcon("pictures/zeZdolaVpravo.png"));
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

    public boolean isStartingPoint() {
        return startingPoint;
    }

    public void setStartingPoint(boolean startingPoint) {
        this.startingPoint = startingPoint;
    }
}
