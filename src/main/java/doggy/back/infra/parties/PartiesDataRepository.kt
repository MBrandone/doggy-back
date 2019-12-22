package doggy.back.infra.parties

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Component

@Component
interface PartiesDataRepository : CrudRepository<PartieDatabase, String> {
}
