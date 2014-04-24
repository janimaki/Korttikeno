/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *
 * @author Jani
 */
public class Tuplausliittyma implements Runnable {

    public Kenoarvonta arvonta;
    private JFrame frame;
    public Tuplaus tuplaus;
    public JLabel kortti;
    public boolean onkoTuplattu;

    public Tuplausliittyma(Kenoarvonta arvonta) {
        this.arvonta = arvonta;
        this.onkoTuplattu = false;

    }

    @Override
    public void run() {
        frame = new JFrame("Tuplaus");
        frame.setPreferredSize(new Dimension(800, 600));
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
        kortti = new JLabel(new ImageIcon(((new ImageIcon("cards/" + "kortti_0" +".png")).getImage()).getScaledInstance(207, 300, java.awt.Image.SCALE_SMOOTH)));
//        kortti = new JLabel();
        TuplausKuuntelija kuuntelija = new TuplausKuuntelija(arvonta, pieni, suuri, kortti, onkoTuplattu, frame);

        pieni.addActionListener(kuuntelija);
        suuri.addActionListener(kuuntelija);

        panel.add(pieni);
        panel.add(suuri);
        panel.add(kortti);

        return panel;
    }

}
