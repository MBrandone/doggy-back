import { Injectable, OnModuleInit } from '@nestjs/common';
import { ConfigService } from '@nestjs/config';
import * as admin from 'firebase-admin';
import { NotifyUsersRepository } from '../../../domain/notifications/notify-users.repository';
import { NotificationToken } from '../../../domain/notifications/notification-token.entity';

@Injectable()
export class FirebaseService implements OnModuleInit, NotifyUsersRepository {
  constructor(private configService: ConfigService) {}

  onModuleInit() {
    const projectId = this.configService.get<string>('FIREBASE_PROJECT_ID');
    const privateKey = this.configService
      .get<string>('FIREBASE_PRIVATE_KEY')
      ?.replace(/\\n/g, '\n');
    const clientEmail = this.configService.get<string>('FIREBASE_CLIENT_EMAIL');

    if (projectId && privateKey && clientEmail) {
      admin.initializeApp({
        credential: admin.credential.cert({
          projectId,
          privateKey,
          clientEmail,
        }),
      });
    }
  }

  async notifyUsers(
    tokens: NotificationToken[],
    title: string,
    content: string,
  ): Promise<void> {
    const promises = tokens.map((token) =>
      this.notifyUser(token.token, title, content),
    );
    await Promise.all(promises);
  }

  private async notifyUser(
    token: string,
    title: string,
    content: string,
  ): Promise<void> {
    try {
      const message = {
        notification: {
          title,
          body: content,
        },
        token,
      };
      await admin.messaging().send(message);
    } catch (error) {
      console.error('Error sending notification:', error);
    }
  }
}
