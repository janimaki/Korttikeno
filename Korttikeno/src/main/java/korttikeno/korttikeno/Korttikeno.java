/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package korttikeno.korttikeno;

import javax.swing.SwingUtilities;
import korttikeno.Kayttoliittyma.Kayttoliittyma;
import korttikeno.Sovelluslogiikka.*;

import korttikeno.Skanneri.Skanneri;

/**
 * Luokka, jossa pelaaja luodaan ja peli käynnistetään.
 *
 * @author Jani
 */
public class Korttikeno {

    public Kenoarvonta arvonta;
    public Skanneri skanneri;

    public Korttikeno(Kenoarvonta arvonta) {
        skanneri = new Skanneri();
        this.arvonta = arvonta;
    }

    /**
     * Metodi, jolla luodaan uusi pelaaja peliin.
     */
    public void uusiPelaaja() {

        System.out.print("Paljonko sijoitat? (0,2-10): ");
        Double aloitussumma = Double.parseDouble(skanneri.nextLine());
        arvonta.uusiPelaaja(aloitussumma);

    }

    /**
     * Metodi, joka käynnistää pelin. Peli loppuu, jos käyttäjä syöttää
     * kierroksen päätteksi "N" tai jos pelaajan saldo menee nollaan.
     */
    public void PelaaTekstina() {
        uusiPelaaja();
//        arvonta.asetaPanos(0);

        while (arvonta.pelaaja.getSaldo() > 0) {
            System.out.print("Panos? (0,2 - 1): ");
            Double panos = Double.parseDouble(skanneri.nextLine());
            arvonta.setPanos(panos);
            System.out.print("Montako korttia? (1-5): ");
            int kortteja = Integer.parseInt(skanneri.nextLine());
            while (arvonta.pelaaja.valitutNumerot.size() != kortteja) {
                System.out.println("Valitse numero: (1-52)");
                int numero = Integer.parseInt(skanneri.nextLine());
                arvonta.pelaaja.valitseNumero(numero);
            }
            arvonta.suoritaArvonta();
            for (Integer numero : arvonta.arvotutNumerot) {
                System.out.println(numero);
            }
            if (!arvonta.onkoVoittoa()) {
                System.out.println(arvonta.pelaaja.toString());

            } else {
                System.out.println("Tuplataanko? (Y/N)");
                String vastaus = skanneri.nextLine();
                if (vastaus.equals("N")) {
                    arvonta.suoritaVoitonmaksu();
                    System.out.println(arvonta.pelaaja.toString());
                } else {
                    System.out.println("Pieni (p) vai Suuri? (s)");
                    String tuplavas = skanneri.nextLine();
                    if (tuplavas.equals("p")) {
                        arvonta.pelaaja.setTuplaus(0);
                    } else {
                        arvonta.pelaaja.setTuplaus(3);
                    }
                    arvonta.tuplaaVoitto();
                    System.out.println("Kortti: " + arvonta.tupla.kortti);
                    arvonta.suoritaVoitonmaksu();
                    System.out.println(arvonta.pelaaja.toString());
                }
            }
            arvonta.valmistaUuttaPelia();
            System.out.println("Jatketaanko? (Y/N):");
            String jatkuu = skanneri.nextLine();
            if (jatkuu.equals("N")) {
                break;
            }

        }
    }

    public void pelaaGraafinen() {
        
    }
}
