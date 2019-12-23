package doggy.back.infra.doggies

import doggy.back.domain.doggy.Doggy
import doggy.back.domain.doggy.DoggyPersistance
import org.springframework.stereotype.Component
import java.util.*
import kotlin.streams.toList

@Component
class DoggyPersistanceSQL(
    private var doggyRepository: DoggyDataRepository,
    private var doggyMapper: DoggyMapper
) : DoggyPersistance {

    override fun recupererToutLesDoggies(): List<Doggy> {
        return doggyRepository.findAll().map { doggyMapper.toDoggy(it) }
    }

    override fun recupererDesDoggyAleatoirementSans(nbDoggies: Long, trigramme: String): MutableList<Doggy> {
        return recupererToutLesDoggies().shuffled().stream()
            .filter { it.trigramme != trigramme }
            .limit(nbDoggies).toList().toMutableList()
    }

    override fun recupererUnDoggy(trigramme: String): Optional<Doggy> {
        return doggyRepository.findById(trigramme).map { doggyMapper.toDoggy(it) }
    }
}