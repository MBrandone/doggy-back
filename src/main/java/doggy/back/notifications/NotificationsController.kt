package doggy.back.notifications

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class NotificationsController(private val useCase: NotifyUsersUseCase) {

    @PostMapping("/notify")
    fun postNotification(@RequestBody body: Body) {
        useCase.execute(body.sender, body.title, body.message)
    }

    data class Body(
        val sender: String,
        val title: String,
        val message: String
    )
}