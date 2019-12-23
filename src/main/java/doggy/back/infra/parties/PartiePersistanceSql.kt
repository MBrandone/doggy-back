package doggy.back.infra.parties

import doggy.back.domain.partie.Partie
import doggy.back.domain.partie.PartiePersistance
import org.springframework.stereotype.Component

@Component
class PartiePersistanceSql(
    private val partiesRepository: PartiesDataRepository,
    private val partieDatabaseMapper: PartieDatabaseMapper
) : PartiePersistance {
    override fun sauverPartie(partie: Partie) {
        val partieDatabase = partieDatabaseMapper.toPartieDatabase(partie)
        partiesRepository.save(partieDatabase)
    }
}