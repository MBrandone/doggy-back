package doggy.back.notifications

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class NotificationsController(
    private val notifyUsersUseCase: NotifyUsersUseCase,
    private val addNotificationTokenUseCase: AddNotificationTokenUseCase
) {

    @PostMapping("/notify")
    fun postNotification(@RequestBody body: NotificationBody) {
        notifyUsersUseCase.execute(body.sender, body.title, body.message)
    }

    @PostMapping("/notify/tokens")
    fun addToken(@RequestBody body: TokenBody) {
        addNotificationTokenUseCase.execute(body.token, body.sender)
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