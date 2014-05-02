package korttikeno.Sovelluslogiikka;

/**
 * Luokka, jossa määritelty kortti, käytännössä kortti on luku välillä 1-52.
 *
 * @author Jani
 */
public class Kortti {

    /**
     * Luku, joka on kortin indeksi (0-52)
     */
    public int luku;

    public Kortti(int luku) {
        this.luku = luku;
    }

    public int getArvo() {
        return this.luku;
    }

    @Override
    public String toString() {
        return "kortti_" + luku;
    }
}
