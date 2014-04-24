/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package korttikeno.Sovelluslogiikka;

import korttikeno.Pelaaja.Pelaaja;

/**
 * Luokka, jossa tarkastellaan onko arvottu kortti pieni vai suuri
 *
 * @author Jani
 */
public class Tuplaus {

    public Kortti kortti;
    public Arpoja arpoja;
//    public Kenoarvonta arvonta;

    public Tuplaus() {
//        this.arvonta = arvonta;
        arpoja = new Arpoja();
        kortti = new Kortti(arpoja.arvoLuvut());

    }

    public int getArvo() {
        return this.kortti.getArvo();
    }

    /**
     * Metodi, joka tarkastelee arvotun kortin arvoa
     *
     * @return 0, jos kortin arvo on 6 tai alle. 1, jos kortti on punainen 7. 2,
     * jos kortti on musta 7. 3, jos kortin arvo on 8 tai suurempi.
     */
    public int korttiPieniVaiSuuri() {
        if ((getArvo() >= 1 && getArvo() <= 5) || (getArvo() >= 13 && getArvo() <= 18) || (getArvo() >= 26 && getArvo() <= 31) || (getArvo() >= 39 && getArvo() <= 44) || getArvo() == 52) {
            return 0; // 0 = pieni
        } else if (getArvo() == 6 || getArvo() == 45) {
            return 2; // musta seiska
        } else if (getArvo() == 19 || getArvo() == 32) {
            return 1; // punanen seiska
        } else {
            return 3; // 3 = suuri
        }
    }
}
