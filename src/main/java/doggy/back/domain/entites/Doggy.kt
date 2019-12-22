package doggy.back.domain.entites

data class Doggy(
    val trigramme: String,
    val nom: String,
    val prenom: String,
    val surnom: String,
    val photo: String,
    val tribu: String,
    val signeParticulier: String,
    val email: String
)