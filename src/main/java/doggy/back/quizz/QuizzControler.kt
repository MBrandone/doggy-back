package doggy.back.quizz

import doggy.back.parties.PartiesRepository
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class QuizzControler(private val partiesRepository: PartiesRepository) {

    @PostMapping("/partie")
    fun postPartie(@RequestBody joueur: Joueur): Partie {
        val partie = Partie.init(joueur.joueur)
        partiesRepository.creerPartie(partie)
        return partie
    }
}


data class Joueur(
    val joueur: String
)

data class Partie(
    val id: String,
    val joueur: String,
    val score: Int,
    val statut: String
) {
    companion object {
        fun init(joueur: String): Partie {
            return Partie(
                UUID.randomUUID().toString(),
                joueur,
                0,
                PartieStatut.EN_COURS.toString()
            )
        }
    }
}

enum class PartieStatut {
    EN_COURS,
    FINIE
}