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
    public Kenoarvonta arvonta;

    public Tuplaus() {
        arpoja = new Arpoja();
        kortti = new Kortti(arpoja.arvoKortti(), arpoja.arvoMaa());

    }

    /**
     * Metodi, joka tarkastelee arvotun kortin arvoa
     *
     * @return 0, jos kortin arvo on 6 tai alle. 1, jos kortti on punainen 7. 2, jos kortti on
     * musta 7. 3, jos kortin arvo on 8 tai suurempi.
     */
    public int korttiPieniVaiSuuri() {
        if (this.kortti.getArvo() <= 6) {
            return 0; // 0 = pieni
        } else if (this.kortti.getArvo() == 7) {
            if (this.kortti.getMaa() == 1 || this.kortti.getMaa() == 2) {
                return 1; // punainen seiska
            } else {
                return 2; // musta seiska
            }
        } else {
            return 3; // 3 = suuri
        }
    }
}
