package Play;

import javax.swing.*;
import Cell.Cell;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;

public class ChooseFrame extends JFrame {

    private JScrollPane paneOfPictures;
    private JPanel buttons;
    private String[] pictures;
    private File directory;
    private Font customFont;
    private Component[] listOfButtons;
    private boolean isPressed;

    public ChooseFrame() throws HeadlessException {

        setSize(1080,750);
        setBackground(Color.white);

        paneOfPictures = new JScrollPane();
        pictures = listDirectoryNames("pictures/");
        listOfButtons = new Component[pictures.length];
        buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));
        System.out.println(pictures.toString());

        try {
            customFont = new Font("SansSerif", Font.BOLD, 50);
        } catch (Exception e) {
            e.printStackTrace();
            customFont = new Font("Arial", Font.PLAIN, 14);
        }

        for(int i = 0;i < pictures.length;i++ ){
            JButton button = new JButton();
            button.setOpaque(true);
            button.setBackground(new Color(176, 87, 215));
            button.setForeground(Color.white);
            button.setText(pictures[i]);
            System.out.println("jmeno " + pictures[i]);
            button.setPreferredSize(new Dimension(1050, 100));
            button.setMaximumSize(new Dimension(1050, 100));
            button.setAlignmentX(Component.LEFT_ALIGNMENT);
            button.setFont(customFont);
            button.setBorder(BorderFactory.createLineBorder(Color.white));

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String file = button.getText();
                    setVisible(false);
                    LetsPlay play = new LetsPlay(file);
                    Timer timer = new Timer(100, d -> {
                        isPressed = play.isPressed();
                    });
                    timer.start();

                }
            });

            buttons.add(button);
            listOfButtons[i] = button;
            System.out.println(pictures[i]);
        }
        paneOfPictures.setViewportView(buttons);

        paneOfPictures.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        paneOfPictures.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(paneOfPictures);
        setVisible(true);
    }

    public Cell[][] deserialization(String filePath){
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
             return (Cell[][]) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
            return null;
    }

    public String[] listDirectoryNames(String path) {
        File parentDirectory = new File(path);

        if (parentDirectory.isDirectory()) {
            File[] files = parentDirectory.listFiles();

            if (files != null) {
                int dirCount = 0;
                for (File file : files) {
                    if (file.isDirectory()) {
                        dirCount++;
                    }
                }

                String[] directoryNames = new String[dirCount];
                int index = 0;
                for (File file : files) {
                    if (file.isDirectory()) {
                        directoryNames[index++] = file.getName();
                    }
                }
                return directoryNames;
            }
        }
        return new String[0];
    }

    //region gettters ans setters

    public String deleteSer(String fileName) {
            return fileName.substring(0, fileName.length() - 4);
    }

    public boolean isPressed() {
        return isPressed;
    }

    public void setPressed(boolean pressed) {
        isPressed = pressed;
    }

    public JScrollPane getPaneOfPictures() {
        return paneOfPictures;
    }

    public void setPaneOfPictures(JScrollPane paneOfPictures) {
        this.paneOfPictures = paneOfPictures;
    }

    public JPanel getButtons() {
        return buttons;
    }

    public void setButtons(JPanel buttons) {
        this.buttons = buttons;
    }

    public String[] getPictures() {
        return pictures;
    }

    public void setPictures(String[] pictures) {
        this.pictures = pictures;
    }

    public File getDirectory() {
        return directory;
    }

    public void setDirectory(File directory) {
        this.directory = directory;
    }

    public Font getCustomFont() {
        return customFont;
    }

    public void setCustomFont(Font customFont) {
        this.customFont = customFont;
    }

    public Component[] getListOfButtons() {
        return listOfButtons;
    }

    public void setListOfButtons(Component[] listOfButtons) {
        this.listOfButtons = listOfButtons;
    }

    //endregion
}

