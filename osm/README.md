# Sujet: La cartographie avec OpenStreetMap

Dans ce projet, nous allons créer et utiliser des services autour de la cartographie digitale. Pour commencer, on va à la base de ces services : la carte.

Comme vous avez pu le voir pendant le cours, il n'est pas simple de gérer la donnée OSM, c'est pour cela qu'on n'ira pas jusqu'a posseder une base OSM. À la place, nous alons utiliser un SVG du monde qui sera tuilé. J'ai déjà créer le service de tuilage, vous n'aurez qu'à appeler cette méthode.

La première partie consiste à créer son serveur de tuiles.

## Le serveur de tuiles

Pour le serveur, nous allons utiliser Spring Boot, la base de code est disponible dans osm-boot. Voici quelques étapes que vous pouvez suivre pour vous aider.

  1. Créez le `RestControlleur` et vos endpoints, typiquement le `/{z}/{x}/{y}.png` vu en cours. Hint: `TileWebService`.
  2. Créez votre `Service` qui va appeler `Svg.getTile(Tile t)`. Hint: `TileService`.
  3. Utilisez votre Service créé juste avant dans votre controlleur.
  4. Faites de la validation sur les tuiles dans votre Service. Hint: `IllegalArgumentException`, nombres négatifs, valeurs de x et y trop grands, z ne doit pas dépasser 24.
  5. Renvoyez des codes d'erreur 400 avec votre validation. Hint: `ControllerAdvice`, `ExceptionHandler`.
  6. Bonus: Utilisez un cache pour ne pas a avoir à générer les tuiles à chaque fois

Maintenant vous avez toutes les fonctionnalités de base d'un serveur de tuiles. Maintenant il faut pouvoir l'afficher, pour cela il faut page web qui pourra afficher votre carte.