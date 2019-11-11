package doggy.back.parties

import doggy.back.quizz.Joueur
import doggy.back.quizz.Partie
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.queryForObject
import org.springframework.stereotype.Component

@Component
class PartiesRepository(private val jdbcTemplate: JdbcTemplate) {

    fun creerPartie(partie: Partie) {
        //language=SQL
        val sql = "INSERT INTO parties VALUES ('${partie.id}','${partie.joueur}',0,'${partie.statut}')"
        jdbcTemplate.update(sql)
    }
}