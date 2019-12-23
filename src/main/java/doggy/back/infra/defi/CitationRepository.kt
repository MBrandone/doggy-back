package doggy.back.infra.defi

import doggy.back.domain.defi.CitationNonTrouveException
import doggy.back.domain.defi.PasDeDefiDisponibleException
import doggy.back.domain.defi.SolutionNonTrouveException
import doggy.back.domain.entites.Citation
import doggy.back.domain.entites.PropositionDeReponse
import doggy.back.infra.doggies.DoggyDatabase
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Component
import java.sql.ResultSet

@Component
class CitationRepository(
    private val nameJdbcTemplate: NamedParameterJdbcTemplate
) {

    fun recupererCitation(idCitation: Int): Citation {
        //language=SQL
        val sql = "SELECT * FROM citations where id = :id"
        val citationDatabase = nameJdbcTemplate.query(
            sql,
            MapSqlParameterSource().addValue("id", idCitation),
            CitationMapper2()
        ).firstOrNull() ?: throw CitationNonTrouveException(idCitation)

        //language=SQL
        val sql2 =
            "SELECT * FROM auteurs_citations inner join doggies on doggies.trigramme = auteurs_citations.trigramme where idcitation = :id"
        val auteurs = nameJdbcTemplate.query(
            sql2,
            MapSqlParameterSource().addValue("id", citationDatabase.id),
            AuteurMapper()
        )
        return Citation(
            citationDatabase.id,
            citationDatabase.texte,
            emptyList()
        )
    }

    fun recupererUnDefiAleatoire(): CitationDatabase {
        //language=SQL
        val sqlDefi = "SELECT * FROM defis ORDER BY random() LIMIT 1"
        val defi = nameJdbcTemplate.query(
            sqlDefi,
            CitationMapper2()
        ).firstOrNull() ?: throw PasDeDefiDisponibleException()

        return CitationDatabase().apply {
            id = defi.id
            texte = defi.texte
            auteurs = emptyList()
        }
    }

    fun recupererSolutionDefi(idDefi: Int): PropositionDeReponse {
        //language=SQL
        val sqlSolutionTrigramme = "SELECT trigramme FROM solution_citations where idDefis = :id"
        val solutionTrigramme = nameJdbcTemplate.query(
            sqlSolutionTrigramme,
            MapSqlParameterSource().addValue("id", idDefi),
            AuteurMapper()
        ).firstOrNull() ?: throw SolutionNonTrouveException()

        //language=SQL
        val sqlSolution = "SELECT * FROM doggies where trigramme = :trigramme"
        val solution = nameJdbcTemplate.query(
            sqlSolution,
            MapSqlParameterSource().addValue("trigramme", solutionTrigramme),
            PropositionMapper()
        ).firstOrNull() ?: throw SolutionNonTrouveException()

        return PropositionDeReponse(solutionTrigramme.trigramme, solution.surnom, solution.photo)
    }

    fun recupererPropositionsAutreQueSolution(trigramme: String): MutableList<PropositionDeReponse> {
        //language=SQL
        val sql = "SELECT * FROM doggies where trigramme <> :trigramme ORDER BY random() LIMIT 3"
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

class CitationMapper2 : RowMapper<CitationDatabase> {
    override fun mapRow(rs: ResultSet, rowNum: Int): CitationDatabase {
        return CitationDatabase().apply {
            id = rs.getInt("id")
            texte = rs.getString("texte")
            auteurs = emptyList()
        }
    }
}

class AuteurMapper : RowMapper<DoggyDatabase> {
    override fun mapRow(rs: ResultSet, rowNum: Int): DoggyDatabase {
        return DoggyDatabase().apply {
            trigramme = rs.getString("trigramme")
            surnom = rs.getString("surnom")
            photo = rs.getString("photo")
            email = rs.getString("email")
            nom = rs.getString("nom")
            prenom = rs.getString("prenom")
            signeParticulier = rs.getString("signe_particulier")
            tribu = rs.getString("tribu")
        }
    }
}

class PropositionMapper : RowMapper<DoggyDatabase> {
    override fun mapRow(rs: ResultSet, rowNum: Int): DoggyDatabase {
        return DoggyDatabase().apply {
            trigramme = rs.getString("trigramme")
            surnom = rs.getString("surnom")
            photo = rs.getString("photo")
            email = rs.getString("email")
            nom = rs.getString("nom")
            prenom = rs.getString("prenom")
            signeParticulier = rs.getString("signe_particulier")
            tribu = rs.getString("tribu")
        }
    }
}