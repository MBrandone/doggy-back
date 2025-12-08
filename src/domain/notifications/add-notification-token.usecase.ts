import { Injectable } from '@nestjs/common';
import { NotificationTokensRepository } from './notification-tokens.repository';
import { NotificationToken } from './notification-token.entity';

@Injectable()
export class AddNotificationTokenUseCase {
  constructor(
    private readonly notificationTokensRepository: NotificationTokensRepository,
  ) {}

  async execute(userTrigramme: string, token: string): Promise<void> {
    const notificationToken = new NotificationToken(userTrigramme, token);
    await this.notificationTokensRepository.addToken(notificationToken);
  }
}
