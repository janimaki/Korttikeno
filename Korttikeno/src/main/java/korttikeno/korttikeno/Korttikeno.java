/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package korttikeno.korttikeno;

import java.util.Random;
import java.util.Scanner;
import korttikeno.Sovelluslogiikka.*;
import korttikeno.Pelaaja.Pelaaja;
import korttikeno.Skanneri.Skanneri;

/**
 *
 * @author Jani
 */
public class Korttikeno {

    Pelaaja pelaaja;
    Kenoarvonta arvonta = new Kenoarvonta();
    private static Scanner lukija = new Scanner(System.in);
    Random random;

    public Korttikeno() {
    }
    

    public void Pelaa() {

        System.out.print("Syötä nimi: ");
        String nimi = lukija.nextLine();
        System.out.print("Paljonko sijoitat? (0,2-10): ");
        Double aloitussumma = Double.parseDouble(lukija.nextLine());
        arvonta.pelaaja.setNimi(nimi);
        arvonta.pelaaja.setSaldo(aloitussumma);
        System.out.print("Panos? (0,2 - 1): ");
        Double panos = Double.parseDouble(lukija.nextLine());
        arvonta.asetaPanos(panos);
        System.out.print("Montako korttia? (1-4): ");
        int kortteja = lukija.nextInt();
        for (int i = 0; i < kortteja; i++) {
            System.out.println("Valitse numero: (1-52)");
            int numero = lukija.nextInt();
            arvonta.pelaaja.valitseNumero(numero);
        }
        arvonta.suoritaArvonta();
        System.out.println(arvonta.pelaaja.toString());
        if (!arvonta.onkoVoittoa()) {
            System.out.println(arvonta.pelaaja.toString());
        } else {
            arvonta.suoritaVoitonmaksu();
            System.out.println(arvonta.pelaaja.toString());
        }
        

    }
}
