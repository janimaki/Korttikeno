package main.java.korttikeno.Kayttoliittyma;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import main.java.korttikeno.Sovelluslogiikka.Kenoarvonta;

/**
 * ActionListener luokka, jolla toteutetaan korttien valinta.
 *
 * @author Jani
 */
public class KortinKuuntelija implements ActionListener {

    public Kenoarvonta arvonta;
    public JButton kortti;
    public HashMap<JButton, Integer> indeksit;


    KortinKuuntelija(Kenoarvonta arvonta, JButton kortti, HashMap indeksit) {
        this.arvonta = arvonta;
        this.kortti = kortti;
        this.indeksit = indeksit;
//        this.pelaa = pelaa;

    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == kortti) {
            if (arvonta.montaValittuaNumeroa() < 5) {
                int numero = indeksit.get(kortti);
                arvonta.pelaaja.valitseKortti(numero);
                kortti.setEnabled(false);
//                if (arvonta.getSaldo()>= 0.2){
//                    pelaa.setEnabled(true);
//                }
                    
            }
        }
    }
}
