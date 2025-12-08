# Guide de Migration Kotlin/Spring → Node.js/NestJS

## Vue d'ensemble

Ce document détaille la migration complète de l'application Doggy Back de Kotlin/Spring Boot vers Node.js/NestJS.

## Structure Avant/Après

### Kotlin/Spring (Avant)
```
src/main/java/doggy/back/
├── domain/
│   ├── doggy/
│   ├── partie/
│   └── citation/
├── infra/
│   ├── doggies/
│   ├── parties/
│   └── defi/
├── rest/
│   ├── doggy/
│   ├── quizz/
│   └── authentication/
└── notifications/
```

### NestJS (Après)
```
src/
├── domain/              # Logique métier identique
│   ├── doggy/
│   ├── partie/
│   ├── citation/
│   └── notifications/
├── infrastructure/      # Implémentations techniques
│   ├── database/
│   └── external/
├── presentation/        # API REST
│   ├── controllers/
│   ├── dto/
│   ├── mappers/
│   └── guards/
└── *.module.ts
```

## Mapping des Concepts

| Kotlin/Spring | NestJS | Notes |
|---------------|---------|-------|
| `@Component` | `@Injectable()` | Même concept de DI |
| `@RestController` | `@Controller()` | Controllers HTTP |
| `@Service` | `@Injectable()` | Services métier |
| `@Autowired` | Constructor injection | Injection implicite |
| `data class` | `class` avec readonly | Immutabilité explicite |
| `interface` | `abstract class` | Pour repositories |
| JPA Entities | TypeORM Entities | ORM similaire |
| Flyway | TypeORM migrations | Migrations DB |
| SpringFox | @nestjs/swagger | Documentation API |

## Migration des Entités

### Doggy
**Avant (Kotlin):**
```kotlin
data class Doggy(
    val trigramme: String,
    val nom: String,
    // ...
)
```

**Après (TypeScript):**
```typescript
export class Doggy {
  constructor(
    public readonly trigramme: string,
    public readonly nom: string,
    // ...
  ) {}
}
```

### Partie
**Avant (Kotlin):**
```kotlin
data class Partie(
    val id: String,
    val joueur: String,
    val score: Int,
    val statut: PartieStatut
) {
    companion object {
        fun init(joueur: String): Partie {
            return Partie(UUID.randomUUID().toString(), joueur, 0, EN_COURS)
        }
    }
}
```

**Après (TypeScript):**
```typescript
export class Partie {
  constructor(
    public readonly id: string,
    public readonly joueur: string,
    public readonly score: number,
    public readonly statut: PartieStatut,
  ) {}

  static init(joueur: string): Partie {
    return new Partie(uuidv4(), joueur, 0, PartieStatut.EN_COURS);
  }
}
```

## Migration des Use Cases

### CreerPartie
**Avant (Kotlin):**
```kotlin
@Component
class CreerPartie(
    private val partiePersistance: PartiePersistance
) {
    fun execute(joueur: String): Partie {
        val partie = Partie.init(joueur)
        partiePersistance.sauverPartie(partie)
        return partie
    }
}
```

**Après (TypeScript):**
```typescript
@Injectable()
export class CreerPartie {
  constructor(private readonly partieRepository: PartieRepository) {}

  async execute(joueur: string): Promise<Partie> {
    const partie = Partie.init(joueur);
    await this.partieRepository.save(partie);
    return partie;
  }
}
```

## Migration des Controllers

### DoggyController
**Avant (Kotlin):**
```kotlin
@RestController
class DoggyController(
    private var recupererDoggy: RecupererDoggy,
    private var doggyApiMapper: DoggyApiMapper
) {
    @GetMapping("/doggies")
    fun getDoggies(): List<DoggyApi> {
        return recupererDoggy.execute().map { doggyApiMapper.toDoggyApi(it) }
    }
}
```

**Après (TypeScript):**
```typescript
@Controller('doggies')
@ApiTags('Doggy')
export class DoggyController {
  constructor(
    private readonly recupererDoggy: RecupererDoggy,
  ) {}

  @Get()
  @ApiOperation({ summary: 'Pour récupérer la liste de tous les doggy' })
  async getDoggies(): Promise<DoggyDto[]> {
    const doggies = await this.recupererDoggy.execute();
    return doggies.map(DoggyMapper.toDto);
  }
}
```

## Migration de l'Authentification

### Google Auth Guard
**Avant (Kotlin):**
```kotlin
@Component
class AuthenticationHandler(
    private val googleAuthRepository: GoogleAuthRepository,
    private val doggyPersistance: DoggyPersistance
) : HandlerInterceptor {
    override fun preHandle(request: HttpServletRequest, ...): Boolean {
        val token = request.getHeader("id_token")
        if (token == null) throw PasAuthentifieException()
        val mail = googleAuthRepository.getMail(token)
        // ...
    }
}
```

**Après (TypeScript):**
```typescript
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

    const email = await this.googleAuthService.validateToken(token);
    const doggy = await this.doggyRepository.findByEmail(email);

    if (!doggy) {
      throw new UnauthorizedException(/* ... */);
    }

    return true;
  }
}
```

## Migration de la Base de Données

### Schema (identique)
La structure de base de données reste **exactement la même** :
- `doggies`
- `parties`
- `citations`
- `auteurs_citations`

### ORM Mapping

**JPA (Avant):**
```kotlin
@Entity
@Table(name = "doggies")
data class DoggyDatabase(
    @Id
    @Column(name = "trigramme")
    val trigramme: String,
    // ...
)
```

**TypeORM (Après):**
```typescript
@Entity('doggies')
export class DoggyEntity {
  @PrimaryColumn({ type: 'varchar', length: 255 })
  trigramme: string;
  // ...
}
```

## Migration des Tests

### JUnit → Jest

**Avant (Kotlin/JUnit):**
```kotlin
@Test
fun `should return all doggies`() {
    val doggies = listOf(doggy1, doggy2)
    whenever(repository.findAll()).thenReturn(doggies)

    val result = useCase.execute()

    assertThat(result).isEqualTo(doggies)
}
```

**Après (TypeScript/Jest):**
```typescript
it('should return all doggies', async () => {
  const doggies = [doggy1, doggy2];
  jest.spyOn(repository, 'findAll').mockResolvedValue(doggies);

  const result = await useCase.execute();

  expect(result).toEqual(doggies);
});
```

## Configuration & Déploiement

### Variables d'environnement

**Spring Boot (application.yml):**
```yaml
spring:
  datasource:
    url: ${DATABASE_URL}
google:
  clientId: ${GOOGLE_CLIENT_ID}
```

**NestJS (.env):**
```env
DATABASE_URL=postgresql://...
GOOGLE_CLIENT_ID=...
```

### Heroku

**Avant (Procfile):**
```
web: java -jar build/libs/doggy-back-1.0-SNAPSHOT.jar
```

**Après (Procfile.nestjs):**
```
web: npm run start:prod
```

## Avantages de la Migration

1. **Performance** : Node.js asynchrone natif vs threads JVM
2. **Écosystème** : npm riche en packages modernes
3. **Uniformité** : TypeScript full-stack possible
4. **Coût** : Moins de mémoire nécessaire
5. **Developer Experience** : Hot-reload rapide, outils modernes

## Compatibilité API

✅ **L'API REST reste 100% compatible** avec l'ancienne version :
- Mêmes endpoints
- Mêmes formats de réponse
- Mêmes codes d'erreur
- Mêmes messages (y compris les "Tema wesh")

## Points d'attention

⚠️ **Asynchrone par défaut** : Toutes les méthodes retournent des Promises
⚠️ **Typage** : TypeScript moins strict que Kotlin (configurable)
⚠️ **Null safety** : Pas de `?` comme en Kotlin, utiliser `| null`
⚠️ **Immutabilité** : `readonly` au lieu de `val`

## Checklist de Migration

- [x] Domain entities migrées
- [x] Use cases migrés
- [x] Repositories migrés
- [x] Controllers migrés
- [x] Authentication migrée
- [x] Notifications migrées
- [x] Tests migrés
- [x] Documentation API (Swagger)
- [x] Migrations DB
- [x] Configuration Heroku
- [x] Dockerfile
- [x] README

## Commandes Utiles

### Développement
```bash
npm run start:dev      # Lance l'app en mode dev
npm test              # Lance les tests
npm run lint          # Vérifie le code
```

### Production
```bash
npm run build         # Compile TypeScript
npm run start:prod    # Lance en prod
npm run migration:run # Exécute les migrations
```

### Heroku
```bash
heroku logs --tail    # Voir les logs
heroku run bash       # Shell sur Heroku
heroku pg:psql        # Accès PostgreSQL
```

## Support

Pour toute question sur la migration, contacter l'équipe technique.
