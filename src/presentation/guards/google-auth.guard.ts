import {
  Injectable,
  CanActivate,
  ExecutionContext,
  UnauthorizedException,
} from '@nestjs/common';
import { GoogleAuthService } from '../../infrastructure/external/google-auth/google-auth.service';
import { DoggyRepository } from '../../domain/doggy/doggy.repository';

@Injectable()
export class GoogleAuthGuard implements CanActivate {
  constructor(
    private readonly googleAuthService: GoogleAuthService,
    private readonly doggyRepository: DoggyRepository,
  ) {}

  async canActivate(context: ExecutionContext): Promise<boolean> {
    const request = context.switchToHttp().getRequest();
    const token = request.headers['id_token'];

    if (!token) {
      throw new UnauthorizedException("T'es pas authentifié donc dégage");
    }

    try {
      const email = await this.googleAuthService.validateToken(token);
      const doggy = await this.doggyRepository.findByEmail(email);

      if (!doggy) {
        throw new UnauthorizedException(
          `T'es pas authentifié donc dégage, si vraiment ${email} devrait être là, demande à benoit, rémi ou brondon`,
        );
      }

      request.user = doggy;
      return true;
    } catch (error) {
      throw new UnauthorizedException("T'es pas authentifié donc dégage");
    }
  }
}
