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
import javax.swing.SwingUtilities;
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
    private JButton tuplattu;
    private boolean onkoTuplaamassa;

    AlapalkinKuuntelija(Kenoarvonta arvonta, JButton pelaaNappi, ArrayList<KortinKuuntelija> kortit, JButton kasvataPanos,
            JButton asetaSaldo, JTextField teksti, JLabel ohjelaatikko, JButton kylla, JButton ei, JLabel tuplaus, JButton tuplattu) {
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
        this.tuplattu = tuplattu;
        this.tuplattu.setEnabled(false);
        this.onkoTuplaamassa = false;

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

        if (arvonta.getPanos() <= arvonta.pelaaja.getSaldo() && arvonta.pelaaja.getSaldo() >= 0.2 && arvonta.montaValittuaNumeroa() > 0 && onkoTuplaamassa==false) {
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
            onkoTuplaamassa = true;
            Tuplausliittyma tupla = new Tuplausliittyma(arvonta);
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
            if (arvonta.pelaaja.getTuplaus() != -1) {
                vapautaNapit();
                peliLoppuun();
                arvonta.pelaaja.setTuplaus(-1);
                tuplattu.setEnabled(false);
                onkoTuplaamassa = false;
            }
        }
    }

    public void suoritaPeli() {
        arvonta.suoritaArvonta();
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

    public void tyhjennaValinnat() {
        for (KortinKuuntelija kuuntelija : this.lista) {
            kuuntelija.kortti.setEnabled(true);
        }
    }

    public void vapautaNapit() {
        kasvataPanos.setEnabled(true);
        pelaa.setEnabled(true);
        tyhjennaValinnat();
    }

    public void peliLoppuun() {
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

    public void suljeNapit() {
        kasvataPanos.setEnabled(false);
        pelaa.setEnabled(false);
        for (KortinKuuntelija kuuntelija : this.lista) {
            kuuntelija.kortti.setEnabled(false);
        }
    }
}
