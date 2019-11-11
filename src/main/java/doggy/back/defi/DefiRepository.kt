package doggy.back.defi

import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Component
import java.sql.ResultSet

@Component
class DefiRepository(
    private val nameJdbcTemplate: NamedParameterJdbcTemplate
) {

    fun recupererDefi(idDefi: String): Defi {
        //language=SQL
        val sql = "SELECT * FROM parties where id = :id"
        val defi = nameJdbcTemplate.query(
            sql,
            MapSqlParameterSource().addValue("id", idDefi),
            DefiMapper()
        ).firstOrNull() ?: TODO()
        //language=SQL
        val sql2 = "SELECT * FROM solution_citations where idDefis = :id"
        val solutions =  nameJdbcTemplate.query(
            sql2,
            MapSqlParameterSource().addValue("id", defi.id),
            SolutionMapper()
        )
        return Defi(
            defi.id,
            defi.citation,
            solutions
        )
    }
}

class DefiMapper : RowMapper<DefiDb> {
    override fun mapRow(rs: ResultSet, rowNum: Int): DefiDb {
        return DefiDb(
            id = rs.getString("id"),
            citation = rs.getString("citation")
        )
    }
}

class SolutionMapper : RowMapper<String> {
    override fun mapRow(rs: ResultSet, rowNum: Int): String {
        return rs.getString("trigramme")
    }
}

data class DefiDb(
    val id: String,
    val citation: String
)

data class Defi(
    val id: String,
    val citation: String,
    val solution: List<String>
)
