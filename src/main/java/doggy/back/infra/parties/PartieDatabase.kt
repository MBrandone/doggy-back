package doggy.back.infra.parties

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "parties")
data class PartieDatabase(
    @Id
    val id: String = "",
    val joueur: String = "",
    val score: Int = 0,
    val statut: String = ""
)