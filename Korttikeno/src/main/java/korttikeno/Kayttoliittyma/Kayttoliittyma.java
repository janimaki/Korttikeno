package korttikeno.Kayttoliittyma;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import korttikeno.Sovelluslogiikka.Kenoarvonta;

/**
 * Käyttöliittymäluokka, joka luo graafisen käyttöliittymän pelille.
 *
 * @author Jani
 */
public class Kayttoliittyma implements Runnable {

    private JFrame frame;
    public Kenoarvonta arvonta;
    public ArrayList<KortinKuuntelija> kortit;
    public HashMap<JButton, Integer> indeksit;

    public Kayttoliittyma() {
        this.arvonta = new Kenoarvonta();
        this.kortit = new ArrayList();
        this.arvonta.setPanos(0.2);
        this.indeksit = new HashMap();
    }

    @Override
    public void run() {
        frame = new JFrame("Korttikeno");
        frame.setPreferredSize(new Dimension(1200, 800));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Metodi luo komponentit käyttöliittymälle.
     *
     * @param container parametri, jonka avulla lisätään komponentit.
     */
    private void luoKomponentit(Container container) {
        container.setLayout(new BorderLayout());

        container.add(new JLabel("Aseta saldo, valitse kortit, valitse panos, paina pelaa, tuplaa voittaessasi, voita paljon!"), BorderLayout.NORTH);

        container.add(luoOikeaPalkki(), BorderLayout.EAST);

        container.add(luoKortit(), BorderLayout.CENTER);

        container.add(luoAlapalkki(), BorderLayout.SOUTH);
    }

    /**
     * Komponentin luontimetodi, jolla luodaan 52 korttia, joista pelaaja voi
     * valita suosikkinsa.
     *
     * @return palauttaa paneelikomponentin
     */
    private JPanel luoKortit() {
        JPanel panel = new JPanel(new GridLayout(4, 13));

        for (int i = 1; i <= 52; i++) {

            JButton kortti = new JButton(new ImageIcon(((new ImageIcon("Korttikeno/cards/" + "kortti_" + i + ".png")).getImage()).getScaledInstance(73, 175, java.awt.Image.SCALE_SMOOTH)));
            kortti.setBackground(Color.lightGray);
            indeksit.put(kortti, i);
            KortinKuuntelija kuuntelija = new KortinKuuntelija(arvonta, kortti, indeksit);

            kortit.add(kuuntelija);
            panel.add(kortti);
            kortti.addActionListener(kuuntelija);
        }
        return panel;
    }

    /**
     * Komponentin luontimetodi, jolla asetetaan panos, jolla peliä pelataan
     *
     * @return palauttaa paneelikomponentin
     */
    private JPanel luoAlapalkki() {
        JPanel panel = new JPanel(new GridLayout(1, 9));

        JButton kasvataPanos = new JButton("Panos: 0.2e");
        JButton pelaaNappi = new JButton("Pelaa: 0.0e");
        JLabel ohjelaatikko = new JLabel("Anna saldosi: ");
        JTextField teksti = new JTextField();
        JButton asetaSaldo = new JButton("aseta saldo");
        JLabel tuplausOhje = new JLabel("Tuplaatko?");
        JButton kylla = new JButton("Kyllä");
        JButton ei = new JButton("Ei");
        JButton tuplattu = new JButton("Tuplaus rdy");

        AlapalkinKuuntelija kuuntelija = new AlapalkinKuuntelija(arvonta, pelaaNappi, kortit, kasvataPanos,
                asetaSaldo, teksti, ohjelaatikko, kylla, ei, tuplausOhje, tuplattu);

        panel.add(pelaaNappi);
        panel.add(kasvataPanos);
        panel.add(ohjelaatikko);
        panel.add(teksti);
        panel.add(asetaSaldo);
        panel.add(tuplausOhje);
        panel.add(kylla);
        panel.add(ei);
        panel.add(tuplattu);


        asetaSaldo.addActionListener(kuuntelija);
        kasvataPanos.addActionListener(kuuntelija);
        pelaaNappi.addActionListener(kuuntelija);
        kylla.addActionListener(kuuntelija);
        ei.addActionListener(kuuntelija);
        tuplattu.addActionListener(kuuntelija);


        return panel;
    }

    /**
     * Komponentin luontimetodi, johon tulee pelaajan valitsemat kortit.
     *
     * @return palauttaa paneelikomponentin
     */
    private JPanel luoOikeaPalkki() {

        JPanel panel = new JPanel(new GridLayout(7, 1));
        JButton poistaValinnat = new JButton("Uudet kortit");
        JButton voitonmaksu = new JButton("Voitot talteen");
        JLabel voitot1 = new JLabel("<html>Voittotaulu:<br/>Valittuja: 1<br/>Osumia 1: panos x5<br/></html>");

        JLabel voitot2 = new JLabel("<html>Valittuja: 2<br/>Osumia 1: panos x2"
                + "<br/>Osumia 2: panos x8<br/></html>");
        JLabel voitot3 = new JLabel("<html>Valittuja: 3<br/>Osumia 1: panos x1<br/>Osumia 2: panos x5"
                + "<br/>Osumia 3: panos x18<br/></html>");
        JLabel voitot4 = new JLabel("<html>Valittuja: 4<br/>Osumia 2: panos x5<br/>Osumia 3: panos x10"
                + "<br/>Osumia 4: panos x20<br/></html>");
        JLabel voitot5 = new JLabel("<html>Valittuja 5:<br/>Osumia 2: panos x2<br/>Osumia 3: panos x10"
                + "<br/>Osumia 4: panos x30<br/>Osumia 5: panos x50</html>");


        OikeanPalkinKuuntelija kuuntelija = new OikeanPalkinKuuntelija(arvonta, poistaValinnat, kortit, voitonmaksu);

        poistaValinnat.addActionListener(kuuntelija);
        voitonmaksu.addActionListener(kuuntelija);

        panel.add(poistaValinnat);
        panel.add(voitonmaksu);
        panel.add(voitot1);
        panel.add(voitot2);
        panel.add(voitot3);
        panel.add(voitot4);
        panel.add(voitot5);




        return panel;
    }
}
