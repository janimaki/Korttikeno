/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package korttikeno.Kayttoliittyma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class KortinKuuntelija implements ActionListener {

    Kenoarvonta arvonta;
    JButton kortti;
    SaldonKuuntelija saldo;
    Kayttoliittyma kayttol;

    KortinKuuntelija(Kenoarvonta arvonta, JButton kortti) {
        this.arvonta = arvonta;
        this.kortti = kortti;

    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == kortti) {
            if (arvonta.montaValittuaNumeroa() < 5) {
                arvonta.pelaaja.valitseNumero(Integer.parseInt(kortti.getText()));
                kortti.setEnabled(false);
            }     
        }
    }

}
