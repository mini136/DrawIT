package Play;

import Cell.Cell;
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

                if(cell.isStrtingPoint()){
                    startingCell = cell;
                    pane.startingPoint(cell.getY(),cell.getX());
                }

            }
        }

        button = new JButton();
        box = new SetUpBox(button);
        box.setTextString(task);

        add(box,BorderLayout.SOUTH);
        add(pane,BorderLayout.CENTER);
        setVisible(true);
    }

    public boolean isPressed(){
        return box.isPressed();
    }

}

