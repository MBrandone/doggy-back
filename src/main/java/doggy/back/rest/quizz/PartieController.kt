package doggy.back.rest.quizz

import doggy.back.domain.CreerPartie
import doggy.back.domain.defi.CorrigerReponse
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

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
    ): Correction {
        return corrigerReponse.execute(idPartie, reponse)
    }
}