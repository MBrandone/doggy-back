import { Module } from '@nestjs/common';
import { NotificationTokensRepository } from './domain/notifications/notification-tokens.repository';
import { NotifyUsersRepository } from './domain/notifications/notify-users.repository';
import { NotificationTokensRepositoryImpl } from './infrastructure/database/repositories/notification-tokens.repository.impl';
import { FirebaseService } from './infrastructure/external/firebase/firebase.service';
import { NotifyUsersUseCase } from './domain/notifications/notify-users.usecase';
import { AddNotificationTokenUseCase } from './domain/notifications/add-notification-token.usecase';
import { NotificationController } from './presentation/controllers/notification.controller';

@Module({
  providers: [
    {
      provide: NotificationTokensRepository,
      useClass: NotificationTokensRepositoryImpl,
    },
    {
      provide: NotifyUsersRepository,
      useClass: FirebaseService,
    },
    FirebaseService,
    NotifyUsersUseCase,
    AddNotificationTokenUseCase,
  ],
  controllers: [NotificationController],
})
export class NotificationModule {}
