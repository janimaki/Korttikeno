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
    public Korttikeno keno;
    
    public Kayttoliittyma(Korttikeno keno) {
        this.keno = keno;
    }

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
        
        JButton yksi = new JButton("0,20e");
        JButton kaksi = new JButton("0,40e");
        JButton kolme = new JButton("0,60e");
        JButton nelja = new JButton("0,80e");
        JButton viisi = new JButton("1,00e");
        
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
        JPanel panel = new JPanel(new GridLayout(2,1));
        JLabel label = new JLabel("Valitut kortit: ");
        JTextField teksti = new JTextField();
        panel.add(label);
        panel.add(teksti);
        return panel;
    }
    
}
