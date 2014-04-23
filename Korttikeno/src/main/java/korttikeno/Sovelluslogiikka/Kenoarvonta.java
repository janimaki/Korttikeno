/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package korttikeno.Sovelluslogiikka;

import korttikeno.Pelaaja.Pelaaja;
import java.util.ArrayList;

/**
 * Luokka, jossa on suurin osa ohjelman logiikasta
 *
 * @author Jani
 */
public class Kenoarvonta {

    public ArrayList<Integer> arvotutNumerot;
    public Arpoja arpoja;
    public Pelaaja pelaaja;
    public Panos panos;
    public Tuplaus tupla;

    public Kenoarvonta() {
        arpoja = new Arpoja();
        arvotutNumerot = new ArrayList();
        this.pelaaja = new Pelaaja(0);

    }

    /**
     * Metodi, joka luo uuden pelaajan peliin
     * 
     * @param saldo saldo, joka pelaajalle asetetaan.
     */
    public void uusiPelaaja(double saldo) {
        this.pelaaja = new Pelaaja(saldo);
    }

    /**
     * Metodi, joka asettaa panoksen arvonnalle. Panos on välillä 0,2-1,0.
     *
     * @param panos panos, jonka käyttäjä asettaa arvonnalle.
     */
    public void asetaPanos(double panos) {
        if (panos >= 1.0) {
            this.panos = new Panos(1.0);
        } else if (panos <= 0.2) {
            this.panos = new Panos(0.2);
        } else {
            this.panos = new Panos(panos);
        }
    }
    
    public void kasvataPanosta(){
        if(getPanos() <= 0.8) {
            panos.setPanos(getPanos()+0.2);
        } else {
            panos.setPanos(0.2);
        }
    }
    
    public double getPanos(){
        return panos.getPanos();
    }
    
    public double getSaldo() {
        return pelaaja.getSaldo();
    }

    /**
     * Metodi, joka asettaa uuden saldon pelaajalle.
     *
     * @param saldo käyttäjän syöttämä uusi saldo.
     */
    public void asetaSaldo(double saldo) {
        pelaaja.setSaldo(saldo);
    }

    /**
     * Metodi, joka vähentää pelaajan saldosta panoksen ja arpoo kymmenen
     * korttia.
     */
    public void suoritaArvonta() {

        if (pelaaja.getSaldo() - panos.getPanos() >= 0) {
            pelaaja.muutaSaldoa(-panos.getPanos());

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
        double jako = osumat/valitut;
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
        return pelaaja.montakoValittuaNumeroa();
    }

    /**
     * Metodi, joka tarkistaa montako oikeaa korttia pelaaja onnistui arvaamaan
     *
     *
     * @return oikeiden osumien määrä.
     */
    public int montakoOsumaa() {
        int osumat = 0;
        for (Integer arvotut : arvotutNumerot) {
            for (Integer valitut : pelaaja.getValitutNumerot()) {
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
     * 7:llä. Uudella panoksella ei pääse enään uuteen arvontaan vaan sitä
     * käytetään vain hyväksi voitonmaksussa.
     * 
     * @return true jos tuplaus oikein ja tuplausta voidaan jatkaa, false muuten.
     */
    public boolean tuplaaVoitto() {
        this.tupla = new Tuplaus();
        int kortinarvo = tupla.korttiPieniVaiSuuri(); // 0 = pieni, , 1 = punanen 7, 2 = musta 7, 3 = suuri
        if (kortinarvo == pelaaja.getTuplaus()) { // tuplaus oikein
            panos.setPanos(panos.getPanos() * 2);
            return true;
        } else if (kortinarvo == 1) { // 
            panos.setPanos(panos.getPanos()); // punainen 7
            return false;
        } else {
            panos.setPanos(0); // tuplaus väärin tai musta 7
            return false;
        }
    }

    /**
     * Metodi, joka päivittää pelaajan saldon, kun pelaaja voittaa. Pelaajan
     * voitto Riippuu panoksesta, valittujen numeroiden sekä osumien määrästä.
     */
    public void suoritaVoitonmaksu() {
        if (montaValittuaNumeroa() == 1) { // yksi kortti valittuna
            if (montakoOsumaa() == 1) {
                pelaaja.muutaSaldoa(panos.getPanos() * 5);
            }
        }
        if (montaValittuaNumeroa() == 2) { // kaksi korttia valittu
            if (montakoOsumaa() == 1) {
                pelaaja.muutaSaldoa(panos.getPanos() * 2);
            } else if (montakoOsumaa() == 2) {
                pelaaja.muutaSaldoa(panos.getPanos() * 8);
            }
        }
        if (montaValittuaNumeroa() == 3) { // kolme korttia valittu
            if (montakoOsumaa() == 1) {
                pelaaja.muutaSaldoa(panos.getPanos() * 1);
            } else if (montakoOsumaa() == 2) {
                pelaaja.muutaSaldoa(panos.getPanos() * 5);
            } else if (montakoOsumaa() == 3) {
                pelaaja.muutaSaldoa(panos.getPanos() * 18);
            }
        }
        if (montaValittuaNumeroa() == 4) { // neljä korttia valittu
            if (montakoOsumaa() == 2) {
                pelaaja.muutaSaldoa(panos.getPanos() * 5);
            } else if (montakoOsumaa() == 3) {
                pelaaja.muutaSaldoa(panos.getPanos() * 10);
            } else if (montakoOsumaa() == 4) {
                pelaaja.muutaSaldoa(panos.getPanos() * 20);
            }
        }
        if (montaValittuaNumeroa() == 5) { // viisi korttia valittu
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
    }

    /**
     * Metodi, joka tyhjentää pelaajan valitsemat numerot, sekä arvotut numerot
     * uutta peliä varten.
     */
    public void valmistaUuttaPelia() {
        pelaaja.valitutNumerot.clear();
        arvotutNumerot.clear();
    }
}
