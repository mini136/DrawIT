package Play;

import Cell.Cell;
import NewImage.SetUpBox;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class LetsPlay extends JFrame {

    private Cell[][] labels;
    private SetUpBox box;
    private String filePath;
    private JButton button;
    public LetsPlay(String filePath){

        setSize(800,1000);
        setResizable(false);
        setLayout(new BorderLayout());

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            this.labels = (Cell[][]) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        button = new JButton();
        box = new SetUpBox(button);

        add(box,BorderLayout.SOUTH);
        setVisible(true);
    }

    public boolean isPressed(){
        return box.isPressed();
    }
}

