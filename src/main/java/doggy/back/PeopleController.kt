package doggy.back

import doggy.back.doggies.DoggiesRepository
import io.swagger.annotations.Api
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@Api(value = "Liste des Doggy", description = "Pour avoir les informations des doggy", tags = ["Doggy"])
class PeopleController {

    @Autowired
    private lateinit var repository: DoggiesRepository

    @CrossOrigin
    @GetMapping("/doggies")
    @ApiOperation(value = "Pour récupérer la liste de tous les doggy", notes = "Si t'es pas dedans ben t'es un looseur")
    fun getDoggies(): List<PeopleJson> {
        return repository.getDoggies().map { it.toJson() }
    }

    @GetMapping("/doggies/{trigramme}")
    @ApiOperation(value = "Pour récupérer un seul doggy", notes = "Essaye pas JOL, il est parti")
    fun getDoggy(
        @PathVariable("trigramme") trigramme: String
    ): PeopleJson {
        return repository.getDoggies(trigramme).toJson()
    }

    private fun People.toJson() = PeopleJson(trigramme, nom, prenom, surnom, photo, tribu, signeParticulier)
}

@ApiModel(value = "Doggy")
data class PeopleJson(
    @ApiModelProperty(value = "Le trigramme du doggy", example = "FHI")
    val trigramme: String,
    @ApiModelProperty(value = "Le nom (de famille) du doggy", example = "Zidane")
    val nom: String,
    @ApiModelProperty(value = "Le prénom du doggy", example = "Raymonde")
    val prenom: String,
    @ApiModelProperty(value = "Le surnom du doggy (si il est pas bon, ben c'est la faute de brondon)", example = "La lime")
    val surnom: String,
    @ApiModelProperty(value = "La plus belle photo du doggy", example = "Ceci est une photo")
    val photo: String,
    @ApiModelProperty(value = "La tribu du doggy", example = "CONEX")
    val tribu: String,
    @ApiModelProperty(value = "Comment tu peux reconnaitre le doggy", example = "Le rire infâme de brondon")
    val signeParticulier: String
)


@ControllerAdvice(assignableTypes = [PeopleController::class])
class PeopleControllerAdvice {

    @ExceptionHandler(DoggiesRepository.DoggyNotFoundException::class)
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