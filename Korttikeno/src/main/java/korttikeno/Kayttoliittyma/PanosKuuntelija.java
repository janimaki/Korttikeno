/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package korttikeno.Kayttoliittyma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import korttikeno.Sovelluslogiikka.Kenoarvonta;
import korttikeno.korttikeno.Korttikeno;

/**
 * ActionListener luokka, jolla toteutetaan panoksen asettaminen peliin.
 *
 * @author Jani
 */
public class PanosKuuntelija implements ActionListener {

    public Kenoarvonta arvonta;
    public Korttikeno keno;
    private JButton yksi;
    private JButton kaksi;
    private JButton kolme;
    private JButton nelja;
    private JButton viisi;
    private JButton pelaa;

    public PanosKuuntelija(Kenoarvonta arvonta, JButton pelaa, JButton yksi, JButton kaksi, JButton kolme,
            JButton nelja, JButton viisi) {
//        this.keno = keno;
        this.yksi = yksi;
        this.kaksi = kaksi;
        this.kolme = kolme;
        this.nelja = nelja;
        this.viisi = viisi;
        this.pelaa = pelaa;
        this.arvonta = arvonta;
        this.pelaa.setEnabled(true);        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
//        if (arvonta.getPanos() == 0) {
//            pelaa.setEnabled(true);
//        } else {
//            pelaa.setText("pelaa, panos: " + arvonta.getPanos());
//        }
        if (ae.getSource() == yksi) {
            arvonta.asetaPanos(0.2);
        } else if (ae.getSource() == kaksi) {
            arvonta.asetaPanos(0.4);
        } else if (ae.getSource() == kolme) {
            arvonta.asetaPanos(0.6);
        } else if (ae.getSource() == nelja) {
            arvonta.asetaPanos(0.8);
        } else if (ae.getSource() == viisi) {
            arvonta.asetaPanos(1);
        }
        pelaa.setText("pelaa, panos: " + arvonta.getPanos());

        if (ae.getSource() == pelaa) {
            if(arvonta.pelaaja.montakoValittuaNumeroa()>0 && arvonta.pelaaja.montakoValittuaNumeroa()<=5){
                pelaa.setEnabled(false);
                arvonta.suoritaArvonta();
            }
        }
    }
}
