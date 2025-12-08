# Back de la doggy :) - NestJS Version

Application quiz sur les citations d'équipe, migrée de Kotlin/Spring Boot vers Node.js/NestJS.

## Architecture

Le projet suit une architecture DDD (Domain-Driven Design) avec séparation claire des responsabilités :

```
src/
├── domain/              # Logique métier pure
│   ├── doggy/          # Entités et use cases Doggy
│   ├── partie/         # Entités et use cases Partie
│   ├── citation/       # Entités et use cases Citation
│   └── notifications/  # Entités et use cases Notifications
├── infrastructure/     # Implémentations techniques
│   ├── database/       # TypeORM entities & repositories
│   └── external/       # Services externes (Firebase, Google Auth)
├── presentation/       # Couche API
│   ├── controllers/    # Controllers NestJS
│   ├── dto/           # Data Transfer Objects
│   ├── mappers/       # Domain <-> DTO mappers
│   └── guards/        # Guards d'authentification
└── *.module.ts        # Modules NestJS
```

## Prerequisites

- Node.js 18+
- PostgreSQL
- Compte Google OAuth2
- Compte Firebase

## Installation

```bash
npm install
```

## Configuration

Créer un fichier `.env` basé sur `.env.example` :

```env
DATABASE_URL=postgresql://user:password@localhost:5432/doggyback
GOOGLE_CLIENT_ID=your-google-client-id
FIREBASE_PROJECT_ID=your-firebase-project-id
FIREBASE_PRIVATE_KEY=your-firebase-private-key
FIREBASE_CLIENT_EMAIL=your-firebase-client-email
PORT=8080
```

## Base de données

### Migrations

```bash
# Exécuter les migrations
npm run migration:run

# Générer une nouvelle migration
npm run migration:generate -- src/infrastructure/database/migrations/MigrationName

# Annuler la dernière migration
npm run migration:revert
```

## Lancement

### Développement
```bash
npm run start:dev
```

### Production
```bash
npm run build
npm run start:prod
```

### Tests
```bash
# Tests unitaires
npm test

# Tests avec coverage
npm run test:cov

# Tests en mode watch
npm run test:watch
```

## API Documentation

Une fois l'application lancée, la documentation Swagger est disponible sur :
```
http://localhost:8080/api
```

## Endpoints principaux

### Doggies
- `GET /doggies` - Liste tous les doggies
- `GET /doggies/:trigramme` - Récupère un doggy par trigramme

### Parties
- `POST /parties` - Créer une nouvelle partie
- `POST /parties/:idPartie/reponse` - Répondre à un défi

### Citations
- `GET /citations/random` - Récupérer une citation aléatoire

### Notifications
- `POST /notifications/notify` - Notifier les utilisateurs
- `POST /notifications/tokens` - Ajouter un token de notification

## Deployment sur Heroku

### Setup initial
```bash
heroku login
heroku create doggy-back-nestjs
heroku addons:create heroku-postgresql:mini
```

### Configuration
```bash
heroku config:set GOOGLE_CLIENT_ID=your-client-id
heroku config:set FIREBASE_PROJECT_ID=your-project-id
heroku config:set FIREBASE_PRIVATE_KEY="your-private-key"
heroku config:set FIREBASE_CLIENT_EMAIL=your-client-email
heroku config:set NODE_ENV=production
```

### Déploiement
```bash
git push heroku master
```

### Migrations sur Heroku
```bash
heroku run npm run migration:run
```

## Tests

Les tests sont écrits avec Jest et couvrent :
- Use cases (logique métier)
- Controllers (intégration)
- Repositories (accès données)

```bash
npm test
```

## Différences avec la version Kotlin/Spring

### Avantages
- **Performance** : Node.js asynchrone natif
- **Ecosystème** : npm et packages JavaScript/TypeScript riches
- **TypeScript** : Typage statique similaire à Kotlin
- **NestJS** : Architecture similaire à Spring (DI, Modules, Guards)

### Équivalences
- `@Component` (Spring) → `@Injectable()` (NestJS)
- `@RestController` → `@Controller()`
- `@Service` → `@Injectable()`
- `@Autowired` → Constructor injection
- JPA/Hibernate → TypeORM
- Flyway → TypeORM migrations

## Architecture DDD préservée

✅ **Domain Layer** : Entités et use cases purs (aucune dépendance externe)
✅ **Infrastructure Layer** : Implémentations concrètes (TypeORM, Firebase)
✅ **Presentation Layer** : Controllers, DTOs, Guards
✅ **Dependency Inversion** : Domain définit les interfaces, Infrastructure implémente

## Contribuer

1. Fork le projet
2. Créer une branche feature (`git checkout -b feature/AmazingFeature`)
3. Commit les changements (`git commit -m 'Add some AmazingFeature'`)
4. Push vers la branche (`git push origin feature/AmazingFeature`)
5. Ouvrir une Pull Request

## License

Privé - Doggy Team
