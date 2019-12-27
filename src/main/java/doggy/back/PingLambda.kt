package doggy.back

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject

@Configuration
class PingLambda(
    private val restTemplate: RestTemplate
) {

    private val LOGGER: Logger = LoggerFactory.getLogger(PingLambda::class.java)

    @Value("\${ping.doggyFrontHost}")
    lateinit var doggyFrontHost: String

    @Value("\${ping.doggyBackHost}")
    lateinit var doggyBackHost: String

    @Scheduled(fixedDelay = 600000)
    fun ping() {
        kotlin.runCatching {
            restTemplate.getForObject<String>(doggyFrontHost)
        }.onSuccess {
            LOGGER.info("Ping front on $doggyFrontHost")
        }.onFailure { e ->
            LOGGER.error("Fail to ping front on $doggyFrontHost", e)
        }
        kotlin.runCatching {
            restTemplate.getForObject<String>("$doggyBackHost/docs/api-docs.json")
        }.onSuccess {
            LOGGER.info("Ping back on $doggyBackHost")
        }.onFailure { e ->
            LOGGER.error("Fail to ping back on $doggyFrontHost", e)
        }
    }
}