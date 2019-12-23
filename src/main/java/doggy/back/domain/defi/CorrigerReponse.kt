package doggy.back.domain.defi

import doggy.back.infra.defi.CitationDataRepository
import doggy.back.infra.parties.PartieNonTrouveeException
import doggy.back.infra.parties.PartiesDataRepository
import doggy.back.rest.quizz.Correction
import doggy.back.rest.quizz.PartieStatut
import doggy.back.rest.quizz.Reponse
import org.springframework.stereotype.Component

@Component
class CorrigerReponse(
    private val partiesRepository: PartiesDataRepository,
    private val citationRepository: CitationDataRepository
) {
    fun execute(idPartie: String, reponse: Reponse): Correction {
        val partie = partiesRepository.findById(idPartie).orElseThrow { PartieNonTrouveeException(idPartie) }
        if (PartieStatut.EN_COURS.toString() == partie.statut) {
            return corriger(reponse)
        } else {
            throw PartieTermineeException(idPartie)
        }
    }

    private fun corriger(reponse: Reponse): Correction {
        val citation = citationRepository.findById(reponse.idCitation).get()
        if (citation.auteurs.any { it.trigramme == reponse.texte }) {
            return Correction(reponse.idCitation, true)
        }
        return Correction(reponse.idCitation, false)
    }
}

class CitationNonTrouveException(val id: Int) : RuntimeException()
class PasDeDefiDisponibleException() : RuntimeException()
class SolutionNonTrouveException() : RuntimeException()
class PartieTermineeException(val id: String) : RuntimeException()
