/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package korttikeno.Kayttoliittyma;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import korttikeno.Sovelluslogiikka.Kenoarvonta;
import korttikeno.korttikeno.Korttikeno;

/**
 * Käyttöliittymäluokka, joka luo graafisen käyttöliittymän pelille.
 *
 * @author Jani
 */
public class Kayttoliittyma implements Runnable {

    private JFrame frame;
    public Korttikeno keno;
    public Kenoarvonta arvonta;
    public ArrayList<KortinKuuntelija> kortit;
    public HashMap<JButton, Integer> indeksit;

    public Kayttoliittyma() {
        this.arvonta = new Kenoarvonta();
        this.keno = new Korttikeno(arvonta);
        this.kortit = new ArrayList();
        this.arvonta.asetaPanos(0.2);
        this.indeksit = new HashMap();
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

    /**
     * Metodi luo komponentit käyttöliittymälle.
     *
     * @param container parametri, jonka avulla lisätään komponentit.
     */
    private void luoKomponentit(Container container) {
        container.setLayout(new BorderLayout());

        container.add(new JLabel("Aseta saldo, valitse kortit, valitse panos, paina pelaa, tuplaa voittaessasi"), BorderLayout.NORTH);

        container.add(luoOikeaPalkki(), BorderLayout.EAST);

        container.add(luoKortit(), BorderLayout.CENTER);

        container.add(luoAlapalkki(), BorderLayout.SOUTH);
    }

    /**
     * Komponentin luontimetodi, jolla luodaan 52 korttia, joista pelaaja voi
     * valita suosikkinsa.
     *
     * @return palauttaa paneelikomponentin
     */
    private JPanel luoKortit() {
        JPanel panel = new JPanel(new GridLayout(4, 13));

        for (int i = 1; i <= 52; i++) {

            JButton kortti = new JButton(new ImageIcon(((new ImageIcon("cards/" + "kortti_" + i + ".png")).getImage()).getScaledInstance(80, 175, java.awt.Image.SCALE_SMOOTH)));
            kortti.setBackground(Color.lightGray);
            indeksit.put(kortti, i);
            KortinKuuntelija kuuntelija = new KortinKuuntelija(arvonta, kortti, indeksit);

            kortit.add(kuuntelija);
            panel.add(kortti);
            kortti.addActionListener(kuuntelija);
        }
        return panel;
    }

    /**
     * Komponentin luontimetodi, jolla asetetaan panos, jolla peliä pelataan
     *
     * @return palauttaa paneelikomponentin
     */
    private JPanel luoAlapalkki() {
        JPanel panel = new JPanel(new GridLayout(1, 8));

        JButton kasvataPanos = new JButton("Panos: 0.2e");
        JButton pelaaNappi = new JButton("Pelaa: 0.0e");
        JLabel ohjelaatikko = new JLabel("Anna saldosi: ");
        JTextField teksti = new JTextField();
        JButton asetaSaldo = new JButton("aseta saldo");
        JLabel tuplausOhje = new JLabel("Tuplaatko?");
        JButton kylla = new JButton("Kyllä");
        JButton ei = new JButton("Ei");

        AlapalkinKuuntelija kuuntelija = new AlapalkinKuuntelija(arvonta, pelaaNappi, kortit, kasvataPanos, asetaSaldo, teksti, ohjelaatikko, kylla, ei, tuplausOhje);

        panel.add(pelaaNappi);
        panel.add(kasvataPanos);
        panel.add(ohjelaatikko);
        panel.add(teksti);
        panel.add(asetaSaldo);
        panel.add(tuplausOhje);
        panel.add(kylla);
        panel.add(ei);

        asetaSaldo.addActionListener(kuuntelija);
        kasvataPanos.addActionListener(kuuntelija);
        pelaaNappi.addActionListener(kuuntelija);
        kylla.addActionListener(kuuntelija);
        ei.addActionListener(kuuntelija);

        return panel;
    }

    /**
     * Komponentin luontimetodi, johon tulee pelaajan valitsemat kortit.
     *
     * @return palauttaa paneelikomponentin
     */
    private JPanel luoOikeaPalkki() {

        JPanel panel = new JPanel(new GridLayout(5, 1));
        JButton poistaValinnat = new JButton("Uudet kortit");
        JLabel voittotaulu = new JLabel("Voittotaulu:");
        JLabel voitot = new JLabel("<html>This is first line.<br/>This is second line.</html>");

        OikeanPalkinKuuntelija kuuntelija = new OikeanPalkinKuuntelija(arvonta, poistaValinnat, kortit, voitot);

        poistaValinnat.addActionListener(kuuntelija);       

        panel.add(poistaValinnat);
        panel.add(voittotaulu);
        panel.add(voitot);
        
        return panel;
    }
}
