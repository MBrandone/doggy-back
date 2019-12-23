package doggy.back.infra.defi

import doggy.back.domain.entites.Citation
import doggy.back.domain.entites.CitationPersistance
import org.springframework.stereotype.Component

@Component
class CitationPersistanceSQL(
    private var citationRepository: CitationDataRepository,
    private var citationMapper: CitationMapper
) : CitationPersistance {


    override fun getRandomCitation(): Citation {
        val citationDatabases = citationRepository.findAll()
        citationDatabases.shuffle()
        return citationMapper.toCitation(citationDatabases.get(0))
    }

}