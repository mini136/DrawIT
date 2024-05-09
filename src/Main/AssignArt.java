package Main;

import Panels.SetUpBox;
import Algorithm.MainAlgorithm;

import javax.swing.*;
import java.awt.*;

public class AssignArt extends JFrame{

    public AssignArt() throws HeadlessException {
        setSize(800,1000);
        setResizable(false);
        setLayout(new BorderLayout());

        SetUpBox box = new SetUpBox();
        MainAlgorithm algorithm = new MainAlgorithm();
        add(box,BorderLayout.SOUTH);
        add(algorithm,BorderLayout.CENTER);
        setVisible(true);

        while (true){
            box.setTextString(algorithm.getVypis());
        }
    }
}
