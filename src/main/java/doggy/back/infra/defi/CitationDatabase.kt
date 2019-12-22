package doggy.back.infra.defi

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "citations")
class CitationDatabase(
    @Id
    var id: String = "",
    var citation: String = "",
    @OneToMany var auteurs: List<DoggyDatabase> = emptyList()
)
