import { Injectable } from '@nestjs/common';
import { NotificationTokensRepository } from '../../../domain/notifications/notification-tokens.repository';
import { NotificationToken } from '../../../domain/notifications/notification-token.entity';

@Injectable()
export class NotificationTokensRepositoryImpl
  implements NotificationTokensRepository
{
  private tokens: Map<string, NotificationToken> = new Map();

  async getTokens(): Promise<NotificationToken[]> {
    return Array.from(this.tokens.values());
  }

  async addToken(token: NotificationToken): Promise<void> {
    this.tokens.set(token.userTrigramme, token);
  }
}
