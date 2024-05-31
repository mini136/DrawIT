package Main;

import NewImage.AssignArt;
import Play.ChooseFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class AppMain extends JFrame {

    private JButton setUp;
    private JButton newArt;
    private JButton findArt;
    private JLabel logo;
    private Font customFont;


    /**
     * Main application window for the drawing application. Provides options to start a new drawing,
     * find an existing drawing, or access options.
     */
    public AppMain() throws HeadlessException {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1080, 750));
        setLayout(new GridBagLayout());
        getContentPane().setBackground(Color.white);
        setIconImage(new ImageIcon("logos/drawItIcon.png").getImage());

        try {
            customFont = new Font("SansSerif", Font.BOLD, 35);
        } catch (Exception e) {
            e.printStackTrace();
            customFont = new Font("Arial", Font.PLAIN, 14);
        }

        logo = new JLabel();
        logo.setIcon(resizeIcon("logos/drawItIcon.png", 300, 300));
        setUp = new JButton("Options");
        setUp.setPreferredSize(new Dimension(300, 100));
        setUp.setBackground(new Color(176, 87, 215));
        setUp.setBorderPainted(true);
        setUp.setFont(customFont);
        setUp.setForeground(Color.white);

        newArt = new JButton("New Picture");
        newArt.setPreferredSize(new Dimension(300, 100));
        newArt.setBackground(new Color(176, 87, 215));
        newArt.setFont(customFont);
        newArt.setForeground(Color.white);
        newArt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                if(!isVisible()) {
                    String fileName = JOptionPane.showInputDialog("Name of your project:");
                    AssignArt assign = new AssignArt(fileName);
                    Timer timer = new Timer(100, d -> {
                        if (assign.isPressed()) {
                            assign.setVisible(false);
                            setVisible(true);
                            ((Timer) d.getSource()).stop();
                        }

                    });
                    timer.start();
                }
            }
        });

        setUp.setBorderPainted(true);
        setUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        findArt = new JButton("Play");
        findArt.setPreferredSize(new Dimension(300, 100));
        findArt.setBackground(new Color(176, 87, 215));
        findArt.setFont(customFont);
        findArt.setForeground(Color.white);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);


        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 0.5;
        gbc.anchor = GridBagConstraints.CENTER;
        add(logo, gbc);


        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weighty = 0.0;
        gbc.anchor = GridBagConstraints.CENTER;
        add(findArt, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(newArt, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        add(setUp, gbc);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        findArt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                if(!isVisible()) {
                    ChooseFrame frame = new ChooseFrame();
                    Timer timer = new Timer(100, d -> {
                        if (frame.isPressed()) {
                            setVisible(true);
                            ((Timer) d.getSource()).stop();
                        }

                     });
                    timer.start();
                }
            }
        });
    }


    /**
     * Resizes an icon to the specified width and height.
     *
     * @param path   the path to the icon file.
     * @param width  the desired width of the icon.
     * @param height the desired height of the icon.
     * @return the resized ImageIcon.
     */
    private ImageIcon resizeIcon(String path, int width, int height) {
        try {
            BufferedImage img = ImageIO.read(new File(path));
            Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImg);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    //region get a set

    public JButton getSetUp() {
        return setUp;
    }

    public void setSetUp(JButton setUp) {
        this.setUp = setUp;
    }

    public JButton getNewArt() {
        return newArt;
    }

    public void setNewArt(JButton newArt) {
        this.newArt = newArt;
    }

    public JButton getFindArt() {
        return findArt;
    }

    public void setFindArt(JButton findArt) {
        this.findArt = findArt;
    }

    public JLabel getLogo() {
        return logo;
    }

    public void setLogo(JLabel logo) {
        this.logo = logo;
    }

    public Font getCustomFont() {
        return customFont;
    }

    public void setCustomFont(Font customFont) {
        this.customFont = customFont;
    }

    //endregion
}
