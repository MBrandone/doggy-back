package doggy.back.domain.notifications

interface NotificationTokensRepository {

    fun getTokens(): List<NotificationToken>
    fun addUser(notificationToken: NotificationToken)
}