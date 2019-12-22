package doggy.back.notifications

import org.springframework.stereotype.Component

@Component
class AddNotificationTokenUseCase(private val repository: NotificationTokensRepository) {

    fun execute(token: String, sender: String) {
        repository.addUser(NotificationToken(token, sender))
    }
}
