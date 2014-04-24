/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package korttikeno.Kayttoliittyma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import korttikeno.Sovelluslogiikka.Kenoarvonta;

/**
 *
 * @author Jani
 */
public class TuplausKuuntelija implements ActionListener {

    private JButton pieni;
    private JButton suuri;
    public Kenoarvonta arvonta;
    public JLabel kortti;
    public boolean onkoTuplattu;
    public JFrame frame;

    public TuplausKuuntelija(Kenoarvonta arvonta, JButton pieni, JButton suuri, JLabel kortti, boolean onko, JFrame frame) {
        this.pieni = pieni;
        this.suuri = suuri;
        this.arvonta = arvonta;
        this.kortti = kortti;
        this.onkoTuplattu = onko;
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == pieni) {
            this.arvonta.pelaaja.setTuplaus(0);

        }
        if (ae.getSource() == suuri) {
            this.arvonta.pelaaja.setTuplaus(3);

        }
        pieni.setEnabled(false);
        suuri.setEnabled(false);
        arvonta.tuplaaVoitto();

        kortti.setIcon(new ImageIcon(((new ImageIcon("cards/" + arvonta.tupla.kortti.toString() + ".png")).getImage()).getScaledInstance(207, 300, java.awt.Image.SCALE_SMOOTH)));

        this.onkoTuplattu = true;
               
//        frame.dispose();

    }
}
