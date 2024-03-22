import javax.swing.*;
import java.awt.*;

public class Render extends JFrame {

    int rowsAndColumms = 10;

    public Render() {

        Zadani zadani = new Zadani();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBackground(Color.WHITE);
        setLayout(new GridLayout(rowsAndColumms,rowsAndColumms));
        setSize(new Dimension(rowsAndColumms * 100,rowsAndColumms * 100));
        setResizable(false);

        for (int i = 0;i < rowsAndColumms * rowsAndColumms;i++){
            Cell cell = new Cell();
            add(cell);
        }

        while(true){
            if(!zadani.getVisibility()){
                setVisible(true);
                break;
            }
        }

    }

    public void setVisible(){
        setVisible(true);
    }
}
