package doggy.back.domain.citation

import doggy.back.domain.doggy.DoggyPersistance
import org.springframework.stereotype.Component
import kotlin.streams.toList

@Component
class recupererNouvelleCitation(
    private val citationPersistance: CitationPersistance,
    private val doggyPersistance: DoggyPersistance
) {
    fun execute(): Citation {
        val citation: Citation = citationPersistance.getRandomCitation()
        val uneReponse = citation.auteurs.shuffled()[0]
        val doggyRandom = doggyPersistance.recupererDesDoggyAleatoirementSans(3L, uneReponse.trigramme)
        if (!doggyRandom.contains(uneReponse)) {
            doggyRandom.add(uneReponse)
        }
        val propositions = doggyRandom.stream().limit(3).toList()
        val nouvelleCitation =
            Citation(citation.id, citation.texte, propositions)
        return nouvelleCitation
    }
}
