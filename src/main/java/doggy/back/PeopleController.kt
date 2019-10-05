package doggy.back

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PeopleController {

    @GetMapping("/people")
    fun getPeople(): List<People> {
        return listOf(
            People("Brondon", "Le Portugais"),
            People("Remi", "Le doggy")
        )
    }
}

data class People (
    val nom: String,
    val prenom: String
)