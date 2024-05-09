package Panels;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SetUpBox extends JPanel {

    private JTextArea textArea;
    private JButton button;
    private boolean buttonPressed;
    public SetUpBox() {
        setPreferredSize(new Dimension(800, 200));
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setPreferredSize(new Dimension(200, 100));
        textArea.setLineWrap(true);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 20));

        add(textArea, BorderLayout.NORTH);

        this.button = new JButton("Zadat");

        int arcWidth = 15; // šířka oblouku
        int arcHeight = 15; // výška oblouku
        Border roundedBorder = BorderFactory.createEmptyBorder(arcWidth, arcWidth,arcWidth,arcWidth);

        button.setBorder(roundedBorder);

        add(button, BorderLayout.CENTER);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonPressed = true;
            }
        });
    }

    public void setTextString(String text){
        textArea.setText(text);
    }

    public boolean isPressed(){
        return buttonPressed;
    }

}
