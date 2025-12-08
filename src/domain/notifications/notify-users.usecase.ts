import { Injectable } from '@nestjs/common';
import { NotificationTokensRepository } from './notification-tokens.repository';
import { NotifyUsersRepository } from './notify-users.repository';

@Injectable()
export class NotifyUsersUseCase {
  constructor(
    private readonly notificationTokensRepository: NotificationTokensRepository,
    private readonly notifyUsersRepository: NotifyUsersRepository,
  ) {}

  async execute(sender: string, title: string, message: string): Promise<void> {
    const tokens = await this.notificationTokensRepository.getTokens();
    const usersToNotify = tokens.filter(
      (token) => token.userTrigramme.toLowerCase() !== sender.toLowerCase(),
    );
    await this.notifyUsersRepository.notifyUsers(usersToNotify, title, message);
  }
}
