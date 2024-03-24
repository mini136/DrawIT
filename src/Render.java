import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class Render extends JFrame {

    int rowsAndColumms = 10;
    private Component[] components;

    public Render() {
        JPanel artBox = new JPanel();
        JPanel setupBox = new JPanel();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(new Dimension(rowsAndColumms * 100,(rowsAndColumms + ((rowsAndColumms / 100)*10)) * 100));
        setResizable(false);

        artBox.setBackground(Color.BLACK);
        artBox.setLayout(new GridLayout(rowsAndColumms,rowsAndColumms));
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

        for (int i = 0;i < rowsAndColumms * rowsAndColumms;i++){
            Cell cell = new Cell();
            artBox.add(cell);
        }

        this.components = getComponents();

        artBox.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                int x = e.getX();
                int y = e.getY();
                System.out.println(x + "  " + y);
            }
        });

        add(artBox);
        add(setupBox);
        setVisible(true);




    }

    public void setVisible(){
        setVisible(true);
    }


}
