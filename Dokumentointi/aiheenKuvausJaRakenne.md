Aihemäärittely

Aihe: Korttipeli nimeltä korttikeno. Peli on vastaavanlainen kuin normaali kenoarvonta, mutta numeroiden sijaan valitaan kortteja. Kun omat kortit on valittu, nostetaan uudesta pakasta 10 korttia, joita verrataan pelaajan valitsemiin kortteihin. Riippuen panoksesta ja osumien määrästä, pelaaja voittaa tietyn summan tai häviää panoksensa.

Käyttäjät: 1 pelaaja

Käyttäjän toiminnot: valita alkusaldo, valita panos, valita kortit, tuplaus

Rakenteen kuvaus:

Kun ohjelma käynnistetään, käyttöliittymä luodaan. Käyttöliittymä luo kenoarvonnan ja pelaajan valintojen mukaan käyttöliittymä ohjaa arvonnan toimintaa. Kun kenoarvonta luodaan, se luo samalla panoksen(0.2) ja pelaajan, jolla on saldo 0. Kun käyttöliittymässä muokataan panosta, kenoarvonta muuttaa Panos-luokan arvoa. Kun pelaaja asettaa saldon itselleen, kenoarvonta muuttaa jo luodun pelaajan saldoattribuuttia.

Kun arvonta on suoritettu, käyttöliittymä kutsuu kenoarvonnan metodeita, jotka tunnistavat voiton. Tuplausmahdollisuus aukeaa. Käyttöliittymän kautta kenoarvonta kutsuu tuplausmetodiaan, joka luo tuplaus-olion, joka arpojan avulla arpoo kortin tuplaukseen. Kenoarvonta tunnistaa pelaajalle asetetun tuplausindeksin (suuri tai pieni) ja vertaa sitä korttiin. Kierroksen lopuksi kenoarvonta suorittaa voitonmaksun eli muuttaa pelaajan saldoa käytetyn panoksen ja voitetun kertoimen mukaan ja valmistaa kenoarvonta-olion uutta arvontaa varten.

