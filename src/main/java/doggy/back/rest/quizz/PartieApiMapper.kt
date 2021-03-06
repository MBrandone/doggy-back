package doggy.back.rest.quizz

import doggy.back.domain.partie.Partie
import org.springframework.stereotype.Component

@Component
class PartieApiMapper {
    fun toPartieApi(partie: Partie): PartieApi =
        PartieApi(
            partie.id,
            partie.joueur,
            partie.score,
            partie.statut.toString()
        )
}