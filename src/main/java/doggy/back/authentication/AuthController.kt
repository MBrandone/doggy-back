package doggy.back.authentication

import doggy.back.ApiError
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@Api(value = "Google Auth", description = "Pour récupérer les infos de connexion de ton compte ", tags = ["Authentification"])
class AuthController {

    @Autowired
    private lateinit var googleAuthRepository: GoogleAuthRepository

    @GetMapping("/auth")
    @ApiOperation(
        value = "Pour récupérer les infos de connexion de ton compte",
        notes = "Si t'es pas dans la doggy, t'aura une erreur"
    )
    fun getDoggies(
        @ApiParam(value = "id_token à obtenir depuis ton front", example = "un très long token", required = true)
        @RequestParam("id") id: String
    ): String {
        return googleAuthRepository.getMail(id)
    }

}

class PasAuthentifieException : RuntimeException()
class TexistePasException(val mail: String) : RuntimeException()

@ControllerAdvice
class PasAuthentifieControllerAdvice {

    @ExceptionHandler
    fun leMecEstPasAuthentifie(exc: PasAuthentifieException): ResponseEntity<ApiError> {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .contentType(MediaType.APPLICATION_JSON)
            .body(ApiError("T'es pas authentifié donc dégage"))
    }

    @ExceptionHandler
    fun leMecEstPasAuthentifie2(exc: TexistePasException): ResponseEntity<ApiError> {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .contentType(MediaType.APPLICATION_JSON)
            .body(ApiError("T'es pas authentifié donc dégage", "si vraiment ${exc.mail} devrait être là, demande à benoit, rémi ou brondon"))
    }
}