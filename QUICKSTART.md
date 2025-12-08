# 🚀 Quickstart - Doggy Back NestJS

Guide de démarrage rapide pour lancer l'application après la migration.

## ⚡ Démarrage Ultra-Rapide (3 commandes)

```bash
# 1. Installer les dépendances
npm install

# 2. Configurer l'environnement
cp .env.example .env
# Éditer .env avec vos valeurs

# 3. Lancer l'application
npm run start:dev
```

✅ L'application est maintenant accessible sur http://localhost:8080
📚 Documentation Swagger : http://localhost:8080/api

---

## 📋 Configuration Minimale

Créer un fichier `.env` avec :

```env
DATABASE_URL=postgresql://user:password@localhost:5432/doggyback
PORT=8080

# Google Auth (optionnel pour dev local sans auth)
GOOGLE_CLIENT_ID=your-client-id

# Firebase (optionnel pour dev local sans notifications)
FIREBASE_PROJECT_ID=your-project-id
FIREBASE_PRIVATE_KEY=your-private-key
FIREBASE_CLIENT_EMAIL=your-client-email
```

---

## 🗄️ Base de Données

### PostgreSQL Local

```bash
# Créer la base
createdb doggyback

# Exécuter les migrations
npm run migration:run
```

### PostgreSQL Docker

```bash
docker run --name postgres-doggy -e POSTGRES_PASSWORD=password -e POSTGRES_DB=doggyback -p 5432:5432 -d postgres:15

# Puis
npm run migration:run
```

---

## 🧪 Tests

```bash
# Tests unitaires
npm test

# Tests avec coverage
npm run test:cov

# Tests en mode watch (développement)
npm run test:watch
```

---

## 📦 Build & Production

```bash
# Build
npm run build

# Lancer en production
npm run start:prod
```

---

## 🐳 Docker

```bash
# Build l'image
docker build -f Dockerfile.nestjs -t doggy-back-nestjs .

# Run le container
docker run -p 8080:8080 --env-file .env doggy-back-nestjs
```

---

## 🚢 Déploiement Heroku

### Premier déploiement

```bash
# Login Heroku
heroku login

# Créer l'app
heroku create doggy-back-nestjs

# Ajouter PostgreSQL
heroku addons:create heroku-postgresql:mini

# Configurer les variables d'environnement
heroku config:set GOOGLE_CLIENT_ID=your-client-id
heroku config:set FIREBASE_PROJECT_ID=your-project-id
heroku config:set NODE_ENV=production

# Déployer
git push heroku master

# Exécuter les migrations
heroku run npm run migration:run

# Ouvrir l'app
heroku open
```

### Déploiements suivants

```bash
git push heroku master
```

---

## 🛠️ Commandes Utiles

### Développement
```bash
npm run start:dev      # Hot-reload activé
npm run lint           # Vérifier le code
npm run format         # Formatter le code
```

### Base de données
```bash
npm run migration:generate -- src/infrastructure/database/migrations/MigrationName
npm run migration:run
npm run migration:revert
```

### Production
```bash
npm run build
npm run start:prod
```

---

## 📊 Endpoints Principaux

### Doggies
```bash
GET  http://localhost:8080/doggies
GET  http://localhost:8080/doggies/:trigramme
```

### Parties
```bash
POST http://localhost:8080/parties
     Body: "JoueurName"

POST http://localhost:8080/parties/:idPartie/reponse
     Body: { "idCitation": 1, "texte": "ABC" }
```

### Citations
```bash
GET  http://localhost:8080/citations/random
```

### Notifications
```bash
POST http://localhost:8080/notifications/notify
     Body: { "sender": "ABC", "title": "Title", "message": "Message" }

POST http://localhost:8080/notifications/tokens
     Body: { "userTrigramme": "ABC", "token": "firebase-token" }
```

---

## 🔍 Vérification Santé

### Test simple
```bash
curl http://localhost:8080/doggies
```

### Swagger UI
Ouvrir http://localhost:8080/api dans le navigateur

---

## 🐛 Troubleshooting

### Port déjà utilisé
```bash
# Changer le port
export PORT=3000
npm run start:dev
```

### Erreur PostgreSQL
```bash
# Vérifier que PostgreSQL est lancé
psql -U postgres -l

# Vérifier DATABASE_URL dans .env
```

### Erreur TypeScript
```bash
# Nettoyer et rebuild
rm -rf dist node_modules
npm install
npm run build
```

### Tests qui échouent
```bash
# Nettoyer cache Jest
npm test -- --clearCache
npm test
```

---

## 📚 Documentation Complète

- **README-NESTJS.md** : Documentation détaillée
- **MIGRATION-GUIDE.md** : Guide de migration depuis Kotlin/Spring
- **CHANGELOG.md** : Historique des changements
- **/api** : Swagger UI interactif

---

## 💡 Conseils Pro

1. **Développement** : Utiliser `npm run start:dev` pour hot-reload
2. **Debug** : Ajouter `--debug` pour mode debug
3. **Logs** : Les logs apparaissent dans stdout
4. **Swagger** : Toujours consulter /api pour la doc API
5. **Tests** : Lancer `npm test` avant chaque commit

---

## ✅ Checklist Post-Installation

- [ ] `npm install` exécuté
- [ ] `.env` configuré
- [ ] PostgreSQL lancé
- [ ] Migrations exécutées
- [ ] Application démarre sur http://localhost:8080
- [ ] Swagger accessible sur http://localhost:8080/api
- [ ] Tests passent avec `npm test`

---

## 🆘 Support

En cas de problème :
1. Vérifier les logs de l'application
2. Consulter README-NESTJS.md
3. Vérifier MIGRATION-GUIDE.md
4. Contacter l'équipe technique

---

**Bon dev ! 🚀**
