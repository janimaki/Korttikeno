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
    public ArrayList<KortinKuuntelija> kortit;


    public Kayttoliittyma() {
        this.arvonta = new Kenoarvonta();
        this.keno = new Korttikeno(arvonta);
        this.kortit = new ArrayList();
        this.arvonta.asetaPanos(0.2);
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
            kortit.add(kuuntelija);
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

        JButton kasvataPanos = new JButton("Panos: 0.2e");
        JButton pelaaNappi = new JButton("Pelaa: ");
//        ButtonGroup buttonGroup = new ButtonGroup();
        JLabel ohjelaatikko = new JLabel("Anna saldosi: ");
        JTextField teksti = new JTextField();
        JButton asetaSaldo = new JButton("aseta saldo");

        

//        buttonGroup.add(kasvataPanos);

        PanosKuuntelija kuuntelija = new PanosKuuntelija(arvonta, pelaaNappi, kortit, kasvataPanos, asetaSaldo, teksti, ohjelaatikko);

        panel.add(pelaaNappi);
        panel.add(kasvataPanos);
        panel.add(ohjelaatikko);
        panel.add(teksti);
        panel.add(asetaSaldo);
        
        asetaSaldo.addActionListener(kuuntelija);
        kasvataPanos.addActionListener(kuuntelija);
        pelaaNappi.addActionListener(kuuntelija);


        return panel;
    }

    /**
     * Komponentin luontimetodi, johon tulee pelaajan valitsemat kortit.
     *
     * @return palauttaa paneelikomponentin
     */
    private JPanel saldoPalkki() {

        JPanel panel = new JPanel(new GridLayout(5, 1));
        JButton poistaValinnat = new JButton("Uudet kortit");
        SaldonKuuntelija kuuntelija = new SaldonKuuntelija(arvonta, poistaValinnat, kortit);
        
        poistaValinnat.addActionListener(kuuntelija);
        panel.add(poistaValinnat);
        return panel;
    }
}
