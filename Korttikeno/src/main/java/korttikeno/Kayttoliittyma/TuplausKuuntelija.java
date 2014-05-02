package korttikeno.Kayttoliittyma;

import korttikeno.Kayttoliittyma.Tuplausliittyma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;
import korttikeno.Sovelluslogiikka.Kenoarvonta;

/**
 * ActionListener luokka, jolla toteutetaan tuplaus.
 *
 * @author Jani
 */
public class TuplausKuuntelija implements ActionListener {

    private JButton pieni;
    private JButton suuri;
    public Kenoarvonta arvonta;
    private JLabel kortti;
    public JFrame frame;
    public static boolean onkoTupla;

    public TuplausKuuntelija(Kenoarvonta arvonta, JButton pieni, JButton suuri, JLabel kortti, JFrame frame, boolean onko) {
        this.pieni = pieni;
        this.suuri = suuri;
        this.arvonta = arvonta;
        this.kortti = kortti;
        this.frame = frame;
        this.onkoTupla = onko;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == pieni) {
            if (!onkoSuljettuJo()) {
                this.arvonta.pelaaja.setTuplaus(0); // 0 on indeksi pienelle
                suoritaTuplausLoppuun();
            } else {
                frame.dispose();
            }
        }
        if (ae.getSource() == suuri) {
            if (!onkoSuljettuJo()) {
                this.arvonta.pelaaja.setTuplaus(3); // 3 on indeksi suurelle
                suoritaTuplausLoppuun();
            } else {
                frame.dispose();
            }
        }



    }

    /**
     * Metodi, joka tarkistaa, onko tuplaus lopetettu jo.
     *
     * @return true jos tuplaus on suljettu, muuten false
     */
    public boolean onkoSuljettuJo() {
        if (Tuplausliittyma.onkoTuplaamassa == false) {
            return true;
        }
        return false;
    }

    /**
     * Metodi, joka asettaa pieni- ja suuri- napit näkyvistä, suorittaa
     * tuplauksen ja näyttää oikean kortin
     */
    public void suoritaTuplausLoppuun() {
        pieni.setEnabled(false);
        suuri.setEnabled(false);
        arvonta.tuplaaVoitto();
        kortti.setIcon(new ImageIcon(((new ImageIcon("Korttikeno/cards/" + arvonta.tupla.kortti.toString() + ".png")).getImage()).getScaledInstance(207, 300, java.awt.Image.SCALE_SMOOTH)));
        Timer timer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                frame.dispose();
            }
        });
        timer.setRepeats(false);
        timer.start();


    }
}
