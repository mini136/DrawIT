import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Render extends JFrame {

    int rowsAndColumms = 10;
    private Component[] components;
    private Cell startingCell;
    private Cell[][] labels = new Cell[rowsAndColumms][rowsAndColumms];
    private int pocetOkynek;
    private int okynkoXold = 0;
    private int okynkoYold = 0;


    public Render() {
        JPanel artBox = new JPanel();
        JPanel setupBox = new JPanel();
        JPanel mainPanel = new JPanel();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(new Dimension(rowsAndColumms * 100,(rowsAndColumms + ((rowsAndColumms / 100)*10)) * 100));
        setLayout(new BorderLayout());
        setResizable(false);

        artBox.setSize(new Dimension(rowsAndColumms * 100,rowsAndColumms * 100));
        artBox.setLayout(new GridLayout(rowsAndColumms,rowsAndColumms));

        setupBox.setSize(new Dimension(rowsAndColumms * 100,((rowsAndColumms / 100)*10)));
        setupBox.setLayout(new GridBagLayout());

        GridBagConstraints g = new GridBagConstraints();


        JLabel text = new JLabel();

        text.setBackground(Color.white);

        g.gridx = 1;
        g.gridy = 1;

        g.weightx = 1;
        g.weighty = 2;

        g.fill = GridBagConstraints.BOTH;

        setupBox.add(text,g);

        JButton done = new JButton();

        done.setSize(new Dimension(500,100));

        g.gridx = 3;
        g.gridy = 1;

        g.weightx = 1;
        g.weighty = 1;

        g.fill = GridBagConstraints.BOTH;

        setupBox.add(done,g);


        this.components = getComponents();


        artBox.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                int x = e.getX();
                int y = e.getY();

                int okynkoY = (e.getX() / (artBox.getWidth() / rowsAndColumms));
                int okynkoX = (e.getY() / (artBox.getHeight() / rowsAndColumms));



                pocetOkynek = okynkoX * rowsAndColumms + okynkoY;

                if (okynkoXold == (okynkoX - 1)) {

                    switch (labels[okynkoXold][okynkoYold].getType()){
                        case STARTINGPOINT -> {
                            labels[okynkoXold][okynkoYold].setTypeOfCell(TypesOfCells.SVISLAZEZHORA);
                        }
                        case SVISLAZEZHORA, ZEZDOLADOLEVA, ZEZDOLADOPRAVA, ZEZHORADOLEVA, ZEZHORADOPRAVA -> labels[okynkoX][okynkoY].setTypeOfCell(TypesOfCells.SVISLAZEZHORA);
                        case VODOROVNAZLEVA -> {
                            labels[okynkoX][okynkoY].setTypeOfCell(TypesOfCells.SVISLAZEZHORA);
                            labels[okynkoXold][okynkoYold].setTypeOfCell(TypesOfCells.ZEZDOLADOLEVA);
                            break;
                        }
                        case VODOROVNAZPRAVA -> {
                            labels[okynkoX][okynkoY].setTypeOfCell(TypesOfCells.SVISLAZEZHORA);
                            labels[okynkoXold][okynkoYold].setTypeOfCell(TypesOfCells.ZEZDOLADOPRAVA);
                            break;
                        }
                        case BLANK -> {
                            labels[okynkoX][okynkoY].setTypeOfCell(TypesOfCells.SVISLAZEZHORA);
                            break;
                        }
                    }
                } else if(okynkoXold == (okynkoX + 1)) {
                    switch (labels[okynkoXold][okynkoYold].getType()){
                        case STARTINGPOINT -> {
                            labels[okynkoXold][okynkoYold].setTypeOfCell(TypesOfCells.SVISLAZEZDOLA);
                        }
                        case SVISLAZEZDOLA, ZEZDOLADOLEVA, ZEZDOLADOPRAVA, ZEZHORADOLEVA, ZEZHORADOPRAVA -> labels[okynkoX][okynkoY].setTypeOfCell(TypesOfCells.SVISLAZEZDOLA);
                        case VODOROVNAZLEVA -> {
                            labels[okynkoX][okynkoY].setTypeOfCell(TypesOfCells.SVISLAZEZDOLA);
                            labels[okynkoXold][okynkoYold].setTypeOfCell(TypesOfCells.ZEZHORADOLEVA);
                            break;
                        }
                        case VODOROVNAZPRAVA -> {
                            labels[okynkoX][okynkoY].setTypeOfCell(TypesOfCells.SVISLAZEZDOLA);
                            labels[okynkoXold][okynkoYold].setTypeOfCell(TypesOfCells.ZEZHORADOPRAVA);
                            break;
                        }
                        case BLANK -> {
                            labels[okynkoXold][okynkoYold].setTypeOfCell(TypesOfCells.SVISLAZEZDOLA);
                            break;
                        }
                    }
                } else if(okynkoYold == (okynkoY - 1)){
                    switch (labels[okynkoXold][okynkoYold].getType()){
                        case STARTINGPOINT -> {
                            labels[okynkoXold][okynkoYold].setTypeOfCell(TypesOfCells.VODOROVNAZLEVA);
                        }
                        case VODOROVNAZLEVA,ZEZDOLADOLEVA,ZEZDOLADOPRAVA,ZEZHORADOLEVA,ZEZHORADOPRAVA -> labels[okynkoX][okynkoY].setTypeOfCell(TypesOfCells.VODOROVNAZLEVA);
                        case SVISLAZEZHORA -> {
                            labels[okynkoX][okynkoY].setTypeOfCell(TypesOfCells.VODOROVNAZLEVA);
                            labels[okynkoXold][okynkoYold].setTypeOfCell(TypesOfCells.ZEZHORADOPRAVA);
                            break;
                        }
                        case SVISLAZEZDOLA -> {
                            labels[okynkoX][okynkoY].setTypeOfCell(TypesOfCells.VODOROVNAZLEVA);
                            labels[okynkoXold][okynkoYold].setTypeOfCell(TypesOfCells.ZEZDOLADOPRAVA);
                            break;
                        }
                        case BLANK -> {
                            labels[okynkoXold][okynkoYold].setTypeOfCell(TypesOfCells.VODOROVNAZLEVA);
                            break;
                        }
                    }
                } else if(okynkoYold == (okynkoY + 1)){
                    labels[okynkoX][okynkoY].setTypeOfCell(TypesOfCells.VODOROVNAZPRAVA);
                    switch (labels[okynkoXold][okynkoYold].getType()){
                        case STARTINGPOINT -> {
                            labels[okynkoXold][okynkoYold].setTypeOfCell(TypesOfCells.VODOROVNAZPRAVA);
                        }
                        case VODOROVNAZPRAVA,ZEZDOLADOLEVA,ZEZDOLADOPRAVA,ZEZHORADOLEVA,ZEZHORADOPRAVA -> labels[okynkoX][okynkoY].setTypeOfCell(TypesOfCells.VODOROVNAZPRAVA);
                        case SVISLAZEZHORA -> {
                            labels[okynkoX][okynkoY].setTypeOfCell(TypesOfCells.VODOROVNAZPRAVA);
                            labels[okynkoXold][okynkoYold].setTypeOfCell(TypesOfCells.ZEZHORADOLEVA);
                            break;
                        }
                        case SVISLAZEZDOLA -> {
                            labels[okynkoX][okynkoY].setTypeOfCell(TypesOfCells.VODOROVNAZPRAVA);
                            labels[okynkoXold][okynkoYold].setTypeOfCell(TypesOfCells.ZEZDOLADOLEVA);
                            break;
                        }
                        case BLANK -> {
                            labels[okynkoXold][okynkoYold].setTypeOfCell(TypesOfCells.VODOROVNAZPRAVA);
                            break;
                        }
                    }
                } else if(labels[okynkoX][okynkoY].getType() == null){
                    labels[okynkoX][okynkoY].setTypeOfCell(TypesOfCells.STARTINGPOINT);
                }

                System.out.println("OldX: " + okynkoXold + " OldY: " + okynkoYold);
                System.out.println("X: " + okynkoX + " Y: " + okynkoY);

                okynkoYold = okynkoY;
                okynkoXold = okynkoX;

                System.out.println(pocetOkynek);
            }
        });

        artBox.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(startingCell == null){
                startingCell = new Cell(true);
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
        add(setupBox,BorderLayout.SOUTH);

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

                Cell cell = new Cell(false);
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

}
