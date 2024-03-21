import javax.swing.*;
import java.awt.*;

public class Cell extends JButton {

    public Cell(){
       setBackground(Color.white);
       setText("");
    }

    public void setTypeOfCell(TypesOfCells typeOfCell){
        switch(typeOfCell){
            case BLANK -> setIcon(null);
            case STARTINGPOINT -> setIcon(new ImageIcon());
        }
    }

    public void getTypeOfCell(){

    }
}
