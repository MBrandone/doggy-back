package doggy.back.infra.doggies

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component

@Component
interface DoggyDataRepository : JpaRepository<DoggyDatabase, String>
