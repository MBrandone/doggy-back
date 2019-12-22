package doggy.back.domain.doggy

import doggy.back.domain.entites.Doggy
import doggy.back.infra.doggies.DoggyNotFoundException
import org.springframework.stereotype.Component

@Component
class RecupererUnDoggy(
    private var doggyPersistance: DoggyPersistance
) {
    fun execute(trigramme: String): Doggy {
        val oDoggy = doggyPersistance.recupererUnDoggy(trigramme)
        return oDoggy.orElseThrow { DoggyNotFoundException(trigramme) }
    }
}