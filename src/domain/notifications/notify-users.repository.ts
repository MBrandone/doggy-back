import { NotificationToken } from './notification-token.entity';

export abstract class NotifyUsersRepository {
  abstract notifyUsers(
    tokens: NotificationToken[],
    title: string,
    content: string,
  ): Promise<void>;
}
