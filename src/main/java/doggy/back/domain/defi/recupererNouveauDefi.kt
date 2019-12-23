package doggy.back.domain.defi

import doggy.back.domain.doggy.DoggyPersistance
import doggy.back.domain.entites.Citation
import doggy.back.domain.entites.CitationPersistance
import org.springframework.stereotype.Component
import kotlin.streams.toList

@Component
class recupererNouveauDefi(
    private val citationPersistance: CitationPersistance,
    private val doggyPersistance: DoggyPersistance
) {

    fun execute(): Citation {
        val citation: Citation = citationPersistance.getRandomCitation()
        val uneReponse = citation.auteurs.shuffled()[0]
        val doggyRandom = doggyPersistance.recupererDesDoggyAleatoirement(3)
        doggyRandom.add(uneReponse)
        val propositions = doggyRandom.stream().limit(3).toList()
        val nouvelleCitation = Citation(citation.id, citation.texte, propositions)
        return nouvelleCitation
    }
}
