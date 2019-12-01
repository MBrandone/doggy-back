### Deployment

1. demande à BEJ de te donner une AWS ssh key et enregistre la
2. `./gradlew clean build`
3. `scp -i {chemin-vers-ta-clef} build/libs/back-1.0-SNAPSHOT.jar ec2-user@ec2-35-180-100-132.eu-west-3.compute.amazonaws.com:~/doggy-back/build/libs/`
4. si erreur WARNING: UNPROTECTED PRIVATE KEY FILE! :
    * `chmod 600 ta-clef`
    * puis relance
5. execute:
    * `ssh -i {chemin-vers-ta-clé} ec2-user@ec2-35-180-100-132.eu-west-3.compute.amazonaws.com`
6. `cd doggy-back`
7. `docker-compose up -d --build`

