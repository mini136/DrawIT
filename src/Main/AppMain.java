package Main;

import Border.RoundedBorder;
import NewImage.AssignArt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AppMain extends JFrame {

    private JButton setUp;
    private JButton newArt;
    private JButton findArt;
    private JTextArea textArea;

    public AppMain() throws HeadlessException {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1080, 1750)); // Nastavení preferované velikosti okna
        setLayout(new GridBagLayout());
        try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }

        textArea = new JTextArea();
        textArea.setText("Draw It");
        setUp = new JButton("Options");
        setUp.setPreferredSize(new Dimension(300, 100)); // Nastavení rozměrů tlačítka
        setUp.setBackground(new Color(176,87,215));
        setUp.setBorderPainted(true);

        newArt = new JButton("Make New Picture");
        newArt.setPreferredSize(new Dimension(300, 100)); // Nastavení rozměrů tlačítka
        newArt.setBackground(new Color(176,87,215));
        newArt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                AssignArt assign = new AssignArt();
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
        findArt.setBackground(new Color(176,87,215));
        setUp.setBorderPainted(true);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20); // Přidání mezery kolem komponent

        // Nastavení pozice a velikosti textové oblasti
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1; // Roztáhnout na 3 sloupce
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH; // Vyplnit jak vodorovně, tak svisle
        add(textArea, gbc);

        // Nastavení pozice a velikosti tlačítka "Play"
        gbc.gridx = 0;
        gbc.gridy = 1; // Změna pozice tlačítka na další řádek
        gbc.gridwidth = 1; // Roztáhnout na 1 sloupec
        gbc.fill = GridBagConstraints.NONE; // Nevyplňovat
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
    }

}