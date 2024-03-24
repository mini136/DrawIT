import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Cell extends JLabel {

    private TypesOfCells type;
    public Cell(){
       setBackground(Color.white);
       setText("");
      /* addMouseListener(new MouseListener() {

           @Override
           public void mouseClicked(MouseEvent e) {

           }

           @Override
           public void mousePressed(MouseEvent e) {

           }

           @Override
           public void mouseReleased(MouseEvent e) {

           }

           @Override
           public void mouseEntered(MouseEvent e) {
                 setTypeOfCell(TypesOfCells.SVISLA);
           }

           @Override
           public void mouseExited(MouseEvent e) {

           }
       });*/
    }



    public void setTypeOfCell(TypesOfCells typeOfCell){
        switch(typeOfCell){
            case BLANK -> setIcon(null);
            case STARTINGPOINT -> setIcon(new ImageIcon("pictures/startingPoint.png"));
            case SVISLA -> setIcon(new ImageIcon("pictures/svislaCara.png"));
            case VODOROVNA -> setIcon(new ImageIcon("pictures/vodorovnaCara.png"));
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
}
