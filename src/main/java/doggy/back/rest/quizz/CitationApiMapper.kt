package doggy.back.rest.quizz

import doggy.back.domain.citation.Citation
import org.springframework.stereotype.Component

@Component
class CitationApiMapper {
    fun toCitationApi(citation: Citation): CitationApi =
        CitationApi(
            citation.id,
            citation.texte,
            citation.auteurs.map { it.trigramme }
        )
}