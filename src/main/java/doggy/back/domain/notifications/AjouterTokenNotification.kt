package doggy.back.domain.notifications

import org.springframework.stereotype.Component

@Component
class AjouterTokenNotification(private val repository: NotificationTokensRepository) {

    fun execute(token: String, sender: String) {
        repository.addUser(NotificationToken(token, sender))
    }
}
