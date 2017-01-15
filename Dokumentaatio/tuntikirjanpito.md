### Tuntikirjanpito
Päivä | Tunnit | Kuvaus
--------------- | ----- | ------
21.12.2016 | 2h | Repositorion luonti ja projektin valmistelu
25.12.2016 | 4h | Ohjelmoinnin aloittaminen ja toteutuksen pohdintaa
26.12.2016 | 2h | Refaktorointia ja luokkakaavion luonti
27.12.2016 | 7h | Kansiorakenne luotu, JUnit testit luotu, pit ja checkstyle generoitu, aihemäärittelyä paranneltu, harkittu tarvitseeko aihemäärittely.md muuttaa aiheenKuvaysJaRakenne.md nimiseksi, README.md palautukseen kelpaavaksi ja yleistä Deadlineen panostautumista
28.12.2016 | 5h | Luotiin puuttuvat Tetris-palat ja lisättiin peliin lisää logiikkaa. Peli voi vihdoin päättyä ja se tunnistaa jos palikka osuu toiseen palikkaan. Seuraavaksi palojen pyöritys ja x-suunnassa liikkuminen.
29.12.2016 | 2h | Luotiin testejä
29.12.2016 | 7h | Tetriksen toiminnan pohtimista ja logiikan lisäystä. Yksi pala pyörii halutusti ja palat eivät mene seinien läpi. 
30.12.2016 | 4h | Logiikan hiomista
31.12.2016 | 2h | I palikka toimii, deadlineen valmistumista, aikataulun vuoksi testit jäivät vajaiksi tavotteista, testit paremmassa kunnossa ensi deadlineen mennessä.
1.12.2016  | 4h | Dokumennoinnin nätintämistä ja rankkaa refaktorointia
2.12.2016  | 3h | Kaikki palat toimivat ja pelin sisäinen logiikka on pääsääntöisesti valmis!
3.12.2016  | 2h | Luotiin testejä
4.12.2016  | 3h | Valmistauduttiin deadlineen (javadoc etc.)
5.12.2016  | 3h | Ihme sekoilua
6.12.2016  | 6h | Käyttöliittymä logiikka toimii jotenkin. Kun painaa aloita nappia, peli alkaa. Pelin piirtologiikka toimii mutta täytyy muuttaa niin että kaivoa on helpompi liikuttaa käyttiksessä helpommin halutulle paikalle. Ainakin kaikille paloille on lisätty oma väri. Lisättiin alkeellinen painovoiman laskeminen joka on sidottu siihen, että peli laskee kaiken 60 kertaa sekunnissa. Tällä hetkellä peliä ei voi pelata, vaan peli satunnaisesti liikuttelee ja pyörittelee palasta, seuraavaksi siihen tulee muutos. Paljon hiottavaa.
7.12.2016  | 4h | Peli on kankea, mutta pelattavissa.
8.12.2016  | 5h | Pieniä muutoksia, sekvenssikaavioita, javadocia, testejä, deadlineen valmistumista yms.
10.12.2016 | 3h | Peli paukutti null pointtereita jos painoi suuntanäppäintä kun palaa ei voinut liikuttaa koska pelillä ei ollut palaa mitä liikuttaa. Se ei tee sitä enään. Kun muutti Piirtaja luokan kohdassa jossa pelin paloja ja seiniä piirrettiin metodin fillRect() metodiksi fill3DRect() niin peli muuttui taianomaisesti 100 kertaa tyylikkäämmän näköiseksi. Peli näyttää nyt levelin ja ulkonäköä muutettiin. Korjattiin bugeja. Peli näyttää seuraavan palan.
11.12.2016 | 8h | Sotkenut koodia ja lisännyt pieniä muutoksia. Pelissä on nyt palanlukitus systeemi.
13.12.2016 | 6h | Peliä voi pelata useita kertoja loputtomasti ilman että sulkee peliä. Painovoima toimii oikein.
14.12.2016 | 5h | Pelin level laskin toimii oikein. Painovoima on oma luokkansa. Uusi luokka, Pelilaskuri hoitaa laskemista vaativia asioita hieman vähentäen Tetrispeli luokan työtaakkaa. Lukitus systeemi toimii niinkuin alun perin haluttiin. Lukitusmittarin jälkeen tulee aina pieni viive ennekuin seuraava pala tulee, ja pala muuttaa väriään hetkeksi. Se toimii myös silloin kun kaivoa tyhjennetään, ja siitä tuli hieno efekti. 
15.12.2016 | 5h | Randomoija joskin vähän kömpelö, toimii niinkuin pitää. Pisteet ja Level asetettu uudelle paikalle. Valmistauduttu viimeiseen Deadlineen, javadoc yms.
... | ... | ...
