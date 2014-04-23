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
    private JButton poista;
    public ArrayList<KortinKuuntelija> kortit;

    public SaldonKuuntelija(Kenoarvonta arvonta, JButton poista, ArrayList kortit) {
        this.arvonta = arvonta;
        this.poista = poista;
        this.kortit = kortit;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == poista) {
            arvonta.pelaaja.tyhjennaRivi();
            for (KortinKuuntelija kuuntelija : kortit) {
                kuuntelija.kortti.setEnabled(true);
            }
        }
    }

    public String naytaValitutKortit() {
        String kortit = "";
        for (Integer kortti : arvonta.pelaaja.getValitutNumerot()) {
            kortit = kortit + kortti + " ";
        }
        return kortit;
    }
}
