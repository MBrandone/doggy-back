package doggy.back.infra.parties

import doggy.back.domain.entites.Partie
import org.springframework.stereotype.Component

@Component
class PartieDatabaseMapper {
    fun toPartieDatabase(partie: Partie): PartieDatabase =
        PartieDatabase().apply {
            id = partie.id
            joueur = partie.joueur
            score = partie.score
            statut = partie.statut
        }
}