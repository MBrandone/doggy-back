package doggy.back.domain.partie

interface PartiePersistance {
    fun sauverPartie(partie: Partie)
    fun recupererPartie(idPartie: String): Partie?
}