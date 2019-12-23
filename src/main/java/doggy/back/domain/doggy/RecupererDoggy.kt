package doggy.back.domain.doggy

import org.springframework.stereotype.Component

@Component
class RecupererDoggy(
    private var doggyPersistance: DoggyPersistance
) {
    fun execute(): List<Doggy> {
        return doggyPersistance.recupererToutLesDoggies();
    }
}