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

    public int luku;

    
    public Kortti(int luku) {
        this.luku = luku;
    }
    
    public int getArvo(){
        return this.luku;
    }

    @Override
    public String toString() {
        return "kortti_" + luku;
    }

}
