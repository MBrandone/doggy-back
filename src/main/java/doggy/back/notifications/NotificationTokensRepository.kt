package doggy.back.notifications

interface NotificationTokensRepository {

    fun getTokens(): List<NotificationToken>
    fun addUser(notificationToken: NotificationToken)
}