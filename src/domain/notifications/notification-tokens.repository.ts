import { NotificationToken } from './notification-token.entity';

export abstract class NotificationTokensRepository {
  abstract getTokens(): Promise<NotificationToken[]>;
  abstract addToken(token: NotificationToken): Promise<void>;
}
