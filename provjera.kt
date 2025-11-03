import Inzenjer
import kotlin.collections.mutableListOf

//1. Interface Osoba
interface Osoba{
    fun identitet() : String
    fun titula() : String
}

//2. Osnovna klasa Inzenjer
abstract class Inzenjer(
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

 //   abstract fun efficiencyScore(inzenjeri : List<Inzenjer>) : Int

}

//3. Izvedene klase Sofverski inženjer i Inženjer elektrotehnike
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

    fun efficiencyScore(inzenjeri: List<SoftverskiInzenjer>) : Int {
        val scores = mutableListOf<Int>()
        var maxScore : Int? = null
        for(inzenjer in inzenjeri){
                scores.add(inzenjer.brojGodinaIskustva * inzenjer.brojZavrsenihProjekata)
        }
        maxScore = scores.reduce{ acc : Int, i ->
            if(acc > i) acc
            else i
        }
        return maxScore
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

    fun efficiencyScore(inzenjeri: List<InzenjerElektrotehnike>) : Int {
        val scores = mutableListOf<Int>()
        var maxScore : Int? = null
        for(inzenjer in inzenjeri){
            scores.add(inzenjer.brojGodinaIskustva * inzenjer.brojStecenihCertifikata)
        }
        maxScore = scores.reduce{ acc : Int, i ->
            if(acc > i) acc
            else i
        }
        return maxScore

    }

}

//4. Grupisanje sa fold()
// acc je na pocetku mutableMapOf<String, MutableList<Inzenjer>>(), prazna mapa
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

//5. Odabir najiskusnijeg sa reduce()
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

//6. Agregacija sa aggregate()
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
 fun efficiencyScore(inzenjeri: List<Inzenjer>) : Int {
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
         if(inzenjer.brojGodinaIskustva * (inzenjer as SoftverskiInzenjer).brojZavrsenihProjekata> acc.brojGodinaIskustva * (acc as SoftverskiInzenjer).brojZavrsenihProjekata){
             najiskusnijiSoftverskiInzenjer = inzenjer
         }
         else najiskusnijiSoftverskiInzenjer = acc
         acc
     }

     najiskusnijiInzenjerElektrotehnike = inzenjeriElektrotehnike.reduce{acc, inzenjer ->
         if(inzenjer.brojGodinaIskustva * (inzenjer as InzenjerElektrotehnike).brojStecenihCertifikata> acc.brojGodinaIskustva *  (acc as InzenjerElektrotehnike).brojStecenihCertifikata){
             najiskusnijiInzenjerElektrotehnike = inzenjer
         }
         else najiskusnijiInzenjerElektrotehnike = acc
         acc
     }

     if(najiskusnijiSoftverskiInzenjer.brojGodinaIskustva * (najiskusnijiSoftverskiInzenjer as SoftverskiInzenjer).brojZavrsenihProjekata >
         najiskusnijiInzenjerElektrotehnike.brojGodinaIskustva * (najiskusnijiInzenjerElektrotehnike as InzenjerElektrotehnike).brojStecenihCertifikata)

         return najiskusnijiSoftverskiInzenjer.brojGodinaIskustva * (najiskusnijiSoftverskiInzenjer as SoftverskiInzenjer).brojZavrsenihProjekata

     else
         return  najiskusnijiInzenjerElektrotehnike.brojGodinaIskustva * (najiskusnijiInzenjerElektrotehnike as InzenjerElektrotehnike).brojStecenihCertifikata



}



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

    //  PROVJERA ISPIS
    val score = efficiencyScore(inzenjeri)
    println("Najefikasniji inzenjer je: ${inzenjeri.ime} - ${inzenjeri.profesionalnaTitula}")
    //

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


