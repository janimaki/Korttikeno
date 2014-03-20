/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package korttikeno.Sovelluslogiikka;

/**
 *
 * @author Jani
 */
public class Kortti implements Comparable<Kortti> {

    public static final int RISTI = 0;
    public static final int RUUTU = 1;
    public static final int HERTTA = 2;
    public static final int PATA = 3;    
    public static final String[] MAAT = {"Risti", "Ruutu", "Hertta", "Pata"};
    public static final String[] ARVOT = {"-","A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
    private int arvo;
    private int maa;

    public Kortti(int arvo, int maa) {
        this.arvo = arvo;
        this.maa = maa;
    }

    @Override
    public String toString() {
        return MAAT[maa] + " " + ARVOT[arvo];
    }

    public int getArvo() {
        return arvo;
    }

    public int getMaa() {
        return maa;
    }

    @Override
    public int compareTo(Kortti kortti) {
        if(this.arvo == kortti.arvo) {
            return this.maa - kortti.maa;
        } else {
            return this.arvo - kortti.arvo;
        }
    }

}

