import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Render extends JFrame {

    int rowsAndColumms = 10;
    private Component[] components;

    private Cell startingCell;

    private Cell[][] labels = new Cell[rowsAndColumms][rowsAndColumms];
    private int pocetOkynek;

    public Render() {
        JPanel artBox = new JPanel();
        JPanel setupBox = new JPanel();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(new Dimension(rowsAndColumms * 100,(rowsAndColumms + ((rowsAndColumms / 100)*10)) * 100));
        setResizable(false);

        artBox.setLayout(new GridBagLayout());
        artBox.setSize(new Dimension(rowsAndColumms * 100,rowsAndColumms * 100));

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

        g.gridx = 3;
        g.gridy = 1;

        g.weightx = 1;
        g.weighty = 1;

        g.fill = GridBagConstraints.BOTH;

        setupBox.add(done,g);

        for (int i = 0;i < rowsAndColumms;i++){
            for (int n = 0;n < rowsAndColumms;n++) {
                GridBagConstraints gc = new GridBagConstraints();
                gc.weightx = 0.0;
                gc.weighty = 0.0;

                gc.gridx = i;
                gc.gridy = n;

                gc.fill = GridBagConstraints.BOTH;
                gc.insets = new Insets(0,0,0,0);

                Cell cell = new Cell(false);
                cell.setSize(new Dimension(100,100));
                cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                labels[i][n] = cell;
                artBox.add(cell,gc);
            }
        }

        this.components = getComponents();

        artBox.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                int x = e.getX();
                int y = e.getY();

                int okynkoX = e.getX() / (artBox.getWidth() / rowsAndColumms);
                int okynkoY = e.getY() / (artBox.getHeight() / rowsAndColumms);

                pocetOkynek = okynkoX * rowsAndColumms + okynkoY;

                labels[okynkoX][okynkoY].setTypeOfCell(TypesOfCells.VODOROVNA);

                GridBagConstraints gc = new GridBagConstraints();
                gc.weightx = 1;
                gc.weighty = 1;

                gc.gridx = okynkoX;
                gc.gridy = okynkoY;

                gc.fill = GridBagConstraints.BOTH;

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

        add(artBox);
        add(setupBox);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                moveMouseToCenter();
            }
        });
        setVisible(true);



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
