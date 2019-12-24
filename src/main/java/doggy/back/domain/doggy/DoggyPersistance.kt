package doggy.back.domain.doggy

interface DoggyPersistance {
    fun recupererToutLesDoggies(): List<Doggy>
    fun recupererDesDoggyAleatoirementSans(nbDoggies: Long, trigramme: String): MutableList<Doggy>
    fun recupererUnDoggy(trigramme: String): Doggy?
}