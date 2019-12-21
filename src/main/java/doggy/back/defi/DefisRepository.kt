package doggy.back.defi

import doggy.back.domain.entites.PropositionDeReponse
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Component
import java.sql.ResultSet

@Component
class DefisRepository(
    private val nameJdbcTemplate: NamedParameterJdbcTemplate
) {

    fun recupererDefi(idDefi: Int): Defi {
        //language=SQL
        val sql = "SELECT * FROM defis where id = :id"
        val defi = nameJdbcTemplate.query(
            sql,
            MapSqlParameterSource().addValue("id", idDefi),
            DefiMapper()
        ).firstOrNull() ?: throw DefiNonTrouveException(idDefi)

        //language=SQL
        val sql2 = "SELECT * FROM solution_citations where idDefis = :id"
        val solutions = nameJdbcTemplate.query(
            sql2,
            MapSqlParameterSource().addValue("id", defi.id),
            SolutionMapper()
        )
        return Defi(
            defi.id.toString(),
            defi.citation,
            solutions
        )
    }

    fun recupererUnDefiAleatoire(): DefiSansSolution {
        //language=SQL
        val sqlDefi = "SELECT * FROM defis ORDER BY RAND() LIMIT 1"
        val defi = nameJdbcTemplate.query(
            sqlDefi,
            DefiMapper()
        ).firstOrNull() ?: throw PasDeDefiDisponibleException()

        return DefiSansSolution(
            defi.id.toString(),
            defi.citation
        )
    }

    fun recupererSolutionDefi(idDefi: String): PropositionDeReponse {
        //language=SQL
        val sqlSolutionTrigramme = "SELECT trigramme FROM solution_citations where idDefis = :id"
        val solutionTrigramme = nameJdbcTemplate.query(
            sqlSolutionTrigramme,
            MapSqlParameterSource().addValue("id", idDefi),
            SolutionMapper()
        ).firstOrNull() ?: throw SolutionNonTrouveException()

        //language=SQL
        val sqlSolution = "SELECT * FROM doggies where trigramme = :trigramme"
        val solution = nameJdbcTemplate.query(
            sqlSolution,
            MapSqlParameterSource().addValue("trigramme", solutionTrigramme),
            PropositionMapper()
        ).firstOrNull() ?: throw SolutionNonTrouveException()

        return PropositionDeReponse(solutionTrigramme, solution.surnom, solution.photo)
    }

    fun recupererPropositionsAutreQueSolution(trigramme: String): MutableList<PropositionDeReponse> {
        //language=SQL
        val sql = "SELECT * FROM doggies where trigramme <> :trigramme ORDER BY RAND() LIMIT 3"
        val doggiesProposes = nameJdbcTemplate.query(
            sql,
            MapSqlParameterSource().addValue("trigramme", trigramme),
            PropositionMapper()
        )

        return mutableListOf(
            PropositionDeReponse(doggiesProposes[0].trigramme, doggiesProposes[0].surnom, doggiesProposes[0].photo),
            PropositionDeReponse(doggiesProposes[1].trigramme, doggiesProposes[1].surnom, doggiesProposes[1].photo),
            PropositionDeReponse(doggiesProposes[2].trigramme, doggiesProposes[2].surnom, doggiesProposes[2].photo)
        )
    }
}
data class Defi(
    val id: String,
    val citation: String,
    val solutions: List<String>
)

class DefiMapper : RowMapper<DefiDb> {
    override fun mapRow(rs: ResultSet, rowNum: Int): DefiDb {
        return DefiDb(
            id = rs.getInt("id"),
            citation = rs.getString("citation")
        )
    }
}

data class DefiDb(
    val id: Int,
    val citation: String
)

class SolutionMapper : RowMapper<String> {
    override fun mapRow(rs: ResultSet, rowNum: Int): String {
        return rs.getString("trigramme")
    }
}

data class DefiSansSolution(
    val id: String,
    val citation: String
)

class PropositionMapper : RowMapper<DoggyDb> {
    override fun mapRow(rs: ResultSet, rowNum: Int): DoggyDb {
        return DoggyDb(
            id = rs.getInt("id"),
            trigramme = rs.getString("trigramme"),
            surnom = rs.getString("surnom"),
            photo = rs.getString("photo")
        )
    }
}

data class DoggyDb(
    val id: Int,
    val trigramme: String,
    val surnom: String,
    val photo: String
)