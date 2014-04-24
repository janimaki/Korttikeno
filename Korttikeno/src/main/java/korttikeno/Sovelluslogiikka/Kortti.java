/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package korttikeno.Sovelluslogiikka;

/**
 * Luokka, jossa määritelty mikä on kortti. Kortilla on arvo 1-13 ja 4 vaihtoehtoa maaksi
 *
 * @author Jani
 */
public class Kortti {

    public static final int RISTI = 0;
    public static final int RUUTU = 1;
    public static final int HERTTA = 2;
    public static final int PATA = 3;
    public static final String[] MAAT = {"Risti", "Ruutu", "Hertta", "Pata"};
    public static final String[] ARVOT = {"-", "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
    private int arvo;
    private int maa;
    public int luku;

    public Kortti(int arvo, int maa) {
        this.arvo = arvo;
        this.maa = maa;
    }
    
    public Kortti(int luku) {
        this.luku = luku;
    }

    @Override
    public String toString() {
        return "kortti_" + luku;
    }

    public int getArvo() {
        return arvo;
    }

    public int getMaa() {
        return maa;
    }
}
