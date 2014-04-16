/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
import korttikeno.Skanneri.Skanneri;
import korttikeno.Sovelluslogiikka.Kenoarvonta;
import korttikeno.korttikeno.Korttikeno;

/**
 *
 * @author janimaki@cs
 */
public class SaldonKuuntelija implements ActionListener {

    Kenoarvonta arvonta;
    Korttikeno keno;
    JButton aseta;
    JTextField uusisaldo;
    JLabel ohje;
    JLabel saldoinfo;
    public Skanneri skanneri;
    JButton poista;
    KortinKuuntelija kortti;
    public ArrayList<KortinKuuntelija> kortit;

    public SaldonKuuntelija(Kenoarvonta arvonta, JButton asetaSaldo, JTextField teksti, JLabel ohje, JLabel saldoinfo, JButton poista, ArrayList kortit) {
//        this.keno = keno;
        this.aseta = asetaSaldo;
        this.uusisaldo = teksti;
        skanneri = new Skanneri();
        this.ohje = ohje;
        this.arvonta = arvonta;
        this.saldoinfo = saldoinfo;
        this.poista = poista;
        this.kortit = kortit;

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.aseta) {
            double saldo = onkoKelpoSaldo(uusisaldo);
            if (saldo >= 0.2 && saldo <= 20.0) {
                arvonta.asetaSaldo(saldo);
                this.aseta.setEnabled(false);
                this.saldoinfo.setText("Saldosi: " + arvonta.pelaaja.getSaldo());
                poista.setText("" + naytaValitutKortit());
            } else {
                this.ohje.setText("saldon pitää olla 0.2-20.0!");
            }
        }
        if (ae.getSource() == poista){
            arvonta.pelaaja.tyhjennaRivi();
            for (KortinKuuntelija kuuntelija : kortit) {
                kuuntelija.kortti.setEnabled(true);
            }
            
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

    public String naytaValitutKortit() {
        String kortit = "";
        for (Integer kortti : arvonta.pelaaja.getValitutNumerot()) {
            kortit = kortit + kortti + " ";
        }
        return kortit;
    }
    
    public void paivitaSaldo(){
        this.saldoinfo.setText("Saldosi: " + arvonta.pelaaja.getSaldo());
    }
}
