package doggy.back.infra.defi

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Component


@Component
interface CitationDataRepository : JpaRepository<CitationDatabase, Int> {
    @Query("select * from citations order by RANDOM() LIMIT 1", nativeQuery = true)
    fun findRandomCitations(): CitationDatabase

}