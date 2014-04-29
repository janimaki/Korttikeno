package main.java.korttikeno.Pelaaja;

import java.util.ArrayList;

/**
 * Luokka, jossa on pelaajan tarvitsemat attribuutit ja metodit, joilla
 * hallinnoidaan pelaajan saldoa, nimeä, valittuja kortteja sekä
 * tuplausvalintaa.
 *
 * @author Jani
 */
public class Pelaaja {

    /**
     * Pelaajan saldo
     */
    private double saldo;
    /**
     * Lista pelaajan valitsemista korteista
     */
    private ArrayList<Integer> valitutKortit;
    /**
     * Pelaajan tuplaus arvo, 0 pieni, 3 suuri, -1 kun ei olla tuplaamassa
     */
    private int tuplaus;

    public Pelaaja(double saldo) {

        this.saldo = saldo;
        valitutKortit = new ArrayList();
        this.tuplaus = -1;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    /**
     * Metodi, joka muuttaa pelaajan saldoa
     *
     * @param maara määrä, jolla saldoa muutetaan
     */
    public void muutaSaldoa(double maara) {
        if (this.saldo + maara >= 0) {
            double uusiSaldo = Math.round((this.saldo + maara) * 100);
            this.saldo = uusiSaldo / 100;
        }
    }

    /**
     * Metodi, jolla pelaaja valitsee kortin. Pelaaja voi valita maksimissaan
     * viisi korttia ja jokainen kortti saa esiintyä vain kertaalleen valituissa
     * korteissa.
     *
     * @param numero indeksi, joka jokaisella kortilla on, indeksöinti on
     * välillä 1-52.
     */
    public void valitseKortti(int numero) {
        if (!valitutKortit.contains(numero)) {
            valitutKortit.add(numero);
        }
    }

    /**
     * Metodi, joka tyhjentää pelaajan valitsemat numerot.
     */
    public void tyhjennaRivi() {
        valitutKortit.clear();
    }

    public ArrayList<Integer> getValitutKortit() {
        return valitutKortit;
    }

    /**
     * Metodi, joka kertoo montakoa korttia pelaaja on valinnut.
     *
     * @return valittujen numeroiden määrä.
     */
    public int montakoValittuaKorttia() {
        return valitutKortit.size();
    }

    public int getTuplaus() {
        return tuplaus;
    }

    public void setTuplaus(int tuplaus) {
        this.tuplaus = tuplaus; // 0 = pieni, 3 = suuri
    }

}
