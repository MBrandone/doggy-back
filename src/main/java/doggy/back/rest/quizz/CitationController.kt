package doggy.back.rest.quizz

import doggy.back.domain.citation.CitationNonTrouveException
import doggy.back.domain.citation.recupererNouvelleCitation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class CitationController(
    private val recupererNouvelleCitation: recupererNouvelleCitation,
    private val citationApiMapper: CitationApiMapper
) {

    @CrossOrigin
    @GetMapping("/citation")
    fun recupererUneCitation(): CitationApi {
        return citationApiMapper.toCitationApi(recupererNouvelleCitation.execute())
    }
}


@ControllerAdvice(assignableTypes = [CitationController::class])
class QuizzControllerAdvice {
    @ExceptionHandler(CitationNonTrouveException::class)
    fun gererCitationNonTrouve(e: CitationNonTrouveException): ResponseEntity<String>? {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body("Tema wesh, le défi avec l'id ${e.id}, il existe pas !")
    }
}
