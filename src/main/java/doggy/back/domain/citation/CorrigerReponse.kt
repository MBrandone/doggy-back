package doggy.back.domain.citation

import doggy.back.domain.partie.PartiePersistance
import doggy.back.domain.partie.PartieStatut.EN_COURS
import doggy.back.infra.parties.PartieNonTrouveeException
import doggy.back.rest.quizz.PartieController
import org.springframework.stereotype.Component

@Component
class CorrigerReponse(
    private val partiePersistance: PartiePersistance,
    private val citationPersistance: CitationPersistance,
    private val calculateurScore: CalculateurScore
) {
    fun execute(idPartie: String, reponse: PartieController.Reponse): ResultatReponse {
        val partie = partiePersistance.recupererPartie(idPartie) ?: throw PartieNonTrouveeException(idPartie)
        if (EN_COURS == partie.statut) {
            val reponseCorrecte = corriger(reponse)
            val tempsReponseMs = reponse.tempsReponse ?: 0L
            val points = calculateurScore.calculerPoints(reponseCorrecte, tempsReponseMs)
            val partieModifiee = partie.ajouterPoints(points)
            partiePersistance.sauverPartie(partieModifiee)
            return ResultatReponse(reponseCorrecte, points)
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

data class ResultatReponse(
    val correct: Boolean,
    val pointsGagnes: Int
)

class CitationNonTrouveException(val id: Int) : RuntimeException()
class PartieTermineeException(val id: String) : RuntimeException()
