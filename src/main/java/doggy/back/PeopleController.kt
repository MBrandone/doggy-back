package doggy.back

import doggy.back.doggies.DoggiesRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class PeopleController {

    @Autowired
    private lateinit var repository: DoggiesRepository

    @GetMapping("/doggies")
    fun getDoggies(): List<People> {
        return repository.getDoggies()
    }

    @GetMapping("/doggies/{trigramme}")
    fun getDoggy(
        @PathVariable("trigramme") trigramme: String
    ): People {
        return repository.getDoggies().find { doggy ->
            doggy.trigramme == trigramme
        } ?: throw DoggyNotFoundException(trigramme)
    }
}

class DoggyNotFoundException(trigramme: String) : Exception("Gros, aucun doggy n'a le trigramme $trigramme")

@ControllerAdvice(assignableTypes = [PeopleController::class])
class PeopleControllerAdvice {

    @ExceptionHandler(DoggyNotFoundException::class)
    fun trigrammeInexistant(): ResponseEntity<ApiError> {
        return ResponseEntity.status(NOT_FOUND)
            .contentType(MediaType.APPLICATION_JSON)
            .body(ApiError("Vas y juju vas crever putin"))
    }
}

data class ApiError(
    val message: String,
    val insulte: String = "C'est encore juju qui fait nimp c'est ça ?"
)

data class People(
    val trigramme: String,
    val nom: String,
    val prenom: String,
    val surnom: String,
    val photo: String,
    val tribu: String,
    val signeParticulier: String
)