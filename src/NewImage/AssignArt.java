package NewImage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
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
        ArtPane002 algorithm = new ArtPane002(Color.black);// Vytvoření nového panelu pro mřížku

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                File newDirectory = new File("pictures/" + name);
                boolean mkdir = newDirectory.mkdir();
                if(mkdir) {
                    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("pictures/" + name + "/" + name + ".ser"))) {
                        oos.writeObject(algorithm.getLabels());
                        System.out.println("Data were saved successfully.");
                    } catch (IOException s) {
                        s.printStackTrace();
                    }
                    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("pictures/" + name + "/" + name + "Txt.ser"))) {
                        oos.writeObject(algorithm.getOutputText());
                        System.out.println("Data were saved successfully.");
                    } catch (IOException s) {
                        s.printStackTrace();
                    }
                } else {
                    System.out.println("slozka se nevitvorila");
                }
            }
        });

        add(algorithm, BorderLayout.NORTH); // Přidání nového panelu s mřížkou do levého horního rohu okna
        add(box, BorderLayout.SOUTH);

        setVisible(true);

        Timer timer = new Timer(100, e -> {
            box.setTextString(algorithm.getOutputText());
            box.repaint();

            if (box.isPressed()) {
                done = true;
                dispose();
            }
        });

        timer.start();
        if (box.isPressed()) {
            timer.stop();
        }
    }

    public boolean isPressed() {
        return box.isPressed();
    }
}
