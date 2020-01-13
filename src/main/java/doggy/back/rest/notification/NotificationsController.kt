package doggy.back.rest.notification

import doggy.back.domain.notifications.AjouterTokenNotification
import doggy.back.domain.notifications.NotifierUsers
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class NotificationsController(
    private val notifierUsers: NotifierUsers,
    private val ajouterTokenNotification: AjouterTokenNotification
) {

    @PostMapping("/notify")
    fun postNotification(@RequestBody body: NotificationBody) {
        notifierUsers.execute(body.sender, body.title, body.message)
    }

    @PostMapping("/notify/tokens")
    fun addToken(@RequestBody body: TokenBody) {
        ajouterTokenNotification.execute(body.token, body.sender)
    }

    data class NotificationBody(
        val sender: String,
        val title: String,
        val message: String
    )

    data class TokenBody(
        val token: String,
        val sender: String
    )
}