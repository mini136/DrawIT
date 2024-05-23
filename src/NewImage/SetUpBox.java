package NewImage;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SetUpBox extends JPanel {

    private JTextArea textArea;
    private JButton button;
    private boolean buttonPressed;
    public SetUpBox(JButton button) {
        setPreferredSize(new Dimension(800, 200));
        setLayout(new BorderLayout());
        setOpaque(true);
        setBackground(Color.white);

        textArea = new JTextArea();
        textArea.setPreferredSize(new Dimension(200, 100));
        textArea.setLineWrap(true);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 20));

        add(textArea, BorderLayout.NORTH);

        this.button = button;

        int arcWidth = 15; // šířka oblouku
        int arcHeight = 15; // výška oblouku
        Border roundedBorder = BorderFactory.createEmptyBorder(arcWidth, arcWidth,arcWidth,arcWidth);

        button.setBorder(roundedBorder);
        button.setOpaque(true);
        button.setBackground(new Color(176, 87, 215));

        add(button, BorderLayout.CENTER);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonPressed = true;
            }
        });
    }

    public void setTextString(ArrayList<String> arrayList){
        textArea.setText(arrayList.toString());
    }

    public boolean isPressed(){
        return buttonPressed;
    }

}
