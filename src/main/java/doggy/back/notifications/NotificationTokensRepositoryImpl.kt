package doggy.back.notifications

import org.springframework.stereotype.Component

@Component
class NotificationTokensRepositoryImpl : NotificationTokensRepository{

    private val tokens = mutableListOf(
        NotificationToken("d9nRobsxIug:APA91bE3nr8xe6raEC57BXVLK6PswngyE9O4YW7ybXPTWmMaZZ5xY3Zr7jIM2_Ri1tToGq8eM4qVnRlCEHG0xv0-oEryJn-1KQKMinZUaf6rYdeq317Mv-bLJyVhfZ0narVDv8A2X0ki", "rdo"),
        NotificationToken("eW54d9L0ivg:APA91bFdcCVDCxFjBXAKvA_2rwBX30z9yV3g5yf7nXcmEpxpKgNAHbJfKhhnbPXWkIvfZfohxPPwjWk6PqGr8QIbFnWPwBeO-IlvWwxN5wWsoF44wa0oUPWETHYzTtKw_6RQrA80cTWu", "bej")
    )

    override fun getTokens(): List<NotificationToken> {
        return tokens
    }

    override fun addUser(notificationToken: NotificationToken) {
        val isUserAlreadyPresent = tokens.find { it.token == notificationToken.token} != null
        if (isUserAlreadyPresent.not()) {
            tokens.add(notificationToken)
        }
    }
}