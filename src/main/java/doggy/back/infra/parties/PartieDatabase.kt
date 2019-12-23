package doggy.back.infra.parties

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "parties")
class PartieDatabase {
    @Id
    lateinit var id: String
    lateinit var joueur: String
    var score: Int = 0
    lateinit var statut: String
}