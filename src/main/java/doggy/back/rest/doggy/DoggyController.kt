package doggy.back.rest.doggy

import doggy.back.domain.doggy.RecupererDoggy
import doggy.back.domain.doggy.RecupererUnDoggy
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
@Api(value = "Liste des Doggy", description = "Pour avoir les informations des doggy", tags = ["Doggy"])
class DoggyController(
    private var recupererDoggy: RecupererDoggy,
    private var recupererUnDoggy: RecupererUnDoggy,
    private var doggyApiMapper: DoggyApiMapper
) {

    @CrossOrigin
    @GetMapping("/doggies")
    @ApiOperation(value = "Pour récupérer la liste de tous les doggy", notes = "Si t'es pas dedans ben t'es un looseur")
    fun getDoggies(): List<DoggyApi> {
        return recupererDoggy.execute().map { doggyApiMapper.toDoggyApi(it) }
    }

    @GetMapping("/doggies/{trigramme}")
    @ApiOperation(value = "Pour récupérer un seul doggy", notes = "Essaye pas JOL, il est parti")
    fun getDoggy(
        @PathVariable("trigramme") trigramme: String
    ): DoggyApi {
        val doggy = recupererUnDoggy.execute(trigramme)
        return doggyApiMapper.toDoggyApi(doggy)
    }
}