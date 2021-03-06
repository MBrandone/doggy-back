package doggy.back.domain.doggy

import doggy.back.infra.doggies.DoggyNotFoundException
import org.springframework.stereotype.Component

@Component
class RecupererUnDoggy(
    private var doggyPersistance: DoggyPersistance
) {
    fun execute(trigramme: String): Doggy {
        return doggyPersistance.recupererUnDoggy(trigramme) ?: throw DoggyNotFoundException(trigramme)
    }
}