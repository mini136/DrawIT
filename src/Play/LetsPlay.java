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
    private boolean pressed;

    public LetsPlay(String filePath) {
        setSize(new Dimension(800, 1000));
        setResizable(false);
        setLayout(new BorderLayout());

        this.pane = new PlayArtPane(Color.BLACK);
        this.task = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            this.labels = (Cell[][]) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        int i = 0;
        int n = 0;
        for (Cell[] row : labels) {
            for (Cell cell : row) {
                switch(cell.getType()){
                    case STARTINGPOINT:
                        task.add("X");
                        System.out.print("START.");
                        break;
                    case UP:
                        task.add("↑");
                        System.out.print("UP.");
                        break;
                    case DOWN:
                        task.add("↓");
                        System.out.print("DOWN.");
                        break;
                    case LEFT:
                        task.add("←");
                        System.out.print("LEFT.");
                        break;
                    case RIGHT:
                        task.add("→");
                        System.out.print("RIGHT.");
                        break;
                    case ENDINGPOINT:
                        task.add("X");
                        System.out.print("END.");
                        break;
                    case BLANK:
                        System.out.print("BLANK.");
                        break;
                    default:
                        break;
                }
                if (cell.getType() == TypesOfCells.STARTINGPOINT) {
                    startingCell = cell;
                    startingCell.setX(n);
                    startingCell.setY(i);
                }
                n++;
            }
            n = 0;
            i++;
        }

        button = new JButton("Check");
        box = new SetUpBox(button);
        box.setTextString(task);

        add(box, BorderLayout.SOUTH);
        add(pane, BorderLayout.CENTER);
        setVisible(true);

        Timer timer = new Timer(100, d -> {
            if(box.isPressed()) {
                Cell[][] playLabels = pane.getLabels();

                outerloop:
                for (int k = 0;k < labels.length;k++) {
                    for (int l = 0; l < labels.length;l++) {
                           if(labels[l][k].getType() != playLabels[l][k].getType()){
                               JOptionPane.showMessageDialog(null,"Wrong!","title",JOptionPane.ERROR_MESSAGE);
                               pressed = true;
                               ((Timer) d.getSource()).stop();
                               break outerloop;
                           }
                    }
                }
                dispose();
            }
        });
        timer.start();

        if (startingCell != null) {
            pane.startingPoint(startingCell.getX(), startingCell.getY());
        }
    }

    public boolean isPressed() {
        return pressed;
    }
}
