package korttikeno.Kayttoliittyma;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import korttikeno.Sovelluslogiikka.Kenoarvonta;
import korttikeno.Sovelluslogiikka.Tuplaus;

/**
 * Käyttöliittymä-luokka, jossa suoritetaan tuplaus.
 *
 * @author Jani
 */
public class Tuplausliittyma implements Runnable {

    public Kenoarvonta arvonta;
    private JFrame frame;
    public JLabel kortti;
    public static boolean onkoTuplaamassa;

    public Tuplausliittyma(Kenoarvonta arvonta, boolean onko) {
        this.arvonta = arvonta;
        this.onkoTuplaamassa = onko;


    }

    @Override
    public void run() {
        frame = new JFrame("Valitse pieni tai suuri, sulje ikkuna ja paina 'Tuplaus rdy'. Jos suljet ikkunan ennen valintaa, paina 'Tuplaus rdy' ja peli jatkuu ilman tuplausta");
        frame.setPreferredSize(new Dimension(1000, 600));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);

    }

    private void luoKomponentit(Container container) {
        container.setLayout(new BorderLayout());

        container.add(luoNapit());
    }

    private JPanel luoNapit() {
        JPanel panel = new JPanel(new GridLayout(1, 3));
        JButton pieni = new JButton("pieni");
        JButton suuri = new JButton("suuri");
        kortti = new JLabel(new ImageIcon(((new ImageIcon("Korttikeno/cards/" + "kortti_0" + ".png")).getImage()).getScaledInstance(207, 300, java.awt.Image.SCALE_SMOOTH)));
        TuplausKuuntelija kuuntelija = new TuplausKuuntelija(arvonta, pieni, suuri, kortti, frame, onkoTuplaamassa);

        pieni.addActionListener(kuuntelija);
        suuri.addActionListener(kuuntelija);

        panel.add(pieni);
        panel.add(suuri);
        panel.add(kortti);

        return panel;
    }
}
