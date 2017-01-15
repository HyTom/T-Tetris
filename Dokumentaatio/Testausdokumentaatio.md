Pelin Tetrispeli luokka on kovin sotkuinen.
Loppuvaiheessa Tetrispeli luokan metodeita ei testattu JUnit testeillä 
koodauksen edetessä (Monta ominaisuutta haluttiin nopeasti sisään mutta niitä
ei saatu koska koodi oli sotkuista koska kiirehdittiin) ja testit jäivät vajaiksi.
Luokkaa on kuitenkin testattu samalla tavalla kuin Kaivo luokkaakin, eli kokeilemalla.
Alussa Tetrisluokka ainoastaan printtaili gamelooppia muistuttavasti kaivon sisältöä
jossa paloja testattiin laittamalla niitä törmäämään toisiinsa, seiniin ja lattiiaan.
Niitä myös käänneltiin ja koitettiin etsiä bugeja Kaivon logiikassa.

Jossain vaiheessa Tetrispeli luokka heitti satunnaisesti kaivoon palasia ja tätä
tehtiin paljon kunnes peli päättyi ja tutkittiin, oliko kaivo laillinen.

Kun Kayttöliittymää alettiin luoda, tuli Tetrispelille paljon lisää vastuuta.
Vaikka Kaivo luokka oli todella iso eikä tarpeeksi hyvää ideaa luokalle joka olisi
jakanut sen vastuita, oli se ainakin toiminnallisuudeltaa tässä vaiheessa jo 
valmis. Tetrispeli luokka vaan joutui aloittelevan käyttöliittymä luojan kokeiden 
uhriksi ja siksi sillä on settereitä ja gettereitä joiden alkuperäisestä olemassaolon 
syystä ei tiedä kukaan. 

Tetrispeli luokka toteuttaa pelin looppia. Se on aika monimutkainen ja sitä on 
testattu lähinnä kokeilemalla paljon. Kun peliin lisättiin lukitusmittari jonka
haluttiin toimivan kuin alkuperäisessä pelissä, niin kokeiltiin monia asioita.
Lukitusmittari lähtee käyntiin sillon, kun pala yrittää mennä alas paikassa jossa
ei voi mennä alemmaksi. Se on pelaajalle vielä viimeinen hetki koittaa muuttaa palasen paikkaa tai asentoa. Lukitusmittari resetoituu kun pala liikkuu
askeleenkin alaspäin. Jos pala ei liiku alas eikä alla ole mitään, jää mittari
niinkuin se oli. Tätä oli erittäin vaikeaa testata testeillä, mutta lähinnä siksi
että en suunnitellut sen toteuttamista testejä silmälläpitäen. Olisi pitänyt.

Looppi muuttui lisää kun halusin mukaan palan lukitustauon. Lukitustauossa pala
välkähtää eikä paloja voi ohjata. Sitten halusin välkähdyken mukaan rivien poistoon
ja peli muuttui bugiseksi. Tetrispelin piti olla silloin myös vastuussa siitä, että
milloin kaivo ei enää omista liikkuvaa palaa, koska muuten pala saattoi jäädä
kentälle tuhoamaan lisää paloja vaikka oli tuhoutunut. 

Eli: Tetrispeli luokkaa on testattu ohjailemalla paloja seiniin, toisiin
paloihin, lattioihin, kääntelemällä niin että voidaan katsoa toimivatko oudoimmatkin
Tetris Grand Masterin mahdollistamat liikkeet, lukittamalla paloja toisiin paloihin, 
antamalla lukitusmittarin kulua, sitten liikuttaen sitä toisiin paikkoihin ja katsoen
mitä tapahtuu, antamalla lukitusmittarin kulua ja tiputtamalla sitä, tuhoten paloja 
eri määrillä paloja eri paikoissa ruutua ja tutkien päteekö gameloop edelleen.

Kiitos kivasta kurssista.
