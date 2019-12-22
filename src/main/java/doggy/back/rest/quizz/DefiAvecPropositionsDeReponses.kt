package doggy.back.rest.quizz

import doggy.back.domain.entites.PropositionDeReponse

data class DefiAvecPropositionsDeReponses(
    val id: String,
    val citation: String,
    val propositions: List<PropositionDeReponse>
)