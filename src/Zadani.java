import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Zadani extends JFrame {

    private String zadani;
    public Zadani() throws HeadlessException {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBackground(Color.white);
        setLayout(new GridBagLayout());
        setSize(1000,300);
        JButton up = new JButton("nahoru");
        JButton down = new JButton("dolu");
        JButton left = new JButton("do leva");
        JButton right = new JButton("do prava");
        JButton done = new JButton("hotovo");
        JLabel text = new JLabel();
        zadani = "";

        GridBagConstraints g = new GridBagConstraints();

        up.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zadani += "↑,";
                text.setText(zadani);
            }
        });

        g.gridx = 1;
        g.gridy = 1;

        g.weightx = 1;
        g.weighty = 1;

        g.fill = GridBagConstraints.BOTH;

        add(up,g);

        down.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zadani += "↓,";
                text.setText(zadani);
            }
        });

        g.gridx = 2;
        g.gridy = 1;

        add(down,g);

        left.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zadani += "←,";
                text.setText(zadani);
            }
        });

        g.gridx = 3;
        g.gridy = 1;

        add(left,g);

        right.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zadani += "→,";
                text.setText(zadani);
            }
        });

        g.gridx = 4;
        g.gridy = 1;

        add(right,g);

        text.setText(zadani);

        g.gridx = 2;
        g.gridy = 2;

        g.fill = 3;

        add(text,g);

        g.gridx = 2;
        g.gridy = 3;

        g.gridwidth = 2;
        g.fill = GridBagConstraints.HORIZONTAL;

        add(done,g);
        setVisible(true);
    }
}
