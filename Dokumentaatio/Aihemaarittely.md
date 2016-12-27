Aihe: Tetris peli, sisältää mahdollisesti useampia pelimoodeja ja highscore
listan. Muuten normaali tetris, ei käytä The Tetris Companyn uusimpia
sääntölisäyksiä kuten vaihtopalikkaa, mutta jos aikaa on sen voi lisätä
pelimoodiksi. Haluan tehdä päämoodista Tetris Grand Master ykkösen kaltaisen.

TGM:n kaltaisen ja normi tetriksestä eriävän pelistä tekee sen oma palikan 
kääntö systeemi (josta lisää sen ollessa ohjelmoinnissa relevantimpi),
sen pistelaskenta (alkaa arvosanasta 9 ja tippuu 1:teen, jonka jälkeen 
on S1 ja nousee S9:seen asti jonka jälkeen on mahdollista saada paras
arvosana GM eli Grand Master) sekä gravity joka nousee ja laskee tasojen
mukaan. Yksi pala on aina yksi taso. Painovoima (monta palaa Tetrimino
tippuu per ruutu) nousee yleensä 100:n tason välein mutta esimerkiksi
tasolla 200 se palaa helpoimpaan painovoimaan ja aloittaa kasvamisen
uudestaan, mutta tästä lisää kun se on ohjelmoinnin kannalta
relevantimpaa. Huomioitavaa vielä on että päästäkseen seuraavaan sata lukuun
on aina rikottava yksi rivi.

Vielä yksi lisäys on pelin tetrominon annon randomointi, peli muistaa aina
palan jonka se viimeksi antoi ja vähentää mahdollisuutta antaa se uudestaan.
Esimerkiksi vanhassa NES:n Tetriksessä pala oli aina täysin satunnainen.
Tästäkin taas lisää myöhemmin.

Tässä tämän hetken luokkakaavio:

![Alt text](TTetrisLuokkaKaavio1.jpg)

Kuvassa on laitettu abstrakti luokka Tetrimino ja sen perilliset eli kaikki
eri tetrispalikat laatikkoon jotta ei tarvitsisi tehdä miljoonaa viivaa.
Tetriminon perilliset perivät Tetriminolta listan Paloista, en ole varma
täytyisikö silloinkin perillisistäkin piirtää viiva Palaan.
