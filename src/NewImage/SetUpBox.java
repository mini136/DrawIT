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
        textArea.setPreferredSize(new Dimension(200, 150));
        textArea.setLineWrap(true);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 20));

        add(textArea, BorderLayout.NORTH);

        this.button = button;

        button.setOpaque(true);
        button.setBackground(new Color(176, 87, 215));
        button.setVisible(true);
        button.setPreferredSize(new Dimension(200,50));

        add(button, BorderLayout.EAST);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonPressed = true;
            }
        });
    }


    //region getters and setters

    public void setTextString(ArrayList<String> arrayList){
        textArea.setText(arrayList.toString());
    }
    public boolean isPressed(){
        return buttonPressed;
    }

    public String getTextFromTextArea(){
        return textArea.getText();
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    public void setTextArea(JTextArea textArea) {
        this.textArea = textArea;
    }

    public JButton getButton() {
        return button;
    }

    public void setButton(JButton button) {
        this.button = button;
    }

    public boolean isButtonPressed() {
        return buttonPressed;
    }

    public void setButtonPressed(boolean buttonPressed) {
        this.buttonPressed = buttonPressed;
    }

    //endregion
}
