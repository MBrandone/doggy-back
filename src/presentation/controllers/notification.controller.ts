import { Controller, Post, Body } from '@nestjs/common';
import { ApiTags, ApiOperation } from '@nestjs/swagger';
import { NotifyUsersUseCase } from '../../domain/notifications/notify-users.usecase';
import { AddNotificationTokenUseCase } from '../../domain/notifications/add-notification-token.usecase';

class NotifyDto {
  sender: string;
  title: string;
  message: string;
}

class AddTokenDto {
  userTrigramme: string;
  token: string;
}

@Controller('notifications')
@ApiTags('Notifications')
export class NotificationController {
  constructor(
    private readonly notifyUsersUseCase: NotifyUsersUseCase,
    private readonly addNotificationTokenUseCase: AddNotificationTokenUseCase,
  ) {}

  @Post('notify')
  @ApiOperation({ summary: 'Notifier les utilisateurs' })
  async notifyUsers(@Body() dto: NotifyDto): Promise<void> {
    await this.notifyUsersUseCase.execute(dto.sender, dto.title, dto.message);
  }

  @Post('tokens')
  @ApiOperation({ summary: 'Ajouter un token de notification' })
  async addToken(@Body() dto: AddTokenDto): Promise<void> {
    await this.addNotificationTokenUseCase.execute(
      dto.userTrigramme,
      dto.token,
    );
  }
}
