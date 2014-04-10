/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package korttikeno.Kayttoliittyma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import korttikeno.korttikeno.Korttikeno;

/**
 *
 * @author Jani
 */
public class PanosKuuntelija implements ActionListener {

    private Korttikeno keno = new Korttikeno();
    private JRadioButton yksi;
    private JRadioButton kaksi;
    private JRadioButton kolme;
    private JRadioButton nelja;
    private JRadioButton viisi;
    private JButton pelaa;

    public PanosKuuntelija(Korttikeno keno, JButton pelaa, JRadioButton yksi, JRadioButton kaksi, JRadioButton kolme,
            JRadioButton nelja, JRadioButton viisi) {
        this.keno = keno;
        this.yksi = yksi;
        this.kaksi = kaksi;
        this.kolme = kolme;
        this.nelja = nelja;
        this.viisi = viisi;
        this.pelaa = pelaa;


    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (keno.arvonta.panos.getPanos() == 0) {
            pelaa.setEnabled(false);
        } else {
            pelaa.setEnabled(true);
        }
        if (ae.getSource() == pelaa) {
            keno.Pelaa();
            pelaa.setEnabled(false);
        }
        if (ae.getSource() == yksi) {
            keno.arvonta.asetaPanos(0.2);
        } else if (ae.getSource() == kaksi) {
            keno.arvonta.asetaPanos(0.4);
        } else if (ae.getSource() == kolme) {
            keno.arvonta.asetaPanos(0.6);
        } else if (ae.getSource() == nelja) {
            keno.arvonta.asetaPanos(0.8);
        } else if (ae.getSource() == viisi) {
            keno.arvonta.asetaPanos(1);
        }
    }
}