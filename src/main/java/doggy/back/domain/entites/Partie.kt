package doggy.back.domain.entites

import doggy.back.rest.quizz.PartieStatut
import java.util.*

data class Partie(
    val id: String,
    val joueur: String,
    val score: Int,
    val statut: String
) {
    companion object {
        fun init(joueur: String): Partie {
            return Partie(
                UUID.randomUUID().toString(),
                joueur,
                0,
                PartieStatut.EN_COURS.toString()
            )
        }
    }
}