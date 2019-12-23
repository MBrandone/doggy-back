package doggy.back.infra.doggies

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "doggies")
class DoggyDatabase {
    @Id
    lateinit var trigramme: String
    lateinit var nom: String
    lateinit var prenom: String
    lateinit var surnom: String
    lateinit var photo: String
    lateinit var tribu: String
    lateinit var signeParticulier: String
    lateinit var email: String
}