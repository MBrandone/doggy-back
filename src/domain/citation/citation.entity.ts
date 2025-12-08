import { Doggy } from '../doggy/doggy.entity';

export class Citation {
  constructor(
    public readonly id: number,
    public readonly texte: string,
    public readonly auteurs: Doggy[],
  ) {}
}
