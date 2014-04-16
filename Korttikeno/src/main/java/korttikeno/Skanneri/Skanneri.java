/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package korttikeno.Skanneri;

import java.util.Scanner;

/**
 * Luokka, jota käytetään käyttäjän syötteen lukemiseen.
 *
 * @author Jani
 */
public class Skanneri {

    public static Scanner lukija;

    public Skanneri() {
        lukija = new Scanner(System.in);
    }

    /**
     * Metodi palauttaa rivin, jonka käyttäjä on antanut syötteenä
     *
     * @return rivi, jonka käyttäjä syöttänyt.
     */
    public String nextLine() {
        return lukija.nextLine();
    }
    
    public Double nextDouble() {
        return lukija.nextDouble();
    }
}
