package doggy.back.domain

import doggy.back.domain.entites.Partie
import doggy.back.infra.parties.PartieDatabaseMapper
import doggy.back.infra.parties.PartiesDataRepository
import org.springframework.stereotype.Component

@Component
class CreerPartie(
    private val partiesRepository: PartiesDataRepository,
    private val partieDatabaseMapper: PartieDatabaseMapper
) {
    fun execute(joueur: String): Partie {
        val partie = Partie.init(joueur)
        val partieDatabase = partieDatabaseMapper.toPartieDatabase(partie)
        partiesRepository.save(partieDatabase)
        return partie
    }
}