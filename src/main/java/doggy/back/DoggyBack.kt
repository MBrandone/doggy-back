package doggy.back

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject


@SpringBootApplication
class DoggyBack {

    private val LOGGER: Logger = LoggerFactory.getLogger(DoggyBack::class.java)

    @Bean
    fun objectMapper() = jacksonObjectMapper()

    @Bean
    fun restTemplate() = RestTemplate()

    @Scheduled(fixedDelay = 600000)
    fun pingFront(restTemplate: RestTemplate) {
        restTemplate.getForObject<String>("https://doggy-skool-app.herokuapp.com/doggies")
        restTemplate.getForObject<String>("https://doggy-skool-app.herokuapp.com")
        LOGGER.info("Ping back & front")
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(DoggyBack::class.java, *args)
}
