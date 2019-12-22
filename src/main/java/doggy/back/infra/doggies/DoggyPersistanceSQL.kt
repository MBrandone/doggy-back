package doggy.back.infra.doggies

import doggy.back.domain.doggy.DoggyPersistance
import doggy.back.domain.entites.Doggy
import org.springframework.stereotype.Component
import java.util.*

@Component
class DoggyPersistanceSQL(
    private var doggyRepository: DoggyDataRepository,
    private var doggyMapper: DoggyMapper
) : DoggyPersistance {

    override fun recupererToutLesDoggies(): List<Doggy> {
        return doggyRepository.findAll().map { doggyMapper.toDoggy(it) }
    }

    override fun recupererUnDoggy(trigramme: String): Optional<Doggy> {
        return doggyRepository.findById(trigramme).map { doggyMapper.toDoggy(it) }
    }
}