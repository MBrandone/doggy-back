package doggy.back.rest.doggy

import doggy.back.infra.doggies.DoggyNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice(assignableTypes = [DoggyController::class])
class DoggyControllerAdvice {
    @ExceptionHandler(DoggyNotFoundException::class)
    fun trigrammeInexistant(e: DoggyNotFoundException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .contentType(MediaType.APPLICATION_JSON)
            .body(e.message)
    }
}
