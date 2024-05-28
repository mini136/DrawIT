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

    public AppMain() throws HeadlessException {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1080, 750)); // Nastavení preferované velikosti okna
        setLayout(new GridBagLayout());
        getContentPane().setBackground(Color.white);
        setIconImage(new ImageIcon("logos/drawItIcon.png").getImage());

        try {
            customFont = new Font("SansSerif", Font.BOLD, 35); // Zde můžete specifikovat jiný font, styl a velikost
        } catch (Exception e) {
            e.printStackTrace();
            customFont = new Font("Arial", Font.PLAIN, 14); // Fallback na základní font, pokud vlastní font není nalezen
        }

        logo = new JLabel();
        logo.setIcon(resizeIcon("logos/drawItIcon.png", 300, 300)); // Resize the icon
        setUp = new JButton("Options");
        setUp.setPreferredSize(new Dimension(300, 100)); // Nastavení rozměrů tlačítka
        setUp.setBackground(new Color(176, 87, 215));
        setUp.setBorderPainted(true);
        setUp.setFont(customFont);
        setUp.setForeground(Color.white);

        newArt = new JButton("New Picture");
        newArt.setPreferredSize(new Dimension(300, 100)); // Nastavení rozměrů tlačítka
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
        findArt.setPreferredSize(new Dimension(300, 100)); // Nastavení rozměrů tlačítka
        findArt.setBackground(new Color(176, 87, 215));
        findArt.setFont(customFont);
        findArt.setForeground(Color.white);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20); // Přidání mezery kolem komponent

        // Nastavení pozice a velikosti textové oblasti
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3; // Roztáhnout na 3 sloupce
        gbc.weightx = 1.0;
        gbc.weighty = 0.5;
        gbc.anchor = GridBagConstraints.CENTER;
        add(logo, gbc);

        // Nastavení pozice a velikosti tlačítka "Play"
        gbc.gridx = 0;
        gbc.gridy = 1; // Změna pozice tlačítka na další řádek
        gbc.gridwidth = 1; // Roztáhnout na 1 sloupec
        gbc.weighty = 0.0;
        gbc.anchor = GridBagConstraints.CENTER;
        add(findArt, gbc);

        // Nastavení pozice a velikosti tlačítka "Make New Picture"
        gbc.gridx = 1;
        gbc.gridy = 1; // Změna pozice tlačítka na další řádek
        add(newArt, gbc);

        // Nastavení pozice a velikosti tlačítka "Options"
        gbc.gridx = 2;
        gbc.gridy = 1; // Změna pozice tlačítka na další řádek
        add(setUp, gbc);

        pack(); // Uložit komponenty
        setLocationRelativeTo(null); // Zarovnat okno do středu obrazovky
        setVisible(true); // Zobrazit okno

        findArt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                if(!isVisible()) {
                    ChooseFrame frame = new ChooseFrame();
                    Timer timer = new Timer(100, d -> {
                        if (frame.isPressed()) {
                            ((Timer) d.getSource()).stop();
                        }

                    });
                    timer.start();
                }
            }
        });
    }

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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AppMain();
            }
        });
    }
}
