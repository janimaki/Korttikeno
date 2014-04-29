package main.java.korttikeno.Kayttoliittyma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import main.java.korttikeno.Sovelluslogiikka.Kenoarvonta;

/**
 * ActionListener luokka, jolla toteutetaan oikean sivupalkin nappien toiminnot.
 *
 * @author Jani
 */
public class OikeanPalkinKuuntelija implements ActionListener {

    public Kenoarvonta arvonta;
    private JButton poista;
    public ArrayList<KortinKuuntelija> kortit;
    private JButton voitot;

    public OikeanPalkinKuuntelija(Kenoarvonta arvonta, JButton poista, ArrayList kortit, JButton voitot) {
        this.arvonta = arvonta;
        this.poista = poista;
        this.kortit = kortit;
        this.voitot = voitot;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == poista) {
            arvonta.pelaaja.tyhjennaRivi();
            for (KortinKuuntelija kuuntelija : kortit) {
                kuuntelija.kortti.setEnabled(true);
            }
        }
//        
        if (ae.getSource() == voitot) {
            if (arvonta.onkoArvontaPaattynyt()&& arvonta.getSaldo()>0) {
                voitot.setText("Voitit " + arvonta.getSaldo() + " euroa!");
                this.arvonta.setSaldo(0);
                voitot.setEnabled(false);
                poista.setEnabled(false);
            } else {
                voitot.setText("<html>Voitot talteen<br/>FAIL!<br/>Arvonta kesken<br/>tai saldo 0</html>");
            }

        }

    }
}
