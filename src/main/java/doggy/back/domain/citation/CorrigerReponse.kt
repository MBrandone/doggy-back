package doggy.back.domain.citation

import doggy.back.infra.defi.CitationDataRepository
import doggy.back.infra.parties.PartieNonTrouveeException
import doggy.back.infra.parties.PartiesDataRepository
import doggy.back.rest.quizz.PartieController
import doggy.back.rest.quizz.PartieStatut
import org.springframework.stereotype.Component

@Component
class CorrigerReponse(
    private val partiesRepository: PartiesDataRepository,
    private val citationRepository: CitationDataRepository
) {
    fun execute(idPartie: String, reponse: PartieController.Reponse): PartieController.Correction {
        val partie = partiesRepository.findById(idPartie).orElseThrow { PartieNonTrouveeException(idPartie) }
        if (PartieStatut.EN_COURS.toString() == partie.statut) {
            return corriger(reponse)
        } else {
            throw PartieTermineeException(idPartie)
        }
    }

    private fun corriger(reponse: PartieController.Reponse): PartieController.Correction {
        val citation = citationRepository.findById(reponse.idCitation)
            .orElseThrow { CitationNonTrouveException(reponse.idCitation) }
        if (citation.auteurs.any { it.trigramme == reponse.texte }) {
            return PartieController.Correction(reponse.idCitation, true)
        }
        return PartieController.Correction(reponse.idCitation, false)
    }
}

class CitationNonTrouveException(val id: Int) : RuntimeException()
class PartieTermineeException(val id: String) : RuntimeException()
