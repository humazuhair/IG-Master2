# IG-Master2 - Langages et Environements Évolués

Supports pour le cours des master 2 informatique de l'Insitut Galilée

Lien du TP1: <https://lipn.univ-paris13.fr/~breuvart/LEE/TP1.pdf>

Prérequis:

-   [java JDK 11](https://openjdk.java.net/) (n'oubliez pas de configurer votre JAVA_HOME)
-   [docker](https://docs.docker.com/install/#supported-platforms)
-   [docker-compose](https://docs.docker.com/compose/install/)
-   [nodejs](https://nodejs.org/en/download/package-manager/)

## Cours 1: Pizzaria sous ~~JEE~~ Spring et API REST

1.  Pour lancer votre base de données (ici ça sera MySQL) :

  `docker-compose up -d mysql-db`

2.  Utilisation du backend :

    1.  Build du projet avec gradle :

      `cd backend && ./gradlew clean build; cd -`

    2.  Lancer le backend :

      `docker-compose up --build servlet vertx`

3.  Build de l'interface utilisateur :

    1.  Build du projet avec npm (le site se trouvera dans dist)

      `npm run build`

    2.  Lancer le frontend :

    `docker-compose up -d --build ui`

4.  Pour utiliser l'interface utilisateur, il faut mettre comme query param `http://127.0.0.1/?url=http://127.0.0.1:8080` par exemple.

## Cours 2: La cartographie avec OpenStreetMap

Le support du cours se trouve [ici](https://joxit.dev/IG-Master2/osm/).
