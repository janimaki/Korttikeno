package main.java.korttikeno.Kayttoliittyma;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import main.java.korttikeno.Sovelluslogiikka.Kenoarvonta;
import main.java.korttikeno.Kayttoliittyma.KortinKuuntelija;

/**
 * ActionListener luokka, jolla toteutetaan alapalkin nappien toiminnot.
 *
 * @author Jani
 */
public class AlapalkinKuuntelija implements ActionListener {

    public ArrayList<KortinKuuntelija> lista;
    public Kenoarvonta arvonta;
    private JButton pelaa;
    private JButton kasvataPanos;
    private JLabel ohjelaatikko;
    private JButton asetaSaldo;
    private JTextField uusiSaldo;
    private JButton kylla;
    private JButton ei;
    private JButton tuplattu;
    private Tuplausliittyma tupla;
    /**
     * Muuttuja, jonka arvo true jos pelaaja on tuplaus-tilassa, muuten false
     */
    public static boolean onkoTuplaamassa;

    AlapalkinKuuntelija(Kenoarvonta arvonta, JButton pelaaNappi, ArrayList<KortinKuuntelija> kortit, JButton kasvataPanos,
            JButton asetaSaldo, JTextField teksti, JLabel ohjelaatikko, JButton kylla, JButton ei, JLabel tuplaus, JButton tuplattu) {
        this.lista = kortit;
        this.pelaa = pelaaNappi;
        this.arvonta = arvonta;
//        this.pelaa.setEnabled(false);
        this.kasvataPanos = kasvataPanos;
        this.ohjelaatikko = ohjelaatikko;
        this.asetaSaldo = asetaSaldo;
        this.uusiSaldo = teksti;
        this.kylla = kylla;
        this.ei = ei;
        this.kylla.setEnabled(false);
        this.ei.setEnabled(false);
        this.tuplattu = tuplattu;
        this.tuplattu.setEnabled(false);
        this.onkoTuplaamassa = false;


    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == this.asetaSaldo) {
            double saldo = onkoKelpoSaldo(uusiSaldo);
            if (saldo >= 0.2 && saldo <= 20.0) {
                arvonta.setSaldo(saldo);
                this.asetaSaldo.setEnabled(false);
                paivitaSaldo();
                this.ohjelaatikko.setText("alkusaldosi: " + arvonta.getSaldo() + "e");
            } else {
                this.ohjelaatikko.setText("kokeileppa 0.2-20.0");
            }
        }

        if (ae.getSource() == kasvataPanos) {
            arvonta.kasvataPanosta();
            kasvataPanos.setText("panos: " + arvonta.getPanos() + "e");
        }

        if (arvonta.getPanos() <= arvonta.pelaaja.getSaldo() && arvonta.pelaaja.getSaldo() >= 0.2 && arvonta.montaValittuaNumeroa() > 0 && onkoTuplaamassa == false) {
            pelaa.setEnabled(true);
        } else {
            pelaa.setEnabled(false);
        }

        if (ae.getSource() == pelaa) {
            if (arvonta.pelaaja.montakoValittuaKorttia() > 0 && arvonta.getSaldo() >= 0.2 && onkoTuplaamassa == false) {
                pelaa.setEnabled(false);
                kasvataPanos.setEnabled(false);

                poistaArvotut();
                suoritaPeli();

            }
        }

        if (ae.getSource() == kylla) {
            onkoTuplaamassa = true;
            tupla = new Tuplausliittyma(arvonta, onkoTuplaamassa);
            SwingUtilities.invokeLater(tupla);
            ei.setEnabled(false);
            kylla.setEnabled(false);
            tuplattu.setEnabled(true);
            suljeNapit();



        }

        if (ae.getSource() == ei) {
            arvonta.suoritaVoitonmaksu();
            vapautaNapit();
            peliLoppuun();

            ei.setEnabled(false);
            kylla.setEnabled(false);
        }

        if (ae.getSource() == tuplattu) {
            if (arvonta.pelaaja.getTuplaus() != -1) { // pelaaja on suorittanut tuplauksen, maksetaan mahdollinen tuplausvoitto.
                arvonta.maksaTuplausVoitto();
                vapautaNapit();
                peliLoppuun();
                arvonta.pelaaja.setTuplaus(-1);
                tuplattu.setEnabled(false);
                onkoTuplaamassa = false;

            } else {
                arvonta.suoritaVoitonmaksu(); // pelaaja ei ole suorittanut tuplausta, palautetaan alkup. voitto.
                vapautaNapit();
                peliLoppuun();
                tuplattu.setEnabled(false);
                onkoTuplaamassa = false;
                Tuplausliittyma.onkoTuplaamassa = false;


            }
        }
    }

    /**
     * Metodi, joka suorittaa arvonnan ja muokkaa nappien näkyvyyttä riippuen
     * siitä voittaako pelaaja arvonnasta.
     */
    public void suoritaPeli() {
        arvonta.suoritaArvonta();
        paivitaSaldo();
        naytaArvotut();
        if (arvonta.onkoVoittoa() == true) {
            kylla.setEnabled(true);
            ei.setEnabled(true);
            pelaa.setEnabled(false);
        } else {

            vapautaNapit();
            arvonta.suoritaVoitonmaksu();
            peliLoppuun();
        }
    }

    /**
     * Metodi, joka asettaa kortita näkyville
     */
    public void tyhjennaValinnat() {
        for (KortinKuuntelija kuuntelija : this.lista) {
            kuuntelija.kortti.setEnabled(true);
        }
    }

    /**
     * Metodi, joka asettaa panos- ja pelaa-napin näkyviin, sekä kortit
     * näkyviin.
     */
    public void vapautaNapit() {
        kasvataPanos.setEnabled(true);
        pelaa.setEnabled(true);
        tyhjennaValinnat();
    }

    /**
     * Metodi, joka päivittää saldon pelaa-nappiin ja valmistelee uuden pelin.
     */
    public void peliLoppuun() {
        paivitaSaldo();
        arvonta.valmistaUuttaPelia();
    }

    /**
     * Metodi, joka tarkistaa onko pelaajan antama alkusaldo kelvollinen
     *
     * @param saldo Käyttäjän antama syöte
     *
     * @return pelaajan antama saldo, jos kelvollinen, muuten 0
     */
    public double onkoKelpoSaldo(JTextField saldo) {
        double alkusaldo = 0;
        try {
            NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
            alkusaldo = Double.parseDouble("" + format.parse(saldo.getText()));
            return alkusaldo;

        } catch (Exception e) {
            return alkusaldo;
        }
    }

    /**
     * Metodi, joka päivittää saldon pelaa-nappiin
     */
    public void paivitaSaldo() {
        pelaa.setText("pelaa: " + arvonta.getSaldo() + "e");
    }

    /**
     * Metodi, joka muuttaa arvottujen korttien taustan vihreäksi.
     */
    public void naytaArvotut() {
        for (Integer numero : arvonta.arvotutNumerot) {
            for (KortinKuuntelija kuuntelija : lista) {
                JButton apum = kuuntelija.kortti;
                if (kuuntelija.indeksit.get(apum).equals(numero)) {
                    kuuntelija.kortti.setBackground(Color.GREEN);
                }
            }
        }
    }

    /**
     * Metodi, joka asettaa kaikkien korttien taustaksi harmaan
     */
    public void poistaArvotut() {
        for (KortinKuuntelija kuuntelija : lista) {
            kuuntelija.kortti.setBackground(Color.lightGray);

        }
    }

    /**
     * Metodi, joka sammuttaa panos- ja pelaa-napin sekä kortit.
     */
    public void suljeNapit() {
        kasvataPanos.setEnabled(false);
        pelaa.setEnabled(false);
        for (KortinKuuntelija kuuntelija : this.lista) {
            kuuntelija.kortti.setEnabled(false);
        }
    }
}
