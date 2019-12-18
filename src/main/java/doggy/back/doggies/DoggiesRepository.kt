package doggy.back.doggies

import doggy.back.People
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Component
import java.sql.ResultSet

@Component
class DoggiesRepository(
    private val jdbcTemplate: JdbcTemplate,
    private val nameJdbcTemplate: NamedParameterJdbcTemplate
) {

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
                signeParticulier = it["signe_particulier"] as String,
                email = it["email"] as String
            )
        }
    }

    fun getDoggies(trigramme: String): People {
        //language=SQL
        val sql = "SELECT * FROM doggies where trigramme = :trigramme"
        return nameJdbcTemplate.query(
            sql,
            MapSqlParameterSource().addValue("trigramme", trigramme),
            PeopleMapper()
        ).firstOrNull() ?: throw DoggyNotFoundException(trigramme)
    }

    class PeopleMapper : RowMapper<People> {
        override fun mapRow(rs: ResultSet, rowNum: Int): People {
            return People(
                trigramme = rs.getString("trigramme"),
                nom = rs.getString("nom"),
                prenom = rs.getString("prenom"),
                surnom = rs.getString("surnom"),
                photo = rs.getString("photo"),
                tribu = rs.getString("tribu"),
                signeParticulier = rs.getString("signe_particulier"),
                email = rs.getString("email")
            )
        }
    }

    class DoggyNotFoundException(trigramme: String) : Exception("Gros, aucun doggy n'a le trigramme $trigramme")


}
