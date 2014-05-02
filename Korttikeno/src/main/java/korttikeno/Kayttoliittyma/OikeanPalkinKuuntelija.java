package korttikeno.Kayttoliittyma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.Timer;
import korttikeno.Sovelluslogiikka.Kenoarvonta;

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
    public JFrame frame;

    public OikeanPalkinKuuntelija(Kenoarvonta arvonta, JButton poista, ArrayList kortit, JButton voitot, JFrame frame) {
        this.arvonta = arvonta;
        this.poista = poista;
        this.kortit = kortit;
        this.voitot = voitot;
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == poista) {
            if (arvonta.onkoArvontaPaattynyt()) {
                arvonta.pelaaja.tyhjennaRivi();
                poista.setText("Uudet kortit");
                for (KortinKuuntelija kuuntelija : kortit) {
                    kuuntelija.kortti.setEnabled(true);
                }

            } else {
                poista.setText("<html>Uudet kortit<br/>Arvonta kesken?</html>");
            }
        }
//        
        if (ae.getSource() == voitot) {
            if (arvonta.onkoArvontaPaattynyt() && arvonta.getSaldo() > 0) {
                voitot.setText("Voitit " + arvonta.getSaldo() + " euroa!");
                this.arvonta.setSaldo(0);
                voitot.setEnabled(false);
                poista.setEnabled(false);
                Timer timer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                frame.dispose();
            }
        });
        timer.setRepeats(false);
        timer.start();
            } else {
                voitot.setText("<html>Voitot talteen<br/>FAIL!<br/>Arvonta kesken<br/>tai saldo 0</html>");
            }

        }

    }
}
