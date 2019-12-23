package doggy.back.domain.partie

import org.springframework.stereotype.Component

@Component
class CreerPartie(
    private val partiePersistance: PartiePersistance
) {
    fun execute(joueur: String): Partie {
        val partie = Partie.init(joueur)
        partiePersistance.sauverPartie(partie)
        return partie
    }
}