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

    public ArrayList<Integer> arvotutNumerot;
    public Arpoja arpoja;
    public Pelaaja pelaaja;
    public Panos panos;

    public Kenoarvonta() {
        arpoja = new Arpoja();
        arvotutNumerot = new ArrayList();
        this.pelaaja = new Pelaaja("uusi", 0);

    }

    public void uusiPelaaja(String nimi, double saldo) {
        this.pelaaja = new Pelaaja(nimi, saldo);
    }

    public void asetaPanos(double panos) {
        if(panos >= 1.0) {
            this.panos = new Panos(1.0);
        } else if (panos<= 0.2) {
            this.panos = new Panos(0.2);
        } else {
            this.panos = new Panos(panos);
        }

    }

    public void asetaSaldo(double saldo) {

        pelaaja.setSaldo(saldo);
    }

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

    public boolean onkoVoittoa() {
        if (montakoOsumaa() / montaValittuaNumeroa() >= 0.4 && montaValittuaNumeroa()>0) {
            return true;
        }
        return false;
    }

    public int montaValittuaNumeroa() {
        return pelaaja.montakoValittuaNumeroa();
    }

    public int montakoOsumaa() {
        int osumat = 0;
        for (Integer arvotut : arvotutNumerot) {
            for (Integer valitut : pelaaja.getValitutNumerot()) {
                if (arvotut == valitut) {
                    osumat++;
                }
            }
        }
        return osumat;
    }

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
                pelaaja.muutaSaldoa(panos.getPanos() * 6);
            }
        }
        if (montaValittuaNumeroa() == 3) { // kolme korttia valittu
            if (montakoOsumaa() == 2) {
                pelaaja.muutaSaldoa(panos.getPanos() * 4);
            } else if (montakoOsumaa() == 3) {
                pelaaja.muutaSaldoa(panos.getPanos() * 8);
            }
        }
        if (montaValittuaNumeroa() == 4) { // neljä korttia valittu
            if (montakoOsumaa() == 2) {
                pelaaja.muutaSaldoa(panos.getPanos() * 3);
            } else if (montakoOsumaa() == 3) {
                pelaaja.muutaSaldoa(panos.getPanos() * 6);
            } else if (montakoOsumaa() == 4) {
                pelaaja.muutaSaldoa(panos.getPanos() * 10);
            }
        }
        if (montaValittuaNumeroa() == 5) { // viisi korttia valittu
            if (montakoOsumaa() == 2) {
                pelaaja.muutaSaldoa(panos.getPanos() * 2);
            } else if (montakoOsumaa() == 3) {
                pelaaja.muutaSaldoa(panos.getPanos() * 4);
            } else if (montakoOsumaa() == 4) {
                pelaaja.muutaSaldoa(panos.getPanos() * 8);
            } else if (montakoOsumaa() == 5) {
                pelaaja.muutaSaldoa(panos.getPanos() * 20);
            }
            
        }
    }
}
