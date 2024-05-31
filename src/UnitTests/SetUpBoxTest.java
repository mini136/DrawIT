package UnitTests;

import NewImage.SetUpBox;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class SetUpBoxTest {

    private SetUpBox setUpBox;
    private JButton button;

    @Before
    public void setUp() {
        button = new JButton("Test Button");
        setUpBox = new SetUpBox(button);
        JFrame frame = new JFrame();
        frame.add(setUpBox);
        frame.pack();
        frame.setVisible(true);
    }

    @Test
    public void testSetTextString() {
        ArrayList<String> testList = new ArrayList<>();
        testList.add("Test");
        testList.add("Text");

        setUpBox.setTextString(testList);

        assertEquals("[Test, Text]", setUpBox.getTextFromTextArea());
    }

    @Test
    public void testButtonPressed() {
        assertFalse(setUpBox.isPressed());

        // Simulate button press
        for (ActionListener al : button.getActionListeners()) {
            al.actionPerformed(new ActionEvent(button, ActionEvent.ACTION_PERFORMED, null));
        }

        assertTrue(setUpBox.isPressed());
    }
}
