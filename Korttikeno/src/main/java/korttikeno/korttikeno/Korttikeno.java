/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package korttikeno.korttikeno;

import korttikeno.Sovelluslogiikka.*;

import korttikeno.Skanneri.Skanneri;

/**
 *
 * @author Jani
 */
public class Korttikeno {

    Kenoarvonta arvonta = new Kenoarvonta();
    public Skanneri skanneri;

    public Korttikeno() {
        skanneri = new Skanneri();
    }

    public void uusiPelaaja() {
        System.out.print("Syötä nimi: ");
        String nimi = skanneri.nextLine();
        System.out.print("Paljonko sijoitat? (0,2-10): ");
        Double aloitussumma = Double.parseDouble(skanneri.nextLine());
        arvonta.uusiPelaaja(nimi, aloitussumma);

    }

    public void Pelaa() {
        if (arvonta.pelaaja.getNimi().equals("uusi")) {
            uusiPelaaja();
        }

        System.out.print("Panos? (0,2 - 1): ");
        Double panos = Double.parseDouble(skanneri.nextLine());
        arvonta.asetaPanos(panos);
        System.out.print("Montako korttia? (1-4): ");
        int kortteja = skanneri.nextInt();
        while (arvonta.pelaaja.valitutNumerot.size() != kortteja) {
            System.out.println("Valitse numero: (1-52)");
            int numero = skanneri.nextInt();
            arvonta.pelaaja.valitseNumero(numero);
        }
        System.out.println(arvonta.pelaaja.toString());
        arvonta.suoritaArvonta();
        System.out.println(arvonta.pelaaja.toString());
        for (Integer numero : arvonta.arvotutNumerot) {
            System.out.println(numero);
        }
        if (!arvonta.onkoVoittoa()) {
            System.out.println(arvonta.pelaaja.toString());
        } else {    //tuplausvaihtoehto myöhemmin
            arvonta.suoritaVoitonmaksu();
            System.out.println(arvonta.pelaaja.toString());
        }


    }
}
