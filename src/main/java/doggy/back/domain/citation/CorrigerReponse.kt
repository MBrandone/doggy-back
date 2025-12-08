package doggy.back.domain.citation

import doggy.back.domain.partie.CalculateurScore
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
    fun execute(idPartie: String, reponse: PartieController.Reponse): ReponseResultat {
        val partie = partiePersistance.recupererPartie(idPartie) ?: throw PartieNonTrouveeException(idPartie)
        if (EN_COURS == partie.statut) {
            val reponseCorrecte = corriger(reponse)
            val tempsReponse = reponse.tempsReponse ?: 0L
            val pointsGagnes = calculateurScore.calculerPoints(reponseCorrecte, tempsReponse)

            val partieModifiee = partie.ajouterPoints(pointsGagnes)
            partiePersistance.sauvegarder(partieModifiee)

            return ReponseResultat(reponseCorrecte, pointsGagnes)
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
