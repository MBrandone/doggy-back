package doggy.back.rest.quizz

import doggy.back.domain.citation.CitationNonTrouveException
import doggy.back.domain.citation.CorrigerReponse
import doggy.back.domain.citation.PartieTermineeException
import doggy.back.domain.partie.CreerPartie
import doggy.back.infra.parties.PartieNonTrouveeException
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class PartieController(
    private val creerPartie: CreerPartie,
    private val partieApiMapper: PartieApiMapper,
    private val corrigerReponse: CorrigerReponse
) {

    @PostMapping("/parties")
    fun postPartie(@RequestBody joueur: String): PartieApi {
        val partie = creerPartie.execute(joueur)
        return partieApiMapper.toPartieApi(partie)
    }

    @PostMapping("/parties/{idPartie}/reponse")
    fun repondreAuDefi(
        @PathVariable("idPartie") idPartie: String,
        @RequestBody reponse: Reponse
    ): Boolean {
        return corrigerReponse.execute(idPartie, reponse)
    }

    data class Reponse(
        val idCitation: Int,
        val texte: String
    )

    @ControllerAdvice(assignableTypes = [PartieController::class])
    class PartieControllerAdvice {
        @ExceptionHandler(PartieNonTrouveeException::class)
        fun gererPartieNonTrouvee(e: PartieNonTrouveeException): ResponseEntity<String>? {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body("Tema wesh, y a pas de partie avec l'id ${e.id} !")
        }

        @ExceptionHandler(PartieTermineeException::class)
        fun gererPartieTerminee(e: PartieTermineeException): ResponseEntity<String>? {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body("Tema wesh, la partie avec l'id ${e.id}, elle est terminé !")
        }

        @ExceptionHandler(CitationNonTrouveException::class)
        fun gererCitationNonTrouve(e: CitationNonTrouveException): ResponseEntity<String>? {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body("Tema wesh, le défi avec l'id ${e.id}, il existe pas !")
        }
    }
}