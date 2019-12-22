package doggy.back.domain.defi

import doggy.back.infra.defi.CitationRepository
import doggy.back.rest.quizz.DefiAvecPropositionsDeReponses
import org.springframework.stereotype.Component

@Component
class recupererNouveauDefi(
    private val citationRepository: CitationRepository
) {

    fun execute(): DefiAvecPropositionsDeReponses {
        val nouveauDefi = citationRepository.recupererUnDefiAleatoire()

        val solution = citationRepository.recupererSolutionDefi(nouveauDefi.id)
        val propositions = citationRepository.recupererPropositionsAutreQueSolution(solution.trigramme)
        propositions.add(solution)
        propositions.shuffled()
        return DefiAvecPropositionsDeReponses(
            nouveauDefi.id,
            nouveauDefi.citation,
            propositions
        )
    }

}
