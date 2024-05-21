package Cell;

import Cell.Cell;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Render extends JFrame {

  /*  int rowsAndColumms = 10;
    private Component[] components;
    private Cell startingCell;
    private Cell[][] labels = new Cell[rowsAndColumms][rowsAndColumms];
    private int pocetOkynek;
    private int okynkoXold = 0;
    private int okynkoYold = 0;

    private JTextArea zadani;
    private String zadaniDoJLabelu;


    public Render() {
        JPanel artBox = new JPanel();
        JPanel setupBox = new JPanel();
        JPanel mainPanel = new JPanel();

        ArrayList<String> zadavajici = new ArrayList<>();

        this.zadani = new JTextArea();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(new Dimension(1080,1500));
        setLayout(new BorderLayout());
        setResizable(true);

        artBox.setSize(new Dimension(rowsAndColumms * 100,rowsAndColumms * 100));
        artBox.setLayout(new GridLayout(rowsAndColumms,rowsAndColumms));

        setupBox.setSize(new Dimension(rowsAndColumms * 100,200));
        setupBox.setLayout(new BorderLayout());

        zadani.setBackground(Color.white);
        zadani.setLineWrap(true);
        zadani.setFont(new Font("Monospaced", Font.PLAIN, 20));
        setupBox.add(zadani,BorderLayout.NORTH);

        JButton done = new JButton();
        done.setSize(1000,200);
        setupBox.add(done,BorderLayout.SOUTH);


        this.components = getComponents();


        artBox.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                int x = e.getX();
                int y = e.getY();

                int okynkoY = (e.getX() / (artBox.getWidth() / rowsAndColumms));
                int okynkoX = (e.getY() / (artBox.getHeight() / rowsAndColumms));

               //←,↑,→,↓

                pocetOkynek = okynkoX * rowsAndColumms + okynkoY;

                if (okynkoXold == (okynkoX - 1)) {

                    switch (labels[okynkoXold][okynkoYold].getType()){
                        case STARTINGPOINT -> {
                            labels[okynkoXold][okynkoYold].setTypeOfCell(TypesOfCells.SVISLAZEZHORA);
                            zadavajici.add("↓");
                        }
                        case SVISLAZEZHORA, ZEZDOLADOLEVA, ZEZDOLADOPRAVA, ZEZHORADOLEVA, ZEZHORADOPRAVA, BLANK -> {
                            labels[okynkoX][okynkoY].setTypeOfCell(TypesOfCells.SVISLAZEZHORA);
                            zadavajici.add("↓");
                            break;
                        }
                        case VODOROVNAZLEVA -> {
                            labels[okynkoX][okynkoY].setTypeOfCell(TypesOfCells.SVISLAZEZHORA);
                            labels[okynkoXold][okynkoYold].setTypeOfCell(TypesOfCells.ZEZDOLADOLEVA);
                            zadavajici.add("↓");
                            break;
                        }
                        case VODOROVNAZPRAVA -> {
                            labels[okynkoX][okynkoY].setTypeOfCell(TypesOfCells.SVISLAZEZHORA);
                            labels[okynkoXold][okynkoYold].setTypeOfCell(TypesOfCells.ZEZDOLADOPRAVA);
                            zadavajici.add("↓");
                            break;
                        }
                    }
                } else if(okynkoXold == (okynkoX + 1)) {
                    switch (labels[okynkoXold][okynkoYold].getType()){
                        case STARTINGPOINT -> {
                            labels[okynkoXold][okynkoYold].setTypeOfCell(TypesOfCells.SVISLAZEZDOLA);
                            zadavajici.add("↑");
                        }
                        case SVISLAZEZDOLA, ZEZDOLADOLEVA, ZEZDOLADOPRAVA, ZEZHORADOLEVA, ZEZHORADOPRAVA -> {
                            labels[okynkoX][okynkoY].setTypeOfCell(TypesOfCells.SVISLAZEZDOLA);
                            zadavajici.add("↑");
                            break;
                        }
                        case VODOROVNAZLEVA -> {
                            labels[okynkoX][okynkoY].setTypeOfCell(TypesOfCells.SVISLAZEZDOLA);
                            labels[okynkoXold][okynkoYold].setTypeOfCell(TypesOfCells.ZEZHORADOLEVA);
                            zadavajici.add("↑");
                            break;
                        }
                        case VODOROVNAZPRAVA -> {
                            labels[okynkoX][okynkoY].setTypeOfCell(TypesOfCells.SVISLAZEZDOLA);
                            labels[okynkoXold][okynkoYold].setTypeOfCell(TypesOfCells.ZEZHORADOPRAVA);
                            zadavajici.add("↑");
                            break;
                        }
                        case BLANK -> {
                            labels[okynkoXold][okynkoYold].setTypeOfCell(TypesOfCells.SVISLAZEZDOLA);
                            zadavajici.add("↑");
                            break;
                        }
                    }
                } else if(okynkoYold == (okynkoY - 1)){
                    switch (labels[okynkoXold][okynkoYold].getType()){
                        case STARTINGPOINT -> {
                            labels[okynkoXold][okynkoYold].setTypeOfCell(TypesOfCells.VODOROVNAZLEVA);
                            zadavajici.add("→");
                        }
                        case VODOROVNAZLEVA,ZEZDOLADOLEVA,ZEZDOLADOPRAVA,ZEZHORADOLEVA,ZEZHORADOPRAVA -> {
                            labels[okynkoX][okynkoY].setTypeOfCell(TypesOfCells.VODOROVNAZLEVA);
                            zadavajici.add("→");
                        }
                        case SVISLAZEZHORA -> {
                            labels[okynkoX][okynkoY].setTypeOfCell(TypesOfCells.VODOROVNAZLEVA);
                            labels[okynkoXold][okynkoYold].setTypeOfCell(TypesOfCells.ZEZHORADOPRAVA);
                            zadavajici.add("→");
                            break;
                        }
                        case SVISLAZEZDOLA -> {
                            labels[okynkoX][okynkoY].setTypeOfCell(TypesOfCells.VODOROVNAZLEVA);
                            labels[okynkoXold][okynkoYold].setTypeOfCell(TypesOfCells.ZEZDOLADOPRAVA);
                            zadavajici.add("→");
                            break;
                        }
                        case BLANK -> {
                            labels[okynkoXold][okynkoYold].setTypeOfCell(TypesOfCells.VODOROVNAZLEVA);
                            zadavajici.add("→");
                            break;
                        }
                    }
                } else if(okynkoYold == (okynkoY + 1)){
                    labels[okynkoX][okynkoY].setTypeOfCell(TypesOfCells.VODOROVNAZPRAVA);
                    switch (labels[okynkoXold][okynkoYold].getType()){
                        case STARTINGPOINT -> {
                            labels[okynkoXold][okynkoYold].setTypeOfCell(TypesOfCells.VODOROVNAZPRAVA);
                            zadavajici.add("←");
                        }
                        case VODOROVNAZPRAVA,ZEZDOLADOLEVA,ZEZDOLADOPRAVA,ZEZHORADOLEVA,ZEZHORADOPRAVA -> {
                            labels[okynkoX][okynkoY].setTypeOfCell(TypesOfCells.VODOROVNAZPRAVA);
                            zadavajici.add("←");
                        }
                        case SVISLAZEZHORA -> {
                            labels[okynkoX][okynkoY].setTypeOfCell(TypesOfCells.VODOROVNAZPRAVA);
                            labels[okynkoXold][okynkoYold].setTypeOfCell(TypesOfCells.ZEZHORADOLEVA);
                            zadavajici.add("←");
                            break;
                        }
                        case SVISLAZEZDOLA -> {
                            labels[okynkoX][okynkoY].setTypeOfCell(TypesOfCells.VODOROVNAZPRAVA);
                            labels[okynkoXold][okynkoYold].setTypeOfCell(TypesOfCells.ZEZDOLADOLEVA);
                            zadavajici.add("←");
                            break;
                        }
                        case BLANK -> {
                            labels[okynkoXold][okynkoYold].setTypeOfCell(TypesOfCells.VODOROVNAZPRAVA);
                            zadavajici.add("←");
                            break;
                        }
                    }
                } else if(labels[okynkoX][okynkoY].getType() == null){
                    labels[okynkoX][okynkoY].setTypeOfCell(TypesOfCells.STARTINGPOINT);
                    zadavajici.add(" X: " + okynkoX + " Y: " + okynkoY);
                }

                System.out.println("OldX: " + okynkoXold + " OldY: " + okynkoYold);
                System.out.println("X: " + okynkoX + " Y: " + okynkoY);

                okynkoYold = okynkoY;
                okynkoXold = okynkoX;

                System.out.println(pocetOkynek);

               zadani.setText(zadavajici.toString());
            }
        });

        artBox.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(startingCell == null){
                startingCell = new Cell(true,Color.black);
            }
            }
        });


        GridBagConstraints g2 = new GridBagConstraints();

        g2.gridx = 1;
        g2.gridy = 1;

        g2.gridheight = 1000;
        g2.gridwidth = 1000;

        add(artBox,BorderLayout.CENTER);

        g2.gridx = 2;
        g2.gridy = 2;

        g2.gridheight = 100;
        g2.gridwidth = 1000;
        add(setupBox,BorderLayout.NORTH);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                moveMouseToCenter();
            }
        });
        setVisible(true);

        for (int i = 0;i < rowsAndColumms;i++){
            for (int n = 0;n < rowsAndColumms;n++) {
                GridBagConstraints gc = new GridBagConstraints();

                gc.weightx = 1;
                gc.weighty = 1;

                gc.gridx = i;
                gc.gridy = n;

                gc.fill = GridBagConstraints.CENTER;

                Cell cell = new Cell(false,Color.black);
                cell.setSize(new Dimension(100,100));
                cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                cell.setType(TypesOfCells.BLANK);
                cell.setText(i + " " + n);
                labels[i][n] = cell;
                artBox.add(cell);
            }
        }

    }

    public void setVisible(){
        setVisible(true);
    }

    private void moveMouseToCenter() {
        try {
            Robot robot = new Robot();
            Point location = getLocationOnScreen();
            int centerX = location.x + getWidth() / 2;
            int centerY = location.y + getHeight() / 2;
            robot.mouseMove(centerX, centerY);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
                 */
}
