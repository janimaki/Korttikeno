/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package korttikeno.Kayttoliittyma;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import korttikeno.Sovelluslogiikka.Kenoarvonta;
import korttikeno.korttikeno.Korttikeno;

/**
 * ActionListener luokka, jolla toteutetaan panoksen asettaminen peliin.
 *
 * @author Jani
 */
public class AlapalkinKuuntelija implements ActionListener {

    public ArrayList<KortinKuuntelija> lista;
    public Kenoarvonta arvonta;
    public Korttikeno keno;
    private JButton pelaa;
    private JButton kasvataPanos;
    private JLabel ohjelaatikko;
    private JButton asetaSaldo;
    private JTextField uusiSaldo;
    private JButton kylla;
    private JButton ei;
    private JLabel tuplausOhje;

    AlapalkinKuuntelija(Kenoarvonta arvonta, JButton pelaaNappi, ArrayList<KortinKuuntelija> kortit, JButton kasvataPanos,
            JButton asetaSaldo, JTextField teksti, JLabel ohjelaatikko, JButton kylla, JButton ei, JLabel tuplaus) {
        this.lista = kortit;
        this.pelaa = pelaaNappi;
        this.arvonta = arvonta;
        this.pelaa.setEnabled(false);
        this.kasvataPanos = kasvataPanos;
        this.ohjelaatikko = ohjelaatikko;
        this.asetaSaldo = asetaSaldo;
        this.uusiSaldo = teksti;
        this.kylla = kylla;
        this.ei = ei;
        this.kylla.setEnabled(false);
        this.ei.setEnabled(false);
        this.tuplausOhje = tuplaus;

    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == this.asetaSaldo) {
            double saldo = onkoKelpoSaldo(uusiSaldo);
            if (saldo >= 0.2 && saldo <= 20.0) {
                arvonta.asetaSaldo(saldo);
                this.asetaSaldo.setEnabled(false);
                paivitaSaldo();
            } else {
                this.ohjelaatikko.setText("saldon pitää olla 0.2-20.0!");
            }
        }

        if (ae.getSource() == kasvataPanos) {
            arvonta.kasvataPanosta();
            kasvataPanos.setText("panos: " + arvonta.getPanos() + "e");
        }

        if (arvonta.getPanos() <= arvonta.pelaaja.getSaldo() && arvonta.pelaaja.getSaldo() >= 0.2 && arvonta.montaValittuaNumeroa() > 0) {
            pelaa.setEnabled(true);
        } else {
            pelaa.setEnabled(false);
        }

        paivitaSaldo();

        if (ae.getSource() == pelaa) {
            if (arvonta.pelaaja.montakoValittuaNumeroa() > 0 && arvonta.pelaaja.montakoValittuaNumeroa() <= 5) {
                pelaa.setEnabled(false);
                kasvataPanos.setEnabled(false);
                poistaArvotut();

                suoritaPeli();

            }
        }

        if (ae.getSource() == kylla) {
            this.tuplausOhje.setText("Pieni vai Suuri?");
            this.kylla.setText("pieni");
            this.ei.setText("suuri");
            arvonta.tuplaaVoitto();
        }

        if (ae.getSource() == ei) {
            vapautaNapit();
            peliLoppuun();
            tyhjennaValinnat();
            ei.setEnabled(false);
            kylla.setEnabled(false);
        }
    }

    public void suoritaPeli() {
        arvonta.suoritaArvonta();
        naytaArvotut();
        if (arvonta.onkoVoittoa() == true) {
            kylla.setEnabled(true);
            ei.setEnabled(true);
        } else {

            tyhjennaValinnat();
            vapautaNapit();
            peliLoppuun();
        }
    }

    public void tyhjennaValinnat() {
        for (KortinKuuntelija kuuntelija : this.lista) {
            kuuntelija.kortti.setEnabled(true);
        }
    }

    public void vapautaNapit() {
        kasvataPanos.setEnabled(true);
        pelaa.setEnabled(true);
    }

    public void peliLoppuun() {
        arvonta.suoritaVoitonmaksu();
        paivitaSaldo();
        arvonta.valmistaUuttaPelia();
    }

    public double onkoKelpoSaldo(JTextField saldo) {
        double alkusaldo = 0;
        try {
            alkusaldo = Double.parseDouble(saldo.getText());
            return alkusaldo;

        } catch (Exception e) {
            return alkusaldo;
        }
    }

    public void paivitaSaldo() {
        pelaa.setText("pelaa: " + arvonta.getSaldo() + "e");
    }

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

    public void poistaArvotut() {
        for (KortinKuuntelija kuuntelija : lista) {
            kuuntelija.kortti.setBackground(Color.lightGray);

        }
    }
}
