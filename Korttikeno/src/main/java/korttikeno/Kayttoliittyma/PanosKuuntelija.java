/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package korttikeno.Kayttoliittyma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
public class PanosKuuntelija implements ActionListener {

    public ArrayList<KortinKuuntelija> lista;
    public Kenoarvonta arvonta;
    public Korttikeno keno;
    private JButton pelaa;
    private JButton kasvataPanos;
    private JLabel ohjelaatikko;
    private JButton asetaSaldo;
    private JTextField uusiSaldo;

    PanosKuuntelija(Kenoarvonta arvonta, JButton pelaaNappi, ArrayList<KortinKuuntelija> kortit, JButton kasvataPanos, JButton asetaSaldo, JTextField teksti, JLabel ohjelaatikko) {
        this.lista = kortit;
        this.pelaa = pelaaNappi;
        this.arvonta = arvonta;
        this.pelaa.setEnabled(false);
        this.kasvataPanos = kasvataPanos;
        this.ohjelaatikko = ohjelaatikko;
        this.asetaSaldo = asetaSaldo;
        this.uusiSaldo = teksti;

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

        if (arvonta.getPanos() > 0 && arvonta.pelaaja.getSaldo() > 0.2 && arvonta.montaValittuaNumeroa() > 0) {
            pelaa.setEnabled(true);
        }
        paivitaSaldo();

        if (ae.getSource() == pelaa) {
            if (arvonta.pelaaja.montakoValittuaNumeroa() > 0 && arvonta.pelaaja.montakoValittuaNumeroa() <= 5) {
                pelaa.setEnabled(false);
                suoritaPeli();
                System.out.println(arvonta.pelaaja.getSaldo());
                tyhjennaValinnat();
                pelaa.setEnabled(true);
                paivitaSaldo();
            }
        }
    }

    public void suoritaPeli() {
        arvonta.suoritaArvonta();
        arvonta.suoritaVoitonmaksu();
        arvonta.valmistaUuttaPelia();
    }

    public void tyhjennaValinnat() {
        for (KortinKuuntelija kuuntelija : this.lista) {
            kuuntelija.kortti.setEnabled(true);
        }
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
        pelaa.setText("pelaa: " + arvonta.getSaldo());
    }
}
