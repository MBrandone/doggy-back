package doggy.back.domain.citation

import org.springframework.stereotype.Component

@Component
class CalculateurScore {

    companion object {
        private const val POINTS_REPONSE_RAPIDE = 100
        private const val POINTS_REPONSE_MOYENNE = 75
        private const val POINTS_REPONSE_LENTE = 50
        private const val PENALITE_REPONSE_INCORRECTE = -10

        private const val SEUIL_RAPIDE_MS = 5000L
        private const val SEUIL_MOYEN_MS = 10000L
    }

    fun calculerPoints(reponseCorrecte: Boolean, tempsReponseMs: Long): Int {
        if (!reponseCorrecte) {
            return PENALITE_REPONSE_INCORRECTE
        }

        return when {
            tempsReponseMs < SEUIL_RAPIDE_MS -> POINTS_REPONSE_RAPIDE
            tempsReponseMs < SEUIL_MOYEN_MS -> POINTS_REPONSE_MOYENNE
            else -> POINTS_REPONSE_LENTE
        }
    }
}
