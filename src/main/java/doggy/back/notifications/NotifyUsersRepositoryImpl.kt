package doggy.back.notifications

import org.slf4j.LoggerFactory
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class NotifyUsersRepositoryImpl(
    private val restTemplate: RestTemplate
) : NotifyUsersRepository {

    private val logger = LoggerFactory.getLogger(NotifyUsersRepositoryImpl::class.java)

    override fun notifyUsers(tokens: List<NotificationToken>, title: String, content: String) {
        logger.info("on va notifier ${tokens.size} users")
        tokens.forEach {
            notifyUser(it, title, content)
        }
    }

    private fun notifyUser(token: NotificationToken, title: String, content: String) {
        val headers = HttpHeaders().apply {
            set("Content-Type", "application/json")
            set("Authorization", "key=AAAANzUnBTU:APA91bHVvpWqTt0SYwnx7wYwxT5a4jz32-FuR6OSx4UwhoaOQqROKC1qe1C8nGzNK7lK6Z3XygcaqizkhIh2UgHRggmoLL_6OCDc43nfmleQcgFL6Pp3WUJNvEcXRRb2eqHv1-T8P8mY")
        }
        val reponse = restTemplate.exchange(
            "https://fcm.googleapis.com/fcm/send",
            HttpMethod.POST,
            HttpEntity<Any>(RequestBody(
                RequestNotification(title, content),
                token.token
            ), headers),
            String::class.java
        )
        logger.info("le body c'est : ${reponse.body}")
    }

    private data class RequestBody(
        val notification: RequestNotification,
        val to: String
    )

    private data class RequestNotification(
        val title: String,
        val body: String,
        val click_action: String = "https://doggy-chat.firebaseapp.com/overview"
    )
}