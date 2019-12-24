package doggy.back.domain.partie

import doggy.back.domain.partie.PartieStatut.EN_COURS
import java.util.*

data class Partie(
    val id: String,
    val joueur: String,
    val score: Int,
    val statut: PartieStatut
) {
    companion object {
        fun init(joueur: String): Partie {
            return Partie(
                UUID.randomUUID().toString(),
                joueur,
                0,
                EN_COURS
            )
        }
    }
}