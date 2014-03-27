/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package korttikeno.Pelaaja;

import java.util.ArrayList;

/**
 *
 * @author Jani
 */
public class Pelaaja {

    public String nimi;
    public double saldo;
    public ArrayList<Integer> valitutNumerot;
    public int tuplaus;

    public Pelaaja(String nimi, double saldo) {
        this.nimi = nimi;
        this.saldo = saldo;
        valitutNumerot = new ArrayList();
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void muutaSaldoa(double maara) {
        if (this.saldo + maara >= 0) {
            this.saldo = this.saldo + maara;
        } 
    }

    public void valitseNumero(int numero) {
        if (valitutNumerot.size() < 5 && !valitutNumerot.contains(numero)) {
            valitutNumerot.add(numero);
        }
    }

    public void tyhjennaRivi() {
        valitutNumerot.clear();
    }

    public ArrayList<Integer> getValitutNumerot() {
        return valitutNumerot;
    }

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
        return nimi + ", " + saldo;
    }
}
