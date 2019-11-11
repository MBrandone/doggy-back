package doggy.back.parties

import doggy.back.quizz.Partie
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Component
import java.sql.ResultSet

@Component
class PartiesRepository(
    private val jdbcTemplate: JdbcTemplate,
    private val nameJdbcTemplate: NamedParameterJdbcTemplate
) {

    fun creerPartie(partie: Partie) {
        //language=SQL
        val sql = "INSERT INTO parties VALUES ('${partie.id}','${partie.joueur}',0,'${partie.statut}')"
        jdbcTemplate.update(sql)
    }

    fun recupererPartie(idPartie: String): Partie {
        //language=SQL
        val sql = "SELECT * FROM parties where id = :id"
        return nameJdbcTemplate.query(
            sql,
            MapSqlParameterSource().addValue("id", idPartie),
            PartieMapper()
        ).firstOrNull() ?: throw BenoitEstUnConException(idPartie)
    }
}

class PartieMapper : RowMapper<Partie> {
    override fun mapRow(rs: ResultSet, rowNum: Int): Partie {
        return Partie(
            id = rs.getString("id"),
            score = rs.getInt("score"),
            joueur = rs.getString("joueur"),
            statut = rs.getString("statut")
        )
    }
}

class BenoitEstUnConException(val id: String) : RuntimeException()
