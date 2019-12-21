package doggy.back.defi

import doggy.back.doggies.DoggiesRepository
import doggy.back.domain.entites.DefiAvecPropositionsDeReponses
import org.springframework.stereotype.Component

@Component
class recupererNouveauDefi(
    private val defisRepository: DefisRepository
) {

    fun execute(): DefiAvecPropositionsDeReponses {
        val nouveauDefi = defisRepository.recupererUnDefiAleatoire()
        val solution = defisRepository.recupererSolutionDefi(nouveauDefi.id)
        val propositions = defisRepository.recupererPropositionsAutreQueSolution(solution.trigramme)
        propositions.add(solution)
        propositions.shuffled()
        return DefiAvecPropositionsDeReponses(nouveauDefi.id, nouveauDefi.citation, propositions)
    }

}
