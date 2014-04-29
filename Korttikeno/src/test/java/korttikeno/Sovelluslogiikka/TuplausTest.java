/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.korttikeno.Sovelluslogiikka;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jani
 */
public class TuplausTest {

    Tuplaus tuplaus;

    public TuplausTest() {
    }

    @Before
    public void setUp() {
        tuplaus = new Tuplaus();
    }


    @Test
    public void kortinVertailuKunnossa() {
        tuplaus.kortti = new Kortti(5);
        assertEquals(0, tuplaus.korttiPieniVaiSuuri());
        tuplaus.kortti = new Kortti(1);
        assertEquals(0, tuplaus.korttiPieniVaiSuuri());
        tuplaus.kortti = new Kortti(13);
        assertEquals(0, tuplaus.korttiPieniVaiSuuri());
        tuplaus.kortti = new Kortti(18);
        assertEquals(0, tuplaus.korttiPieniVaiSuuri());
        tuplaus.kortti = new Kortti(26);
        assertEquals(0, tuplaus.korttiPieniVaiSuuri());
        tuplaus.kortti = new Kortti(31);
        assertEquals(0, tuplaus.korttiPieniVaiSuuri());
        tuplaus.kortti = new Kortti(39);
        assertEquals(0, tuplaus.korttiPieniVaiSuuri());
        tuplaus.kortti = new Kortti(44);
        assertEquals(0, tuplaus.korttiPieniVaiSuuri());
        tuplaus.kortti = new Kortti(52);
        assertEquals(0, tuplaus.korttiPieniVaiSuuri());
//
        tuplaus.kortti = new Kortti(7);
        assertEquals(3, tuplaus.korttiPieniVaiSuuri());
        tuplaus.kortti = new Kortti(12);
        assertEquals(3, tuplaus.korttiPieniVaiSuuri());
        tuplaus.kortti = new Kortti(20);
        assertEquals(3, tuplaus.korttiPieniVaiSuuri());
        tuplaus.kortti = new Kortti(25);
        assertEquals(3, tuplaus.korttiPieniVaiSuuri());
        tuplaus.kortti = new Kortti(33);
        assertEquals(3, tuplaus.korttiPieniVaiSuuri());
        tuplaus.kortti = new Kortti(38);
        assertEquals(3, tuplaus.korttiPieniVaiSuuri());
        tuplaus.kortti = new Kortti(46);
        assertEquals(3, tuplaus.korttiPieniVaiSuuri());
        tuplaus.kortti = new Kortti(51);
        assertEquals(3, tuplaus.korttiPieniVaiSuuri());

        tuplaus.kortti = new Kortti(6);
        assertEquals(2, tuplaus.korttiPieniVaiSuuri());
        tuplaus.kortti = new Kortti(45);
        assertEquals(2, tuplaus.korttiPieniVaiSuuri());
        tuplaus.kortti = new Kortti(19);
        assertEquals(1, tuplaus.korttiPieniVaiSuuri());
        tuplaus.kortti = new Kortti(32);
        assertEquals(1, tuplaus.korttiPieniVaiSuuri());
    }
}
