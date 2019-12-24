package doggy.back.domain.citation

import doggy.back.domain.partie.PartiePersistance
import doggy.back.domain.partie.PartieStatut.EN_COURS
import doggy.back.infra.parties.PartieNonTrouveeException
import doggy.back.rest.quizz.PartieController
import org.springframework.stereotype.Component

@Component
class CorrigerReponse(
    private val partiePersistance: PartiePersistance,
    private val citationPersistance: CitationPersistance
) {
    fun execute(idPartie: String, reponse: PartieController.Reponse): Boolean {
        val partie = partiePersistance.recupererPartie(idPartie) ?: throw PartieNonTrouveeException(idPartie)
        if (EN_COURS == partie.statut) {
            return corriger(reponse)
        } else {
            throw PartieTermineeException(idPartie)
        }
    }

    private fun corriger(reponse: PartieController.Reponse): Boolean {
        return (citationPersistance.recupererCitation(reponse.idCitation)
            ?: throw  CitationNonTrouveException(reponse.idCitation))
            .auteurs.any { it.trigramme == reponse.texte }
    }
}

class CitationNonTrouveException(val id: Int) : RuntimeException()
class PartieTermineeException(val id: String) : RuntimeException()
