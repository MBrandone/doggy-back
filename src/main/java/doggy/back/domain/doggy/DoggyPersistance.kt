package doggy.back.domain.doggy

import doggy.back.domain.entites.Doggy
import java.util.*

interface DoggyPersistance {
    fun recupererToutLesDoggies(): List<Doggy>
    fun recupererDesDoggyAleatoirement(nbDoggies: Int): MutableList<Doggy>
    fun recupererUnDoggy(trigramme: String): Optional<Doggy>
}