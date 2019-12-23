package doggy.back.rest.doggy

import doggy.back.domain.doggy.Doggy
import org.springframework.stereotype.Component

@Component
class DoggyApiMapper {
    fun toDoggyApi(doggy: Doggy): DoggyApi =
        DoggyApi(
            doggy.trigramme,
            doggy.nom,
            doggy.prenom,
            doggy.surnom,
            doggy.photo,
            doggy.tribu,
            doggy.signeParticulier,
            doggy.email
        )
}
