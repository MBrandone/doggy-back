package doggy.back.doggies

import doggy.back.People
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component

@Component
class DoggiesRepository(private val jdbcTemplate: JdbcTemplate) {

    private val doggyList = listOf(
        People(
            trigramme = "BRM",
            nom = "Martins",
            prenom = "Brandone",
            surnom = "Brondon Le Portugais",
            photo = "https://firebasestorage.googleapis.com/v0/b/doggy-chat.appspot.com/o/doggy-photos%2Fbrm.jpg?alt=media&token=f25980ed-b9fd-451e-90e3-3eed64989de7",
            tribu = "WEBF",
            signeParticulier = "rit très fort"
        ),
        People(
            trigramme = "RDO",
            nom = "Dormoy",
            prenom = "Remi",
            surnom = "Rémido",
            photo = "https://firebasestorage.googleapis.com/v0/b/doggy-chat.appspot.com/o/2019-09-26T12%3A57%3A47.835Zyolo?alt=media&token=246f6e8f-2fa0-4974-baa3-2c63b5d75e69",
            tribu = "CONEX",
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
            trigramme = "LAAL",
            nom = "Lambert",
            prenom = "Alizée",
            surnom = "Alizouz",
            photo = "https://firebasestorage.googleapis.com/v0/b/doggy-chat.appspot.com/o/doggy-photos%2Falizee.jpg?alt=media&token=8aedd71b-73e7-4bfb-bcf3-af4ef1049155",
            tribu = "UX",
            signeParticulier = "voit le bon côté des choses"
        ),
        People(
            trigramme = "DAS",
            nom = "Sala",
            prenom = "Daniel",
            surnom = "Daniboy",
            photo = "https://firebasestorage.googleapis.com/v0/b/doggy-chat.appspot.com/o/doggy-photos%2Fdanyboy.jpg?alt=media&token=02490ee8-94a8-4385-8a1c-8d56a7aa187a",
            tribu = "SCALE",
            signeParticulier = "a le plus beau boule du monde"
        ),
        People(
            trigramme = "MBI",
            nom = "Biardeau",
            prenom = "Marie",
            surnom = "Marizouz",
            photo = "https://firebasestorage.googleapis.com/v0/b/doggy-chat.appspot.com/o/doggy-photos%2Fmarie.jpg?alt=media&token=e89b9025-35f3-40df-b6a0-3073b4372fe0",
            tribu = "OCAC",
            signeParticulier = "boit des shots"
        ),
        People(
            trigramme = "JDO",
            nom = "Dompe",
            prenom = "Juliette",
            surnom = "Juju",
            photo = "https://firebasestorage.googleapis.com/v0/b/doggy-chat.appspot.com/o/doggy-photos%2Fjuliette.jpg?alt=media&token=6abf4e32-73e2-46e2-813b-79f0c454bb07",
            tribu = "ARCHI",
            signeParticulier = "fait des cocktails"
        ),
        People(
            trigramme = "BME",
            nom = "Meriaux",
            prenom = "Benoit",
            surnom = "Renoit",
            photo = "https://firebasestorage.googleapis.com/v0/b/doggy-chat.appspot.com/o/doggy-photos%2Fbme.jpg?alt=media&token=2bd7adbd-a886-42a4-bcea-e41bdf69f49d",
            tribu = "NAD",
            signeParticulier = "court très vite"
        ),
        People(
            trigramme = "FAM",
            nom = "Massin",
            prenom = "Faustine",
            surnom = "Faustino le bateau",
            photo = "https://firebasestorage.googleapis.com/v0/b/doggy-chat.appspot.com/o/doggy-photos%2Ffaustine.jpg?alt=media&token=f4909448-49cb-49cc-8e5f-d273cb927819",
            tribu = "NAD",
            signeParticulier = "parle beaucoup de sexe"
        ),
        People(
            trigramme = "CLI",
            nom = "Liu",
            prenom = "Can",
            surnom = "Can",
            photo = "https://firebasestorage.googleapis.com/v0/b/doggy-chat.appspot.com/o/doggy-photos%2Fcan.jpg?alt=media&token=4d648e28-ba9d-4cf9-a176-f34ce1f83a36",
            tribu = "ARCHI",
            signeParticulier = "sait bien faire vibrer ses fesses"
        ),
        People(
            trigramme = "CEM",
            nom = "Martin",
            prenom = "Cedric",
            surnom = "Cédric",
            photo = "https://firebasestorage.googleapis.com/v0/b/doggy-chat.appspot.com/o/doggy-photos%2Fcem.jpg?alt=media&token=ec8eefc5-8c8e-4ceb-bdff-8c7540084cab",
            tribu = "NAD",
            signeParticulier = "maîtrise l'art de maîtriser"
        ),
        People(
            trigramme = "CYG",
            nom = "Guegan",
            prenom = "Cyril",
            surnom = "Cygounette",
            photo = "https://firebasestorage.googleapis.com/v0/b/doggy-chat.appspot.com/o/doggy-photos%2Fcyril.jpg?alt=media&token=2a26a82e-46af-4c06-8269-aa88285b4481",
            tribu = "ARCHI",
            signeParticulier = "Met des grandes claques dans le dos"
        ),
        People(
            trigramme = "MEG",
            nom = "Menanteau",
            prenom = "Mégane",
            surnom = "Megazouz",
            photo = "https://firebasestorage.googleapis.com/v0/b/doggy-chat.appspot.com/o/doggy-photos%2Fmegane.jpg?alt=media&token=d473bbb5-b521-41ff-8e0a-a454865a547d",
            tribu = "WEBF",
            signeParticulier = "C'est une chaudière"
        ),
        People(
            trigramme = "JORO",
            nom = "Robert",
            prenom = "Joseph",
            surnom = "JOROOOOO",
            photo = "https://firebasestorage.googleapis.com/v0/b/doggy-chat.appspot.com/o/doggy-photos%2Fjoseph.jpg?alt=media&token=82961ccf-3774-49c3-9476-cc769db5a723",
            tribu = "ARCHI",
            signeParticulier = "Court très vite"
        ),
        People(
            trigramme = "LJA",
            nom = "Jacquemin",
            prenom = "Leo",
            surnom = "Léo",
            photo = "https://firebasestorage.googleapis.com/v0/b/doggy-chat.appspot.com/o/doggy-photos%2Fleo.jpg?alt=media&token=b6b16194-c0a5-4d7e-9bc5-feccaba555fc",
            tribu = "WEBF",
            signeParticulier = "fait des dry january indéfiniment"
        ),
        People(
            trigramme = "MID",
            nom = "Durdevic",
            prenom = "Mila",
            surnom = "Mila",
            photo = "https://firebasestorage.googleapis.com/v0/b/doggy-chat.appspot.com/o/doggy-photos%2Fmila.jpg?alt=media&token=25b68c44-a34e-4536-a554-7f6c284e6324",
            tribu = "CRAFT",
            signeParticulier = "Prend une grande caisse par an"
        ),
        People(
            trigramme = "SLU",
            nom = "Lundy",
            prenom = "Stéphane",
            surnom = "Le Mondai",
            photo = "https://firebasestorage.googleapis.com/v0/b/doggy-chat.appspot.com/o/doggy-photos%2Fslu.jpg?alt=media&token=527e368e-75ec-413c-bdb5-1d8fc8482e9a",
            tribu = "Autres",
            signeParticulier = "Maîtrise le feu"
        ),
        People(
            trigramme = "RBO",
            nom = "Botter",
            prenom = "Rafaëlle",
            surnom = "Le fantôme",
            photo = "https://firebasestorage.googleapis.com/v0/b/doggy-chat.appspot.com/o/doggy-photos%2Frafaelle.jpg?alt=media&token=d361b266-d31d-49d2-b682-52e9c2ba091f",
            tribu = "Autres",
            signeParticulier = ""
        )
    )

    fun getDoggies(): List<People> {
        //language=SQL
        val sql = "SELECT * FROM doggies"
        return jdbcTemplate.queryForList(sql).map {
            People(
                trigramme = it["trigramme"] as String,
                nom = it["nom"] as String,
                prenom = it["prenom"] as String,
                surnom = it["surnom"] as String,
                photo = it["photo"] as String,
                tribu = it["tribu"] as String,
                signeParticulier = it["signe_particulier"] as String
            )
        }
    }

}
