/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package korttikeno.Sovelluslogiikka;

import korttikeno.Pelaaja.Pelaaja;
import java.util.ArrayList;

/**
 *
 * @author Jani
 */
public class Kenoarvonta {

    ArrayList<Integer> arvotutNumerot;
    Arpoja arpoja;
    Pelaaja pelaaja ;

    public Kenoarvonta() {
        arpoja = new Arpoja();
        arvotutNumerot = new ArrayList();
        pelaaja = new Pelaaja("Jani",10);
    }
    
    public void asetaRahaa(int saldo){
        pelaaja.setSaldo(saldo);
    }
    
    public void suoritaArvonta() {
        
        if (pelaaja.getSaldo() - 1 > 0) {
            pelaaja.muutaSaldoa(-1);
            
            while (arvotutNumerot.size() < 10) {
                int arvottuLuku = arpoja.arvoLuvut();
                if (!arvotutNumerot.contains(arvottuLuku)) {
                    arvotutNumerot.add(arvottuLuku);
                }
            }
        }       
    }

    public int montakoOsumaa() {
        int osumat = 0;
        for (Integer arvotut : arvotutNumerot) {
            for (Integer valitut : pelaaja.getValitutNumerot()) {
                if (arvotut.equals(valitut)) {
                    osumat++;
                }
            }
        }
        return osumat ;
    }

    public void suoritaVoitonmaksu() {
        if (pelaaja.montakoValittuaNumeroa() == 1) { // yksi kortti valittuna
            if (montakoOsumaa() == 1) {
                pelaaja.muutaSaldoa(4);
            }
        }
        if (pelaaja.montakoValittuaNumeroa() == 2) { // kaksi korttia valittu
            if (montakoOsumaa() == 1) {
                pelaaja.muutaSaldoa(2);
            } else if (montakoOsumaa() == 2) {
                pelaaja.muutaSaldoa(4);
            }
        }
        if (pelaaja.montakoValittuaNumeroa() == 3) { // kolme korttia valittu
            if (montakoOsumaa() == 2) {
                pelaaja.muutaSaldoa(2);
            } else if (montakoOsumaa() == 3) {
                pelaaja.muutaSaldoa(4);
            }
        }
        if (pelaaja.montakoValittuaNumeroa() == 4) { // neljä korttia valittu
            if (montakoOsumaa() == 2) {
                pelaaja.muutaSaldoa(1);
            } else if (montakoOsumaa() == 3) {
                pelaaja.muutaSaldoa(3);
            } else if (montakoOsumaa() == 4) {
                pelaaja.muutaSaldoa(6);
            }
        }
    }
}

