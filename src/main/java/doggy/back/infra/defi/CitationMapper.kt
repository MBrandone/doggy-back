package doggy.back.infra.defi

import doggy.back.domain.entites.Citation
import doggy.back.infra.doggies.DoggyMapper
import org.springframework.stereotype.Component

@Component
class CitationMapper(
    private var doggyMapper: DoggyMapper
) {
    fun toCitation(citationDatabase: CitationDatabase): Citation =
        Citation(
            citationDatabase.id,
            citationDatabase.texte,
            citationDatabase.auteurs.map { doggyMapper.toDoggy(it) }
        )
}