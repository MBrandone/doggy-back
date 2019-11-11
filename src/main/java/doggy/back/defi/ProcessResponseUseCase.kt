package doggy.back.defi

import doggy.back.parties.PartiesRepository
import doggy.back.quizz.PartieStatut
import doggy.back.quizz.Reponse
import org.springframework.stereotype.Component

@Component
class ProcessResponseUseCase(
    private val partiesRepository: PartiesRepository,
    private val defiRepository: DefiRepository
) {

    fun execute(idPartie: String, reponse: Reponse): Defi {
        val partie = partiesRepository.recupererPartie(idPartie)
        if (partie.statut == PartieStatut.EN_COURS.toString()) {
            return defiRepository.recupererDefi(reponse.idDefi)
        } else {
            throw BrandoneEstUnConException(idPartie)
        }
    }
}

class BrandoneEstUnConException(val id: String) : RuntimeException()
