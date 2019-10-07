package doggy.back.doggies

import doggy.back.People
import org.springframework.stereotype.Component

@Component
class DoggiesRepository {

    private val doggyList = listOf(
        People(
            trigramme = "BRM",
            nom = "Le Portugais",
            prenom = "Brandone",
            surnom = "Brond",
            photo = "https://firebasestorage.googleapis.com/v0/b/doggy-chat.appspot.com/o/doggy-photos%2Fbrm.jpg?alt=media&token=f25980ed-b9fd-451e-90e3-3eed64989de7",
            tribu = "WEBF",
            signeParticulier = "rit très fort"
        ),
        People(
            trigramme = "RDO",
            nom = "Dormoy",
            prenom = "Remi",
            surnom = "Rémido",
            photo = "url",
            tribu = "MOB",
            signeParticulier = "boit du Ricard"
        ),
        People(
            trigramme = "BEJ",
            nom = "Jarlier",
            prenom = "Benoit",
            surnom = "Bénoit",
            photo = "https://firebasestorage.googleapis.com/v0/b/doggy-chat.appspot.com/o/doggy-photos%2Fbej.jpg?alt=media&token=821ad501-21f1-4250-9480-b32dec5b64a8",
            tribu = "NAD",
            signeParticulier = "boit beaucoup"
        ),
        People(
            trigramme = "MBI",
            nom = "Biardeau",
            prenom = "Marie",
            surnom = "Marizouz",
            photo = "https://firebasestorage.googleapis.com/v0/b/doggy-chat.appspot.com/o/doggy-photos%2Fmarie.jpg?alt=media&token=e89b9025-35f3-40df-b6a0-3073b4372fe0",
            tribu = "OCAC",
            signeParticulier = "boit des shots"
        )
    )

    fun getDoggies() = doggyList

}
