package NewImage;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AssignArt extends JFrame{

    private boolean done;
    private ArrayList<String> zadani;
    public AssignArt() throws HeadlessException {
        setSize(800,1000);
        setResizable(false);
        setLayout(new BorderLayout());

        SetUpBox box = new SetUpBox();
        ArtPane002 algorithm = new ArtPane002(Color.black);
        add(box,BorderLayout.SOUTH);
        add(algorithm,BorderLayout.CENTER);
        setVisible(true);
        Timer timer = new Timer(100, e -> {
            box.setTextString(algorithm.getOutputText());
            box.repaint();

            if(box.isPressed()){
                setVisible(false);
                done = true;
            }
        });
        timer.start();
    }
}
