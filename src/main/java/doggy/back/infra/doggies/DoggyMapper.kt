package doggy.back.infra.doggies

import doggy.back.domain.doggy.Doggy
import org.springframework.stereotype.Component

@Component
class DoggyMapper {
    fun toDoggy(doggyDatabase: DoggyDatabase): Doggy =
        Doggy(
            doggyDatabase.trigramme,
            doggyDatabase.nom,
            doggyDatabase.prenom,
            doggyDatabase.surnom,
            doggyDatabase.photo,
            doggyDatabase.tribu,
            doggyDatabase.email,
            doggyDatabase.signeParticulier
        )
}