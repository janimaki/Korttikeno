/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package korttikeno.Sovelluslogiikka;

/**
 * Luokka, joka määrittää pelin panoksen. 
 * 
 * @author Jani
 */
public class Panos {
    public double panos = 0;
  
    
    public Panos(double paukku){
        this.panos = paukku;
    }

    public double getPanos() {
        return panos;
    }

    public void setPanos(double panos) {
        this.panos = panos;
    }
    
    
    
    
}
