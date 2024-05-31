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
            customFont = new Font("SansSerif", Font.BOLD, 50); // Zde můžete specifikovat jiný font, styl a velikost
        } catch (Exception e) {
            e.printStackTrace();
            customFont = new Font("Arial", Font.PLAIN, 14); // Fallback na základní font, pokud vlastní font není nalezen
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

        // Check if the path is a directory
        if (parentDirectory.isDirectory()) {
            // Get all files and directories in the parent directory
            File[] files = parentDirectory.listFiles();

            if (files != null) {
                // Count directories
                int dirCount = 0;
                for (File file : files) {
                    if (file.isDirectory()) {
                        dirCount++;
                    }
                }

                // Create an array for directory names
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
        return new String[0]; // Return an empty array if the path is not a directory or no directories found
    }

    public String deleteSer(String fileName) {
            return fileName.substring(0, fileName.length() - 4);
    }

    public boolean isPressed() {
        return isPressed;
    }

    public void setPressed(boolean pressed) {
        isPressed = pressed;
    }
}

