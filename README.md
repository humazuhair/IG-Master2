# IG-Master2 - Langages et Environements Évolués

Supports pour le cours des master 2 informatique de l'Insitut Galilée

Lien du TP1: <https://lipn.univ-paris13.fr/~breuvart/LEE/TP1.pdf>

## TP1: Pizzaria sous ~~JEE~~ Spring et API REST

1.  Pour lancer votre base de données (ici ça sera MySQL) :

```sh
docker run -d -p 3306:3306 --name mysql-db -e MYSQL_ROOT_PASSWORD=toor -v $(pwd)/initdb:/docker-entrypoint-initdb.d/ mysql:8
```

2.  Utilisation du backend :
    -   Avec le module servlet sur un tomcat :
        1.  Build du module

```sh
mvn -Pservlet clean package
```

        2.  Lancer le backend :
            -   Avec maven et plugin cargo :

```sh
cd pizzeria-servlet && mvn cargo:run
```

    -   Avec docker:

```sh
docker build -t ig-master/pizzeria:servlet pizzeria-servlet
docker run -ti --rm -p 8080:8080 --link mysql-db:mysql-db -e JAVA_OPTS=-DdataSource.jdbcUrl=mysql://mysql-db:3306/pizzeria ig-master/pizzeria:servlet
```

    -   Avec le module vertx (fat-jar) :
        1.  Build du module

```sh
mvn -Pvertx clean package
```

        2.  Lancer le backend :
            -   Avec java:

```sh
java -jar pizzeria-vertx/target/pizzeria-vertx-jar-with-dependencies.jar 
```

    -   Avec docker:

```sh
docker build -t ig-master/pizzeria:vertx pizzeria-vertx
docker run -ti --rm -p 8080:8080 --link mysql-db:mysql-db -e JAVA_OPTS=-DdataSource.jdbcUrl=mysql://mysql-db:3306/pizzeria ig-master/pizzeria:vertx
```

4.  Build de l'interface utilisateur :

```sh
npm run build # le site se trouvera dans dist
```

5.  Build du docker :

```sh
docker build -t ig-master/pizzeria-ui .
```

6.  Lancer le docker ui :

```sh
docker run -ti --rm -p 80:80 ig-master/pizzeria-ui
```

7.  Pour utiliser l'interface utilisateur, il faut mettre comme query param `http://127.0.0.1/?url=http://127.0.0.1:8080` par exemple.
