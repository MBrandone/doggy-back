package doggy.back.domain.entites

data class DefiAvecPropositionsDeReponses(
    val id: String,
    val citation: String,
    val propositions: List<PropositionDeReponse>
)