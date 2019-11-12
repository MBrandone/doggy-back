package doggy.back.doggies

import doggy.back.People
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component

@Component
class DoggiesRepository(private val jdbcTemplate: JdbcTemplate) {

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
