package main.java.korttikeno.Sovelluslogiikka;

import java.text.DecimalFormat;

/**
 * Luokka, joka määrittää pelin panoksen.
 *
 * @author Jani
 */
public class Panos {

    /**
     * Pelin panos
     */
    public double panos = 0;

    public Panos(double paukku) {
        this.panos = paukku;
    }

    public double getPanos() {
        return panos;
    }

    public void setPanos(double panos) {
        panos = Math.round(panos * 100);
        this.panos = panos / 100;
    }
}
