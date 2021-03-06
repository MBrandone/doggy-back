package doggy.back.infra.defi

import doggy.back.domain.citation.Citation
import doggy.back.domain.citation.CitationPersistance
import org.springframework.data.repository.findByIdOrNull
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

    override fun recupererCitation(idCitation: Int): Citation? {
        return citationRepository.findByIdOrNull(idCitation)?.let { citationMapper.toCitation(it) }
    }
}