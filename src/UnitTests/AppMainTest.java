package UnitTests;

import Main.AppMain;
import NewImage.AssignArt;
import Play.ChooseFrame;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static org.junit.Assert.*;

public class AppMainTest {

    private AppMain appMain;

    @Before
    public void setUp() {
        SwingUtilities.invokeLater(() -> appMain = new AppMain());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testInitialization() {
        assertNotNull(appMain);
        assertEquals(new Dimension(1080, 750), appMain.getPreferredSize());
        assertEquals(Color.white, appMain.getContentPane().getBackground());
        assertNotNull(appMain.getLogo());
        assertNotNull(appMain.getSetUp());
        assertNotNull(appMain.getNewArt());
        assertNotNull(appMain.getFindArt());
    }

    @Test
    public void testNewArtButton() {
        JButton newArtButton = appMain.getNewArt();
        assertNotNull(newArtButton);
        assertEquals("New Picture", newArtButton.getText());

        for (ActionListener al : newArtButton.getActionListeners()) {
            al.actionPerformed(new ActionEvent(newArtButton, ActionEvent.ACTION_PERFORMED, null));
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertFalse(appMain.isVisible());
    }

}
