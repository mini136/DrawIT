package Play;

import Cell.Cell;
import Cell.TypesOfCells;
import NewImage.SetUpBox;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class LetsPlay extends JFrame {

    private Cell[][] labels;
    private SetUpBox box;
    private String filePath;
    private JButton button;
    private PlayArtPane pane;
    private ArrayList<String> task;
    private Cell startingCell;
    private boolean canDraw;
    public LetsPlay(String filePath){

        setSize(800,1000);
        setResizable(false);
        setLayout(new BorderLayout());

        this.pane = new PlayArtPane(Color.BLACK);
        this.task = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            this.labels = (Cell[][]) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


        //←,↑,→,↓

        int i = 0;
        int n = 0;
        for (Cell[] row : labels) {
            for (Cell cell : row) {
                switch(cell.getType()){
                    case UP:
                        task.add("↑");
                    case DOWN:
                        task.add("↓");
                    case LEFT:
                        task.add("←");
                    case RIGHT:
                        task.add("→");
                    default:
                        break;

                }
                n++;

                if(cell.getType() == TypesOfCells.STARTINGPOINT){
                    startingCell = cell;
                    pane.startingPoint(i,n);
                }

            }
            n = 0;
            i++;
        }

        button = new JButton("Check");
        box = new SetUpBox(button);
        box.setTextString(task);

        add(box,BorderLayout.SOUTH);
        add(pane,BorderLayout.NORTH);
        setVisible(true);
    }

    public boolean isPressed(){
        return box.isPressed();
    }

}

