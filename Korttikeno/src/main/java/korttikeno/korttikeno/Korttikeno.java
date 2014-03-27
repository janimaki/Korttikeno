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

    
    Kenoarvonta arvonta = new Kenoarvonta();
    private static Scanner lukija = new Scanner(System.in);
    Random random;
    public Skanneri skanneri;

    public Korttikeno() {
        skanneri = new Skanneri();
    }
    
    public void uusiPelaaja(){
        System.out.print("Syötä nimi: ");
        String nimi = lukija.nextLine();       
        System.out.print("Paljonko sijoitat? (0,2-10): ");
        Double aloitussumma = Double.parseDouble(lukija.nextLine()); 
        arvonta.uusiPelaaja(nimi,aloitussumma);
        
    }

    public void Pelaa() {        
        if(arvonta.pelaaja.getNimi().equals("uusi")){         
            uusiPelaaja();
        }
       
        System.out.print("Panos? (0,2 - 1): ");
        Double panos = Double.parseDouble(lukija.nextLine());
        arvonta.asetaPanos(panos);
        System.out.print("Montako korttia? (1-4): ");
        int kortteja = lukija.nextInt();
        while(arvonta.pelaaja.valitutNumerot.size()!=kortteja){
            System.out.println("Valitse numero: (1-52)");
            int numero = lukija.nextInt();
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
