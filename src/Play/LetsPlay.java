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

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("pictures/" + filePath + "/" + filePath + ".ser"))) {
            this.labels = (Cell[][]) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("pictures/" + filePath + "/" + filePath + "Txt.ser"))) {
            this.task = (ArrayList<String>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        int i = 0;
        int n = 0;
        for (Cell[] row : labels) {
            for (Cell cell : row) {
                switch (cell.getType()) {
                    case STARTINGPOINT:
                        System.out.print("START.");
                        break;
                    case UP:
                        System.out.print("UP.");
                        break;
                    case DOWN:
                        System.out.print("DOWN.");
                        break;
                    case LEFT:
                        System.out.print("LEFT.");
                        break;
                    case RIGHT:
                        System.out.print("RIGHT.");
                        break;
                    case ENDINGPOINT:
                        System.out.print("END.");
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
            if (box.isPressed()) {
                Cell[][] playLabels = pane.getLabels();

                // Vypisování typů buněk kromě BLANK
                System.out.println("Current play labels:");
                for (Cell[] row : playLabels) {
                    for (Cell cell : row) {
                        if (cell.getType() != TypesOfCells.BLANK) {
                            switch (cell.getType()) {
                                case STARTINGPOINT:
                                    System.out.print("START.");
                                    break;
                                case UP:
                                    System.out.print("UP.");
                                    break;
                                case DOWN:
                                    System.out.print("DOWN.");
                                    break;
                                case LEFT:
                                    System.out.print("LEFT.");
                                    break;
                                case RIGHT:
                                    System.out.print("RIGHT.");
                                    break;
                                case ENDINGPOINT:
                                    System.out.print("END.");
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                }

                // Porovnání buněk
                outerloop:
                for (int k = 0; k < labels.length; k++) {
                    for (int l = 0; l < labels.length; l++) {
                        if (playLabels[l][k].getType() != TypesOfCells.BLANK) {
                            switch (playLabels[l][k].getType()) {
                                case ENDINGPOINT:
                                case STARTINGPOINT:
                                    break;
                                case UP:
                                case DOWN:
                                case LEFT:
                                case RIGHT:
                                    if (playLabels[l][k].getType() != labels[l][k].getType()) {
                                        JOptionPane.showMessageDialog(null, "Wrong!", "title", JOptionPane.ERROR_MESSAGE);
                                        pressed = true;
                                        break outerloop;
                                    }
                            }
                        }
                    }
                }

                JOptionPane.showConfirmDialog(null,"You Won","Wining message",JOptionPane.OK_OPTION);

                ((Timer) d.getSource()).stop();
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
