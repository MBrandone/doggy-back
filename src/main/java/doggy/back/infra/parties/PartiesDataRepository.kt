package doggy.back.infra.parties

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component

@Component
interface PartiesDataRepository : JpaRepository<PartieDatabase, String> {
}
