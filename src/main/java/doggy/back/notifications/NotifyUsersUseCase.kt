package doggy.back.notifications

import org.springframework.stereotype.Component

@Component
class NotifyUsersUseCase(
    private val notificationTokensRepository: NotificationTokensRepository,
    private val notifyUsersRepository: NotifyUsersRepository
) {

    fun execute(sender: String, title: String, message: String) {
        val usersToNotify = notificationTokensRepository.getTokens()
            .filterNot { it.userTrigramme == sender }
        notifyUsersRepository.notifyUsers(usersToNotify, title, message)
    }
}