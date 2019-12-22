package doggy.back.infra.defi

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "doggies")
class DoggyDatabase(
    @Id
    var trigramme: String = "",
    var nom: String = "",
    var prenom: String = "",
    var surnom: String = "",
    var photo: String = "",
    var tribu: String = "",
    var signeParticulier: String = "",
    var email: String = ""
)