package doggy.back.infra.defi

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Component

@Component
interface CitationDataRepository : CrudRepository<CitationDatabase, String> {
}