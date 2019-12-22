package doggy.back.infra.parties

import doggy.back.domain.entites.Partie
import org.springframework.stereotype.Component

@Component
class PartieDatabaseMapper {
    fun toPartieDatabase(partie: Partie): PartieDatabase =
        PartieDatabase(
            partie.id,
            partie.joueur,
            partie.score,
            partie.statut
        )
}