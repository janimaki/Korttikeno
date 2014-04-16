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

    public Kayttoliittyma() {
        this.arvonta = new Kenoarvonta();
        this.keno = new Korttikeno(arvonta);
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

        container.add(new JLabel("Aseta panos, valitse 1-5 korttia, paina 'pelaa'"), BorderLayout.NORTH);

        container.add(saldoPalkki(), BorderLayout.EAST);

        container.add(luoKortit(), BorderLayout.CENTER);

        container.add(luoPanos(), BorderLayout.SOUTH);
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
            JButton asd = new JButton("" + i);
            KortinKuuntelija kuuntelija = new KortinKuuntelija(arvonta, asd);
            panel.add(asd);
            asd.addActionListener(kuuntelija);
        }
        return panel;
    }

    /**
     * Komponentin luontimetodi, jolla asetetaan panos, jolla peliä pelataan
     *
     * @return palauttaa paneelikomponentin
     */
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

        PanosKuuntelija kuuntelija = new PanosKuuntelija(arvonta, paukku, yksi, kaksi, kolme, nelja, viisi);

        panel.add(paukku);
        panel.add(yksi);
        panel.add(kaksi);
        panel.add(kolme);
        panel.add(nelja);
        panel.add(viisi);

        paukku.addActionListener(kuuntelija);
        yksi.addActionListener(kuuntelija);
        kaksi.addActionListener(kuuntelija);
        kolme.addActionListener(kuuntelija);
        nelja.addActionListener(kuuntelija);
        viisi.addActionListener(kuuntelija);

        return panel;
    }

    /**
     * Komponentin luontimetodi, johon tulee pelaajan valitsemat kortit.
     *
     * @return palauttaa paneelikomponentin
     */
    private JPanel saldoPalkki() {

        JPanel panel = new JPanel(new GridLayout(5, 1));
        JLabel cards = new JLabel("korttisi: ");
        JLabel saldo = new JLabel("Saldosi: 0");
        JLabel ohjelaatikko = new JLabel("Anna saldosi: ");
        JTextField teksti = new JTextField();
        JButton asetaSaldo = new JButton("aseta saldo");
        SaldonKuuntelija kuuntelija = new SaldonKuuntelija(arvonta, asetaSaldo, teksti, ohjelaatikko, saldo, cards);
        asetaSaldo.addActionListener(kuuntelija);
        panel.add(cards);
        panel.add(saldo);
        panel.add(ohjelaatikko);
        panel.add(teksti);
        panel.add(asetaSaldo);
        return panel;
    }
}
