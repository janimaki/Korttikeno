/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package korttikeno.Sovelluslogiikka;

import korttikeno.Pelaaja.Pelaaja;

/**
 *
 * @author Jani
 */
public class Tuplaus {
    private Kortti kortti;
    private Arpoja arpoja;
    private Pelaaja pelaaja;
    private Panos panos;
    
    public Tuplaus(){
        
    }
    
    public int korttiPieniVaiSuuri(){
        if(this.kortti.getArvo()<=6) {
            return 0; // 0 = pieni
        } else if(this.kortti.getArvo()==7){
            if(this.kortti.getMaa()==1||this.kortti.getMaa()==2){
                return 1; 
            } else {
                return 2; 
            }                      
        } else {
            return 3;
        }
    }
    
    
    public void tuplaa(double summa){
        kortti = new Kortti(arpoja.arvoKortti(),arpoja.arvoMaa());
        if(korttiPieniVaiSuuri()==1){ // kortti on punainen seiska eli pelaaja saa panoksen takaisin
            pelaaja.muutaSaldoa(1);
        } else if(korttiPieniVaiSuuri()==2){ // kortti on musta seiska, eli pelaaja menettää panoksen
            return;
        } else if(korttiPieniVaiSuuri()==3){ // kortti on suuri
            if(pelaaja.getTuplaus()==3){ // 3 tarkoittaa suurta
                pelaaja.muutaSaldoa(2);
            } else {
                return;
            }
        } else { // kortti on pieni
            if(pelaaja.getTuplaus()==0){ // 0 tarkoittaa pientä
                pelaaja.muutaSaldoa(2);
            }
        }
        
    }
}
