# RMAS-2025-hw1
Zadaca br. 1 za predmet Razvoj mobilnih aplikacija i servisa 2025/26

# Zadaća 1 — Kotlin provjera znanja  
**Predmet:** Razvoj mobilnih aplikacija i servisa  
**Semestar:** 2025/2026  
---

## Uvod
U ovom README file-u objašnjen je način na koji je izrađena prva zadaća. Cilj zadaće je proći kroz osnove Kotlin programskog jezika.

---

## Kako pokrenuti zadacu
Kopirati kod iz zadaca1.kt file-a koji se nalazi u HW1 direktoriju i zalijepiti ga u okruzenje Kotlin Playground.
---

## Dokumentacija

### **1. Interface osoba**
```kotlin
interface Osoba{
    fun identitet() : String
    fun titula() : String
}
```

U ovom koraku kreiran je interface Osoba i deklarisane su metode identitet i titula.
U interfejsu metodi se ne mogu implementirani, ali to ce biti odradjeno u glavnoj klasi.

---

### **2. Osnovna klasa Inzenjer**
```kotlin
open class Inzenjer(
    var ime: String, 
    var prezime: String,
    var profesionalnaTitula: String,
    var brojGodinaIskustva: Int,
    var skupEkspertiza: MutableList<String>) : Osoba{
    
    init{
        require(ime.isNotBlank()) {"Potrebno je unijeti ime inženjera!"}
        require(prezime.isNotBlank()) {"Potrebno je unijeti prezime inženjera!"}
        require(profesionalnaTitula.isNotBlank()) {"Potrebno je unijeti profesionalnu titulu inženjera!"}
        require(brojGodinaIskustva >= 0) {"Broj godina iskustva treba da je ≥ 0!"}
        require(skupEkspertiza.isNotEmpty()) {"Skup ekspertiza treba biti popunjen!"}
    }
    
    override fun identitet() = "$ime $prezime"
    override fun titula() = profesionalnaTitula
    
    //7.  Ispis podataka
    fun osnovneInformacije() {
        println("Inženjer: ${identitet()}")
        println("Titula: $profesionalnaTitula")
        println("Godine iskustva: $brojGodinaIskustva")
        println("Ekspertize: $skupEkspertiza")
    }
    
}
```
Metoda Inzenjer implementira interface Osoba i sadrzi sljedece clanove:
-ime
-prezime
-profesionalnaTitula
-brojGodinaIskustva
-skupEkspertiza
U zaglavlju klase kreiran je i konstruktor.
Metod identitet kada se override-a u glavnoj klasi treba da vrati ime i prezime inzenjera, tj osobe jer klasa Inzenjer implementira interface Osoba, a titula ce vratiti poslovnu titulu inzenjera.
Pored override-anih metoda, u ovoj klasi implementirana je i funkcija osnovneInformacije i ona vraca informacije o inzenjeru.
U init bloku odradjena je validacija podatka koristenjem funkcije require.
Do require funkcije doslo se koristenjem ChatGpt AI alata, a prompt je bio sljedeci:
"Kako u kotlin programskom jeziku odraditi validaciju podataka". Odgovor AI alata bile su funkcije require, check i assert uz objasnjenje kako se koja koristi i kako se ponasa u kodu. Funkcija require je odabran zato sto se smatrala najprikladnijom za izradu zadatka.
Ispod slijede opisi funkcija koje je ChatGPT predlozio:

1. require(condition) koristi se za provjeru ulaznih vrijednosti funkcije ili konstruktora.
Ako je uslov false, baca IllegalArgumentException.

2. check(condition) koristi se za provjeru internih (stanja) uslova programa, tj. da li je objekat u ispravnom stanju tokom izvršavanja.
Ako je uslov false, baca IllegalStateException.

3. assert(condition) koristi se za provjere koje vrijede samo tokom razvoja (u debug modu).
Ako uslov nije ispunjen, baca AssertionError.
Ali u production modu (kad su -ea isključene), assert se ne izvršava.

Vazno je naglasiti da se ispred kljucne rijeci class dodala kljucna rijec open kako bi se od klase Inzenjer izvele subklase SofverskiInzenjer i InzenjerElektrtehnike.

---

### 3. Izvedene klase Sofverski inženjer i Inženjer elektrotehnike
```kotlin
class SoftverskiInzenjer(
    ime: String, 
    prezime: String, 
    brojGodinaIskustva: Int, 
    skupEkspertiza: MutableList<String>, 
    var brojZavrsenihProjekata: Int): Inzenjer(ime, prezime, "Softverski inženjer", brojGodinaIskustva, skupEkspertiza){
    
    init{
        require(brojZavrsenihProjekata>=0) {"Broj završenih projekata ne može biti manji od 0!"}
    }
    
    //Zahtjev zadatka:
    //Obezbijediti da sve izvedene klase zadrže osnovne metode interfejsa.
    //Naredne dvije linije su nepotrebne jer izvedena klasa ove metode svakako nasljeđuje od glavne klase Inzenjer
    override fun identitet() = super.identitet()
    override fun titula() = super.titula()
    
    fun uspjesnost(): String{
        return when (brojZavrsenihProjekata) {
            0 -> "Nema projekata"
            in 1..5 -> "Početnik"
            in 6..10 -> "Uspješan"
            in 11..15 -> "Super uspješan"
            else -> "Stručnjak"
    	}
    }
    
    //7.  Ispis podataka
    fun informacijeSoftverInzenjer(){
        osnovneInformacije();
        println("Broj završenih projekata: $brojZavrsenihProjekata")
    }
   
}

class InzenjerElektrotehnike(
    ime: String, 
    prezime: String, 
    brojGodinaIskustva: Int, 
    skupEkspertiza: MutableList<String>, 
    var brojStecenihCertifikata: Int): Inzenjer(ime, prezime, "Inženjer elektrotehnike", brojGodinaIskustva, skupEkspertiza) {
    
    init{
        require(brojStecenihCertifikata>=0) {"Broj stečenih certifikata ne može biti manji od 0!"}
    }
    
    //Zahtjev zadatka:
    //Obezbijediti da sve izvedene klase zadrže osnovne metode interfejsa.
    //Naredne dvije linije su nepotrebne jer izvedena klasa ove metode svakako nasljeđuje od glavne klase Inzenjer
    override fun identitet() = super.identitet()
    override fun titula() = super.titula()
    
    fun uspjesnost(): String{
        return when(brojStecenihCertifikata){
            0 -> "Nema projekata"
            in 1..5 -> "Početnik"
            in 6..10 -> "Uspješan"
            in 11..15 -> "Super uspješan"
            else -> "Stručnjak"
        }
    }
    
    //7.  Ispis podataka
    fun informacijeInzenjerElektrotehnike(){
        osnovneInformacije();
        println("Broj stečenih certifikata: $brojStecenihCertifikata")
    }
    
}
```

Izvedene klase SoftverskiInzenjer i InzenjerElektrotehnike imaju ista polja kao i klasa Inzenjer, s tim da klasa SoftverskiInzenjer ima dodatni clan, a to je brojZavrsenihProjekata, dok klasa InzenjerElektrotehnike ima dodatni clan brojStecenihCertifikata.
U zaglavlju obje izvedene klase kreirani su njihovi odgovarajuci primarni konstruktori, ali uz poziv konstruktora glavne klase. U konstruktoru glavne klase, na mjestu gdje treba biti clan profesionalnaTitula nalazi se ili "Softvevrski inženjer" ili "Inženjer elektrotehnike" zavisno od toga koja je izvedena klasa u pitanju (moze se zakljuciti na osnovu naziva klasa).
U init bloku, pomocu funkcije require odradjena je validacija dodatnih clanova klasa. 
Buduci da u tekstu zadatka stoji: "Obezbijediti da sve izvedene klase zadrže osnovne metode interfejsa.", funkcije identitet i titula su override-ane, mada se to nije moralo uraditi jer izvedene klase svakako ove metode nasljedjuju od glavne klase Inzenjer kako pise i u komentaru u kodu. 
Funkcije su override-ane na nacin da se koristi super.identitet() ili super.titula(). Kljucna rijec super se koristi zato sto su ove metode vec override-ane u main klasi, a nakon super. dolazi funkcija koja je override-ana u Inzenjer klasi.
Za izvedenu klasu SoftverskiInzenjer definisana su jos dva metoda, uspjesnost() koja nista ne uzima, a vraca string i informacijeSoftverinzenjer koja nista ne uzima, a vraca Unit.
Buduci da nije specificirano kako treba funkcionisati funkcija uspjesnost(), na osnovu broja zavrsenih projekata, vraca se string koliko je inzenjer uspjesan (Početnik, Upsješan...). 
Funkcija informaicjeSoftverInzenjer unutar svog tijela poziva funkciju osnovneInformaicje koje je naslijedila od Inzenjer klase, a zatim iz poziva funkcije dodaje se i ispis za dodatni clan klase SoftverskiInzenjer.
Novi metodi u klasi InzenjerElektrotehnike su uspjesnost(), koja radi apsolutno isto kao i uspjesnost() iz klase SoftverskiInzenjer, samo sto se koristi brojSteecnihCertifikataa u when statement-u. 
Funkcija informacijeInzenjerElektortehnike() iza poziva osnovneInformacije ispisuje broj stecenih certifikata.

---

### **4. Grupisanje sa fold()**
```kotlin
fun grupisiInzenjerePoEkspertizi(inzenjeri: List<Inzenjer>) : Map<String, List<Inzenjer>>{
    return inzenjeri.fold(mutableMapOf<String, MutableList<Inzenjer>>()){
        acc, inzenjer -> if(inzenjer.brojGodinaIskustva > 5){
            for(ekspertiza in inzenjer.skupEkspertiza){
                if(!acc.containsKey(ekspertiza)){
                    acc[ekspertiza] = mutableListOf()
                }
                acc[ekspertiza]!!.add(inzenjer)
            }
        }
        acc
    }
}
```

**Kako funkcionise fold():** fold() uzima početnu vrijednost (akumulator) i fold-a sve elemente liste u jedan rezultat. Drugim riječima prolazi kroz listu i svaki put ažurira neku vrijednost (acc) i na kraju vraća konačni rezultat.

Funkcija koja koristi fold() u svojoj implementaciji zove se grupisiInzenjerePoEkspertizi i uzima listu inzenjera, tj inzenjeri: List<Inzenjer>, a vraca mapu stringova koji se mapiraju u listu inzenjera.
fold() unutar malih zagrada koristi neki akumulator ili acc kako se to obicno oznacava.
acc koji je koristen pri implementaciji ove funkcije je mutableMapOf<String, MutableList<Inzenjer>>() sto je prazna mapa. Sva logika odvija se dalje unutar zagrda {}. 
Uzima se acc i inzenjer, a promjenljiva inzenjer predstavlja jednog od inzenjera iz liste inzenjera i od njega se dobiva brojGodinaIskustva na sljedeci nacin inzenjer.brojGodinaIskustva i provjerava se da li je dobivena vrijednost veca od 5.
Ukoliko jeste od tog inzenjera se pristupa skupuEkspertiza i za svaku od ekspertiza se provjerava da li postoji u vec u mapi, tj da li je ta ekspertiza vec kljuc nekog para u mapi.
Ako nije kreiera se novi par u kojem je kljuc ekspertiza koja nije postojala u mapi i na pocetku se ta ekspertiza mapira u praznu listu, u suprotnom inzenjer se ubacuje u listu koja je asocirana sa ekspertizom koja je kljuc u mapi. Izlaskom iz for loop-a vraca se acc.

---

### **5. Odabir najiskusnijeg sa reduce()**
```kotlin
fun odaberiNajiskusnijegInzenjera(inzenjeri: List<Inzenjer>): List<Inzenjer?>{
	val softverskiInzenjeri = mutableListOf<Inzenjer>()
    val inzenjeriElektrotehnike = mutableListOf<Inzenjer>()
    var najiskusnijiSoftverskiInzenjer : Inzenjer? = null
    var najiskusnijiInzenjerElektrotehnike :Inzenjer? = null
    
    for(inzenjer in inzenjeri){
        if(inzenjer.profesionalnaTitula == "Softverski inženjer"){
            softverskiInzenjeri.add(inzenjer)
        }
        else inzenjeriElektrotehnike.add(inzenjer)
    }
    
    najiskusnijiSoftverskiInzenjer = softverskiInzenjeri.reduce{acc, inzenjer -> 
    	if(inzenjer.brojGodinaIskustva > acc.brojGodinaIskustva){
    		najiskusnijiSoftverskiInzenjer = inzenjer
    	}
    	else najiskusnijiSoftverskiInzenjer = acc
        acc
    }
    
    najiskusnijiInzenjerElektrotehnike = inzenjeriElektrotehnike.reduce{acc, inzenjer ->
        if(inzenjer.brojGodinaIskustva > acc.brojGodinaIskustva){
        	najiskusnijiInzenjerElektrotehnike = inzenjer
        }
        else najiskusnijiInzenjerElektrotehnike = acc
        acc
    }
    
    return listOf(najiskusnijiSoftverskiInzenjer, najiskusnijiInzenjerElektrotehnike)
}
```

**Kako funkcionise reduce():** reduce() je sličan fold(), ali nema početnu vrijednost.
Kao početni akumulator (acc) koristi prvi element liste. Zato se reduce() ne može pozvati na praznoj listi jer nema prvi element.

Kreirane su dvije mutableList-e, softverskiInzenjeri i inzenjeriElektrotehnike.
for loop-om se prolazi kroz listu inzenjera koja je data kao ulazni parametar funkcije i na osnovu uslova se odredjuje koji inzenjer ce se dodati u odgovarajucu listu.
Takodjer, kreirane su i dvije promjenljive koje ce cuvati najiskusnije inzenjere iz svojih kategorija, a te varijable su: najiskusnijiSoftverskiInzenjer i najiskusnijiInzenjerElektrotehnike.
U varijable se smjesti najiskusniji inzenjer koristeci reduce() funkciju ciji je akumulator acc na pocetku prvi inzenjer iz novokreirane liste, a inzenjer drugi inzenjer iz liste. U if statementu se inzenjeri (bilo da su u pitnju softverski inzenjeri ili inzenjeri elektrotehnike) porede na osnovu clana brojGodinaIskustva. Onaj inzenjer cija je vrijednost clana brojGodinaIskustva veca se pohranjuje u varijablu najiskusnijiSoftverskiInzenjer ili najiskusnijiInzenjerElektrotehnike, a nakon pohrane se vraca akumulator acc.
 
---

### **6. Agregacija sa aggregate()**
```kotlin
fun ukupanBrojProjekataCertifikataKompanije(inzenjeri: List<Inzenjer>) : Int {
    val grouped = inzenjeri.groupingBy { inzenjer ->
        inzenjer.profesionalnaTitula
    }

    val aggregated: Map<String, Int> = grouped.aggregate { key, acc: Int?, element, first ->
        if (key == "Softverski inženjer") {
            (acc ?: 0) + (element as SoftverskiInzenjer).brojZavrsenihProjekata
        } else {
            (acc ?: 0) + (element as InzenjerElektrotehnike).brojStecenihCertifikata
        }
    }
    return aggregated.values.sum()
}
```

**Kako funkcionise agregate():** Funkcija aggregate() u je napredna metoda koja se koristi zajedno sa groupingBy() kako bi se izvrsila prilagodjena agregacija podataka po grupama. 
Tokom obrade, aggregate prolazi kroz sve elemente liste, grupisane po odredjenom kriterijumu, i za svaku grupu primijenjuje datu lambda-funkciju koja prima kljuc grupe, trenutni akumulirani rezultat (acc), trenutni element (element) i indikator da li je to prvi element u grupi (first).


Za implementaciju funkcije ukupanBrojProjekataCertifikataKompanije koristen je AI alat ChatGPT, a prompt je bio proslijedjen tekst zadatka: "Kreirati funkcionalnost koja koristi **`aggregate()`** da izračuna ukupan broj:
  - projekata svih softverskih inženjera,  
  - certifikata svih elektrotehničkih inženjera.  
- Rezultat treba biti zbir obje vrijednosti — ukupni broj svih projekata i certifikata u kompaniji."

**Kako funkcija radi?**
Sljedeci dio koda sluzi da se lista inzenjera podijeli na listu inzenjera koji su softverski inzenjeri i koji su inzenjeri elektrotehnike.
Funkcija groupingBy ne pravi odmah grupe, nego kreira objekat tipa Grouping koji opisuje kako ce grupisanje izgledati. grouped nije još mapa, nego specijalni Kotlin-ov interni tip koji sadrzi kolekciju (inzenjeri) i nacin grupisanja, odnosno kljuc po kojem se grupise, a to je inzenjer.profesionalnaTitula.

```kotlin
val grouped = inzenjeri.groupingBy { inzenjer ->
        inzenjer.profesionalnaTitula
    }
```

Sljedeci dio koda koristi funkciju aggregate da za svaku grupu inzenjera (grupisanu prema profesionalnoj tituli) izracuna zbir relevantnih vrijednosti, odnosno broj zavrsenih projekata za softverske inzenjere i broj stecenih certifikata za inzenjere elektrotehnike.
Lambda funkcija prima cetiri argumenta: key (kljuc grupe), acc (trenutni akumulator koji cuva dosadasnji zbir), element (trenutni inzenjer koji se obradjuje) i first (pokazatelj da li je element prvi u grupi).
Unutar lambde, koristi se operator ?: kako bi se obezbijedila pocetna vrijednost akumulatora (nula ako je acc null).
Zavisno od tipa grupe (key), element se kastuje na odgovarajucu klasu i njegov broj projekata ili certifikata se dodaje u zbir. Rezultat aggregate funkcije je mapa u kojoj su kljucevi profesionalne titule, a vrijednosti sume projekata i certifikata po grupama.
Na kraju, pozivom aggregated.values.sum() sabiraju se sve vrijednosti iz mape, cime se dobija ukupni broj projekata i certifikata za sve inzenjere zajedno.

```kotlin
val aggregated: Map<String, Int> = grouped.aggregate { key, acc: Int?, element, first ->
        if (key == "Softverski inženjer") {
            (acc ?: 0) + (element as SoftverskiInzenjer).brojZavrsenihProjekata
        } else {
            (acc ?: 0) + (element as InzenjerElektrotehnike).brojStecenihCertifikata
        }
    }
    return aggregated.values.sum()
```

---

### **7. Ispis podataka**
1. Klasa Inzenjer
```kotlin
fun osnovneInformacije() {
        println("Inženjer: ${identitet()}")
        println("Titula: $profesionalnaTitula")
        println("Godine iskustva: $brojGodinaIskustva")
        println("Ekspertize: $skupEkspertiza")
    }
```
2. Klasa SoftverskiInzenjer
```kotlin
fun informacijeSoftverInzenjer(){
        osnovneInformacije();
        println("Broj završenih projekata: $brojZavrsenihProjekata")
    }
```

3. Klasa InzenjerElektrotehnike
```kotlin
fun informacijeInzenjerElektrotehnike(){
        osnovneInformacije();
        println("Broj stečenih certifikata: $brojStecenihCertifikata")
    }
```

Ove funkcije nalaze se unutar svojih klasa i kada su objasnjavane klase objasnjene su i funkcije za ispis podatka.

---

### **8. Glavni tok programa i provjera ispravnosti**
```kotlin
fun main() {
    //Kreiranje liste inženjera
    val inzenjeri = listOf(
        SoftverskiInzenjer("Ana", "A.", 8, mutableListOf("Kotlin", "Java"), 10),
        SoftverskiInzenjer("Marko", "M.", 4, mutableListOf("Python", "Kotlin"), 3),
        SoftverskiInzenjer("Jelena", "J.", 12, mutableListOf("Java", "C++"), 15),
        InzenjerElektrotehnike("Ivana", "I.", 7, mutableListOf("Elektronika", "Energetika"), 5),
        InzenjerElektrotehnike("Petar", "P.", 10, mutableListOf("Energetika", "Automatika"), 8),
        InzenjerElektrotehnike("Milan", "M.", 3, mutableListOf("Telekomunikacije"), 2)
    )

    println("=== Grupisanje po ekspertizama (fold) ===")
    val ekspertizeMap = grupisiInzenjerePoEkspertizi(inzenjeri)
    for ((ekspertiza, lista) in ekspertizeMap) {
        println("$ekspertiza: ${lista.map { it.identitet() }}")
    }

    println("\n=== Najiskusniji inženjeri (reduce) ===")
    val najiskusniji = odaberiNajiskusnijegInzenjera(inzenjeri)
    println("Najiskusniji softverski inženjer: ${najiskusniji[0]?.identitet()} - ${najiskusniji[0]?.brojGodinaIskustva} godina")
    println("Najiskusniji elektrotehnički inženjer: ${najiskusniji[1]?.identitet()} - ${najiskusniji[1]?.brojGodinaIskustva} godina")

    println("\n=== Ukupan broj projekata i certifikata (aggregate) ===")
    val ukupno = ukupanBrojProjekataCertifikataKompanije(inzenjeri)
    println("Ukupan broj projekata i certifikata: $ukupno")

    println("\n=== Ispis svih inženjera ===")
    for (inzenjer in inzenjeri) {
        when (inzenjer) {
            is SoftverskiInzenjer -> inzenjer.informacijeSoftverInzenjer()
            is InzenjerElektrotehnike -> inzenjer.informacijeInzenjerElektrotehnike()
        }
        println("----------------------------")
    }

    //Provjere ispravnosti
    println("\n=== Provjere ispravnosti ===")
    
     // Provjera 1: najiskusniji inženjeri
    println("Očekivani najiskusniji softverski inženjer: Jelena J. (12 godina)")
    println("Dobijeni rezultat: ${najiskusniji[0]?.identitet()} (${najiskusniji[0]?.brojGodinaIskustva} godina)")
    if (najiskusniji[0]?.identitet() == "Jelena J." && najiskusniji[0]?.brojGodinaIskustva == 12)
        println("Najiskusniji softverski inženjer tačan")
    else
        println("Greška u određivanju najiskusnijeg softverskog inženjera")

    println("Očekivani najiskusniji elektroinženjer: Petar P. (10 godina)")
    println("Dobijeni rezultat: ${najiskusniji[1]?.identitet()} (${najiskusniji[1]?.brojGodinaIskustva} godina)")
    if (najiskusniji[1]?.identitet() == "Petar P." && najiskusniji[1]?.brojGodinaIskustva == 10)
        println("Najiskusniji elektroinženjer tačan")
    else
        println("Greška u određivanju najiskusnijeg elektroinženjera")

    // Provjera 2: aggregate vraća tačan zbir
    val ocekivanoUkupno = 10 + 3 + 15 + 5 + 8 + 2 // zbir svih projekata i certifikata
    println("\nOčekivani zbir (projekti + certifikati): $ocekivanoUkupno")
    println("Dobijeni zbir: $ukupno")
    if (ukupno == ocekivanoUkupno)
        println("Aggregate rezultat tačan")
    else
        println("Greška u aggregate izračunu")

    // Provjera 3: fold filtrira samo inženjere s više od 5 godina iskustva
    val sviFiltriraniImajuViseOd5 = ekspertizeMap.values.flatten().all { it.brojGodinaIskustva > 5 }
    println("\nOčekivano: svi inženjeri u fold mapi imaju > 5 godina iskustva")
    if (sviFiltriraniImajuViseOd5)
        println("Fold filtriranje ispravno")
    else
        println("Greška u filtriranju fold funkcije")
}


```

Kompletan glavni tok programa i provjera ispravnosti dobivena je koristenjem AI alata ChatGPT.
Prompt je bio kopija implementiranog koda (odradjene stavke od 1 do 7), kopija teksta zadatka koji dat nakon naslova Glavni tok programa i kopija teksta nakon naslova Provjera ispravnosti.

---



