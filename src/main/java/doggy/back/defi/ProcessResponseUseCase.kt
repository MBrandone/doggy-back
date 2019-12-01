package doggy.back.defi

import doggy.back.parties.PartiesRepository
import doggy.back.quizz.Correction
import doggy.back.quizz.PartieStatut
import doggy.back.quizz.Reponse
import org.springframework.stereotype.Component

@Component
class ProcessResponseUseCase(
    private val partiesRepository: PartiesRepository,
    private val defiRepository: DefiRepository
) {

    fun execute(idPartie: String, reponse: Reponse): Correction {
        val partie = partiesRepository.recupererPartie(idPartie)
        if (PartieStatut.EN_COURS.toString() == partie.statut) {
            return corriger(reponse)
        } else {
            throw DefiNonTrouveException(idPartie)
        }
    }

    private fun corriger(reponse: Reponse): Correction {
        val recupererDefi = defiRepository.recupererDefi(reponse.idDefi)
        if (recupererDefi.citation == reponse.texte) {
            return Correction(reponse.idDefi, true);
        }
        return Correction(reponse.idDefi, false);
    }
}

class DefiNonTrouveException(val id: String) : RuntimeException()
