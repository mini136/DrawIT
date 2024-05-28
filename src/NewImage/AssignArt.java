package NewImage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class AssignArt extends JFrame {
    private boolean done;
    private ArrayList<String> zadani;
    private String name;
    private JButton button;
    private SetUpBox box;

    public AssignArt(String name) throws HeadlessException {
        this.name = name;
        setSize(800, 1000);
        setResizable(false);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.black);

        button = new JButton("Save");
        box = new SetUpBox(button);
        ArtPane002 algorithm = new ArtPane002(Color.black);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("pictures/" + name + ".ser"))) {
                    oos.writeObject(algorithm.getLabels());
                    System.out.println("Data were saved successfully.");
                } catch (IOException s) {
                    s.printStackTrace();
                }
            }
        });

        add(box, BorderLayout.SOUTH);
        add(algorithm, BorderLayout.CENTER);

        setVisible(true);

        Timer timer = new Timer(100, e -> {
            box.setTextString(algorithm.getOutputText());
            box.repaint();

            if (box.isPressed()) {
                setVisible(false);
                done = true;
            }
        });

        timer.start();
    }

    public boolean isPressed() {
        return box.isPressed();
    }
}
