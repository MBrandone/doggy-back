package doggy.back.domain.doggy

import java.util.*

interface DoggyPersistance {
    fun recupererToutLesDoggies(): List<Doggy>
    fun recupererDesDoggyAleatoirementSans(nbDoggies: Long, trigramme: String): MutableList<Doggy>
    fun recupererUnDoggy(trigramme: String): Optional<Doggy>
}