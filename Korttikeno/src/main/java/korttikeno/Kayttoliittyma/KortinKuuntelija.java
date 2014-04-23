/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package korttikeno.Kayttoliittyma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
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

    public Kenoarvonta arvonta;
    public JButton kortti;
    public HashMap<JButton,Integer> indeksit;
    

    KortinKuuntelija(Kenoarvonta arvonta, JButton kortti, HashMap indeksit) {
        this.arvonta = arvonta;
        this.kortti = kortti;
        this.indeksit = indeksit;

    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == kortti) {
            if (arvonta.montaValittuaNumeroa() < 5) {
                int numero = indeksit.get(kortti);
                arvonta.pelaaja.valitseNumero(numero);
                kortti.setEnabled(false);
                
            }     
        }
    }

}
