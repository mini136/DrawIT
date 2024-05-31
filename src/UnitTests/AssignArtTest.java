package UnitTests;

import NewImage.AssignArt;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import static org.junit.Assert.*;

public class AssignArtTest {

    private AssignArt assignArt;
    private String testName = "testArt";

    @Before
    public void setUp() {
        assignArt = new AssignArt(testName);
    }

    @Test
    public void testInitialization() {
        assertNotNull(assignArt);
        assertEquals(800, assignArt.getWidth());
        assertEquals(1000, assignArt.getHeight());
        assertFalse(assignArt.isResizable());
        assertEquals(BorderLayout.class, assignArt.getLayout().getClass());
        assertEquals(Color.BLACK, assignArt.getContentPane().getBackground());
    }

    @Test
    public void testButtonAction() {
        JButton button = (JButton) findComponent(assignArt, JButton.class, "Save");
        assertNotNull(button);

        ActionEvent event = new ActionEvent(button, ActionEvent.ACTION_PERFORMED, "Save");
        for (ActionListener al : button.getActionListeners()) {
            al.actionPerformed(event);
        }

        File dir = new File("pictures/" + testName);
        assertTrue(dir.exists() && dir.isDirectory());

        File serFile = new File(dir, testName + ".ser");
        File txtSerFile = new File(dir, testName + "Txt.ser");
        assertTrue(serFile.exists());
        assertTrue(txtSerFile.exists());

        serFile.delete();
        txtSerFile.delete();
        dir.delete();
    }

    @Test
    public void testTimer() throws InterruptedException {
        assignArt.setVisible(true);
        Thread.sleep(200);

        JTextArea textArea = (JTextArea) findComponent(assignArt, JTextArea.class, null);
        assertNotNull(textArea);
        assertTrue(textArea.getText().isEmpty());
    }

    private Component findComponent(Container container, Class<?> clazz, String identifier) {
        for (Component component : container.getComponents()) {
            if (clazz.isInstance(component)) {
                if (identifier == null || (component instanceof JButton && identifier.equals(((JButton) component).getText())) || identifier.equals(component.getName())) {
                    return component;
                }
            } else if (component instanceof Container) {
                Component found = findComponent((Container) component, clazz, identifier);
                if (found != null) {
                    return found;
                }
            }
        }
        return null;
    }
}
