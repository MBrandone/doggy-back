package doggy.back.infra.parties

import doggy.back.domain.partie.Partie
import doggy.back.domain.partie.PartieStatut
import org.springframework.stereotype.Component

@Component
class PartieDatabaseMapper {
    fun toPartieDatabase(partie: Partie): PartieDatabase =
        PartieDatabase().apply {
            id = partie.id
            joueur = partie.joueur
            score = partie.score
            statut = partie.statut.toString()
        }

    fun toPartie(partieDatabase: PartieDatabase): Partie =
        Partie(
            id = partieDatabase.id,
            joueur = partieDatabase.joueur,
            score = partieDatabase.score,
            statut = PartieStatut.valueOf(partieDatabase.statut)
        )
}