package doggy.back.infra.doggies

import doggy.back.infra.defi.DoggyDatabase
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Component

@Component
interface DoggyDataRepository : CrudRepository<DoggyDatabase, String> {

}
