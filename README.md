#Back de la doggy :)

##Deployment
### Heroku
1. demande à BME de te donner le compte heroku
2. log toi avec la heroku belt `brew install heroku/brew/heroku` puis `heroku login`
3. ajoute la remote heroku avec la commande : `heroku git:remote -a doggy-back`
4. pour deployer la branche master: `git push heroku master`
5. c'est accessible [ici](https://doggy-back.herokuapp.com/)

### Gestion de la configuration:
pour overrider la configuration par défaut défini dans le application.yml, 
il faut déclarer des clés de config dans heroku, qui seront accessibles en variables d'environnement
pour cela on utilise l'heroku belt ou l'[interface web](https://dashboard.heroku.com/apps/doggy-back/settings)
`heroku config:set MA_CLE=1`

pour surcharger le clientId par exemple, cela donne:

`heroku config:set GOOGLE_CLIENTID=1`

ou l'url de la datasource de la db (heroku le fait tout seul avec l'addon postgres):

`heroku config:set SPRING_DATASOURCE_URL=ma_nouvelle_chaine`

## Dev 
### Prerequis:
- Java 1.8

### Lancement des tests
1. `./gradlew clean test`

### Lancement de l'appli en local
1.  `./gradlew clean bootRun`

