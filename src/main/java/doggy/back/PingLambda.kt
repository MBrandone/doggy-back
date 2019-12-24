package doggy.back

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject

@Configuration
class PingLambda(
    private val restTemplate: RestTemplate
) {

    private val LOGGER: Logger = LoggerFactory.getLogger(PingLambda::class.java)

    @Scheduled(fixedDelay = 600000)
    fun pingFront() {
        restTemplate.getForObject<String>("https://doggy-skool-app.herokuapp.com")
        restTemplate.getForObject<String>("https://doggy-skool-app.herokuapp.com/doggies")
        LOGGER.info("Ping back & front")
    }
}