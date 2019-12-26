package doggy.back.domain.notifications

data class NotificationToken(
    val token: String,
    val userTrigramme: String
)