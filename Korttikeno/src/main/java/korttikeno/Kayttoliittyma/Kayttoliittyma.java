/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package korttikeno.Kayttoliittyma;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import korttikeno.korttikeno.Korttikeno;

/**
 *
 * @author Jani
 */
public class Kayttoliittyma implements Runnable {

    private JFrame frame;
    private Korttikeno keno = new Korttikeno();
    private JTextField keski;
    private JTextField ylin;

    @Override
    public void run() {
        frame = new JFrame("Korttikeno");
        frame.setPreferredSize(new Dimension(1200, 800));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void luoKomponentit(Container container) {
        container.setLayout(new BorderLayout());

        container.add(new JLabel("Aseta panos, valitse 1-5 korttia, paina 'pelaa'"), BorderLayout.NORTH);
        
        container.add(valitutKortit(), BorderLayout.EAST);
        
        container.add(luoKortit(), BorderLayout.CENTER);

        container.add(luoPanos(), BorderLayout.SOUTH);
        
        

    }
    
    private JPanel luoKortit() {
        JPanel panel = new JPanel(new GridLayout(4,13));
        for (int i = 1; i <= 52; i++) {
            JButton asd = new JButton("" + i);
            panel.add(asd);
        }
        return panel;
    }

    private JPanel luoPanos() {
        JPanel panel = new JPanel(new GridLayout(1, 5));
        JButton paukku = new JButton("Pelaa: ");
        ButtonGroup buttonGroup = new ButtonGroup();
        
        JRadioButton yksi = new JRadioButton("0,20e");
        JRadioButton kaksi = new JRadioButton("0,40e");
        JRadioButton kolme = new JRadioButton("0,60e");
        JRadioButton nelja = new JRadioButton("0,80e");
        JRadioButton viisi = new JRadioButton("1,00e");
        
        buttonGroup.add(yksi);
        buttonGroup.add(kaksi);
        buttonGroup.add(kolme);
        buttonGroup.add(nelja);
        buttonGroup.add(viisi);
        
        PanosKuuntelija kuuntelija = new PanosKuuntelija(keno, paukku, yksi, kaksi, kolme, nelja, viisi);
        
        panel.add(paukku);
        panel.add(yksi);
        panel.add(kaksi);
        panel.add(kolme);
        panel.add(nelja);
        panel.add(viisi);
        
        paukku.addActionListener(kuuntelija);
        
        return panel;
    }
    
    
    private JPanel valitutKortit(){
        JPanel panel = new JPanel(new GridLayout(1,3));
        JLabel label = new JLabel("Valitut kortit:                                                                                      ");
        panel.add(label);
        return panel;
    }
    
}