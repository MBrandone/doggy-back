package doggy.back.rest.quizz

import doggy.back.domain.defi.CitationNonTrouveException
import doggy.back.domain.defi.PartieTermineeException
import doggy.back.domain.defi.recupererNouveauDefi
import doggy.back.domain.entites.Citation
import doggy.back.infra.parties.PartieNonTrouveeException
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class QuizzController(
    private val recupererNouveauDefi: recupererNouveauDefi
) {

    @CrossOrigin
    @GetMapping("/defi")
    fun recupererUnDefi(): Citation {
        return recupererNouveauDefi.execute()
    }
}

@ControllerAdvice(assignableTypes = [QuizzController::class])
class QuizzControllerAdvice {

    @ExceptionHandler(PartieNonTrouveeException::class)
    fun gererPartieNonTrouvee(e: PartieNonTrouveeException): ResponseEntity<String>? {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .contentType(MediaType.APPLICATION_JSON)
            .body("Tema wesh, y a pas de partie avec l'id ${e.id} !")
    }

    @ExceptionHandler(CitationNonTrouveException::class)
    fun gererDefiNonTrouve(e: CitationNonTrouveException): ResponseEntity<String>? {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .contentType(MediaType.APPLICATION_JSON)
            .body("Tema wesh, le défi avec l'id ${e.id}, il existe pas !")
    }

    @ExceptionHandler(PartieTermineeException::class)
    fun gererPartieTerminee(e: PartieTermineeException): ResponseEntity<String>? {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .contentType(MediaType.APPLICATION_JSON)
            .body("Tema wesh, la partie avec l'id ${e.id}, elle est terminé !")
    }
}


data class Correction(
    val idDefi: Int,
    val estCorrecte: Boolean
)


data class Reponse(
    val idCitation: Int,
    val texte: String
)

data class Joueur(
    val joueur: String
)


enum class PartieStatut {
    EN_COURS,
    FINIE
}
