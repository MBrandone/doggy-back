package doggy.back.notifications

interface NotifyUsersRepository {

    fun notifyUsers(tokens: List<NotificationToken>, title: String, content: String)
}