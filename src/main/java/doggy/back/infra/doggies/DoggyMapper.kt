package doggy.back.infra.doggies

import doggy.back.domain.entites.Doggy
import doggy.back.infra.defi.DoggyDatabase
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