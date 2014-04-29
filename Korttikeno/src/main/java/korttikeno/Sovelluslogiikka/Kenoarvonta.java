package korttikeno.Sovelluslogiikka;

import korttikeno.Pelaaja.Pelaaja;
import java.util.ArrayList;

/**
 * Luokka, jossa on suurin osa ohjelman logiikasta
 *
 * @author Jani
 */
public class Kenoarvonta {

    /**
     * Lista, jossa arvottujen korttien indeksit
     */
    public ArrayList<Integer> arvotutNumerot;
    private Arpoja arpoja;
    public Pelaaja pelaaja;
    public Panos panos;
    public Tuplaus tupla;
    /**
     * Apumuuttuja, joka pitää muistissa kierroksen panoksen
     */
    private static double panosMuistaja;

    public Kenoarvonta() {
        arpoja = new Arpoja();
        arvotutNumerot = new ArrayList();
        this.pelaaja = new Pelaaja(0);

    }

    /**
     * Metodi, jolla voidaan asettaa panos. Lähinnä käytössä tuplauksen
     * yhteydessä, kun halutaan käyttöön vanha panos, joka oli voimassa ennen
     * tuplausta.
     *
     * @param panos panos, jonka käyttäjä asettaa arvonnalle.
     */
    public void setPanos(double panos) {
        this.panos = new Panos(panos);
    }

    /**
     * Metodi, jolla kasvatetaan panosta 0.2:lla. Kun saavutetaan 1.0, alkaa
     * kasvattaminen taas 0.2:sta.
     *
     */
    public void kasvataPanosta() {
        if (getPanos() <= 0.8) {
            panos.setPanos(getPanos() + 0.2);
        } else {
            panos.setPanos(0.2);
        }
    }

    public double getPanos() {
        return panos.getPanos();
    }

    public double getSaldo() {
        return pelaaja.getSaldo();
    }

    public void setSaldo(double saldo) {
        pelaaja.setSaldo(saldo);
    }

    /**
     * Metodi, joka vähentää pelaajan saldosta panoksen ja arpoo kymmenen
     * korttia. Metodi ottaa myös panoksen talteen mahdollista tuplausta varten.
     */
    public void suoritaArvonta() {
        panosMuistaja = getPanos();
        if (pelaaja.getSaldo() - getPanos() >= 0) {
            pelaaja.muutaSaldoa(-getPanos());

            while (arvotutNumerot.size() < 10) {
                int arvottuLuku = arpoja.arvoLuvut();
                if (!arvotutNumerot.contains(arvottuLuku)) {
                    arvotutNumerot.add(arvottuLuku);
                }
            }
        }
    }

    /**
     * Metodi, joka tarkistaa saiko pelaaja tarpeeksi osumia voittaakseen.
     *
     * @return true jos osumia on tarpeeksi voittoon, muuten false
     */
    public boolean onkoVoittoa() {
        double osumat = montakoOsumaa();
        double valitut = montaValittuaNumeroa();
        double jako = osumat / valitut;
        if (jako > 0.3) {
            return true;
        }
        return false;
    }

    /**
     * Metodi, joka palauttaa pelaajan valitsemien numeroiden määrän.
     *
     * @return pelaajan valitsemien korttien lukumäärä.
     */
    public int montaValittuaNumeroa() {
        return pelaaja.montakoValittuaKorttia();
    }

    /**
     * Metodi, joka tarkistaa montako oikeaa korttia pelaaja onnistui arvaamaan
     *
     * @return oikeiden osumien määrä.
     */
    public int montakoOsumaa() {
        int osumat = 0;
        for (Integer arvotut : arvotutNumerot) {
            for (Integer valitut : pelaaja.getValitutKortit()) {
                if (arvotut.equals(valitut)) {
                    osumat++;
                }
            }
        }
        return osumat;
    }

    /**
     * Metodi, joka luo uuden tuplauksen, vertailee arvotun kortin arvoa
     * pelaajan veikkaamaan arvoon ja nostaa panoksen kaksinkertaiseksi oikealla
     * vastauksella, nollaksi väärälle ja pitää panoksen samana punaisella
     * 7:llä.
     *
     */
    public void tuplaaVoitto() {

        this.tupla = new Tuplaus();
        int kortinarvo = tupla.korttiPieniVaiSuuri(); // 0 = pieni, , 1 = punanen 7, 2 = musta 7, 3 = suuri

        if (kortinarvo == pelaaja.getTuplaus()) { // tuplaus oikein
            setPanos(getPanos() * 2);

        } else if (kortinarvo == 1) { // 
            // punainen 7, panos pysyy ennallaan
        } else {
            setPanos(0); // tuplaus väärin tai musta 7

        }
    }

    /**
     * Metodi, joka tuplauksen jälkeen kutsuu suoritaVoitonmaksu() ja asettaa
     * alkuperäisen panoksen peliin.
     *
     */
    public void maksaTuplausVoitto() {
        suoritaVoitonmaksu();
        setPanos(panosMuistaja);
    }

    /**
     * Metodi, joka tarkistaa onko arvonta jo päättynyt.
     *
     * @return true jos arvonta on päättynyt, muuten false.
     */
    public boolean onkoArvontaPaattynyt() {
        if (arvotutNumerot.isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * Metodi, joka päivittää pelaajan saldon, kun pelaaja voittaa. Pelaajan
     * voitto Riippuu panoksesta, valittujen numeroiden sekä osumien määrästä.
     */
    public void suoritaVoitonmaksu() {
        if (montaValittuaNumeroa() == 1) { // yksi kortti valittuna
            maksaYhdenNumeronVoitto();
        }
        if (montaValittuaNumeroa() == 2) { // kaksi korttia valittu
            maksaKahdenNumeronVoitto();
        }
        if (montaValittuaNumeroa() == 3) { // kolme korttia valittu
            maksaKolmenNumeronVoitto();
        }
        if (montaValittuaNumeroa() == 4) { // neljä korttia valittu
            maksaNeljanNumeronVoitto();
        }
        if (montaValittuaNumeroa() == 5) { // viisi korttia valittu
            maksaViidenNumeronVoitto();
        }
    }

    public void maksaYhdenNumeronVoitto() {
        if (montakoOsumaa() == 1) {
            pelaaja.muutaSaldoa(panos.getPanos() * 5);
        }
    }

    public void maksaKahdenNumeronVoitto() {
        if (montakoOsumaa() == 1) {
            pelaaja.muutaSaldoa(panos.getPanos() * 2);
        } else if (montakoOsumaa() == 2) {
            pelaaja.muutaSaldoa(panos.getPanos() * 8);
        }
    }

    public void maksaKolmenNumeronVoitto() {
        if (montakoOsumaa() == 1) {
            pelaaja.muutaSaldoa(panos.getPanos() * 1);
        } else if (montakoOsumaa() == 2) {
            pelaaja.muutaSaldoa(panos.getPanos() * 5);
        } else if (montakoOsumaa() == 3) {
            pelaaja.muutaSaldoa(panos.getPanos() * 18);
        }
    }

    public void maksaNeljanNumeronVoitto() {
        if (montakoOsumaa() == 2) {
            pelaaja.muutaSaldoa(panos.getPanos() * 5);
        } else if (montakoOsumaa() == 3) {
            pelaaja.muutaSaldoa(panos.getPanos() * 10);
        } else if (montakoOsumaa() == 4) {
            pelaaja.muutaSaldoa(panos.getPanos() * 20);
        }
    }

    public void maksaViidenNumeronVoitto() {
        if (montakoOsumaa() == 2) {
            pelaaja.muutaSaldoa(panos.getPanos() * 2);
        } else if (montakoOsumaa() == 3) {
            pelaaja.muutaSaldoa(panos.getPanos() * 10);
        } else if (montakoOsumaa() == 4) {
            pelaaja.muutaSaldoa(panos.getPanos() * 30);
        } else if (montakoOsumaa() == 5) {
            pelaaja.muutaSaldoa(panos.getPanos() * 50);
        }
    }

    /**
     * Metodi, joka tyhjentää pelaajan valitsemat numerot, sekä arvotut numerot
     * uutta peliä varten.
     */
    public void valmistaUuttaPelia() {
        pelaaja.tyhjennaRivi();
        arvotutNumerot.clear();
    }
}
