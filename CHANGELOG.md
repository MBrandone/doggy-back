# Changelog

## [2.0.0] - Migration vers NestJS

### 🚀 Changed - Migration Majeure
- **Migration complète de Kotlin/Spring Boot vers Node.js/NestJS**
  - Réécriture complète en TypeScript
  - Architecture DDD préservée
  - Backward compatible avec l'API REST existante

### ✨ Added
- **NestJS Framework**
  - Modules NestJS pour chaque domaine (Doggy, Partie, Citation, Notification)
  - Dependency Injection native
  - Decorators TypeScript (@Injectable, @Controller, etc.)

- **TypeORM**
  - Remplacement de JPA/Hibernate par TypeORM
  - Migrations TypeORM (équivalent Flyway)
  - Support PostgreSQL maintenu

- **Documentation**
  - Swagger UI intégré (@nestjs/swagger)
  - README-NESTJS.md détaillé
  - MIGRATION-GUIDE.md complet
  - Documentation API interactive

- **Tests**
  - Suite de tests Jest complète
  - Tests unitaires pour use cases
  - Coverage de test maintenu
  - Mocking avec jest.fn()

- **Configuration**
  - Support .env natif (@nestjs/config)
  - Configuration TypeORM centralisée
  - Variables d'environnement Heroku

- **Infrastructure**
  - Dockerfile optimisé pour Node.js
  - Procfile Heroku adapté
  - Support Docker production-ready

### 🔄 Migrated

#### Domain Layer
- ✅ Doggy entity et use cases (RecupererDoggy, RecupererUnDoggy)
- ✅ Partie entity et use cases (CreerPartie)
- ✅ Citation entity et use cases (CorrigerReponse, RecupererNouvelleCitation)
- ✅ Notification entities et use cases (NotifyUsers, AddNotificationToken)

#### Infrastructure Layer
- ✅ DoggyRepository → DoggyRepositoryImpl (TypeORM)
- ✅ PartieRepository → PartieRepositoryImpl (TypeORM)
- ✅ CitationRepository → CitationRepositoryImpl (TypeORM)
- ✅ Firebase integration (notifications push)
- ✅ Google OAuth2 (authentification)

#### Presentation Layer
- ✅ DoggyController avec Swagger docs
- ✅ PartieController avec validation
- ✅ CitationController
- ✅ NotificationController
- ✅ GoogleAuthGuard (équivalent HandlerInterceptor)
- ✅ DTOs et Mappers

### 🗄️ Database
- Schema PostgreSQL maintenu (100% compatible)
- Tables : doggies, parties, citations, auteurs_citations
- Migration initiale TypeORM créée

### 🔒 Security
- Google OAuth2 maintenu
- GoogleAuthGuard avec validation de token
- Mêmes règles d'authentification

### 🔔 Notifications
- Firebase Admin SDK intégré
- Push notifications maintenues
- Architecture repository préservée

### 📦 Dependencies
- @nestjs/core, @nestjs/common (^10.0.0)
- @nestjs/typeorm (^10.0.0)
- @nestjs/swagger (^7.0.0)
- typeorm (^0.3.17)
- pg (^8.11.0)
- uuid (^9.0.0)
- firebase-admin (^11.0.0)
- google-auth-library (^9.0.0)

### 🛠️ Developer Experience
- Hot-reload avec nest start --watch
- TypeScript strict mode
- Prettier et ESLint configurés
- Jest watch mode
- Swagger UI locale

### 📝 API Compatibility
- ✅ GET /doggies
- ✅ GET /doggies/:trigramme
- ✅ POST /parties
- ✅ POST /parties/:idPartie/reponse
- ✅ GET /citations/random
- ✅ POST /notifications/notify
- ✅ POST /notifications/tokens

### 🔄 Breaking Changes
**Aucun** - L'API REST reste 100% rétrocompatible

### 📊 Performance Improvements
- Node.js asynchrone natif (non-blocking I/O)
- Consommation mémoire réduite vs JVM
- Démarrage plus rapide
- Hot-reload instantané en dev

### 🌐 Deployment
- Heroku compatible (Procfile adapté)
- Docker support (Dockerfile.nestjs)
- Variables d'environnement migrées
- Production-ready

### 🎯 Architecture Principles Preserved
- ✅ Domain-Driven Design (DDD)
- ✅ Dependency Inversion
- ✅ Clean Architecture
- ✅ Use Case pattern
- ✅ Repository pattern
- ✅ Separation of concerns

### 📚 Documentation Files
- README-NESTJS.md : Documentation complète NestJS
- MIGRATION-GUIDE.md : Guide de migration détaillé
- CHANGELOG.md : Historique des changements
- .env.example : Template de configuration

---

## [1.0.0] - Version Kotlin/Spring Boot (Baseline)

### Features
- Application quiz sur citations d'équipe
- Gestion des Doggies (membres)
- Système de parties/quiz
- Citations avec auteurs multiples
- Notifications push Firebase
- Authentification Google OAuth2
- Architecture DDD
- API REST
- Base PostgreSQL
- Déploiement Heroku

---

**Migration effectuée par** : Umans Agent
**Date** : 2024
**Effort** : Migration complète full-stack
