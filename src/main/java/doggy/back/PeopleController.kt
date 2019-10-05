package doggy.back

import org.springframework.http.HttpStatus.*
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.lang.Exception

@RestController
class PeopleController {

    val doggyList = listOf(
        People("BRM","Le Portugais", "Brandone", "Brond", "url", "WEBF", "rit très fort"),
        People("RDO", "Dormoy", "Remi", "Rémido", "url", "MOB", "boit du Ricard"),
        People("BEJ", "Jarlier", "Benoit", "Bénoit", "url", "NAD", "boit beaucoup"),
        People("MBI", "Biardeau", "Marie", "Marizouz", "url", "OCAC", "boit des shots")
    )

    @GetMapping("/doggies")
    fun getDoggies(): List<People> {
        return doggyList
    }

    @GetMapping("/doggies/{trigramme}")
    fun getDoggy(
        @PathVariable("trigramme") trigramme: String
    ): People {
        return doggyList.find { doggy ->
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

data class People (
    val trigramme: String,
    val nom: String,
    val prenom: String,
    val surnom: String,
    val photo: String,
    val tribu: String,
    val signeParticulier: String
)