/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package korttikeno.Pelaaja;

import java.util.ArrayList;

/**
 * Luokka, jossa on pelaajan tarvitsemat attribuutit ja metodit, joilla
 * hallinnoidaan pelaajan saldoa, nimeä, valittuja kortteja sekä
 * tuplausvalintaa.
 *
 * @author Jani
 */
public class Pelaaja {
   
    public double saldo;
    public ArrayList<Integer> valitutNumerot;
    public int tuplaus;

    public Pelaaja(double saldo) {
        
        this.saldo = saldo;
        valitutNumerot = new ArrayList();
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
            double uusiSaldo = Math.round((this.saldo+maara)*100);
            this.saldo = uusiSaldo/100;
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
    public void valitseNumero(int numero) {
        if (!valitutNumerot.contains(numero)) {
            valitutNumerot.add(numero);
        }
    }

    /**
     * Metodi, joka tyhjentää pelaajan valitsemat numerot.
     */
    public void tyhjennaRivi() {
        valitutNumerot.clear();
    }

    public ArrayList<Integer> getValitutNumerot() {
        return valitutNumerot;
    }

    /**
     * Metodi, joka kertoo montakoa korttia pelaaja on valinnut.
     *
     * @return valittujen numeroiden määrä.
     */
    public int montakoValittuaNumeroa() {
        return valitutNumerot.size();
    }

    public int getTuplaus() {
        return tuplaus;
    }

    public void setTuplaus(int tuplaus) {
        this.tuplaus = tuplaus; // 0 = pieni, 3 = suuri
    }

    @Override
    public String toString() {
        return "sinun saldosi: " + saldo;
    }
}
